<template>
    <q-layout view="lHh Lpr lFf">
      <q-header elevated class="glossy">
        <q-toolbar>
          <q-btn
            flat
            dense
            round
            @click="leftDrawerOpen = !leftDrawerOpen"
            aria-label="Menu"
            icon="menu"
          />
  
          <q-toolbar-title>
            Gremio App
          </q-toolbar-title>
  
          <div class="q-pa-md q-gutter-sm">
            <q-avatar color="dark" text-color="white">J</q-avatar>
          </div>
        </q-toolbar>
      </q-header>
  
      <q-drawer
        v-model="leftDrawerOpen"
        show-if-above
        bordered
        class="bg-grey-2"
      >
        <q-list>
          <q-item-label header>GREMIO</q-item-label>
          <q-item clickable tag="router-link" to="/home">
            <q-item-section avatar>
              <q-icon name="house" />
            </q-item-section>
            <q-item-section>
              <q-item-label>Home</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable tag="router-link" to ="/my-tasks">
            <q-item-section avatar>
              <q-icon name="check_circle" />
            </q-item-section>
            <q-item-section>
              <q-item-label>My Tasks</q-item-label>
            </q-item-section>
          </q-item>
          <q-item clickable tag="router-link" to="/inbox">
            <q-item-section avatar>
              <q-icon name="notifications" />
            </q-item-section>
            <q-item-section>
              <q-item-label>Inbox</q-item-label>
            </q-item-section>
          </q-item>
          <q-item clickable tag="router-link" to="/reporting">
            <q-item-section avatar>
              <q-icon name="show_chart" />
            </q-item-section>
            <q-item-section>
              <q-item-label>Reporting</q-item-label>
            </q-item-section>
          </q-item>
          <q-item clickable tag="router-link" to="/portfolios">
            <q-item-section avatar>
              <q-icon name="description" />
            </q-item-section>
            <q-item-section>
              <q-item-label>Portfolios</q-item-label>
            </q-item-section>
          </q-item>
          <q-item clickable tag="router-link" to="/goals">
            <q-item-section avatar>
              <q-icon name="change_history" />
            </q-item-section>
            <q-item-section>
              <q-item-label>Goals</q-item-label>
            </q-item-section>
          </q-item>
        </q-list>
      </q-drawer>
  
      <q-page-container>
        <router-view></router-view>
      </q-page-container>
    </q-layout>

  </template>

<script>
import { ref } from 'vue'
export default{
    name:'NavigationMenu',

    setup () {
    return {
        leftDrawerOpen: ref(false)
    }
  }
}
</script>