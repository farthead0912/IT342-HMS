import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import "../patient-styles/PatientDashboard.css";

const PatientDashboard = () => {
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
            <li onClick={() => navigate("/patient-appointments")}>Appointments</li>
            <li onClick={() => navigate("/patient-medical-records")}>Medical Records</li>
            <li onClick={() => navigate("/patient-telemedicine")}>Telemedicine</li>
            <li onClick={() => navigate("/patient-prescriptions")}>Prescriptions</li>
            <li onClick={() => navigate("/ai-chatbot")}>AI Chatbot</li>
          </ul>
        </div>
      </aside>

      <div className="main-content">
        <header className="topbar-StaffDashboard">
          <span>Patient Dashboard Page</span>
          <button className="logout-btn" onClick={handleLogout}>Logout</button>
        </header>

        <section className="dashboard-section">
          <h2>Patient Dashboard</h2>

          {/* Cards Row */}
          <div className="info-cards">
            <div className="card">
              <p>Upcoming Appointments</p>
              <h3>2 Scheduled</h3>
            </div>
            <div className="card">
              <p>Latest Prescription</p>
              <h3>Blood Pressure Medication</h3>
            </div>
            <div className="card">
              <p>Medical Records</p>
              <h3>Last Checkup: June 15</h3>
            </div>
          </div>

          {/* Appointments Table */}
          <h3 className="section-title">Your Appointments</h3>
          <div className="table-container">
            <table>
              <thead>
                <tr>
                  <th>Date</th>
                  <th>Time</th>
                  <th>Doctor</th>
                  <th>Status</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>June 30, 2025</td>
                  <td>2:00 PM</td>
                  <td>Dr. Calzada</td>
                  <td>Confirmed</td>
                </tr>
                <tr>
                  <td>July 1, 2025</td>
                  <td>10:00 AM</td>
                  <td>Dr. Rivera</td>
                  <td>Pending</td>
                </tr>
              </tbody>
            </table>
          </div>

          {/* Prescriptions Table */}
          <h3 className="section-title">Your Prescriptions</h3>
          <div className="table-container">
            <table>
              <thead>
                <tr>
                  <th>Date</th>
                  <th>Medication</th>
                  <th>Dosage</th>
                  <th>Doctor</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>June 30</td>
                  <td>Paracetamol</td>
                  <td>500mg</td>
                  <td>Dr. Calzada</td>
                </tr>
                <tr>
                  <td>July 1, 2025</td>
                  <td>Metformin</td>
                  <td>850mg</td>
                  <td>Dr. Rivera</td>
                </tr>
              </tbody>
            </table>
          </div>

          {/* AI Chatbot Section */}
          <div className="chatbot-section">
            <h3>AI Chatbot for Consultations & Schedule</h3>
            <p>Chat with our AI assistant to book appointments and get medical advice.</p>
            <button className="start-chat-btn">Start Chat</button>
          </div>
        </section>
      </div>
    </div>
  );
};

export default PatientDashboard;
