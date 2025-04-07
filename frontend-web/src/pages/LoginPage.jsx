import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../styles/LoginPage.css";

const LoginPage = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = (e) => {
    e.preventDefault();

    // Dummy staff credentials (you can replace this with actual API logic)
    if (username === "staff@example.com" && password === "123") {
      const user = {
        username,
        role: "staff", // role stored for protected route
      };

      // Save user to localStorage
      localStorage.setItem("user", JSON.stringify(user));

      // Redirect to staff dashboard
      navigate("/staff-dashboard");
    } else {
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
          <img
            src="/assets/login.jpg"
            alt="Healthcare professional"
          />
        </div>
      </div>
    </div>
  );
};

export default LoginPage;