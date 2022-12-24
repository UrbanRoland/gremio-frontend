<template>
          <div class="flex min-h-full items-center justify-center py-12 px-4 sm:px-6 lg:px-8">
            <div class="container max-w-sm mx-auto flex-1 flex flex-col items-center justify-center px-2" >
                <div class="bg-white px-6 py-8 rounded shadow-md text-black w-full max-w-md space-y-8">
                    <h1 class="mb-8 text-3xl text-center">Sign up</h1>
                    <form @submit.prevent="handleSubmit">
                      <input 
                        type="text"
                        class="block border border-grey-light w-full p-3 rounded mb-4"
                        name="fullname"
                        placeholder="Full Name"
                        v-model="formData.name" />

                      <input 
                        type="text"
                        class="block border border-grey-light w-full p-3 rounded mb-4"
                        name="Username"
                        placeholder="Username"
                        v-model="formData.username" />

                      <input 
                        type="text"
                        class="block border border-grey-light w-full p-3 rounded mb-4"
                        name="email"
                        placeholder="Email" 
                        v-model="formData.email"/>

                      <input 
                        type="password"
                        class="block border border-grey-light w-full p-3 rounded mb-4"
                        name="password"
                        placeholder="Password"
                        v-model="formData.password" />
                      <input 
                        type="password"
                        class="block border border-grey-light w-full p-3 rounded mb-4 "
                        name="confirm_password"
                        placeholder="Confirm Password"
                        v-model="formData.confirmPassword" />

                    <button type="submit" class="group relative flex w-full justify-center rounded-md border border-transparent bg-indigo-600 py-2 px-4 text-sm font-medium text-white hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">
                      <span class="absolute inset-y-0 left-0 flex items-center pl-3">
                        <PlusIcon class="h-5 w-5 text-indigo-500 group-hover:text-indigo-400" aria-hidden="true" />
                      </span>
                         Sign up
                    </button>
                    <div class="text-center text-sm text-grey-dark mt-4">
                        By signing up, you agree to the 
                        <a class="no-underline border-b border-grey-dark text-grey-dark" href="#">
                            Terms of Service
                        </a> and 
                        <a class="no-underline border-b border-grey-dark text-grey-dark" href="#">
                            Privacy Policy
                        </a>
                    </div>
                  </form>
                </div>

                <div class="text-grey-dark mt-6">
                    Already have an account? 
                    <router-link class="no-underline border-b border-blue text-blue" to="/signin">
                        Log in
                    </router-link>.
                </div>
            </div>
        </div>
</template>

<script>
import axios from 'axios';
import { PlusIcon } from '@heroicons/vue/20/solid'
export default {
  name: 'SignUp',
  components: {
    PlusIcon
  },
    methods: {
        async handleSubmit(){
          console.log("sasdasmadkaskdmsdamdasd")
          console.log("aaaaaaaaaaaaaaaaaaaa"+ this.fromData)
          if(this.fromData.password !== this.formData.confirmPassword){
            alert("nem egyenlo")
            return false
          }
          return await axios.post("api/auth/signup", this.formData)
            .then(() => {
              this.$router.push("/signin")
            })
            .catch( err => {
              console.log(err.response.data.message)
            })
        }
    },
  data () {
    return {
        formData:{
            name: '',
            email:'',
            username: '',
            password: null,
            confirmPassword: null
        }

    }
  }
}
</script>

<style scope>

</style>