import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import "../staff-styles/StaffPatientsRecords.css";
import "../staff-styles/AddNewPatientsRecords.css";

const AddNewPatientsRecords = () => {
  const navigate = useNavigate();
  const { user, logout } = useAuth();

  const [formData, setFormData] = useState({
    name: "",
    age: "",
    condition: "",
    lastVisit: "",
  });

  useEffect(() => {
    if (!user) navigate("/login");
  }, [user, navigate]);

  const handleLogout = () => {
    logout();
    navigate("/");
  };

  const handleChange = e => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = e => {
    e.preventDefault();
    console.log("New Patient Record:", formData);
    alert("Patient record added successfully!");
    navigate("/staff-patient-records");
  };

  const handleCancel = () => {
    navigate("/staff-patient-records"); // Navigates back to patient records page
  };

  return (
    <div className="dashboard-container">
      {/* Sidebar */}
      <aside className="sidebar">
        <div className="logo">HMS</div>
        <p className="menu-title">Menu</p>
        <ul>
          <li onClick={() => navigate("/staff-dashboard")}>Dashboard</li>
          <li onClick={() => navigate("/staff-admission")}>Admissions</li>
          <li className="active">Patient Records</li>
          <li onClick={() => navigate("/staff-billing")}>Billings</li>
          <li onClick={() => navigate("/staff-inventory")}>Inventory</li>
          <li onClick={() => navigate("/staff-rooms")}>Rooms</li>
        </ul>
      </aside>

      {/* Main content */}
      <div className="main-content">
        <header className="topbar-StaffPatientsRecords">
          <span>Staff – Add New Patient Record</span>
          <button className="logout-btn" onClick={handleLogout}>Logout</button>
        </header>

        <section className="dashboard-section">
          <h2 className="page-title">Add New Patient Record</h2>

          {/* center wrapper */}
          <div className="form-container">
            <form className="add-record-form" onSubmit={handleSubmit}>
              <label>
                Patient Name:
                <input
                  type="text"
                  name="name"
                  value={formData.name}
                  onChange={handleChange}
                  required
                />
              </label>

              <label>
                Age:
                <input
                  type="number"
                  name="age"
                  value={formData.age}
                  onChange={handleChange}
                  required
                />
              </label>

              <label>
                Condition:
                <input
                  type="text"
                  name="condition"
                  value={formData.condition}
                  onChange={handleChange}
                  required
                />
              </label>

              <label>
                Last Visit:
                <input
                  type="date"
                  name="lastVisit"
                  value={formData.lastVisit}
                  onChange={handleChange}
                  required
                />
              </label>

              <div className="form-buttons">
                <button type="submit" className="add-btn-DoctorPatientsRecords">
                  Submit Record
                </button>
                <button
                  type="button"
                  className="cancel-btn"
                  onClick={handleCancel}
                >
                  Cancel
                </button>
              </div>
            </form>
          </div>
        </section>
      </div>
    </div>
  );
};

export default AddNewPatientsRecords;
