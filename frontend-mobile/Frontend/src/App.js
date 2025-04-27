// src/App.js
import React from 'react';
import Header from './components/Header';
import TodoList from './components/TodoList';
import Footer from './components/Footer';
import './App.css';

function App() {
    return (
        <div className="App">
            <Header />
            <TodoList />
            <Footer />
        </div>
    );
}

export default App;
