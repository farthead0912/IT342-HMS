import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../staff-styles/StaffBilling.css";

const StaffBilling = () => {
  const navigate = useNavigate();

  // Sample billing records
  const [billingRecords] = useState([
    { patient: "Earl Owen V. Calzada", amount: "$200", status: "Pending" },
    { patient: "Benjie Rivera Jr.", amount: "$500", status: "Paid" },
    { patient: "Maria Clara", amount: "$300", status: "Pending" },
    { patient: "Jose Maria", amount: "$150", status: "Paid" },
  ]);

  const [searchTerm, setSearchTerm] = useState("");
  const [statusFilter, setStatusFilter] = useState("");
  const [filteredRecords, setFilteredRecords] = useState(billingRecords);

  const handleLogout = () => {
    localStorage.removeItem("user");
    navigate("/");
  };

  const handleFilterClick = () => {
    const results = billingRecords.filter((record) => {
      const isSearchMatch =
        record.patient.toLowerCase().includes(searchTerm.toLowerCase());
      const isStatusMatch = statusFilter
        ? record.status.toLowerCase() === statusFilter.toLowerCase()
        : true;
      return isSearchMatch && isStatusMatch;
    });
    setFilteredRecords(results);
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
            <li onClick={() => navigate("/staff-admission")}>Admissions</li>
            <li onClick={() => navigate("/staff-patient-records")}>Patient Records</li>
            <li className="active">Billings</li>
            <li onClick={() => navigate("/staff-inventory")}>Inventory</li>
            <li onClick={() => navigate("/staff-rooms")}>Rooms</li>
          </ul>
        </div>
      </aside>

      {/* Main Content */}
      <div className="main-content">
        <header className="topbar-StaffBilling">
          <span>Staff Billing Page</span>
          <button className="logout-btn" onClick={handleLogout}>
            Logout
          </button>
        </header>

        <section className="dashboard-section">
          <h2>Billing Management</h2>

          {/* Filter Controls */}
          <div className="filter-controls">
            <input
              type="text"
              placeholder="Search by Patient Name"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
            <select
              value={statusFilter}
              onChange={(e) => setStatusFilter(e.target.value)}
            >
              <option value="">All</option>
              <option value="pending">Pending</option>
              <option value="paid">Paid</option>
            </select>
            <button className="filter-btn" onClick={handleFilterClick}>
              Filter
            </button>
          </div>

          <div className="table-container">
            <h3 className="billing-records">Billing Records</h3>
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
                {filteredRecords.map((record, index) => (
                  <tr key={index}>
                    <td>{record.patient}</td>
                    <td>{record.amount}</td>
                    <td>{record.status}</td>
                    <td>Mark as Paid or Generate Invoice</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </section>
      </div>
    </div>
  );
};

export default StaffBilling;