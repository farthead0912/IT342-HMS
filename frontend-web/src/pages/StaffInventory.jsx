import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import "../styles/StaffInventory.css";

const StaffInventory = () => {
  const { user, logout } = useAuth();
  const navigate = useNavigate();
  const [searchTerm, setSearchTerm] = useState("");

  // Mock inventory data
  const [inventory, setInventory] = useState([
    { id: 1, name: "Surgical Masks", category: "Medical Supplies", quantity: 500, status: "In Stock" },
    { id: 2, name: "IV Fluids", category: "Pharmacy", quantity: 10, status: "Low Stock" },
    { id: 3, name: "Thermometers", category: "Equipment", quantity: 50, status: "In Stock" },
    { id: 4, name: "Bandages", category: "Medical Supplies", quantity: 200, status: "In Stock" },
    { id: 5, name: "Syringes", category: "Medical Supplies", quantity: 5, status: "Out of Stock" },
    { id: 6, name: "Antibiotics", category: "Medications", quantity: 0, status: "Out of Stock" }
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

  // Calculate inventory stats
  const totalItems = inventory.length;
  const lowStockItems = inventory.filter(item => item.status === "Low Stock").length;
  const outOfStockItems = inventory.filter(item => item.status === "Out of Stock").length;

  // Filter inventory based on search term
  const filteredInventory = inventory.filter(item =>
    item.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
    item.category.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="inventory-container">
      {/* Sidebar */}
      <aside className="sidebar">
        <div className="logo">HMS</div>
        <div>
          <p className="menu-title">Menu</p>
          <ul>
            <li onClick={() => navigate("/staff-dashboard")}>Dashboard</li>
            <li onClick={() => navigate("/staff-appointments")}>Appointments</li>
            <li onClick={() => navigate("/staff-billing")}>Billings</li>
            <li className="active">Inventory</li>
            <li onClick={() => navigate("/staff-rooms")}>Rooms</li>
          </ul>
        </div>
      </aside>

      {/* Main Content */}
      <div className="main-content">
        <header className="topbar">
          <span>Staff-Inventory-Page</span>
          <button className="logout-btn" onClick={handleLogout}>Logout</button>
        </header>

        <section className="inventory-section">
          <h2>Inventory Management</h2>

          {/* Inventory Summary Cards */}
          <div className="inventory-summary">
            <div className="summary-card">
              <h3>Total Items</h3>
              <p className="count">{totalItems}</p>
            </div>
            <div className="summary-card">
              <h3>Low Stock Items</h3>
              <p className="count low-stock">{lowStockItems}</p>
            </div>
            <div className="summary-card">
              <h3>Out of Stock</h3>
              <p className="count out-of-stock">{outOfStockItems}</p>
            </div>
          </div>

          {/* Search and Add New Item */}
          <div className="inventory-actions">
            <div className="search-container">
              <input
                type="text"
                placeholder="Search Inventory..."
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                className="search-input"
              />
            </div>
            <button className="add-item-btn">Add New Item</button>
          </div>

          {/* Inventory List Title */}
          <div className="inventory-list-header">
            <h3>Inventory List</h3>
          </div>

          {/* Inventory Table */}
          <div className="table-container">
            <table>
              <thead>
                <tr>
                  <th>Item Name</th>
                  <th>Category</th>
                  <th>Stock</th>
                  <th>Status</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                {filteredInventory.map(item => (
                  <tr key={item.id}>
                    <td>{item.name}</td>
                    <td>{item.category}</td>
                    <td>{item.quantity}</td>
                    <td className={`status ${item.status.replace(/\s+/g, '-').toLowerCase()}`}>
                      {item.status}
                    </td>
                    <td>
                      <button className="action-btn update">Update</button>
                      <button className="action-btn remove">Remove</button>
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

export default StaffInventory;