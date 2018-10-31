import React, {Componnet} from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { link } from 'react-router-dom';

class StaffList extends Component {
	
	constructor(props) {
		super(props);
		this.state = {staffs: [], isLoading: true};
		this.remove = this.remove.bind(this);
	}
	
	componentDidMount() {
		this.setState({isLoading: true});
		
		fetch('api/staffs')
		.then(response=>response.json())
		.then(data=>this.setState({staffs: data, isLoading: false}));
	}
	
	async remove(id) {
		await fetch('/api/staff/${id}', {
			method: 'DELETE',
			headers: {
				'Accept': 'application/json',
				'Content-Type': 'application/json'
			}
		}).then(() => {
			let updatedStaffs = [...this.state.staffs].filter(i => i.id !== id);
			this.setState({staffs: updatedStaffs});
		});
	}
	
	render() {
		const {staffs, isLoading} = this.state;
		
		if(isLoading) {
			return <p>Loading...</p>;
		}
		
		const staffList = staffs.map(staff => {
			return 
			<tr key={staff.id}>
			  <td>{staff.name}</td>
			  <td>{staff.department}</td>
			  <td>{staff.position}</td>
			  <td>{staff.permission}</td>
			  <td>{staff.phoneNo}</td>
			  <td>{staff.enrolledSince}</td>
			  <td>
			    <ButtonGroup>
			      <Button size='sm' color='primary' tag={Link} to={"/staffs/"+staff.id}>Edit</Button>
			      <Button size='sm' color='danger' onClick={() => this.remove(staff.id)}>Delete</Button>
			    </ButtonGroup>
			  </td>
			</tr>
		});
		
		return (
		<div>
		  <AppNavbar/>
		  <Container fluid>
		    <div className="float-right">
		      <Button color="success" tag={Link} to="/groups/new">Add Staff</Button>
		    </div>
		    <h3>Staff Management</h3>
		    <Table className="mt-4">
		      <thead>
		      <tr>
		        <th width="15%">Name</th>
		        <th width="10%">Department</th>
		        <th width="10%">Position</th>
		        <th width="20%">Permission</th>
		        <th width="20%">Phone No.</th>
		        <th width="10%">Enrolled Since</th>
		        <th width="15%">Actions</th>
		      </tr>
		      </thead>
		      <tbody>
		        {staffList}
		      </tbody>
		    </Table>
		  </Container>
		</div>
		);
	}
}

export default StaffList