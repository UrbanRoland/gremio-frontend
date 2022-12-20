<template>
    <q-layout>
        <q-page-container>
          <q-page class="bg-grey-9 window-height window-width row justify-center items-center">
    <div class="column">
      <div class="row">
        <h5 class="text-h5 text-white q-my-md">Gremio</h5>
      </div>
      <div class="row">
        <q-card square bordered class="q-pa-lg shadow-1">
          <q-card-section>
            <q-form class="q-gutter-md">
                <q-input square filled clearable v-model="formData.name" type="text" label="name"
                lazy-rules
                :rules="[ val => val && val.length > 0 || 'Please type your name!']"/>
                <q-input square filled clearable v-model="formData.email" type="email" label="email"
              lazy-rules
                :rules="[ val => val && val.length > 0 || 'Please type your email!']"/>
              <q-input square filled clearable v-model="formData.username" type="text" label="username"
              lazy-rules
                :rules="[ val => val && val.length > 0 || 'Please type your username!']"/>
              <q-input square filled clearable v-model="formData.password" type="password" label="password"
              lazy-rules
                :rules="[ val => val && val.length > 0 || 'Please type your password!']" />
            </q-form>
          </q-card-section>
          <q-card-actions class="q-px-md">
            <q-btn v-on:click="submit()" unelevated color="secondary" size="lg" class="full-width" label="Submit" />
          </q-card-actions>
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
import {signup} from '../services/auth'

export default {
    methods: {
        submit(){
            signup(this.formData);
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