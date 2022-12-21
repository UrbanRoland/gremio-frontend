<template>

  <div>
      <h3 v-if="user">Hi , {{ user.username }}</h3>
      <h3 v-if="!user"> You are not logged in!</h3>
  </div>


  

</template>

<script>
import {mapGetters} from 'vuex'

export default {
  name: 'HomeComponent',
  components: {
    
  },
  computed:{
    ...mapGetters(['user'])
  }
}
</script>
