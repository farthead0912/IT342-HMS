import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "../staff-styles/RegisterPage.css";

const RegisterPage = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    username: "",
    email: "",
    password: "",
    role: ""
  });
  const [selectedRole, setSelectedRole] = useState("");
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleRoleSelect = (role) => {
    setSelectedRole(role);
    setFormData({ ...formData, role });
    setIsDropdownOpen(false);
  };

  const toggleDropdown = () => {
    setIsDropdownOpen(!isDropdownOpen);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!formData.username || !formData.email || !formData.password || !selectedRole) {
      alert("Please fill in all required fields");
      return;
    }

    try {
      const response = await axios.post(
        "https://it342-hms-medisync.onrender.com/users/register",
        {
          username: formData.username,
          email: formData.email,
          password: formData.password,
          role: formData.role,
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      alert("Registration successful!");
      navigate("/login");
    } catch (error) {
      console.error("Registration failed:", error.response?.data || error.message);
      alert("Registration failed. Please try again.");
    }
  };

  return (
    <div className="register-page-container">
      <div className="register-layout">
        <div className="left-panel">
          <div className="logo">HMS</div>
          <div className="welcome-content">
            <h1>Welcome to Hospital Management System</h1>
            <p>
              A secure platform for managing patient records, doctor schedules,
              bed availability, equipment usage, and billing processes.
            </p>

            <div className="role-selector">
              <h3>Register As:</h3>
              <div className="dropdown">
                <button className="dropdown-toggle" onClick={toggleDropdown}>
                  {selectedRole || "Pick an option"}
                </button>
                {isDropdownOpen && (
                  <div className="dropdown-menu">
                    <div className="dropdown-item" onClick={() => handleRoleSelect("Doctor")}>
                      Doctor
                    </div>
                    <div className="dropdown-item" onClick={() => handleRoleSelect("Patient")}>
                      Patient
                    </div>
                    <div className="dropdown-item" onClick={() => handleRoleSelect("Staff")}>
                      Staff
                    </div>
                  </div>
                )}
              </div>
            </div>
          </div>
        </div>

        <div className="right-panel">
          <div className="register-form-container">
            <h2>Register Now</h2>
            <form onSubmit={handleSubmit} className="register-form">
              <div className="form-row">
                <div className="form-group">
                  <label htmlFor="username">Username</label>
                  <input
                    type="text"
                    id="username"
                    name="username"
                    value={formData.username}
                    onChange={handleChange}
                    required
                  />
                </div>
              </div>

              <div className="form-group">
                <label htmlFor="email">Email address</label>
                <input
                  type="email"
                  id="email"
                  name="email"
                  value={formData.email}
                  onChange={handleChange}
                  required
                />
              </div>

              <div className="form-group">
                <label htmlFor="password">Password</label>
                <input
                  type="password"
                  id="password"
                  name="password"
                  value={formData.password}
                  onChange={handleChange}
                  required
                />
              </div>

              <button type="submit" className="register-button">
                Register
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default RegisterPage;
