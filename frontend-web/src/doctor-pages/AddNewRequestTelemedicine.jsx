import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import "../doctor-styles/DoctorTelemedicine.css";
import "../doctor-styles/AddNewRequestTelemedicine.css";

const AddNewRequestTelemedicine = () => {
  const navigate = useNavigate();
  const { user, logout } = useAuth();
  const [formData, setFormData] = useState({
    name: "",
    age: "",
    condition: "",
    reason: "",
    preferredDate: "",
    preferredTime: "",
  });

  useEffect(() => {
    if (!user) {
      navigate("/login");
    }
  }, [user, navigate]);

  const handleLogout = () => {
    logout();
    navigate("/");
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault(); // Prevent page reload on submit
    console.log("Form Submitted with data:", formData);

    // Simulate API call or navigate to another page
    // For now, we just navigate back to the telemedicine page
    navigate("/doctor-telemedicine");
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
          <span>Telemedicine – New Request</span>
          <button className="logout-btn" onClick={handleLogout}>Logout</button>
        </header>

        <section className="dashboard-section">
          <h2 className="page-title">New Telemedicine Request</h2>
          <div className="prescription-form-container">
            <form className="prescription-form" onSubmit={handleSubmit}>
              <label>Name:</label>
              <input
                name="name"
                value={formData.name}
                onChange={handleChange}
                required
              />

              <label>Age:</label>
              <input
                name="age"
                type="number"
                value={formData.age}
                onChange={handleChange}
                required
              />

              <label>Condition:</label>
              <input
                name="condition"
                value={formData.condition}
                onChange={handleChange}
                required
              />

              <label>Reason for Consultation:</label>
              <textarea
                name="reason"
                rows="5"
                value={formData.reason}
                onChange={handleChange}
                required
              />

              <label>Preferred Date:</label>
              <input
                name="preferredDate"
                type="date"
                value={formData.preferredDate}
                onChange={handleChange}
                required
              />

              <label>Preferred Time:</label>
              <input
                name="preferredTime"
                type="time"
                value={formData.preferredTime}
                onChange={handleChange}
                required
              />

              <button type="submit" className="add-btn">
                Submit Request
              </button>
            </form>
          </div>
        </section>
      </div>
    </div>
  );
};

export default AddNewRequestTelemedicine;
