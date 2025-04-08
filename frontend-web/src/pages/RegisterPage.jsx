import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/RegisterPage.css";

const RegisterPage = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    specialization: "",
    password: "",
    department: "",
    role: ""
  });
  const [isStaffAuthorized, setIsStaffAuthorized] = useState(false);
  const [selectedRole, setSelectedRole] = useState("");
  const [isDropdownOpen, setIsDropdownOpen] = useState(false);

  // Check if user is authorized staff - in a real app, this would verify with your backend
  useEffect(() => {
    // Simulated authentication check - replace with your actual auth logic
    const checkStaffAuthorization = async () => {
      // This would be an API call in a real application
      const userRole = localStorage.getItem("userRole");
      setIsStaffAuthorized(userRole === "admin" || userRole === "staff");
    };

    checkStaffAuthorization();
  }, []);

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
    
    // Validate form
    if (!formData.firstName || !formData.lastName || !formData.email || 
        !formData.password || !selectedRole) {
      alert("Please fill in all required fields");
      return;
    }

    try {
      // This would be an API call in a real application
      console.log("Submitting registration data:", formData);
      
      // Simulate successful registration
      alert("Registration successful!");
      navigate("/login");
    } catch (error) {
      console.error("Registration failed:", error);
      alert("Registration failed. Please try again.");
    }
  };

  // Redirect or show message if not authorized
  if (!isStaffAuthorized) {
    return (
      <div className="unauthorized-container">
        <h2>Access Restricted</h2>
        <p>Only staff members can access the registration page.</p>
        <button className="auth-button" onClick={() => navigate("/login")}>
          Go to Login
        </button>
      </div>
    );
  }

  return (
    <div className="register-page-container">
      <div className="register-layout">
        <div className="left-panel">
          <div className="logo">LOGO</div>
          <div className="welcome-content">
            <h1>Welcome to Hospital Management System</h1>
            <p>
              A secure platform for managing patient records, doctor schedules, 
              bed availability, equipment usage, and billing processes.
            </p>
            
            <div className="role-selector">
              <h3>Register As:</h3>
              <div className="dropdown">
                <button 
                  className="dropdown-toggle" 
                  onClick={toggleDropdown}
                >
                  {selectedRole || "Pick an option"}
                </button>
                {isDropdownOpen && (
                  <div className="dropdown-menu">
                    <div 
                      className="dropdown-item" 
                      onClick={() => handleRoleSelect("Doctor")}
                    >
                      Doctor
                    </div>
                    <div 
                      className="dropdown-item" 
                      onClick={() => handleRoleSelect("Patient")}
                    >
                      Patient
                    </div>
                    <div 
                      className="dropdown-item" 
                      onClick={() => handleRoleSelect("Staff")}
                    >
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
                  <label htmlFor="firstName">First Name</label>
                  <input
                    type="text"
                    id="firstName"
                    name="firstName"
                    value={formData.firstName}
                    onChange={handleChange}
                    required
                  />
                </div>
                <div className="form-group">
                  <label htmlFor="lastName">Last Name</label>
                  <input
                    type="text"
                    id="lastName"
                    name="lastName"
                    value={formData.lastName}
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
                <label htmlFor="specialization">Specialization</label>
                <input
                  type="text"
                  id="specialization"
                  name="specialization"
                  value={formData.specialization}
                  onChange={handleChange}
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

              <div className="form-group">
                <label htmlFor="department">Department</label>
                <input
                  type="text"
                  id="department"
                  name="department"
                  value={formData.department}
                  onChange={handleChange}
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