    // src/components/TodoItem.js
    import React from 'react';

    const TodoItem = ({ task, description, completed, onToggle }) => {
        return (
            <div className="todo-item">
                <input 
                    type="checkbox" 
                    checked={completed} 
                    onChange={onToggle} 
                />
                <span>{task}: {description}</span>
            </div>
        );
    };

    export default TodoItem;
