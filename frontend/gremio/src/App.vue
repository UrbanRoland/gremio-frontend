<template>
  <div v-if=" this.$route.path === '/signin' || this.$route.path ==='/signup' ">
    <router-view/>
  </div>
  <div v-else>
    <Navigation/>
  </div>
</template>

<script>
import Navigation from './components/Navigation.vue'
export default {
  name: 'LayoutDefault',

  components: {
    Navigation
  },
}
</script>
