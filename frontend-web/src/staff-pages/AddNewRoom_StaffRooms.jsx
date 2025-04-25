import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import "../staff-styles/AddNewRoom_StaffRooms.css";

const AddNewRoom_StaffRooms = () => {
  const navigate = useNavigate();
  const { logout } = useAuth();

  const [roomNumber, setRoomNumber] = useState("");
  const [roomType, setRoomType] = useState("");
  const [roomStatus, setRoomStatus] = useState("Available");

  const handleLogout = () => {
    logout();
    navigate("/");
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const newRoom = {
      number: roomNumber,
      type: roomType,
      status: roomStatus,
    };
    console.log("New Room to be added:", newRoom);
    // You can redirect or pass this data to a backend
    navigate("/staff-rooms");
  };

  // Function to get status badge class
  const getStatusClass = () => {
    switch (roomStatus) {
      case "Available":
        return "status-badge status-available";
      case "Occupied":
        return "status-badge status-occupied";
      default:
        return "status-badge";
    }
  };

  return (
    <div className="dashboard-container">
      {/* Sidebar */}
      <aside className="sidebar">
        <div className="logo">HMS</div>
        <div>
          <p className="menu-title">Menu</p>
          <ul>
            <li onClick={() => navigate("/staff-dashboard")}>Dashboard</li>
            <li onClick={() => navigate("/staff-admission")}>Admissions</li>
            <li onClick={() => navigate("/staff-billing")}>Billings</li>
            <li onClick={() => navigate("/staff-inventory")}>Inventory</li>
            <li className="active">Rooms</li>
          </ul>
        </div>
      </aside>

      {/* Main Content */}
      <div className="main-content">
        <header className="topbar-StaffAddmision">
          <span>Add New Room</span>
          <button className="logout-btn" onClick={handleLogout}>Logout</button>
        </header>

        <section className="room-management-section">
          <h2>Add Room Details</h2>
          <form onSubmit={handleSubmit} className="add-room-form">
            <div className="form-group">
              <label>Room Number</label>
              <input
                type="text"
                value={roomNumber}
                onChange={(e) => setRoomNumber(e.target.value)}
                required
              />
            </div>
            <div className="form-group">
              <label>Room Type</label>
              <input
                type="text"
                value={roomType}
                onChange={(e) => setRoomType(e.target.value)}
                required
              />
            </div>
            <div className="form-group">
              <label>Status</label>
              <div className="status-wrapper">
                <select
                  value={roomStatus}
                  onChange={(e) => setRoomStatus(e.target.value)}
                >
                  <option value="Available">Available</option>
                  <option value="Occupied">Occupied</option>
                </select>
                <span className={getStatusClass()}>{roomStatus}</span>
              </div>
            </div>
            <button type="submit" className="add-room-btn">Save Room</button>
          </form>
        </section>
      </div>
    </div>
  );
};

export default AddNewRoom_StaffRooms;
