import React, { Component } from "react";
import { connect } from "react-redux";


class FetchId extends Component {
 

  renderList() {
    
      return (

      <tr>
         <td> {this.props.data.id}</td>
        <td> {this.props.data.firstName}</td>
        <td> {this.props.data.lastName}</td>
        <td> {this.props.data.email}</td>
      </tr>

      );
   
  }

  render() {
    return (
      <table className="table table-hover">
        <thead>
          <tr>
            <th>id</th>
            <th>firstName</th>
            <th>lastName</th>
            <th>email</th>
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

export default connect(mapStateToProps)(FetchId);
