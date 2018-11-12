import { combineReducers } from "redux";
import FetchDataReducer from "./reducer_fetchdata";

const rootReducer = combineReducers({
  data: FetchDataReducer
});

export default rootReducer;
