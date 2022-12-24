<template>


<h1 class="text-pink-400">assad</h1>



</template>


<script>
import {mapGetters} from 'vuex'

export default {
  name: 'MyProfile',
  data: () => ({
    localTime: " "
  }),
  components: {
    
  },
  computed:{
    ...mapGetters(['user'])
  },
  methods: {
    getTime: function () {
      return new Date().toLocaleTimeString();
    },
    showLocaleTime: function () {
      var time = this;
      setInterval(function () {
       time.localTime = new Date().toLocaleTimeString();
    }, 1000);
   }

  },
  mounted: function (){
    this.time = this.getTime();
    this.showLocaleTime()
  }
}
</script>

<style scoped>
 h2 {
    margin: 0;
 }
 .q-item__label{
    padding-right: 10px;
 }
 i{
    padding-right: 5px;
 }
</style>