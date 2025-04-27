// src/components/TodoList.js
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import TodoItem from './TodoItem';
import AddTodoList from './AddTodoList';
import AddTask from './AddTask';
import Tab from './Tab';

const TodoList = () => {
    const [activeList, setActiveList] = useState(null);
    const [todoLists, setTodoLists] = useState({});

    useEffect(() => {
        fetchTodos();
    }, []);

    const fetchTodos = async () => {
        try {
            const response = await axios.get('http://localhost:8080/api/todo-lists'); // Adjust the URL as needed
            const fetchedLists = response.data.reduce((acc, list) => {
                acc[list.title] = { ...list, tasks: list.tasks || [] }; // Adjust based on your API response structure
                return acc;
            }, {});
            setTodoLists(fetchedLists);
        } catch (error) {
            console.error('Error fetching todo lists:', error);
        }
    };

    const addTodoList = async (list) => {
        try {
            const response = await axios.post('http://localhost:8080/api/todo-lists', list); // Adjust the URL as needed
            const newList = response.data; // Assuming your backend returns the created list
            setTodoLists((prevLists) => ({
                ...prevLists,
                [newList.title]: { ...newList, tasks: [] }
            }));
            setActiveList(newList.title);
        } catch (error) {
            console.error('Error adding todo list:', error);
        }
    };

    const addTask = async (task) => {
        if (activeList) {
            try {
                const response = await axios.post(`http://localhost:8080/api/todo-lists/${activeList}/tasks`, task); // Adjust the URL as needed
                const newTask = response.data; // Assuming your backend returns the created task
                setTodoLists((prevLists) => ({
                    ...prevLists,
                    [activeList]: {
                        ...prevLists[activeList],
                        tasks: [...prevLists[activeList].tasks, { ...newTask, completed: false }]
                    }
                }));
            } catch (error) {
                console.error('Error adding task:', error);
            }
        }
    };

    const toggleTaskCompletion = (taskIndex) => {
        if (activeList) {
            setTodoLists((prevLists) => {
                const tasks = prevLists[activeList].tasks.map((task, index) =>
                    index === taskIndex ? { ...task, completed: !task.completed } : task
                );
                return {
                    ...prevLists,
                    [activeList]: {
                        ...prevLists[activeList],
                        tasks,
                    },
                };
            });
        }
    };

    return (
        <div className="todo-list-container">
            <aside className="sidebar">
                <AddTodoList onAdd={addTodoList} />
                <div className="tabs">
                    {Object.keys(todoLists).map((list) => (
                        <Tab 
                            key={list} 
                            label={list} 
                            isActive={list === activeList} 
                            onClick={() => setActiveList(list)} 
                        />
                    ))}
                </div>
            </aside>
            <main className="main-content">
                {activeList && (
                    <>
                        <AddTask onAdd={addTask} />
                        <div className="tasks">
                            {todoLists[activeList].tasks.map((task, index) => (
                                <TodoItem 
                                    key={index} 
                                    task={task.title} 
                                    description={task.description} 
                                    completed={task.completed}
                                    onToggle={() => toggleTaskCompletion(index)}
                                />
                            ))}
                        </div>
                    </>
                )}
            </main>
        </div>
    );
};

export default TodoList;
