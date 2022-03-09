<template>
    <md-app
            v-cloak
            md-mode="fixed"
            md-waterfall
    >
        <md-app-toolbar class="md-primary md-dense">
            <div class="md-toolbar-section-start">
                <router-link class="md-title" to="/">YouSong</router-link>
            </div>
            <div class="md-subheading md-toolbar-section-end">
                <router-link class="md-title" to="/artists">Interpreten</router-link>
            </div>
        </md-app-toolbar>
        <md-app-content>
            <router-view/>
        </md-app-content>
    </md-app>
</template>

<script>
export default {
    name: 'App',
    components: {},
    methods: {},
}
</script>

<style scoped>
</style>
