import React from "react";
import { Component } from "react";

import SearchBar from "../containers/search_bar";
import ApiDataList from "../containers/api_data_list";
import FetchId from "../containers/fetch_id";

export default class App extends Component {
  render() {
    return (
      <div>
        <SearchBar />
        <ApiDataList />
      </div>
    );
  }
}
