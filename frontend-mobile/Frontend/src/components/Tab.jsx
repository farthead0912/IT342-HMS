// src/components/Tab.js
import React from 'react';

const Tab = ({ label, isActive, onClick }) => {
    return (
        <div 
            className={`tab ${isActive ? 'active' : ''}`} 
            onClick={onClick}
        >
            {label}
        </div>
    );
};

export default Tab;
