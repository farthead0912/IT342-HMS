import { AuthProvider } from "./context/AuthContext";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import LazyLanding from "./pages/LandingPage";
import RegisterPage from "./pages/RegisterPage";
import LoginPage from "./pages/LoginPage";
import StaffDashboard from "./pages/StaffDashboard";
import StaffAppointments from "./pages/StaffAppointments"; // ✅ Import the StaffAppointments page
import { useAuth } from "./context/AuthContext";

// 🔐 Protected Route for Staff and Admin
const StaffProtectedRoute = ({ children }) => {
  const { user, isLoading, error } = useAuth();

  if (isLoading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error.message}</div>;
  }

  const isAuthorized = user && (user.role === "staff" || user.role === "admin");

  if (!isAuthorized) {
    return <Navigate to="/login" replace />;
  }

  return children;
};

function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<LazyLanding />} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/login" element={<LoginPage />} />

          {/* 🔐 Protected Route for Staff Dashboard */}
          <Route
            path="/staff-dashboard"
            element={
              <StaffProtectedRoute>
                <StaffDashboard />
              </StaffProtectedRoute>
            }
          />

          {/* 🔐 Protected Route for Staff Appointments */}
          <Route
            path="/staff-appointments"
            element={
              <StaffProtectedRoute>
                <StaffAppointments />
              </StaffProtectedRoute>
            }
          />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;