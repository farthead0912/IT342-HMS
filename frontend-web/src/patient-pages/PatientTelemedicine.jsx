// src/pages/PatientTelemedicine.jsx
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import "../patient-styles/PatientTelemedicine.css";

const PatientTelemedicine = () => {
  const navigate = useNavigate();
  const { user, logout } = useAuth();
  const [search, setSearch] = useState("");
  const [telemedicineAppointments, setTelemedicineAppointments] = useState([
    {
      doctorName: "Dr. Sarah Johnson",
      specialization: "Cardiology",
      date: "28/03/2025",
      time: "10:00 AM",
      reason: "Routine checkup",
      status: "Scheduled",
    },
    {
      doctorName: "Dr. Michael Chen",
      specialization: "Endocrinology",
      date: "15/04/2025",
      time: "02:30 PM",
      reason: "Diabetes follow-up",
      status: "Pending Approval",
    },
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

  const filteredAppointments = telemedicineAppointments.filter((appointment) =>
    appointment.doctorName.toLowerCase().includes(search.toLowerCase())
  );

  const handleJoinSession = (index) => {
    // In a real app, this would navigate to the video consultation
    alert(`Joining telemedicine session with ${telemedicineAppointments[index].doctorName}`);
    navigate("/telemedicine-session");
  };

  const handleCancelAppointment = (index) => {
    const confirmed = window.confirm("Are you sure you want to cancel this appointment?");
    if (confirmed) {
      const updatedAppointments = [...telemedicineAppointments];
      updatedAppointments[index].status = "Cancelled";
      setTelemedicineAppointments(updatedAppointments);
    }
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
            <li className="active">Telemedicine</li>
            <li onClick={() => navigate("/patient-prescriptions")}>Prescriptions</li>
            <li onClick={() => navigate("/ai-chatbot")}>AI Chatbot</li>
          </ul>
        </div>
      </aside>

      <div className="main-content">
        <header className="topbar-PatientTelemedicine">
          <span>Patient Telemedicine Services</span>
          <button className="logout-btn" onClick={handleLogout}>
            Logout
          </button>
        </header>

        <section className="dashboard-section">
          <h2 className="page-title">My Telemedicine Appointments</h2>

          <div className="search-bar">
            <input
              type="text"
              placeholder="Search Doctor..."
              value={search}
              onChange={(e) => setSearch(e.target.value)}
            />
            <button
              className="new-appointment-btn"
              onClick={() => navigate("/telemedicine/new-appointment")}
            >
              Request New Appointment
            </button>
          </div>

          <div className="table-container">
            <table>
              <thead>
                <tr>
                  <th>Doctor Name</th>
                  <th>Specialization</th>
                  <th>Date</th>
                  <th>Time</th>
                  <th>Reason for Visit</th>
                  <th>Status</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {filteredAppointments.map((appointment, i) => (
                  <tr key={i}>
                    <td>{appointment.doctorName}</td>
                    <td>{appointment.specialization}</td>
                    <td>{appointment.date}</td>
                    <td>{appointment.time}</td>
                    <td>{appointment.reason}</td>
                    <td>
                      <span className={`status-badge ${appointment.status.toLowerCase().replace(' ', '-')}`}>
                        {appointment.status}
                      </span>
                    </td>
                    <td className="actions">
                      {appointment.status === "Scheduled" && (
                        <>
                          <button
                            className="join-btn"
                            onClick={() => handleJoinSession(i)}
                          >
                            Join Session
                          </button>
                          <button
                            className="reschedule-btn"
                            onClick={() => navigate(`/telemedicine/reschedule/${i}`)}
                          >
                            Reschedule
                          </button>
                          <button
                            className="cancel-btn"
                            onClick={() => handleCancelAppointment(i)}
                          >
                            Cancel
                          </button>
                        </>
                      )}
                      {appointment.status === "Pending Approval" && (
                        <button
                          className="cancel-btn"
                          onClick={() => handleCancelAppointment(i)}
                        >
                          Cancel Request
                        </button>
                      )}
                      {appointment.status === "Cancelled" && (
                        <button
                          className="reschedule-btn"
                          onClick={() => navigate(`/telemedicine/reschedule/${i}`)}
                        >
                          Reschedule
                        </button>
                      )}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>

          <div className="telemedicine-info">
            <h3>About Telemedicine</h3>
            <p>
              Our telemedicine service allows you to consult with healthcare professionals from the comfort of your home. 
              Virtual appointments are conducted through secure video conferencing and are suitable for many types of 
              medical consultations, including follow-ups, prescription renewals, and non-emergency health concerns.
            </p>
            <div className="info-cards">
              <div className="info-card">
                <h4>Before Your Appointment</h4>
                <ul>
                  <li>Test your camera and microphone</li>
                  <li>Prepare a list of symptoms or questions</li>
                  <li>Find a quiet, private space</li>
                  <li>Have your medications list ready</li>
                </ul>
              </div>
              <div className="info-card">
                <h4>Technical Requirements</h4>
                <ul>
                  <li>Stable internet connection</li>
                  <li>Device with camera and microphone</li>
                  <li>Updated browser (Chrome, Firefox, Safari)</li>
                  <li>Headphones (recommended)</li>
                </ul>
              </div>
            </div>
          </div>
        </section>
      </div>
    </div>
  );
};

export default PatientTelemedicine;