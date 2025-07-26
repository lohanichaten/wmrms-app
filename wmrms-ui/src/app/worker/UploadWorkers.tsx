"use client"

import React, { useState } from "react"
import * as XLSX from "xlsx"
import Papa from "papaparse"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { DataTable } from "@/components/ui/data-table"
import { columns, type Worker } from "../worker/worker-table-columns"

export default function UploadWorkersPage() {
  const [parsedData, setParsedData] = useState<Worker[]>([])
  const [error, setError] = useState("")

  const handleFileUpload = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0]
    if (!file) return

    const fileExt = file.name.split(".").pop()

    const reader = new FileReader()
    reader.onload = (event) => {
      const data = event.target?.result
      if (!data) return

      try {
        if (fileExt === "csv") {
          Papa.parse(data as string, {
            header: true,
            complete: (results: { data: Worker[] }) => {
              const cleaned = results.data.map((worker)=>({
                ...worker,
                id: String(worker.id ?? ""),
                phone: String(worker.phone ?? ""),
              }))
              setParsedData(cleaned)
            },
            error: (err: { message: React.SetStateAction<string> }) => setError(err.message),
          })
        } else if (["xls", "xlsx"].includes(fileExt || "")) {
          const workbook = XLSX.read(data, { type: "binary" })
          const sheetName = workbook.SheetNames[0]
          const sheet = workbook.Sheets[sheetName]
          const json = XLSX.utils.sheet_to_json<Worker>(sheet)
          const cleaned = json.map((worker)=>({
                ...worker,
                id: String(worker.id ?? ""),
                phone: String(worker.phone ?? ""),
              }))
          setParsedData(cleaned)
        } else {
          setError("Unsupported file type.")
        }
      } catch (err) {
        setError("Parsing failed.")
      }
    }

    if (fileExt === "csv") {
      reader.readAsText(file)
    } else {
      reader.readAsBinaryString(file)
    }
  }

  const handleSubmit = async () => {
    try {
      const responses = await Promise.all(
        parsedData.map((worker) =>
          fetch("http://localhost:3001/workers", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(worker),
          })
        )
      )
      alert("Upload successful!")
      setParsedData([])
    } catch (err) {
      console.error(err)
      setError("Upload failed.")
    }
  }

  return (
    <div className="p-6 space-y-4">
      <h2 className="text-2xl font-semibold">Upload Workers (CSV / Excel)</h2>

      <Input type="file" accept=".csv,.xlsx,.xls" onChange={handleFileUpload} />
      {error && <p className="text-sm text-red-500">{error}</p>}

      {parsedData.length > 0 && (
        <>
          <h3 className="text-lg font-medium">Preview</h3>
          <DataTable columns={columns} data={parsedData} />
          <Button onClick={handleSubmit} className="mt-4">
            Submit All
          </Button>
        </>
      )}
    </div>
  )
}
