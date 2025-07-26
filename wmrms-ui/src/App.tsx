import "./App.css";
import AdminDashboard from "./app/admin/AdminDashboard";
import DoctorDashboard from "./app/doctor/DoctorDashboard";
import LoginPage from "./app/login/page";
import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import ManagerDashboard from "./app/manager/ManagerDashboard";
import CreateWorkerForm from "./app/worker/CreateWorkerForm";
import ManagerHome from "./app/manager/ManagerHome";
import WorkerList from "./app/worker/WorkerList";
import UploadWorkersPage from "./app/worker/UploadWorkers";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/admin" element={<AdminDashboard />} />
        <Route path="/doctor" element={<DoctorDashboard />} />
        {/* <Route path="/manager" element={<ManagerDashboard />} /> */}
        {/* <Route path="/manager/workers/new" element={<CreateWorkerForm />} /> */}
        <Route path="/manager" element={<ManagerDashboard />}>
          <Route index element={<ManagerHome />} />
          <Route path="workers" element={<WorkerList />} />
          <Route path="workers/new" element={<CreateWorkerForm />} />
          <Route path="workers/upload" element={<UploadWorkersPage />} />
          {/* <Route path="reports" element={<ReportsPage />} /> */}
        </Route>
      </Routes>
    </Router>
    // <Page/>
  );
}

export default App;
