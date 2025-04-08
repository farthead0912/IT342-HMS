import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import "../styles/StaffDashboard.css";

const StaffDashboard = () => {
  const navigate = useNavigate();
  const { user, logout } = useAuth();
  
  // Check if user is authenticated
  useEffect(() => {
    console.log("Current user in dashboard:", user);
    if (!user) {
      navigate("/login");
    }
  }, [user, navigate]);

  const handleLogout = () => {
    // Use the auth context logout function
    logout();
    console.log("Logging out, redirecting to home");
    // Redirect to the landing page
    navigate("/");
  };

  return (
    <div className="dashboard-container">
      {/* Sidebar */}
      <aside className="sidebar">
        <div className="logo">HMS</div>
        <div>
          <p className="menu-title">Menu</p>
          <ul>
            <li className="active">Dashboard</li>
            <li onClick={() => navigate("/staff-appointments")}>Appointments</li>
            <li onClick={() => navigate("/staff-billing")}>Billings</li>
            <li onClick={() => navigate("/staff-inventory")}>Inventory</li>
            <li onClick={() => navigate("/staff-rooms")}>Rooms</li>          </ul>
        </div>
      </aside>

      {/* Main Content */}
      <div className="main-content">
        {/* Top Bar */}
        <header className="topbar">
          <span>Staff-Dashboard-Page</span>
          <button className="logout-btn" onClick={handleLogout}>Logout</button>
        </header>

        {/* Dashboard Content */}
        <section className="dashboard-section">
          <h2>Staff Dashboard</h2>

          {/* Info Cards */}
          <div className="info-cards">
            <div className="card">
              <p>Appointment's Today</p>
              <h3>35</h3>
            </div>
            <div className="card">
              <p>Rooms Available</p>
              <h3>10</h3>
            </div>
            <div className="card">
              <p>Pending Bills</p>
              <h3>$100,000</h3>
            </div>
          </div>

          {/* Billing Transactions */}
          <h3 className="section-title">Recent Billing Transactions</h3>
          <div className="table-container">
            <table>
              <thead>
                <tr>
                  <th>Patient</th>
                  <th>Amount</th>
                  <th>Status</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Earl Owen V. Calzada</td>
                  <td>$200</td>
                  <td>Paid</td>
                </tr>
                <tr>
                  <td>Benjie Rivera Jr.</td>
                  <td>$1000</td>
                  <td>Pending</td>
                </tr>
              </tbody>
            </table>
          </div>

          {/* Inventory Stock */}
          <h3 className="section-title">Inventory Stock</h3>
          <div className="table-container">
            <table>
              <thead>
                <tr>
                  <th>Item</th>
                  <th>Quantity</th>
                  <th>Status</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Surgical Masks</td>
                  <td>500</td>
                  <td>In Stock</td>
                </tr>
                <tr>
                  <td>IV Fluids</td>
                  <td>200</td>
                  <td>Low Stock</td>
                </tr>
              </tbody>
            </table>
          </div>
        </section>
      </div>
    </div>
  );
};

export default StaffDashboard;