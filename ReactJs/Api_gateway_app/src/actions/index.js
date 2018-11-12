import axios from "axios";

//const API_KEY = "6a78596d062df78380eff5944c4e5567";
//onst ROOT_URL = `http://api.openweathermap.org/data/2.5/forecast?appid=${API_KEY}`;

export const FETCH_DATA = "FETCH_DATA";

export const FETCH_ALL = "FETCH_ALL";


export function fetchData(term) {
  //const url = `${ROOT_URL}&q=${city},us`;
  //const request = axios.get(url);


const request = axios.get(`https://emerson.azure-api.net/emerson/customers/${term}`, {

method: 'GET',
  headers: {
    'Accept': 'application/json',
    'Content-Type': 'application/json',
    //'Ocp-Apim-Subscription-Key':'6093b372a582436295da6a24db3b8e89'
    //'Access-Control-Allow-Origin':  '*'
   //'Access-Control-Allow-Methods': 'POST, GET, PUT, DELETE, OPTIONS', 
   // 'Access-Control-Allow-Headers': '*',
   }

});
console.log(request);


  return {
    type: FETCH_DATA,
    payload: request
  };
}

export function fetchAll() {
  const myKey = '6093b372a582436295da6a24db3b8e89';

  var config = {
    headers: {"Access-Control-Allow-Origin": "*"}
  };
  
const request = axios.get('https://emerson.azure-api.net/emerson/customers/all');
console.log(request);


  return {
    type: FETCH_ALL,
    payload: request
  };
}

