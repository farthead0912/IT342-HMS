// src/components/AddTodoList.js
import React, { useState } from 'react';

const AddTodoList = ({ onAdd }) => {
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');

    const handleAdd = () => {
        if (title) {
            onAdd({ title, description });
            setTitle('');
            setDescription('');
        }
    };

    return (
        <div className="add-todo-list">
            <input 
                type="text" 
                value={title} 
                onChange={(e) => setTitle(e.target.value)} 
                placeholder="List Title"
            />
            <textarea 
                value={description} 
                onChange={(e) => setDescription(e.target.value)} 
                placeholder="Description"
            />
            <button onClick={handleAdd}>Add To-Do List</button>
        </div>
    );
};

export default AddTodoList;
