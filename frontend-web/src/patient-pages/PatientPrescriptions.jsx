// src/pages/PatientPrescriptions.jsx
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import "../patient-styles/PatientPrescriptions.css";

const PatientPrescriptions = () => {
  const navigate = useNavigate();
  const { user, logout } = useAuth();
  const [search, setSearch] = useState("");
  
  const [prescriptions, setPrescriptions] = useState([
    {
      id: "PRX-001",
      medicationName: "Lisinopril",
      dosage: "10mg",
      frequency: "Once daily",
      prescribedBy: "Dr. Sarah Johnson",
      prescribedDate: "10/03/2025",
      expiryDate: "10/09/2025",
      remainingRefills: 3,
      status: "Active"
    },
    {
      id: "PRX-002",
      medicationName: "Metformin",
      dosage: "500mg",
      frequency: "Twice daily",
      prescribedBy: "Dr. Michael Chen",
      prescribedDate: "05/02/2025",
      expiryDate: "05/08/2025",
      remainingRefills: 5,
      status: "Active"
    },
    {
      id: "PRX-003",
      medicationName: "Atorvastatin",
      dosage: "20mg",
      frequency: "Once daily at bedtime",
      prescribedBy: "Dr. Sarah Johnson",
      prescribedDate: "15/01/2025",
      expiryDate: "15/07/2025",
      remainingRefills: 0,
      status: "Expired"
    }
  ]);

  useEffect(() => {
    if (!user) {
      navigate("/login");
    }
  }, [user, navigate]);

  const handleLogout = () => {
    logout();
    navigate("/");
  };

  const filteredPrescriptions = prescriptions.filter(
    (prescription) =>
      prescription.medicationName.toLowerCase().includes(search.toLowerCase()) ||
      prescription.prescribedBy.toLowerCase().includes(search.toLowerCase())
  );

  const handleRequestRefill = (id) => {
    // In a real app, this would send a refill request to the backend
    alert(`Refill request sent for prescription ID: ${id}`);
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
            <li onClick={() => navigate("/patient-medical-records")}>Medical Records</li>
            <li onClick={() => navigate("/patient-telemedicine")}>Telemedicine</li>
            <li className="active">Prescriptions</li>
            <li onClick={() => navigate("/ai-chatbot")}>AI Chatbot</li>
          </ul>
        </div>
      </aside>
      <div className="main-content">
        <header className="topbar-PatientPrescriptions">
          <span>My Prescriptions</span>
          <button className="logout-btn" onClick={handleLogout}>
            Logout
          </button>
        </header>
        <section className="dashboard-section">
          <h2 className="page-title">My Prescriptions</h2>
          <div className="search-bar">
            <input
              type="text"
              placeholder="Search by medication or doctor..."
              value={search}
              onChange={(e) => setSearch(e.target.value)}
            />
            <button
              className="request-btn"
              onClick={() => navigate("/prescriptions/new-request")}
            >
              Request New Prescription
            </button>
          </div>
          <div className="table-container">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Medication</th>
                  <th>Dosage</th>
                  <th>Frequency</th>
                  <th>Prescribed By</th>
                  <th>Prescribed Date</th>
                  <th>Expiry Date</th>
                  <th>Refills Left</th>
                  <th>Status</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {filteredPrescriptions.map((prescription, i) => (
                  <tr key={i}>
                    <td>{prescription.id}</td>
                    <td>{prescription.medicationName}</td>
                    <td>{prescription.dosage}</td>
                    <td>{prescription.frequency}</td>
                    <td>{prescription.prescribedBy}</td>
                    <td>{prescription.prescribedDate}</td>
                    <td>{prescription.expiryDate}</td>
                    <td>{prescription.remainingRefills}</td>
                    <td>
                      <span 
                        className={`status-badge ${prescription.status.toLowerCase()}`}
                      >
                        {prescription.status}
                      </span>
                    </td>
                    <td className="actions">
                      <button
                        className="view-btn"
                        onClick={() => navigate(`/prescriptions/details/${prescription.id}`)}
                      >
                        View Details
                      </button>
                      {prescription.status === "Active" && prescription.remainingRefills > 0 && (
                        <button
                          className="refill-btn"
                          onClick={() => handleRequestRefill(prescription.id)}
                        >
                          Request Refill
                        </button>
                      )}
                      {prescription.status === "Active" && (
                        <button
                          className="print-btn"
                          onClick={() => alert(`Printing prescription: ${prescription.id}`)}
                        >
                          Print
                        </button>
                      )}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
          <div className="prescriptions-info">
            <h3>Prescription Information</h3>
            <p>
              Your prescriptions are listed here with their current status and details. Active prescriptions can 
              be refilled if refills are remaining. For any questions about your medications or if you experience 
              side effects, please contact your healthcare provider.
            </p>
            <div className="info-cards">
              <div className="info-card">
                <h4>Prescription Refills</h4>
                <ul>
                  <li>Request refills at least 7 days before you run out</li>
                  <li>Refill requests are typically processed within 48 hours</li>
                  <li>You'll receive a notification when your refill is ready</li>
                  <li>Controlled medications may require a doctor's appointment</li>
                </ul>
              </div>
              <div className="info-card">
                <h4>Medication Safety</h4>
                <ul>
                  <li>Take medications as prescribed</li>
                  <li>Store medications at recommended temperatures</li>
                  <li>Keep medications away from children</li>
                  <li>Discard expired medications safely</li>
                </ul>
              </div>
            </div>
          </div>
        </section>
      </div>
    </div>
  );
};

export default PatientPrescriptions;