import type { ColumnDef } from "@tanstack/react-table"

export type Worker = {
  id: string
  fullName: string
  email: string
  phone: string
  department: string
}

export const columns: ColumnDef<Worker>[] = [
  {
    accessorKey: "id",
    header: "ID",
  },
  {
    accessorKey: "fullName",
    header: "Name",
  },
  {
    accessorKey: "email",
    header: "Email",
  },
  {
    accessorKey: "phone",
    header: "Phone",
  },
  {
    accessorKey: "department",
    header: "Department",
  },
]
