import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import "../staff-styles/StaffRooms.css";

const StaffRooms = () => {
  const navigate = useNavigate();
  const { user, logout } = useAuth();

  const [rooms, setRooms] = useState([
    { id: 1, number: "101", type: "ICU", status: "Occupied" },
    { id: 2, number: "300", type: "Private", status: "Available" },
  ]);

  const [searchTerm, setSearchTerm] = useState("");
  const [editingRoomId, setEditingRoomId] = useState(null);
  const [editedRoom, setEditedRoom] = useState({
    number: "",
    type: "",
    status: "",
  });

  const totalRooms = 120;
  const occupiedRooms = 75;
  const availableRooms = 45;

  const handleLogout = () => {
    localStorage.removeItem("user");
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
    navigate("/staff-rooms/add");
  };

  const handleEditRoom = (roomId) => {
    const roomToEdit = rooms.find((room) => room.id === roomId);
    setEditingRoomId(roomId);
    setEditedRoom({ ...roomToEdit });
  };

  const handleSaveEdit = (roomId) => {
    const updatedRooms = rooms.map((room) =>
      room.id === roomId ? { ...room, ...editedRoom } : room
    );
    setRooms(updatedRooms);
    setEditingRoomId(null);
    setEditedRoom({ number: "", type: "", status: "" });
  };

  const handleCancelEdit = () => {
    setEditingRoomId(null);
    setEditedRoom({ number: "", type: "", status: "" });
  };

  const handleRemoveRoom = (roomId) => {
    setRooms(rooms.filter((room) => room.id !== roomId));
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
            <li onClick={() => navigate("/staff-patient-records")}>Patient Records</li>
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
          <button className="logout-btn" onClick={handleLogout}>
            Logout
          </button>
        </header>

        {/* Room Management Content */}
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
                    <td>
                      {editingRoomId === room.id ? (
                        <input
                          type="text"
                          value={editedRoom.number}
                          onChange={(e) =>
                            setEditedRoom({ ...editedRoom, number: e.target.value })
                          }
                        />
                      ) : (
                        room.number
                      )}
                    </td>
                    <td>
                      {editingRoomId === room.id ? (
                        <input
                          type="text"
                          value={editedRoom.type}
                          onChange={(e) =>
                            setEditedRoom({ ...editedRoom, type: e.target.value })
                          }
                        />
                      ) : (
                        room.type
                      )}
                    </td>
                    <td>
                      {editingRoomId === room.id ? (
                        <input
                          type="text"
                          value={editedRoom.status}
                          onChange={(e) =>
                            setEditedRoom({ ...editedRoom, status: e.target.value })
                          }
                        />
                      ) : (
                        room.status
                      )}
                    </td>
                    <td>
                      {editingRoomId === room.id ? (
                        <>
                          <button
                            className="action-btn save-btn"
                            onClick={() => handleSaveEdit(room.id)}
                          >
                            Save
                          </button>
                          <button
                            className="action-btn cancel-btn"
                            onClick={handleCancelEdit}
                          >
                            Cancel
                          </button>
                        </>
                      ) : (
                        <>
                          <button
                            className="action-btn update-btn"
                            onClick={() => handleEditRoom(room.id)}
                          >
                            Update
                          </button>
                          <button
                            className="action-btn remove-btn"
                            onClick={() => handleRemoveRoom(room.id)}
                          >
                            Remove
                          </button>
                        </>
                      )}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
};

export default StaffRooms;
