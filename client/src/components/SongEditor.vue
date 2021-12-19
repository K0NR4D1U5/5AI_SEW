<template>
  <div>{{ this.name }}</div>
</template>

<script>
export default {
  name: "SongEditor",
  data() {
    return {
      song: SongEntitiy
    }
  },
  mounted() {
    console.log(this.$route.params);
    this.name = this.$route.params.data.title;
  },
  methods: {
    updateSong() {
      editSong(this.song, {kunstler})
    }
  }
}
</script>

<style scoped>

</style>