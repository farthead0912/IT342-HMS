import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import "../doctor-styles/DoctorPrescriptions.css";
import "../doctor-styles/AddNewPatientPrescription.css";

const AddNewPatientPrescription = () => {
  const navigate = useNavigate();
  const { user, logout } = useAuth();
  const [formData, setFormData] = useState({
    patient: "",
    medication: "",
    symptoms: "",
    dosage: "",
    instructions: "",
    date: "",
    order: "no",
  });

  useEffect(() => {
    if (!user) navigate("/login");
  }, [user, navigate]);

  const handleLogout = () => {
    logout();
    navigate("/");
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const newPrescription = {
      id: Date.now(),
      patient: formData.patient,
      medication: formData.medication,
      symptoms: formData.symptoms,
      dosage: formData.dosage,
      instructions: formData.instructions,
      date: formData.date,
      order: formData.order,
    };
    const existing = JSON.parse(localStorage.getItem("prescriptions")) || [];
    existing.push(newPrescription);
    localStorage.setItem("prescriptions", JSON.stringify(existing));
    navigate("/doctor-prescriptions");
  };

  return (
    <div className="dashboard-container">
      <aside className="sidebar">
        <div className="logo">HMS</div>
        <p className="menu-title">Menu</p>
        <ul>
          <li onClick={() => navigate("/doctor-dashboard")}>Dashboard</li>
          <li onClick={() => navigate("/doctor-admission")}>Admissions</li>
          <li onClick={() => navigate("/doctor-patient-records")}>Patient Records</li>
          <li onClick={() => navigate("/doctor-telemedicine")}>Telemedicine</li>
          <li className="active">Prescriptions</li>
        </ul>
      </aside>

      <div className="main-content">
        <header className="topbar-DoctorPrescription">
          <span>Add Patient Prescription</span>
          <button className="logout-btn" onClick={handleLogout}>
            Logout
          </button>
        </header>

        <section className="dashboard-section">
          <h2 className="page-title">Prescription Information & Order</h2>
          <div className="prescription-form-container">
            <form className="prescription-form" onSubmit={handleSubmit}>
              <label>Patient Name</label>
              <input
                type="text"
                name="patient"
                value={formData.patient}
                onChange={handleInputChange}
                required
              />

              <label>Medication</label>
              <input
                type="text"
                name="medication"
                value={formData.medication}
                onChange={handleInputChange}
                required
              />

              <label>Symptoms</label>
              <input
                type="text"
                name="symptoms"
                value={formData.symptoms}
                onChange={handleInputChange}
              />

              <label>Dosage</label>
              <input
                type="text"
                name="dosage"
                value={formData.dosage}
                onChange={handleInputChange}
                required
              />

              <label>Usage Instructions</label>
              <input
                type="text"
                name="instructions"
                value={formData.instructions}
                onChange={handleInputChange}
              />

              <label>Date Issued</label>
              <input
                type="date"
                name="date"
                value={formData.date}
                onChange={handleInputChange}
                required
              />

              <label>Order Medication</label>
              <select
                name="order"
                value={formData.order}
                onChange={handleInputChange}
              >
                <option value="yes">Yes, Order Now</option>
                <option value="no">No, Save for Later</option>
              </select>

              <button type="submit" className="add-btn">
                Save Prescription
              </button>
            </form>
          </div>
        </section>
      </div>
    </div>
  );
};

export default AddNewPatientPrescription;
