// src/components/Footer.js
import React from 'react';

const Footer = () => {
    return (
        <footer className="footer">
            <p>&copy; {new Date().getFullYear()} My To-Do List App</p>
        </footer>
    );
};

export default Footer;
