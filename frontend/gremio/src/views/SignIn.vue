<template>
    <q-layout>
        <q-page-container>
          <q-page class="bg-grey-9 window-height window-width row justify-center items-center">
    <div class="column">
      <div class="row">
        <h5 class="text-h5 text-white q-my-md">Sign in</h5>
      </div>
      <div class="row">
        <q-card square bordered class="q-pa-lg shadow-1">
          <q-card-section>
            <q-form class="q-gutter-md" @submit.prevent="handleSubmit">
              <q-input square filled clearable v-model="formData.username" type="text" label="username"
              lazy-rules
                :rules="[ val => val && val.length > 0 || 'Please type your username!']"/>
              <q-input square filled clearable v-model="formData.password" type="password" label="password"
              lazy-rules
                :rules="[ val => val && val.length > 0 || 'Please type your password!']" />
              <q-card-actions class="q-px-md">
                <q-btn unelevated color="secondary" size="lg" type="submit" class="full-width" label="Submit" />
              </q-card-actions>
            </q-form>
          </q-card-section>

          <q-card-section class="text-center q-pa-none">
            <p class="text-grey-6">Not reigistered? <router-link to="/signup">Created an Account</router-link></p>
          </q-card-section>
        </q-card>
        
      </div>
    </div>
  </q-page>
  </q-page-container>
</q-layout>


</template>

<script>
import axios from 'axios'
export default {

    methods: {
      async handleSubmit(){
        const response = await axios.post("/api/auth/signin", this.formData);

        localStorage.setItem('token', response.data.token)
        localStorage.setItem('username', response.data.username)
          this.$router.push("/home")
          console.log("Login " + JSON.stringify(response.data))

      }
    },
  data () {
    return {
        formData:{
            username: '',
            password: ''
        }

    }
  }
}
</script>

<style scope>
.q-card {
  width: 360px;
}
</style>