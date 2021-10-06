<template>
  <md-app
      v-cloak
      md-waterfall
      md-mode="fixed"
  >
    <md-app-toolbar class="md-primary md-dense">
      <div class="md-toolbar-section-start">
        <router-link to="/" class="md-title">Song Display</router-link>
      </div>
      <SongDisplay></SongDisplay>
    </md-app-toolbar>

    <md-app-content>
      <router-view/>
    </md-app-content>
  </md-app>
</template>

<script>
import SongDisplay from "@/components/SongDisplay/SongDisplay";

export default {
  name: 'App',

  components: {
    SongDisplay
  },

  methods: {},
}
</script>

<style scoped>
</style>
