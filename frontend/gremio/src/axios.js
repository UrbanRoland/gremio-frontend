import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:9000/'
axios.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('token')
