import React, { Component } from "react";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import { fetchData } from "../actions/index";
import { fetchAll } from "../actions/index";

class SearchBar extends Component {
  constructor(props) {
    super(props);

    this.state = { term: "" };

    this.onInputChange = this.onInputChange.bind(this);
    this.onFormSubmit = this.onFormSubmit.bind(this);
  }

  onInputChange(event) {
    this.setState({ term: event.target.value });
  }

  onFormSubmit(event) {
    event.preventDefault();

    this.props.fetchData(this.state.term);
    this.setState({ term: "" });
  }

  fetchAll(event) {
    event.preventDefault();
    this.props.fetchAll();
    
  }

  
  render() {
    return (

      <form onSubmit={this.onFormSubmit} className="input-group">
      
      
        
        <input
           placeholder="Enter id"
          value={this.state.term}
          onChange={this.onInputChange}
          required="true"
          
        />
       <button type="submit"  className="btn btn-secondary"> Fetch By Id</button>
        <span className="input-group-btn">
          <button className="btn btn-secondary" onClick={this.fetchAll.bind(this)}>Fetch All</button>
           </span>
        
      </form>


    );
  }
}

function mapDispatchToProps(dispatch) {
  return bindActionCreators({ fetchData, fetchAll}, dispatch);
}

export default connect(null, mapDispatchToProps)(SearchBar);
