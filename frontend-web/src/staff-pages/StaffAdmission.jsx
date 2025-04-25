import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../staff-styles/StaffAdmission.css";

const StaffAdmission = () => {
  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    patient: "",
    date: "",
    time: "",
    doctor: "",
    reason: "",
    status: "pending",
  });

  const [admissions, setAdmissions] = useState([
    {
      patient: "Earl Owen V. Calzada",
      date: "2025-04-07",
      time: "9:00 AM",
      doctor: "Dr. Smith",
      reason: "High fever and weakness",
      status: "confirmed",
    },
    {
      patient: "Benjie Rivera Jr.",
      date: "2025-04-07",
      time: "10:30 AM",
      doctor: "Dr. Lopez",
      reason: "Follow-up for diabetes management",
      status: "pending",
    },
  ]);

  const handleLogout = () => {
    localStorage.removeItem("user");
    navigate("/");
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleAddAdmission = (e) => {
    e.preventDefault();
    setAdmissions([...admissions, formData]);
    setFormData({
      patient: "",
      date: "",
      time: "",
      doctor: "",
      reason: "",
      status: "pending",
    });
  };

  return (
    <div className="dashboard-container">
      <aside className="sidebar">
        <div className="logo">HMS</div>
        <div>
          <p className="menu-title">Menu</p>
          <ul>
            <li onClick={() => navigate("/staff-dashboard")}>Dashboard</li>
            <li className="active">Admissions</li>
            <li onClick={() => navigate("/staff-patient-records")}>Patient Records</li>
            <li onClick={() => navigate("/staff-billing")}>Billings</li>
            <li onClick={() => navigate("/staff-inventory")}>Inventory</li>
            <li onClick={() => navigate("/staff-rooms")}>Rooms</li>
          </ul>
        </div>
      </aside>

      <div className="main-content">
        <header className="topbar-StaffAddmision">
          <span>Staff Access Walk-in Patient Admission</span>
          <button className="logout-btn" onClick={handleLogout}>
            Logout
          </button>
        </header>

        <section className="dashboard-section">
          <h2>Today's Admissions</h2>

          <form className="filter-controls" onSubmit={handleAddAdmission}>
            <label>
              Patient Name:
              <input
                type="text"
                name="patient"
                value={formData.patient}
                onChange={handleChange}
                required
              />
            </label>
            <label>
              Date:
              <input
                type="date"
                name="date"
                value={formData.date}
                onChange={handleChange}
                required
              />
            </label>
            <label>
              Time:
              <input
                type="time"
                name="time"
                value={formData.time}
                onChange={handleChange}
                required
              />
            </label>
            <label>
              Doctor:
              <input
                type="text"
                name="doctor"
                value={formData.doctor}
                onChange={handleChange}
                required
              />
            </label>
            <label>
              Reason:
              <input
                type="text"
                name="reason"
                value={formData.reason}
                onChange={handleChange}
                placeholder="e.g. Chest pain, fever, check-up..."
                required
              />
            </label>
            <label>
              Status:
              <select
                name="status"
                value={formData.status}
                onChange={handleChange}
              >
                <option value="confirmed">Confirmed</option>
                <option value="pending">Pending</option>
                <option value="cancelled">Cancelled</option>
              </select>
            </label>
            <button className="filter-btn" type="submit">
              Add Admission
            </button>
          </form>

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

export default StaffAdmission;