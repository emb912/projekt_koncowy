import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8080'
axios.defaults.headers.post["Content-type"]='application/json'
export const request = (method, url, data) => {
    return axios({
        method: method,
        url: url,
        data: data,
        headers: {
          'Authorization': `Bearer ${localStorage.getItem('token')}`
      }
    });
};

export const setAuthHeader = (token) => {
  if (token) {
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
  } else {
      delete axios.defaults.headers.common['Authorization'];
  }
};