import React from "react";
import { useNavigate } from "react-router-dom";
import "../styles/StaffBilling.css";

const StaffBilling = () => {
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
            <li onClick={() => navigate("/staff-appointments")}>Appointments</li>
            <li className="active">Billings</li>
            <li onClick={() => navigate("/staff-inventory")}>Inventory</li>
            <li onClick={() => navigate("/staff-rooms")}>Rooms</li>
          </ul>
        </div>
      </aside>

      {/* Main Content */}
      <div className="main-content">
        <header className="topbar">
          <span>Staff-Billing-Page</span>
          <button className="logout-btn" onClick={handleLogout}>
            Logout
          </button>
        </header>

        <section className="dashboard-section">
          <h2>Billing Management</h2>

          {/* Filter Controls */}
          <div className="filter-controls">
            <input type="text" placeholder="Search by Patient Name" />
            <select>
              <option value="">All</option>
              <option value="pending">Pending</option>
              <option value="paid">Paid</option>
            </select>
            <button className="filter-btn">Filter</button>
          </div>

          <div className="table-container">
            <h3>Billing Records</h3>
            <table>
              <thead>
                <tr>
                  <th>Patient</th>
                  <th>Amount</th>
                  <th>Status</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Earl Owen V. Calzada</td>
                  <td>$200</td>
                  <td>Pending</td>
                  <td>Mark as Paid or Generate Invoice</td>
                </tr>
                <tr>
                  <td>Benjie Rivera Jr.</td>
                  <td>$500</td>
                  <td>Paid</td>
                  <td>View Invoice</td>
                </tr>
              </tbody>
            </table>
          </div>
        </section>
      </div>
    </div>
  );
};

export default StaffBilling;