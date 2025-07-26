import { navConfigByRole } from "@/components/sidebar-config";
import Dashboard from "../dashboard/page";
import { Outlet } from "react-router-dom";

export default function ManagerDashboard() {
  const user = JSON.parse(localStorage.getItem("user") || "{}");
  const config = navConfigByRole["manager"];
  return (
    <Dashboard
      navMain={config.navMain}
      navDocuments={config.navDocuments}
      navSecondary={config.navSecondary}
      user={{
        name: user.name,
        email: user.email,
        avatar: "/avatars/manager.jpg",
      }}
    >
      <Outlet/>
      </Dashboard>
  );
}
