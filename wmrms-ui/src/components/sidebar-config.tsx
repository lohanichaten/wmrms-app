// src/constants/nav-config.ts
import {
  IconDashboard,
  IconUsers,
  IconReport,
  IconSettings,
  IconListDetails,
  IconChartBar,
  IconFolder,
  IconDatabase,
  IconPlus,
  IconUpload,
  IconEye,
} from "@tabler/icons-react"

export const navConfigByRole = {
  admin: {
    navMain: [
      { title: "Dashboard", url: "/admin", icon: IconDashboard },
      { title: "User Management", url: "/admin/users", icon: IconUsers },
      { title: "Reports", url: "/admin/reports", icon: IconReport },
    ],
    navDocuments: [
      { name: "User Logs", url: "/admin/logs", icon: IconDatabase },
    ],
    navSecondary: [
      { title: "Settings", url: "#", icon: IconSettings },
    ],
  },
  doctor: {
    navMain: [
      { title: "Dashboard", url: "/doctor", icon: IconDashboard },
      { title: "Patients", url: "/doctor/patients", icon: IconListDetails },
      { title: "Reports", url: "/doctor/reports", icon: IconChartBar },
    ],
    navDocuments: [],
    navSecondary: [
      { title: "Settings", url: "#", icon: IconSettings },
    ],
  },
  manager: {
  navMain: [
    { title: "Dashboard", url: "/manager", icon: IconDashboard },
    {
      title: "Workers",
      icon: IconFolder,
      url: "/manager/workers",
      items: [
        { title: "View Workers", url: "/manager/workers", icon: IconEye },
        { title: "Add New Worker", url: "/manager/workers/new", icon: IconPlus },
        { title: "Upload Workers", url: "/manager/workers/upload", icon: IconUpload },
      ],
    },
    { title: "Reports", url: "/manager/reports", icon: IconReport },
  ],
  navDocuments: [],
  navSecondary: [
    { title: "Settings", url: "#", icon: IconSettings },
  ],
}
}
