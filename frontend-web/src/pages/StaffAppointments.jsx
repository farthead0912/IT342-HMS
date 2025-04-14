import React from "react";
import { useNavigate } from "react-router-dom";
import "../styles/StaffAppointments.css";

const StaffAppointments = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("user");
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
            <li onClick={() => navigate("/staff-dashboard")}>Dashboard</li>
            <li className="active">Appointments</li>
            <li onClick={() => navigate("/staff-billing")}>Billings</li>
            <li onClick={() => navigate("/staff-inventory")}>Inventory</li>
            <li onClick={() => navigate("/staff-rooms")}>Rooms</li>
          </ul>
        </div>
      </aside>

      {/* Main Content */}
      <div className="main-content">
        <header className="topbar">
          <span>Staff-Appointments-Page</span>
          <button className="logout-btn" onClick={handleLogout}>
            Logout
          </button>
        </header>

        <section className="dashboard-section">
          <h2>Appointments Today</h2>

          {/* Filter Controls */}
          <div className="filter-controls">
            <label>
              Choose Month:
              <input type="month" name="month" />
            </label>

            <label>
              Status:
              <select name="status">
                <option value="">All</option>
                <option value="confirmed">Confirmed</option>
                <option value="pending">Pending</option>
                <option value="cancelled">Cancelled</option>
              </select>
            </label>

            <button className="filter-btn">Filter</button>
          </div>

          <div className="table-container">
            <table>
              <thead>
                <tr>
                  <th>Patient</th>
                  <th>Date</th>
                  <th>Time</th>
                  <th>Doctor</th>
                  <th>Status</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Earl Owen V. Calzada</td>
                  <td>2025-04-07</td>
                  <td>9:00 AM</td>
                  <td>Dr. Smith</td>
                  <td className="status-confirmed">Confirmed</td>
                </tr>
                <tr>
                  <td>Benjie Rivera Jr.</td>
                  <td>2025-04-07</td>
                  <td>10:30 AM</td>
                  <td>Dr. Lopez</td>
                  <td className="status-pending">Pending</td>
                </tr>
              </tbody>
            </table>
          </div>
        </section>
      </div>
    </div>
  );
};

export default StaffAppointments;
