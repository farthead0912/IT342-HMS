import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import "../styles/StaffRooms.css";

const StaffRooms = () => {
  const navigate = useNavigate();
  const { user, logout } = useAuth();
  
  // Sample room data
  const [rooms, setRooms] = useState([
    { id: 1, number: "101", type: "ICU", status: "Occupied" },
    { id: 2, number: "300", type: "Private", status: "Available" },
    // Add more rooms as needed
  ]);
  
  const [searchTerm, setSearchTerm] = useState("");
  
  // Total rooms count
  const totalRooms = 120;
  const occupiedRooms = 75;
  const availableRooms = 35;
  
  const handleLogout = () => {
    logout();
    navigate("/");
  };
  
  const handleSearch = (e) => {
    setSearchTerm(e.target.value);
  };
  
  const filteredRooms = rooms.filter(room => 
    room.number.toLowerCase().includes(searchTerm.toLowerCase()) ||
    room.type.toLowerCase().includes(searchTerm.toLowerCase()) ||
    room.status.toLowerCase().includes(searchTerm.toLowerCase())
  );
  
  const handleAddRoom = () => {
    // Add logic to open a modal or navigate to add room form
    console.log("Add new room clicked");
  };
  
  const handleUpdateOrRemove = (roomId, action) => {
    console.log(`${action} room with ID: ${roomId}`);
    // Add logic for updating or removing rooms
  };

  return (
    <div className="dashboard-container">
      {/* Sidebar */}
      <aside className="sidebar">
        <div className="logo">LOGO</div>
        <div>
          <p className="menu-title">Menu</p>
          <ul>
            <li onClick={() => navigate("/staff-dashboard")}>Dashboard</li>
            <li onClick={() => navigate("/staff-appointments")}>Appointments</li>
            <li onClick={() => navigate("/staff-billing")}>Billings</li>
            <li onClick={() => navigate("/staff-inventory")}>Inventory</li>
            <li className="active">Rooms</li>
          </ul>
        </div>
      </aside>

      {/* Main Content */}
      <div className="main-content">
        {/* Top Bar */}
        <header className="topbar">
          <span>Staff-Rooms-Page</span>
          <button className="logout-btn" onClick={handleLogout}>Logout</button>
        </header>

        {/* Room Management Content */}
        <section className="room-management-section">
          <h2>Room Management</h2>

          {/* Room Statistics Cards */}
          <div className="room-stats-cards">
            <div className="room-card">
              <h3>Total Rooms</h3>
              <p className="room-count">{totalRooms}</p>
            </div>
            <div className="room-card">
              <h3>Occupied Rooms</h3>
              <p className="room-count occupied">{occupiedRooms}</p>
            </div>
            <div className="room-card">
              <h3>Available Rooms</h3>
              <p className="room-count available">{availableRooms}</p>
            </div>
          </div>

          {/* Search and Add Room */}
          <div className="room-actions">
            <input 
              type="text" 
              placeholder="Search Room..." 
              value={searchTerm}
              onChange={handleSearch}
              className="search-input"
            />
            <button className="add-room-btn" onClick={handleAddRoom}>
              Add New Room
            </button>
          </div>

          {/* Room List */}
          <div className="room-list-container">
            <h3 className="room-list-title">Room List</h3>
            <div className="table-container">
              <table className="room-table">
                <thead>
                  <tr>
                    <th>#</th>
                    <th>Room Number</th>
                    <th>Type</th>
                    <th>Status</th>
                    <th>Actions</th>
                  </tr>
                </thead>
                <tbody>
                  {filteredRooms.map((room, index) => (
                    <tr key={room.id}>
                      <td>{index + 1}</td>
                      <td>{room.number}</td>
                      <td>{room.type}</td>
                      <td className={room.status.toLowerCase() === "available" ? "status-available" : "status-occupied"}>
                        {room.status}
                      </td>
                      <td>
                        <button 
                          className="action-btn"
                          onClick={() => handleUpdateOrRemove(room.id, "Update")}
                        >
                          Update or Remove
                        </button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        </section>
      </div>
    </div>
  );
};

export default StaffRooms;