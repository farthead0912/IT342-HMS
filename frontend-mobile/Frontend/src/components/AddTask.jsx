import React, { useState } from 'react';

const AddTask = ({ onAdd }) => {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [taskTitle, setTaskTitle] = useState('');
    const [taskDescription, setTaskDescription] = useState('');
    const [dueDate, setDueDate] = useState('');

    const handleAdd = () => {
        if (taskTitle) {
            onAdd({ 
                title: taskTitle, 
                description: taskDescription, 
                dueDate,
                createdAt: new Date().toISOString() // Capture the creation date
            });
            setTaskTitle('');
            setTaskDescription('');
            setDueDate('');
            setIsModalOpen(false); // Close the modal after adding
        }
    };

    return (
        <div className="add-task">
            <button onClick={() => setIsModalOpen(true)}>Add Task</button>

            {isModalOpen && (
                <div className="modal">
                    <div className="modal-content">
                        <h2>Add Task</h2>
                        <input 
                            type="text" 
                            value={taskTitle} 
                            onChange={(e) => setTaskTitle(e.target.value)} 
                            placeholder="Task Title"
                        />
                        <textarea 
                            value={taskDescription} 
                            onChange={(e) => setTaskDescription(e.target.value)} 
                            placeholder="Task Description"
                        />
                        <input 
                            type="datetime-local" 
                            value={dueDate} 
                            onChange={(e) => setDueDate(e.target.value)} 
                        />
                        <button onClick={handleAdd}>Submit</button>
                        <button onClick={() => setIsModalOpen(false)}>Cancel</button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default AddTask;
