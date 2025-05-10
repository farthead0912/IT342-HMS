import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import "../doctor-styles/DoctorDashboard.css";

const DoctorDashboard = () => {
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
      <aside className="sidebar">
        <div className="logo">HMS</div>
        <div>
          <p className="menu-title">Menu</p>
          <ul>
            <li className="active">Dashboard</li>
            <li onClick={() => navigate("/doctor-admission")}>Admissions</li>
            <li onClick={() => navigate("/doctor-patient-records")}>Patient Records</li>
            <li onClick={() => navigate("/doctor-telemedicine")}>Telemedicine</li>
            <li onClick={() => navigate("/doctor-prescriptions")}>Prescriptions</li>
          </ul>
        </div>
      </aside>

      <div className="main-content">
        <header className="topbar-DoctorsDashboard">
          <span>Doctor Dashboard</span>
          <button className="logout-btn" onClick={handleLogout}>Logout</button>
        </header>

        <section className="dashboard-section">
          <h2 className="page-title">Doctor Dashboard</h2>

          <div className="info-cards">
            <div className="card">
              <p>Today's Admissions</p>
              <h3>18</h3>
            </div>
            <div className="card">
              <p>Pending Prescriptions</p>
              <h3>5</h3>
            </div>
            <div className="card">
              <p>Telemedicine Calls</p>
              <h3>7</h3>
            </div>
          </div>

          <h3 className="section-title">Recent Patient Records</h3>
          <div className="table-container">
            <table>
              <thead>
                <tr>
                  <th>Patient Name</th>
                  <th>Date</th>
                  <th>Time</th>
                  <th>Condition</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Maria Sanchez</td>
                  <td>April 21, 2025</td>
                  <td>10:00 AM</td>
                  <td>Hypertension</td>
                </tr>
                <tr>
                  <td>John Reyes</td>
                  <td>April 20, 2025</td>
                  <td>2:00 PM</td>
                  <td>Diabetes</td>
                </tr>
              </tbody>
            </table>
          </div>

          {/* Prescriptions Section */}
          <h3 className="section-title">Recent Prescriptions</h3>
          <div className="table-container">
            <table>
              <thead>
                <tr>
                  <th>Patient Name</th>
                  <th>Medication</th>
                  <th>Dosage</th>
                  <th>Instructions</th>
                  <th>Date Issued</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Maria Sanchez</td>
                  <td>Lisinopril</td>
                  <td>10 mg</td>
                  <td>Take once daily</td>
                  <td>April 21, 2025</td>
                </tr>
                <tr>
                  <td>John Reyes</td>
                  <td>Metformin</td>
                  <td>500 mg</td>
                  <td>Take twice daily</td>
                  <td>April 20, 2025</td>
                </tr>
              </tbody>
            </table>
          </div>
        </section>
      </div>
    </div>
  );
};

export default DoctorDashboard;