<template>
  <md-list-item>
    <md-icon class="md-primary">music_note</md-icon>

    <div class="md-list-item-text">
      <span>{{ song.title }}</span>
      <span>{{ song.artist }}</span>
    </div>
    <div>
      <md-chip v-for="genre in generateGenreList" :key="genre">{{ genre }}</md-chip>
    </div>
  </md-list-item>
</template>

<script>
export default {
    name: 'Song',
    props: {
        song: Object,
        required: true,
    },
  computed: {
      generateGenreList() {
        return this.song.genre.split("|");
      }
  }
}
</script>

<style scoped>
</style>