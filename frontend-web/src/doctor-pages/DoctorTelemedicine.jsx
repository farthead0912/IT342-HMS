import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import "../doctor-styles/DoctorTelemedicine.css";

const DoctorTelemedicineRequests = () => {
  const navigate = useNavigate();
  const { user, logout } = useAuth();
  const [search, setSearch] = useState("");
  const [requests, setRequests] = useState([
    {
      name: "Earl Owen V. Calzada",
      age: 22,
      condition: "Diabetes",
      reason: "Routine checkup",
      preferredDate: "28/03/2025",
      preferredTime: "10:00 AM",
      status: "Pending", // New field for status
    },
    {
      name: "Benjie Rivera Jr.",
      age: 21,
      condition: "Hypertension",
      reason: "Consultation for blood pressure management",
      preferredDate: "27/03/2025",
      preferredTime: "02:00 PM",
      status: "Pending", // New field for status
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

  const filteredRequests = requests.filter((r) =>
    r.name.toLowerCase().includes(search.toLowerCase())
  );

  const handleConsultationApproval = (index) => {
    const updatedRequests = [...requests];
    updatedRequests[index].status = "Approved"; // Update the status to "Approved"
    setRequests(updatedRequests); // Update the state
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
          <span>Telemedicine – Second Opinion</span>
          <button className="logout-btn" onClick={handleLogout}>
            Logout
          </button>
        </header>

        <section className="dashboard-section">
          <h2 className="page-title">Second Opinion Requests</h2>

          <div className="search-bar">
            <input
              type="text"
              placeholder="Search Patient..."
              value={search}
              onChange={(e) => setSearch(e.target.value)}
            />
            <button
              className="new-request-btn-Telemedicine"
              onClick={() => navigate("/doctor-telemedicine/new-request")}
            >
              New Request
            </button>
          </div>

          <div className="table-container">
            <table>
              <thead>
                <tr>
                  <th>Patient Name</th>
                  <th>Age</th>
                  <th>Condition</th>
                  <th>Reason for Consultation</th>
                  <th>Preferred Date</th>
                  <th>Preferred Time</th>
                  <th>Consultation Status</th> {/* Added column for Consultation Status */}
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {filteredRequests.map((r, i) => (
                  <tr key={i}>
                    <td>{r.name}</td>
                    <td>{r.age}</td>
                    <td>{r.condition}</td>
                    <td>{r.reason}</td>
                    <td>{r.preferredDate}</td>
                    <td>{r.preferredTime}</td>
                    <td>{r.status}</td> {/* Display status */}
                    <td className="actions">
                      <button
                        onClick={() => {
                          handleConsultationApproval(i); // Update status on click
                          navigate("/doctor-consultation");
                        }}
                      >
                        Start Consultation
                      </button>
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

export default DoctorTelemedicineRequests;