import axios from 'axios'

let BASE_URL = "http://localhost:9000/api/auth"

 export function signin (formData) {

    axios.post(BASE_URL+"/signin", formData)
        .then(() => window.location="/home")
        .catch((error) => console.log(error))
 }