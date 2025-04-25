import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import "../doctor-styles/DoctorPrescriptions.css";

const DoctorPrescriptions = () => {
  const navigate = useNavigate();
  const { user, logout } = useAuth();
  const [prescriptions, setPrescriptions] = useState([]);

  useEffect(() => {
    if (!user) {
      navigate("/login");
      return;
    }

    const storedPrescriptions = JSON.parse(localStorage.getItem("prescriptions")) || [];
    setPrescriptions(storedPrescriptions);
  }, [user, navigate]);

  const handleLogout = () => {
    logout();
    navigate("/");
  };

  const handleAddNew = () => {
    navigate("/doctor-prescriptions/add-new-patients-prescriptions");
  };

  const handleDelete = (id) => {
    const confirmDelete = window.confirm("Are you sure you want to delete this prescription?");
    if (confirmDelete) {
      const updated = prescriptions.filter((p) => p.id !== id);
      setPrescriptions(updated);
      localStorage.setItem("prescriptions", JSON.stringify(updated));
    }
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
            <li onClick={() => navigate("/doctor-telemedicine")}>Telemedicine</li>
            <li className="active">Prescriptions</li>
          </ul>
        </div>
      </aside>

      <div className="main-content">
        <header className="topbar-DoctorPrescription">
          <span>Doctor Prescriptions</span>
          <button className="logout-btn" onClick={handleLogout}>Logout</button>
        </header>

        <section className="dashboard-section">
          <div className="header-bar">
            <h2 className="page-title">Patient Prescriptions</h2>
            <button className="add-btn" onClick={handleAddNew}>
              Add New Patient Prescription Info
            </button>
          </div>

          <h3 className="section-title">Existing Prescriptions</h3>

          <div className="table-container">
            <table>
              <thead>
                <tr>
                  <th>Patient Name</th>
                  <th>Medication</th>
                  <th>Symptoms</th>
                  <th>Dosage</th>
                  <th>Usage Instructions</th>
                  <th>Date Issued</th>
                  <th>Order Medication</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {prescriptions.length > 0 ? (
                  prescriptions.map((p) => (
                    <tr key={p.id}>
                      <td>{p.patient}</td>
                      <td>{p.medication}</td>
                      <td>{p.symptoms || "-"}</td>
                      <td>{p.dosage}</td>
                      <td>{p.instructions || "-"}</td> {/* Change here */}
                      <td>{p.date}</td>
                      <td>
                        {p.order === "yes" ? (
                          <span className="order-yes">Yes, Order Now</span>
                        ) : (
                          <span className="order-no">No, Save for Later</span>
                        )}
                      </td>
                      <td>
                        <button className="delete-btn" onClick={() => handleDelete(p.id)}>
                          Delete
                        </button>
                      </td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan="8" style={{ textAlign: "center", padding: "1rem" }}>
                      No prescriptions found.
                    </td>
                  </tr>
                )}
              </tbody>
            </table>
          </div>
        </section>
      </div>
    </div>
  );
};

export default DoctorPrescriptions;
