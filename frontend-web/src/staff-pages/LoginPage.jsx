/*LoginPage.jsx*/

import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import "../staff-styles/LoginPage.css";
import loginImage from "../assets/login.jpg";

const LoginPage = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();
  const { login } = useAuth();

  const handleLogin = (e) => {
    e.preventDefault();
    console.log("Login attempt with:", username, password);

    // Dummy staff credentials
    if (username === "staff@example.com" && password === "123") {
      const user = { username, role: "staff" };
      login(user);
      console.log("Login successful, navigating to staff dashboard");
      navigate("/staff-dashboard");
    }

    // Dummy doctor credentials
    else if (username === "doctor@example.com" && password === "456") {
      const user = { username, role: "doctor" };
      login(user);
      console.log("Login successful, navigating to doctor dashboard");
      navigate("/doctor-dashboard");
    }

    // ✅ Dummy patient credentials
    else if (username === "patient@example.com" && password === "789") {
      const user = { username, role: "patient" };
      login(user);
      console.log("Login successful, navigating to patient dashboard");
      navigate("/patient-dashboard");
    }

    // ❌ Invalid credentials
    else {
      alert("Invalid credentials. Please try again.");
    }
  };

  return (
    <div className="login-container">
      <div className="login-card">
        <div className="login-form">
          <div className="form-group">
            <label htmlFor="username">Username</label>
            <input
              type="text"
              id="username"
              className="form-input"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>
          <div className="form-group">
            <label htmlFor="password">Password</label>
            <input
              type="password"
              id="password"
              className="form-input"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <div className="forgot-password">
            <span>Forgot your password? </span>
            <a href="#" className="contact-link">Contact Us</a>
          </div>
          <button onClick={handleLogin} className="login-btn">
            Log in
          </button>
        </div>
        <div className="healthcare-image">
          <img src={loginImage} alt="Login Healthcare" />
        </div>
      </div>
    </div>
  );
};

export default LoginPage;