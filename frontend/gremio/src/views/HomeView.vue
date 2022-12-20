<template>

  <div>
      <h3 v-if="user">Hi , {{ user.username }}</h3>
      <h3 v-if="!user"> You are not logged in!</h3>
  </div>


  

</template>

<script>
import axios from 'axios'

export default {
  name: 'HomeComponent',

  components: {
    
  },
  data(){
    return {
      user: null
    }
  },

  async created(){
    const response = await axios.get('api/auth/user')
    console.log(response);
    this.user = response.data;
  }
}
</script>
