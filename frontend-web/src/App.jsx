import { AuthProvider } from "./context/AuthContext";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import { useAuth } from "./context/AuthContext";

// Public Pages
import LazyLanding from "./staff-pages/LandingPage";
import RegisterPage from "./staff-pages/RegisterPage";
import LoginPage from "./staff-pages/LoginPage";

// Staff/Admin Pages
import StaffDashboard from "./staff-pages/StaffDashboard";
import StaffAppointments from "./staff-pages/StaffAdmission";
import StaffPatientsRecords from "./staff-pages/StaffPatientsRecords";
import AddNewPatientsRecords from "./staff-pages/AddNewPatientsRecords";
import StaffBilling from "./staff-pages/StaffBilling";
import StaffInventory from "./staff-pages/StaffInventory";
import AddNewItem from "./staff-pages/AddNewItem_StaffInventory";
import StaffRooms from "./staff-pages/StaffRooms";
import AddNewRoom from "./staff-pages/AddNewRoom_StaffRooms";

// Doctor Pages
import DoctorDashboard from "./doctor-pages/DoctorDashboard";
import DoctorAdmission from "./doctor-pages/DoctorAdmission";
import DoctorPatientsRecords from "./doctor-pages/DoctorPatientsRecords";
import DoctorTelemedicine from "./doctor-pages/DoctorTelemedicine";
import AddNewRequestTelemedicine from "./doctor-pages/AddNewRequestTelemedicine";
import StartConsultationTelemedicine from "./doctor-pages/StartConsultationTelemedicine"; // Import the StartConsultation page
import DoctorPrescriptions from "./doctor-pages/DoctorPrescriptions";
import AddNewPatientPrescription from "./doctor-pages/AddNewPatientPrescription";

// 🔐 Staff/Admin Route Protection
const StaffProtectedRoute = ({ children }) => {
  const { user, loading, error } = useAuth();
  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;
  const isAuthorized = user && (user.role === "staff" || user.role === "admin");
  if (!isAuthorized) return <Navigate to="/login" replace />;
  return children;
};

// 🔐 Doctor Route Protection
const DoctorProtectedRoute = ({ children }) => {
  const { user, loading, error } = useAuth();
  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;
  const isDoctor = user && user.role === "doctor";
  if (!isDoctor) return <Navigate to="/login" replace />;
  return children;
};

function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          {/* 🌐 Public Routes */}
          <Route path="/" element={<LazyLanding />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/login" element={<LoginPage />} />

          {/* 🔐 Staff/Admin Routes */}
          <Route path="/staff-dashboard" element={<StaffProtectedRoute><StaffDashboard /></StaffProtectedRoute>} />
          <Route path="/staff-admission" element={<StaffProtectedRoute><StaffAppointments /></StaffProtectedRoute>} />
          <Route path="/staff-patient-records" element={<StaffProtectedRoute><StaffPatientsRecords /></StaffProtectedRoute>} />
          <Route path="/staff-add-new-patients-records" element={<StaffProtectedRoute><AddNewPatientsRecords /></StaffProtectedRoute>} />

          <Route path="/staff-billing" element={<StaffProtectedRoute><StaffBilling /></StaffProtectedRoute>} />
          <Route path="/staff-inventory" element={<StaffProtectedRoute><StaffInventory /></StaffProtectedRoute>} />
          <Route path="/add-new-item" element={<StaffProtectedRoute><AddNewItem /></StaffProtectedRoute>} />
          <Route path="/staff-rooms" element={<StaffProtectedRoute><StaffRooms /></StaffProtectedRoute>} />
          <Route path="/staff-rooms/add" element={<StaffProtectedRoute><AddNewRoom /></StaffProtectedRoute>} />

          {/* 🔐 Doctor Routes */}
          <Route path="/doctor-dashboard" element={<DoctorProtectedRoute><DoctorDashboard /></DoctorProtectedRoute>} />
          <Route path="/doctor-admission" element={<DoctorProtectedRoute><DoctorAdmission /></DoctorProtectedRoute>} />
          <Route path="/doctor-patient-records" element={<DoctorProtectedRoute><DoctorPatientsRecords /></DoctorProtectedRoute>} />
          <Route path="/doctor-telemedicine" element={<DoctorProtectedRoute><DoctorTelemedicine /></DoctorProtectedRoute>} />
          <Route path="/doctor-telemedicine/new-request" element={<DoctorProtectedRoute><AddNewRequestTelemedicine /></DoctorProtectedRoute>} />
          <Route path="/doctor-prescriptions" element={<DoctorProtectedRoute><DoctorPrescriptions /></DoctorProtectedRoute>} />
          <Route path="/doctor-prescriptions/add-new-patients-prescriptions" element={<DoctorProtectedRoute><AddNewPatientPrescription /></DoctorProtectedRoute>} />
          <Route path="/doctor-consultation" element={<DoctorProtectedRoute><StartConsultationTelemedicine /></DoctorProtectedRoute>} /> 
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;
