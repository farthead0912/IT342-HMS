import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import "../staff-styles/StaffInventory.css";

const StaffInventory = () => {
  const { user, logout } = useAuth();
  const navigate = useNavigate();
  const [searchTerm, setSearchTerm] = useState("");
  const [editingItem, setEditingItem] = useState(null);
  const [editValues, setEditValues] = useState({ name: "", category: "", quantity: 0, status: "" });

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
    localStorage.removeItem("user");
    navigate("/");
  };
  
  const totalItems = inventory.length;
  const lowStockItems = inventory.filter(item => item.status === "Low Stock").length;
  const outOfStockItems = inventory.filter(item => item.status === "Out of Stock").length;

  const filteredInventory = inventory.filter(item =>
    item.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
    item.category.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const startEdit = (item) => {
    setEditingItem(item.id);
    setEditValues({ name: item.name, category: item.category, quantity: item.quantity, status: item.status });
  };

  const saveEdit = (id) => {
    setInventory(prev =>
      prev.map(item =>
        item.id === id ? { ...item, ...editValues } : item
      )
    );
    setEditingItem(null);
  };

  const cancelEdit = () => {
    setEditingItem(null);
  };

  const removeItem = (id) => {
    setInventory(prev => prev.filter(item => item.id !== id));
  };

  return (
    <div className="inventory-container">
      <aside className="sidebar">
        <div className="logo">HMS</div>
        <div>
          <p className="menu-title">Menu</p>
          <ul>
            <li onClick={() => navigate("/staff-dashboard")}>Dashboard</li>
            <li onClick={() => navigate("/staff-admission")}>Admissions</li>
            <li onClick={() => navigate("/staff-patient-records")}>Patient Records</li>
            <li onClick={() => navigate("/staff-billing")}>Billings</li>
            <li className="active">Inventory</li>
            <li onClick={() => navigate("/staff-rooms")}>Rooms</li>
          </ul>
        </div>
      </aside>

      <div className="main-content">
        <header className="topbar-StaffInventory">
          <span>Staff Inventory Page</span>
          <button className="logout-btn" onClick={handleLogout}>Logout</button>
        </header>

        <section className="inventory-section">
          <h2>Inventory Management</h2>

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
            <button className="add-item-btn" onClick={() => navigate("/add-new-item")}>Add New Item</button>
          </div>

          <div className="inventory-list-header">
            <h3>Inventory List</h3>
          </div>

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
                    {editingItem === item.id ? (
                      <>
                        <td>
                          <input
                            value={editValues.name}
                            onChange={e => setEditValues({ ...editValues, name: e.target.value })}
                          />
                        </td>
                        <td>
                          <input
                            value={editValues.category}
                            onChange={e => setEditValues({ ...editValues, category: e.target.value })}
                          />
                        </td>
                        <td>
                          <input
                            type="number"
                            value={editValues.quantity}
                            onChange={e => setEditValues({ ...editValues, quantity: parseInt(e.target.value) })}
                          />
                        </td>
                        <td>
                          <select
                            value={editValues.status}
                            onChange={e => setEditValues({ ...editValues, status: e.target.value })}
                          >
                            <option>In Stock</option>
                            <option>Low Stock</option>
                            <option>Out of Stock</option>
                          </select>
                        </td>
                        <td>
                          <button className="action-btn save-btn" onClick={() => saveEdit(item.id)}>Save</button>
                          <button className="action-btn cancel-btn" onClick={cancelEdit}>Cancel</button>
                        </td>
                      </>
                    ) : (
                      <>
                        <td>{item.name}</td>
                        <td>{item.category}</td>
                        <td>{item.quantity}</td>
                        <td className={`status ${item.status.replace(/\s+/g, '-').toLowerCase()}`}>
                          {item.status}
                        </td>
                        <td>
                          <button className="action-btn update" onClick={() => startEdit(item)}>Update</button>
                          <button className="action-btn remove" onClick={() => removeItem(item.id)}>Remove</button>
                        </td>
                      </>
                    )}
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
