<template>
  <div v-if=" this.$route.path === '/signin' || this.$route.path ==='/signup' || this.$route.name === 'NotFoundPage' ">
    <router-view/>
  </div>
  <div v-else>
    <Navigation/>
  </div>
</template>

<script>
import Navigation from './components/Navigation.vue'
export default {
  name: 'App',

  components: {
    Navigation
  },
}
</script>
