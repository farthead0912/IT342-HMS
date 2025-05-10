import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import "../staff-styles/AddNewItem_StaffInventory.css";

const AddNewItem_StaffInventory = ({ onAddItem }) => {
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  const [name, setName] = useState("");
  const [category, setCategory] = useState("");
  const [quantity, setQuantity] = useState("");
  const [status, setStatus] = useState("In Stock");

  const handleLogout = () => {
    logout();
    navigate("/");
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onAddItem?.({ name, category, quantity: parseInt(quantity, 10), status });
    navigate("/staff-inventory");
  };

  const getStatusClass = () => {
    switch (status) {
      case "In Stock":
        return "status-badge status-in-stock";
      case "Low Stock":
        return "status-badge status-low-stock";
      case "Out of Stock":
        return "status-badge status-out-of-stock";
      default:
        return "status-badge";
    }
  };

  return (
    <div className="inventory-container">
      <aside className="sidebar">
        <div className="logo">HMS</div>
        <p className="menu-title">Menu</p>
        <ul>
          <li onClick={() => navigate("/staff-dashboard")}>Dashboard</li>
          <li onClick={() => navigate("/staff-admission")}>Admissions</li>
          <li onClick={() => navigate("/staff-billing")}>Billings</li>
          <li onClick={() => navigate("/staff-inventory")}>Inventory</li>
          <li onClick={() => navigate("/staff-rooms")}>Rooms</li>
        </ul>
      </aside>

      <div className="main-content">
        <header className="topbar-AddNewItem">
          <span>Add New Inventory Item</span>
          <button className="logout-btn" onClick={handleLogout}>
            Logout
          </button>
        </header>

        <section className="add-item-section">
          <h2>Add New Item</h2>
          <form onSubmit={handleSubmit} className="add-item-form">
            <div className="form-group">
              <label htmlFor="name">Item Name</label>
              <input
                id="name"
                type="text"
                value={name}
                onChange={(e) => setName(e.target.value)}
                required
              />
            </div>
            <div className="form-group">
              <label htmlFor="category">Category</label>
              <input
                id="category"
                type="text"
                value={category}
                onChange={(e) => setCategory(e.target.value)}
                required
              />
            </div>
            <div className="form-group">
              <label htmlFor="quantity">Quantity</label>
              <input
                id="quantity"
                type="number"
                value={quantity}
                onChange={(e) => setQuantity(e.target.value)}
                required
              />
            </div>
            <div className="form-group">
              <label htmlFor="status">Stock Status</label>
              <div className="status-wrapper">
                <select
                  id="status"
                  value={status}
                  onChange={(e) => setStatus(e.target.value)}
                >
                  <option>In Stock</option>
                  <option>Low Stock</option>
                  <option>Out of Stock</option>
                </select>
                <span className={getStatusClass()}>{status}</span>
              </div>
            </div>
            <button type="submit" className="submit-btn">
              Add Item
            </button>
          </form>
        </section>
      </div>
    </div>
  );
};

export default AddNewItem_StaffInventory;