import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import "../staff-styles/StaffPatientsRecords.css";

const StaffPatientsRecords = () => {
    const navigate = useNavigate();
    const { user, logout } = useAuth();

    const [searchTerm, setSearchTerm] = useState("");
    const [patients, setPatients] = useState([
        { name: "John Doe", age: 45, condition: "Diabetes", lastVisit: "June 15, 2024" },
        { name: "Jane Smith", age: 32, condition: "Hypertension", lastVisit: "May 28, 2024" },
        { name: "Alice Johnson", age: 29, condition: "Asthma", lastVisit: "April 10, 2024" },
    ]);
    const [editIndex, setEditIndex] = useState(null);
    const [editedPatient, setEditedPatient] = useState(null);

    useEffect(() => {
        if (!user) {
            navigate("/login");
        }
    }, [user, navigate]);

    const handleLogout = () => {
        logout();
        navigate("/");
    };

    const handleSearchChange = (e) => {
        setSearchTerm(e.target.value);
    };

    const filteredPatients = patients.filter((patient) =>
        patient.name.toLowerCase().includes(searchTerm.toLowerCase())
    );

    const startEditing = (index) => {
        setEditIndex(index);
        setEditedPatient({ ...patients[index] });
    };

    const cancelEditing = () => {
        setEditIndex(null);
        setEditedPatient(null);
    };

    const saveEdit = () => {
        const updatedPatients = [...patients];
        updatedPatients[editIndex] = editedPatient;
        setPatients(updatedPatients);
        setEditIndex(null);
        setEditedPatient(null);
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setEditedPatient((prev) => ({ ...prev, [name]: value }));
    };

    return (
        <div className="dashboard-container">
            <aside className="sidebar">
                <div className="logo">HMS</div>
                <div>
                    <p className="menu-title">Menu</p>
                    <ul>
                        <li onClick={() => navigate("/staff-dashboard")}>Dashboard</li>
                        <li onClick={() => navigate("/staff-admission")}>Admissions</li>
                        <li className="active">Patient Records</li>
                        <li onClick={() => navigate("/staff-billing")}>Billings</li>
                        <li onClick={() => navigate("/staff-inventory")}>Inventory</li>
                        <li onClick={() => navigate("/staff-rooms")}>Rooms</li>
                    </ul>
                </div>
            </aside>

            <div className="main-content">
                <header className="topbar-StaffPatientsRecords">
                    <span>Staff - Patient Records</span>
                    <button className="logout-btn" onClick={handleLogout}>Logout</button>
                </header>

                <section className="dashboard-section">
                    <h2 className="page-title">Patient Records</h2>

                    <div className="record-controls">
                        <input
                            type="text"
                            placeholder="Search Patient..."
                            className="search-box"
                            value={searchTerm}
                            onChange={handleSearchChange}
                        />
                        <button className="add-btn-DoctorPatientsRecords" onClick={() => navigate("/staff-add-new-patients-records")}>
                            Add New Record
                        </button>
                    </div>

                    <div className="table-container">
                        <h3 className="section-title">Patient History</h3>
                        <table>
                            <thead>
                                <tr>
                                    <th>Patient Name</th>
                                    <th>Age</th>
                                    <th>Condition</th>
                                    <th>Last Visit</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {filteredPatients.map((patient, index) => (
                                    <tr key={index}>
                                        {editIndex === index ? (
                                            <>
                                                <td>
                                                    <input
                                                        name="name"
                                                        value={editedPatient.name}
                                                        onChange={handleInputChange}
                                                    />
                                                </td>
                                                <td>
                                                    <input
                                                        name="age"
                                                        type="number"
                                                        value={editedPatient.age}
                                                        onChange={handleInputChange}
                                                    />
                                                </td>
                                                <td>
                                                    <input
                                                        name="condition"
                                                        value={editedPatient.condition}
                                                        onChange={handleInputChange}
                                                    />
                                                </td>
                                                <td>
                                                    <input
                                                        name="lastVisit"
                                                        value={editedPatient.lastVisit}
                                                        onChange={handleInputChange}
                                                    />
                                                </td>
                                                <td>
                                                    <button className="action-btn save-btn" onClick={saveEdit}>Save</button>
                                                    <button className="action-btn cancel-btn" onClick={cancelEditing}>Cancel</button>
                                                </td>
                                            </>
                                        ) : (
                                            <>
                                                <td>{patient.name}</td>
                                                <td>{patient.age}</td>
                                                <td>{patient.condition}</td>
                                                <td>{patient.lastVisit}</td>
                                                <td>
                                                    <button className="action-btn update-btn" onClick={() => startEditing(index)}>
                                                        Update
                                                    </button>
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

export default StaffPatientsRecords;
