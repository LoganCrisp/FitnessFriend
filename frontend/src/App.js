import React from 'react';
import './App.css';
import NavigationBar from './components/NavigationBar';

function App() {
  return (
    <div className="App">
      <NavigationBar />
      <div className="content">
        <h1>Welcome to Fitness Friend</h1>
        <p>Your fitness journey starts here!</p>
      </div>
    </div>
  );
}

export default App;
