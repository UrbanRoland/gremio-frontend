<template>
  <div>
    <Navigation />
  </div>
</template>

<script>
import { ref } from 'vue'
import Navigation from './components/Navigation.vue'

export default {
  name: 'LayoutDefault',

  components: {
    Navigation
  },

  setup () {
    return {
      leftDrawerOpen: ref(false)
    }
  }
}
</script>
