import { navConfigByRole } from "@/components/sidebar-config";
import Dashboard from "../dashboard/page";

export default function AdminDashboard() {
  const user = JSON.parse(localStorage.getItem("user") || "{}")
  const config = navConfigByRole["admin"]
  return (
    <Dashboard
    navMain={config.navMain}
    navDocuments={config.navDocuments}
    navSecondary={config.navSecondary}
    user={{
      name: user.name,
      email:user.email,
      avatar: "/avatars/admin.jpg",
    }}
    />
  )
}