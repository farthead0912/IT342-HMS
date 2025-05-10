import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import "../doctor-styles/DoctorPatientsRecords.css";

const DoctorPatientsRecords = () => {
  const navigate = useNavigate();
  const { user, logout } = useAuth();

  useEffect(() => {
    if (!user) {
      navigate("/login");
    }
  }, [user, navigate]);

  const handleLogout = () => {
    logout();
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
            <li onClick={() => navigate("/doctor-dashboard")}>Dashboard</li>
            <li onClick={() => navigate("/doctor-admission")}>Admissions</li>
            <li className="active">Patient Records</li>
            <li onClick={() => navigate("/doctor-telemedicine")}>Telemedicine</li>
            <li onClick={() => navigate("/doctor-prescriptions")}>Prescriptions</li>
          </ul>
        </div>
      </aside>

      {/* Main Content */}
      <div className="main-content">
        <header className="topbar-DoctorPatientsRecords">
          <span>Doctor - Patient Records</span>
          <button className="logout-btn" onClick={handleLogout}>Logout</button>
        </header>

        <section className="dashboard-section">
          <h2 className="page-title">Patient Records</h2>

          {/* Search Only */}
          <div className="record-controls">
            <input
              type="text"
              placeholder="Search Patient..."
              className="search-box"
            />
          </div>

          {/* Patient Records Table */}
          <div className="table-container">
            <h3 className="section-title">Patient History</h3>
            <table>
              <thead>
                <tr>
                  <th>Patient Name</th>
                  <th>Age</th>
                  <th>Condition</th>
                  <th>Last Visit</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>John Doe</td>
                  <td>45</td>
                  <td>Diabetes</td>
                  <td>June 15, 2024</td>
                  <td>
                    <button className="view-btn">View</button>
                    <button className="update-btn">Update</button>
                  </td>
                </tr>
                <tr>
                  <td>Jane Smith</td>
                  <td>32</td>
                  <td>Hypertension</td>
                  <td>May 28, 2024</td>
                  <td>
                    <button className="view-btn">View</button>
                    <button className="update-btn">Update</button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </section>
      </div>
    </div>
  );
};

export default DoctorPatientsRecords;