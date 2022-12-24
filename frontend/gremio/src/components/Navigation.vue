<template>
  <!--
    This example requires updating your template:

    ```
    <html class="h-full bg-gray-100">
    <body class="h-full">
    ```
  -->
  <div class="min-h-full">
    <Disclosure as="nav" class="bg-gray-800" v-slot="{ open }">
      <div class="mx-auto max-w-7xl px-4 sm:px-6 lg:px-8">
        <div class="flex h-16 items-center justify-between">
          <div class="flex items-center">
            <div class="flex-shrink-0">
              <img class="h-8 w-8" src="../assets/logo.svg" alt="Your Company" />
            </div>
            <div >
              <div class="ml-10 flex items-baseline space-x-4">
                <router-link v-for="item in navigation" :key="item.name" :to="item.to"  :class="['text-gray-300 hover:bg-gray-700 hover:text-white', 'px-3 py-2 rounded-md text-sm font-medium']">{{ item.name }}</router-link>
              </div>
            </div>
          </div>
          <div class="hidden md:block">
            <div class="ml-4 flex items-center md:ml-6">
              <button type="button" class="rounded-full bg-gray-800 p-1 text-gray-400 hover:text-white focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800">
                <span class="sr-only">View notifications</span>
                <BellIcon class="h-6 w-6" aria-hidden="true" />
              </button>

              <!-- Profile dropdown -->
              <Menu as="div" class="relative ml-3">
                <div>
                  <MenuButton class="flex max-w-xs items-center rounded-full bg-gray-800 text-sm focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800">
                    <span class="sr-only">Open user menu</span>
                    <div class="overflow-hidden relative w-10 h-10 bg-gray-100 rounded-full dark:bg-gray-600">
                      <svg class="absolute -left-1 w-12 h-12 text-gray-400" fill="currentColor"
                        viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
                        <path fill-rule="evenodd" d="M10 9a3 3 0 100-6 3 3 0 000 6zm-7 9a7 7 0 1114 0H3z" clip-rule="evenodd"></path>
                      </svg>
          
                    </div>
                  </MenuButton>
                </div>
                <transition enter-active-class="transition ease-out duration-100" enter-from-class="transform opacity-0 scale-95" enter-to-class="transform opacity-100 scale-100" leave-active-class="transition ease-in duration-75" leave-from-class="transform opacity-100 scale-100" leave-to-class="transform opacity-0 scale-95">
                  <MenuItems class="absolute right-0 z-10 mt-2 w-48 origin-top-right rounded-md bg-white py-1 shadow-lg ring-1 ring-black ring-opacity-5 focus:outline-none">
                    <MenuItem v-for="item in userNavigation" :key="item.name"  v-slot="{ active }">
                      <router-link :to="item.to" :class="[active ? 'bg-gray-100' : '', 'block px-4 py-2 text-sm text-gray-700']">{{ item.name }}</router-link>            
                    </MenuItem>
                    <MenuItem>
                      <a href="javascript:void(0)" v-if="user" @click="handleLogOut" class="bg-gray-10 block px-4 py-2 text-sm text-gray-700">Log out</a>
                    </MenuItem>
                  </MenuItems>
                </transition>
              </Menu>
            </div>
          </div>
          <div class="-mr-2 flex md:hidden">
            <!-- Mobile menu button -->
            <DisclosureButton class="inline-flex items-center justify-center rounded-md bg-gray-800 p-2 text-gray-400 hover:bg-gray-700 hover:text-white focus:outline-none focus:ring-2 focus:ring-white focus:ring-offset-2 focus:ring-offset-gray-800">
              <span class="sr-only">Open main menu</span>
              <Bars3Icon v-if="!open" class="block h-6 w-6" aria-hidden="true" />
              <XMarkIcon v-else class="block h-6 w-6" aria-hidden="true" />
            </DisclosureButton>
          </div>
        </div>
      </div>
    </Disclosure>

    <header class="bg-white shadow">
      <div class="mx-auto max-w-7xl py-6 px-4 sm:px-6 lg:px-8">
        <h1 class="text-3xl font-bold tracking-tight text-gray-900">Dashboard</h1>
      </div>
    </header>
    <main>
      <div class="mx-auto max-w-7xl py-6 sm:px-6 lg:px-8">
        <!-- Replace with your content -->
        <div class="px-4 py-6 sm:px-0">
          <router-view/>
        </div>
        <!-- /End replace -->
      </div>
    </main>
  </div>
</template>


<script>
import { Disclosure, DisclosureButton, Menu, MenuButton, MenuItem, MenuItems } from '@headlessui/vue'


import {mapGetters} from 'vuex'

export default{
    name:'NavigationMenu',

    data() {
      return {
        navigation:[
          { name: 'Home', to: '/home'},
          { name: 'My Tasks', to:'/my-tasks'},
          { name: 'Inbox', to: '/inbox'},
          { name: 'Reporting', to: '/reporting'},
          { name: 'Portfolios', to: '/portfolios'},
          { name: 'Goals', to: '/goals',},
        ],
        userNavigation: [
          { name: 'My Profile', to: '/myprofile' },
          { name: 'Settings', to: '#' },
        ]
      }
    },

    methods: {
      
     async handleLogOut(){
        this.$store.dispatch('user',null);
        localStorage.removeItem('token');
        this.$router.push("/signin");
      }
    },
    components:{
      Disclosure, DisclosureButton, Menu, MenuButton, MenuItem, MenuItems
    },

    computed: {
      ...mapGetters(['user'])
    },
}
</script>

<style scoped>
.router-link-active{
  --tw-text-opacity: 1;
    color: rgb(255 255 255 / var(--tw-text-opacity));
  background: #111827;
}
</style>