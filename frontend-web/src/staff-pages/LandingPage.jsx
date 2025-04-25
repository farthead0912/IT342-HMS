import React, { Suspense, useState, useEffect } from "react";
import { Link } from "react-router-dom";
import "../staff-styles/index.css"; // Ensure styles are applied

const LandingPage = () => {
  const [isVisible, setIsVisible] = useState(true);
  const [lastScrollY, setLastScrollY] = useState(0);

  useEffect(() => {
    const handleScroll = () => {
      const scrollY = window.scrollY;
      setIsVisible(scrollY < lastScrollY); // Show navbar/footer when scrolling up
      setLastScrollY(scrollY);
    };

    window.addEventListener("scroll", handleScroll);
    return () => window.removeEventListener("scroll", handleScroll);
  }, [lastScrollY]);

  return (
    <div className="landing-container">
      {/* Navigation Bar */}
      <nav className={`navbar ${isVisible ? "show" : "hide"}`}>
        <ul className="nav-links">
          <li><Link to="/about">About Us</Link></li>
          <li><Link to="/services">Services</Link></li>
          <li><Link to="/contact">Contact Us</Link></li>
          <li><Link to="/login" className="login-btn">Login</Link></li>
          <li><Link to="/register" className="register-btn">Register</Link></li>
        </ul>
      </nav>

      {/* Hero Section */}
      <div className="hero-section">
        <div className="hero-content">
          <h1><span className="highlight">MediSync</span>
          </h1>
          <h2 className="tagline">Providing Quality Healthcare</h2>
          <p>Advanced technology and expert care at your service</p>
          <div className="cta-buttons">
            <Link to="/services" className="cta-button">Explore Services</Link>
            <Link to="/register" className="cta-button register-cta">Register Now</Link>
          </div>
        </div>
      </div>

      {/* Footer */}
      <footer className={`footer ${isVisible ? "show" : "hide"}`}>
        <p>Hospital Management System</p>
      </footer>
    </div>
  );
};

// Lazy load component for performance optimization
export default function LazyLanding() {
  return (
    <Suspense fallback={<div className="loading">Loading...</div>}>
      <LandingPage />
    </Suspense>
  );
}
