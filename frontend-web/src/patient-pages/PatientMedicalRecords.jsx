// src/pages/PatientMedicalRecords.jsx
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import "../patient-styles/PatientMedicalRecords.css";

const PatientMedicalRecords = () => {
  const navigate = useNavigate();
  const { user, logout } = useAuth();
  const [activeTab, setActiveTab] = useState("patientInfo");

  useEffect(() => {
    if (!user) {
      navigate("/login");
    }
  }, [user, navigate]);

  const handleLogout = () => {
    logout();
    navigate("/");
  };

  // Placeholder handlers
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
            <li onClick={() => navigate("/patient-appointments")}>Appointments</li>
            <li className="active">Medical Records</li>
            <li onClick={() => navigate("/patient-telemedicine")}>Telemedicine</li>
            <li onClick={() => navigate("/patient-prescriptions")}>Prescriptions</li>
            <li onClick={() => navigate("/ai-chatbot")}>AI Chatbot</li>
          </ul>
        </div>
      </aside>

      <div className="main-content">
        <header className="topbar-PatientMedicalRecords">
          <span>Patient Record Page</span>
          <button className="logout-btn" onClick={handleLogout}>Logout</button>
        </header>

        <section className="dashboard-section">
          <h2>My Medical Records</h2>

          {/* Medical Records Navigation */}
          <div className="medical-records-nav">
            <button 
              className={activeTab === "patientInfo" ? "nav-button active" : "nav-button"}
              onClick={() => setActiveTab("patientInfo")}
            >
              Patient Information
            </button>
            <button 
              className={activeTab === "appointments" ? "nav-button active" : "nav-button"}
              onClick={() => setActiveTab("appointments")}
            >
              Your Appointments
            </button>
            <button 
              className={activeTab === "testResults" ? "nav-button active" : "nav-button"}
              onClick={() => setActiveTab("testResults")}
            >
              Test Results
            </button>
            <button 
              className={activeTab === "medications" ? "nav-button active" : "nav-button"}
              onClick={() => setActiveTab("medications")}
            >
              Medications
            </button>
          </div>

          {/* Content based on active tab */}
          <div className="records-content">
            {activeTab === "patientInfo" && (
              <div className="patient-info-section">
                <h3>Personal Information</h3>
                <div className="info-card">
                  <div className="info-row">
                    <span className="info-label">Name:</span>
                    <span className="info-value">Earl Owen V. Calzada</span>
                  </div>
                  <div className="info-row">
                    <span className="info-label">Date of Birth:</span>
                    <span className="info-value">January 15, 1985</span>
                  </div>
                  <div className="info-row">
                    <span className="info-label">Gender:</span>
                    <span className="info-value">Male</span>
                  </div>
                  <div className="info-row">
                    <span className="info-label">Blood Type:</span>
                    <span className="info-value">O+</span>
                  </div>
                  <div className="info-row">
                    <span className="info-label">Primary Physician:</span>
                    <span className="info-value">Dr. Sarah Johnson</span>
                  </div>
                </div>
              </div>
            )}

            {activeTab === "appointments" && (
              <div className="appointments-section">
                <div className="table-container">
                  <table>
                    <thead>
                      <tr>
                        <th></th>
                        <th>Patient</th>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Status</th>
                        <th>Actions</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr>
                        <td>1</td>
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
                        <td>2</td>
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
              </div>
            )}

            {activeTab === "testResults" && (
              <div className="test-results-section">
                <h3>Recent Test Results</h3>
                <div className="test-results-list">
                  <div className="test-result-item">
                    <div className="test-result-header">
                      <span className="test-name">Blood Test</span>
                      <span className="test-date">May 10, 2025</span>
                    </div>
                    <div className="test-result-details">
                      <p>Results are within normal range. Cholesterol slightly elevated.</p>
                      <button className="view-details-btn">View Full Report</button>
                    </div>
                  </div>
                  <div className="test-result-item">
                    <div className="test-result-header">
                      <span className="test-name">X-Ray (Chest)</span>
                      <span className="test-date">April 22, 2025</span>
                    </div>
                    <div className="test-result-details">
                      <p>No abnormalities detected. Lungs clear.</p>
                      <button className="view-details-btn">View Full Report</button>
                    </div>
                  </div>
                </div>
              </div>
            )}

            {activeTab === "medications" && (
              <div className="medications-section">
                <h3>Current Medications</h3>
                <div className="medications-list">
                  <div className="medication-item">
                    <span className="medication-name">Lisinopril 10mg</span>
                    <span className="medication-schedule">Once daily</span>
                    <span className="medication-purpose">Blood pressure</span>
                    <span className="refills">Refills: 2</span>
                    <button className="refill-btn">Request Refill</button>
                  </div>
                  <div className="medication-item">
                    <span className="medication-name">Atorvastatin 20mg</span>
                    <span className="medication-schedule">Once daily at bedtime</span>
                    <span className="medication-purpose">Cholesterol</span>
                    <span className="refills">Refills: 5</span>
                    <button className="refill-btn">Request Refill</button>
                  </div>
                </div>
              </div>
            )}
          </div>
        </section>
      </div>
    </div>
  );
};

export default PatientMedicalRecords;