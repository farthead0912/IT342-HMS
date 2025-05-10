// src/pages/PatientAppointments.jsx
import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import "../patient-styles/PatientAppointments.css";

const PatientAppointments = () => {
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

  // Placeholder handlers for button actions
  const handleReschedule = (name) => {
    alert(`Rescheduling appointment for ${name}`);
  };

  const handleCancel = (name) => {
    alert(`Cancelling appointment for ${name}`);
  };

  const handleJoinTelemedicine = (name) => {
    alert(`Joining telemedicine session for ${name}`);
  };

  return (
    <div className="dashboard-container">
      <aside className="sidebar">
        <div className="logo">HMS</div>
        <div>
          <p className="menu-title">Menu</p>
          <ul>
            <li onClick={() => navigate("/patient-dashboard")}>Dashboard</li>
            <li className="active">Appointments</li>
            <li onClick={() => navigate("/patient-medical-records")}>Medical Records</li>
            <li onClick={() => navigate("/patient-telemedicine")}>Telemedicine</li>
            <li onClick={() => navigate("/patient-prescriptions")}>Prescriptions</li>
            <li onClick={() => navigate("/ai-chatbot")}>AI Chatbot</li>
          </ul>
        </div>
      </aside>

      <div className="main-content">
        <header className="topbar-PatientAppointments">
          <span>Patient Appointments Page</span>
          <button className="logout-btn" onClick={handleLogout}>Logout</button>
        </header>

        <section className="dashboard-section">
          <h2>My Appointments</h2>

          {/* Filter and Book Section */}
          <div className="filter-section">
            <input type="date" />
            <select>
              <option value="all">All</option>
              <option value="pending">Pending</option>
              <option value="confirmed">Confirmed</option>
            </select>
            <button className="filter-btn">Filter</button>
            <button className="book-btn">
              Book New Appointment
            </button>
          </div>

          {/* Appointments Table */}
          <div className="table-container">
            <table>
              <thead>
                <tr>
                  <th>Patient</th>
                  <th>Date</th>
                  <th>Time</th>
                  <th>Status</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Earl Owen V. Calzada</td>
                  <td>June 28</td>
                  <td>10:00 AM</td>
                  <td>Pending</td>
                  <td>
                    <button className="reschedule-btn" onClick={() => handleReschedule("Earl Owen V. Calzada")}>
                      Reschedule
                    </button>
                    <button className="cancel-btn" onClick={() => handleCancel("Earl Owen V. Calzada")}>
                      Cancel
                    </button>
                  </td>
                </tr>
                <tr>
                  <td>Benjie Rivera Jr.</td>
                  <td>June 29</td>
                  <td>2:00 PM</td>
                  <td>Confirmed</td>
                  <td>
                    <button className="telemedicine-btn" onClick={() => handleJoinTelemedicine("Benjie Rivera Jr.")}>
                      Join Telemedicine
                    </button>
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

export default PatientAppointments;
