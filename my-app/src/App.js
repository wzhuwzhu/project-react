import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class App extends Component {
	state = {
			isLoading: true,
			staffs: []
	};
	
	async componnetDidMount() {
		const response = await fetch('/api/staffs');
		const body = await response.json();
		this.setState({staffs: body, isLoading: false});
	}
	
  render() {
	  const {staffs, isLoading} = this.state;
	  
	  if (isLoading) {
		  return <p>Loading...</p>;
	  }
	  
    return (
     <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Welcome</h1>
        </header>
        <div className="App-intro">
            <h2>Staff Management</h2>
            {staffs.map(staff =>
              <div key={staff.id}>
                {staff.name}
              </div>
            )}
        </div>
      </div>
    );
  }
}

export default App;
