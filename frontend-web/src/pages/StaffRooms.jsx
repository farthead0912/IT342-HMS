import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import "../styles/StaffRooms.css";

const StaffRooms = () => {
  const navigate = useNavigate();
  const { user, logout } = useAuth();

  const [rooms, setRooms] = useState([
    { id: 1, number: "101", type: "ICU", status: "Occupied" },
    { id: 2, number: "300", type: "Private", status: "Available" },
  ]);

  const [searchTerm, setSearchTerm] = useState("");

  const totalRooms = 120;
  const occupiedRooms = 75;
  const availableRooms = 45;

  const handleLogout = () => {
    logout();
    navigate("/");
  };

  const handleSearch = (e) => {
    setSearchTerm(e.target.value);
  };

  const filteredRooms = rooms.filter((room) =>
    [room.number, room.type, room.status]
      .join(" ")
      .toLowerCase()
      .includes(searchTerm.toLowerCase())
  );

  const handleAddRoom = () => {
    const newRoom = {
      id: rooms.length + 1,
      number: prompt("Enter Room Number:"),
      type: prompt("Enter Room Type:"),
      status: prompt("Enter Status (Available/Occupied):"),
    };
    if (newRoom.number && newRoom.type && newRoom.status) {
      setRooms([...rooms, newRoom]);
    }
  };

  const handleUpdateRoom = (roomId) => {
    const updated = rooms.map((room) =>
      room.id === roomId
        ? {
            ...room,
            number: prompt("Update Room Number:", room.number),
            type: prompt("Update Room Type:", room.type),
            status: prompt("Update Status:", room.status),
          }
        : room
    );
    setRooms(updated);
  };

  const handleRemoveRoom = (roomId) => {
    const confirmDelete = window.confirm("Are you sure to delete this room?");
    if (confirmDelete) {
      setRooms(rooms.filter((room) => room.id !== roomId));
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
            <li onClick={() => navigate("/staff-addmision")}>Addmission</li>
            <li onClick={() => navigate("/staff-billing")}>Billings</li>
            <li onClick={() => navigate("/staff-inventory")}>Inventory</li>
            <li className="active">Rooms</li>
          </ul>
        </div>
      </aside>

      {/* Main Content */}
      <div className="main-content">
        <header className="topbar-StaffAddmision">
          <span>Staff Rooms Page</span>
          <button className="logout-btn" onClick={handleLogout}>Logout</button>
        </header>

        <section className="room-management-section">
          <h2>Room Management</h2>

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
                        <button className="action-btn update-btn" onClick={() => handleUpdateRoom(room.id)}>Update</button>
                        <button className="action-btn remove-btn" onClick={() => handleRemoveRoom(room.id)}>Remove</button>
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