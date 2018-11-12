import { FETCH_DATA } from "../actions/index";
import { FETCH_ALL } from "../actions/index";

export default function(state = [], action) {
  switch (action.type) {
    case FETCH_DATA:
      //return [action.payload.data, ...state];
      console.log([action.payload.data]);
         return [action.payload.data];
     case FETCH_ALL:
      //return [action.payload.data, ...state];
      console.log(action.payload.data);
         return action.payload.data;     
         default:
		return state;
  }
 // return state;
}
