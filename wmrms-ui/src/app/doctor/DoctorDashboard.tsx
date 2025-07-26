import { navConfigByRole } from "@/components/sidebar-config";
import Dashboard from "../dashboard/page";

export default function DoctorDashboard() {
  const user = JSON.parse(localStorage.getItem("user") || "{}")
  const config = navConfigByRole["doctor"]
  return (
    <Dashboard
    navMain={config.navMain}
    navDocuments={config.navDocuments}
    navSecondary={config.navSecondary}
    user={{
      name: user.name,
      email:user.email,
      avatar: "/avatars/doctor.jpg",
    }}
    />
  )
}