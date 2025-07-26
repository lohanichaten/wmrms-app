
import { columns, type Worker } from "../worker/worker-table-columns"
import { DataTable } from "@/components/ui/data-table" 
import { Input } from "@/components/ui/input"
import { useEffect, useState } from "react"

export default function WorkerListPage() {
  const [workers, setWorkers] = useState<Worker[]>([])
  const [loading, setLoading] = useState(true)
  const [filteredWorkers, setFilteredWorkers] = useState<Worker[]>([])
  const [search, setSearch] = useState("")

  useEffect(() => {
    fetch("http://localhost:3001/workers")
      .then((res) => res.json())
      .then((data) => {
        setWorkers(data)
        setFilteredWorkers(data)
        setLoading(false)
      })
      .catch((err) => {
        console.error("Error fetching workers", err)
        setLoading(false)
      })
  }, [])

  useEffect(() => {
    const lowerSearch = search.toLowerCase()

    const filtered = workers.filter((worker) =>
      worker.fullName.toLowerCase().includes(lowerSearch) ||
      worker.id.toString().includes(lowerSearch)
    )

    setFilteredWorkers(filtered)
  }, [search, workers])

  if (loading) return <p className="p-4">Loading workers...</p>

  return (
    <div className="p-4 space-y-4">
      <div className="flex items-center gap-5">
        <h2 className="text-2xl font-bold mb-4">Workers</h2>
        <Input
          placeholder="Search by ID or Name..."
          value={search}
          onChange={(e) => setSearch(e.target.value)}
          className="max-w-sm"
        />
      </div>
      <DataTable columns={columns} data={filteredWorkers} />
    </div>
  )
}
