<template>
    <q-layout>
        <q-page-container>
          <q-page class="bg-grey-9 window-height window-width row justify-center items-center">
    <div class="column">
      <div class="row">
        <h5 class="text-h5 text-white q-my-md">Sign up</h5>
      </div>
      <div class="row">
        <q-card square bordered class="q-pa-lg shadow-1">
          <q-card-section>
            <q-form class="q-gutter-md" @submit.prevent="handleSubmit">
                <q-input square filled clearable v-model="formData.name" type="text" 
                label="name" hint="Name minimum 3 character!"
                lazy-rules
                :rules="[ 
                  val => val && val.length > 0 || 'Please type your name!',
                  val => val.length > 0 && val.length >= 3 || 'Must be at least 3 character!']"/>
                <q-input square filled clearable v-model="formData.email" type="email" label="email"
              lazy-rules
                :rules="[ val => val && val.length > 0 || 'Please type your email!']"/>
              <q-input square filled clearable v-model="formData.username" type="text" 
              label="username" hint="Username minimum 3 character!"
              lazy-rules
                :rules="[ 
                  val => val && val.length > 0 || 'Please type your username!',
                  val => val.length > 0 && val.length >= 3 || 'Must be at least 3 character!']"/>
              <q-input square filled clearable v-model="formData.password" type="password" 
              label="password" hint="Password minimum 6 character!"
              lazy-rules
                :rules="[ 
                  val => val && val.length > 0 || 'Please type your password!',
                  val => val.length > 0 && val.length >= 6 || 'Must be at least 6 character!']" />
              <q-card-actions class="q-px-md">
                <q-btn unelevated color="secondary" type="submit" size="lg" class="full-width" label="Submit" />
              </q-card-actions>
            </q-form>
          </q-card-section>
          <q-card-section class="text-center q-pa-none">
            <p class="text-grey-6">You have an account? <router-link to="/signin">Login</router-link></p>
          </q-card-section>
        </q-card>
      </div>
    </div>
  </q-page>
  </q-page-container>
</q-layout>
</template>

<script>
import axios from 'axios';
import { useQuasar } from 'quasar'
export default {
  name: 'SignUp',
  setup () {
    const $q = useQuasar()
    return {
      triggerNegative (msg) {
        $q.notify({
          type: 'negative',
          message: msg
        })
      },
      triggerOngoing () {
        const notif = $q.notify({
          type: 'ongoing',
          message: 'Creating you account please wait...'
        })
        setTimeout(() => {
          notif({
            type: 'positive',
            message: 'Success!',
            timeout: 1000,
            actions: this.$router.push("/signin")
          })
        }, 4000)
      }
    }
  },

    methods: {
        async handleSubmit(){
          return await axios.post("api/auth/signup", this.formData)
            .then(() => {
              this.triggerOngoing()
            })
            .catch( err => {
              this.triggerNegative(err.response.data.message)
            })
        }
    },
  data () {
    return {
        formData:{
            name: '',
            email:'',
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