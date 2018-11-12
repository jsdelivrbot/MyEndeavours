import React, { Component } from "react";
import { connect } from "react-redux";



class ApiDataList extends Component {
  renderList() {
    return this.props.data.map((api_data, index) => {
      return (

      <tr key= {index}>
         <td> {api_data.id}</td>
        <td> {api_data.firstName}</td>
        <td> {api_data.lastName}</td>
        <td> {api_data.email}</td>

      </tr>

      );
    });
  }

  render() {
    return (
      <table className="table table-hover">
        <thead>
          <tr>
            <th>Id</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email Address</th>
          </tr>
        </thead>
        <tbody>
          {this.renderList()}

        </tbody>
      </table>
    );
  }
}


function mapStateToProps({ data }) {
  return { data };
}

export default connect(mapStateToProps)(ApiDataList);
