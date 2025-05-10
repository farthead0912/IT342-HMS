/* DoctorAdmission.jsx */

import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../doctor-styles/DoctorAdmission.css";

const DoctorAdmission = () => {
  const navigate = useNavigate();

  const [admissions, setAdmissions] = useState([
    {
      patient: "Earl Owen V. Calzada",
      date: "2025-04-21",
      time: "9:00 AM",
      doctor: "Dr. Smith",
      reason: "High fever and weakness",
      status: "confirmed",
    },
    {
      patient: "Benjie Rivera Jr.",
      date: "2025-04-07",
      time: "10:30 AM",
      doctor: "Dr. Adams",
      reason: "Follow-up for diabetes management",
      status: "pending",
    },
    {
      patient: "Jake Williams",
      date: "2025-04-21",
      time: "11:00 AM",
      doctor: "Dr. Smith",
      reason: "Fever",
      status: "pending",
    },
  ]);

  const handleLogout = () => {
    localStorage.removeItem("user");
    navigate("/");
  };

  const updateStatus = (index, newStatus) => {
    const updatedAdmissions = [...admissions];
    updatedAdmissions[index].status = newStatus;
    setAdmissions(updatedAdmissions);

    // Show a pop-up notification
    if (newStatus === "confirmed") {
      alert(`Admission for ${updatedAdmissions[index].patient} has been confirmed.`);
    } else if (newStatus === "cancelled") {
      alert(`Admission for ${updatedAdmissions[index].patient} has been cancelled.`);
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
            <li className="active">Admissions</li>
            <li onClick={() => navigate("/doctor-patient-records")}>Patient Records</li>
            <li onClick={() => navigate("/doctor-telemedicine")}>Telemedicine</li>
            <li onClick={() => navigate("/doctor-prescriptions")}>Prescriptions</li>
          </ul>
        </div>
      </aside>

      <div className="main-content">
        <header className="topbar-DoctorAdmission">
          <span>Doctor Walk-in Patient Admission</span>
          <button className="logout-btn" onClick={handleLogout}>
            Logout
          </button>
        </header>

        <section className="dashboard-section">
          <h2>Today's Admissions</h2>

          <div className="table-container">
            <table>
              <thead>
                <tr>
                  <th>Patient</th>
                  <th>Date</th>
                  <th>Time</th>
                  <th>Doctor</th>
                  <th>Reason</th>
                  <th>Status</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
                {admissions.map((entry, index) => (
                  <tr key={index}>
                    <td>{entry.patient}</td>
                    <td>{entry.date}</td>
                    <td>{entry.time}</td>
                    <td>{entry.doctor}</td>
                    <td>{entry.reason}</td>
                    <td
                      className={
                        entry.status === "confirmed"
                          ? "status-confirmed"
                          : entry.status === "pending"
                          ? "status-pending"
                          : "status-cancelled"
                      }
                    >
                      {entry.status.charAt(0).toUpperCase() + entry.status.slice(1)}
                    </td>
                    <td>
                      {entry.status === "pending" && (
                        <div className="button-container">
                          <button
                            className="status-btn confirmed-btn"
                            onClick={() => updateStatus(index, "confirmed")}
                          >
                            Confirm
                          </button>
                          <button
                            className="status-btn cancelled-btn"
                            onClick={() => updateStatus(index, "cancelled")}
                          >
                            Cancel
                          </button>
                        </div>
                      )}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </section>
      </div>
    </div>
  );
};

export default DoctorAdmission;