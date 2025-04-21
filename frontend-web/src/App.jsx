import { AuthProvider } from "./context/AuthContext";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import LazyLanding from "./pages/LandingPage";
import RegisterPage from "./pages/RegisterPage";
import LoginPage from "./pages/LoginPage";
import StaffDashboard from "./pages/StaffDashboard";
import StaffAppointments from "./pages/StaffAddmision";
import StaffBilling from "./pages/StaffBilling";
import StaffInventory from "./pages/StaffInventory";
import StaffRooms from "./pages/StaffRooms";
import { useAuth } from "./context/AuthContext";

// 🔐 Protected Route for Staff and Admin
const StaffProtectedRoute = ({ children }) => {
  const { user, loading, error } = useAuth();

  console.log("Protected route - user:", user, "loading:", loading);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>Error: {error}</div>;
  }

  const isAuthorized = user && (user.role === "staff" || user.role === "admin");

  if (!isAuthorized) {
    console.log("User not authorized, redirecting to login");
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

          {/* 🔐 Staff Dashboard */}
          <Route
            path="/staff-dashboard"
            element={
              <StaffProtectedRoute>
                <StaffDashboard />
              </StaffProtectedRoute>
            }
          />

          {/* 🔐 Staff Appointments */}
          <Route
            path="/staff-addmision"
            element={
              <StaffProtectedRoute>
                <StaffAppointments />
              </StaffProtectedRoute>
            }
          />

          {/* 🔐 Staff Billing Page */}
          <Route
            path="/staff-billing"
            element={
              <StaffProtectedRoute>
                <StaffBilling />
              </StaffProtectedRoute>
            }
          />

          {/* 🔐 Staff Inventory Page */}
          <Route
            path="/staff-inventory"
            element={
              <StaffProtectedRoute>
                <StaffInventory />
              </StaffProtectedRoute>
            }
          />

          {/* 🔐 Staff Rooms Page */}
          <Route
            path="/staff-rooms"
            element={
              <StaffProtectedRoute>
                <StaffRooms />
              </StaffProtectedRoute>
            }
          />
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}

export default App;