/*StartConsultationTelemedicine.jsx*/

import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import "../doctor-styles/DoctorTelemedicine.css";

const StartConsultationTelemedicine = () => {
  const navigate = useNavigate();
  const { user, logout } = useAuth();
  const [doctorNotes, setDoctorNotes] = useState("");
  const [prescription, setPrescription] = useState("");

  if (!user) {
    navigate("/login");
  }

  const handleLogout = () => {
    logout();
    navigate("/");
  };

  const handleSubmit = () => {
    // Here you can handle the form submission, e.g., send data to your API
    console.log("Consultation Submitted");
  };

  return (
    <div className="dashboard-container">
      <aside className="sidebar">
        <div className="logo">HMS</div>
        <div>
          <p className="menu-title">Menu</p>
          <ul>
            <li onClick={() => navigate("/doctor-dashboard")}>Dashboard</li>
            <li onClick={() => navigate("/doctor-admission")}>Admissions</li>
            <li onClick={() => navigate("/doctor-patient-records")}>Patient Records</li>
            <li className="active">Telemedicine</li>
            <li onClick={() => navigate("/doctor-prescriptions")}>Prescriptions</li>
          </ul>
        </div>
      </aside>

      <div className="main-content">
        <header className="topbar-DoctorTelemedicine">
          <span>Telemedicine – Start Consultation</span>
          <button className="logout-btn" onClick={handleLogout}>
            Logout
          </button>
        </header>

        <section className="form-container">
          <div className="telemedicine-form">
            {/* Patient Information */}
            <h3 className="page-title">Patient Information</h3>
            <div className="patient-info">
              <p><strong>Name:</strong> Earl Owen V. Calzada</p>
              <p><strong>Age:</strong> 22</p>
              <p><strong>Condition:</strong> Diabetes</p>
              <p><strong>Symptoms:</strong> Fatigue, frequent urination, excessive thirst</p>
              <p><strong>Last Visit:</strong> March 25, 2025</p>
              <p><strong>Uploaded Reports:</strong> <a href="#" className="text-link">View Lab Results</a></p>
            </div>

            {/* Doctor's Notes */}
            <label htmlFor="doctor-notes">Doctor’s Notes</label>
            <textarea
              id="doctor-notes"
              className="input-field"
              placeholder="Enter your diagnosis and recommendations..."
              value={doctorNotes}
              onChange={(e) => setDoctorNotes(e.target.value)}
            />

            {/* Prescription */}
            <label htmlFor="prescription">Prescription (if needed)</label>
            <textarea
              id="prescription"
              className="input-field"
              placeholder="Enter medication details..."
              value={prescription}
              onChange={(e) => setPrescription(e.target.value)}
            />

            {/* Submit Button */}
            <button className="new-request-btn" onClick={handleSubmit}>
              Submit Consultation
            </button>
          </div>
        </section>
      </div>
    </div>
  );
};

export default StartConsultationTelemedicine;
