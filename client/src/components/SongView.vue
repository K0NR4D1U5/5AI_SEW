<template>
  <div>
    <md-toolbar class="md-pagination" md-elevation="0">
      <md-button :disabled="currentPage === 1" @click="currentPage = 1; load()" class="md-icon-button"><md-icon>first_page</md-icon></md-button>
      <md-button :disabled="currentPage === 1" @click="currentPage--; load()" class="md-icon-button"><md-icon>navigate_before</md-icon></md-button>
      <span>{{ currentPage }} / {{ maximumPages }}</span>
      <md-button :disabled="currentPage === maximumPages" @click="currentPage++; load()" class="md-icon-button"><md-icon>navigate_next</md-icon></md-button>
      <md-button :disabled="currentPage === maximumPages" @click="currentPage = maximumPages; load()" class="md-icon-button"><md-icon>last_page</md-icon></md-button>
    </md-toolbar>
    <md-list
        class="md-song-list-container">
      <song
          v-for="song in songs"
          :key="song._links.self.href"
          :song="song"
      />
    </md-list>
  </div>
</template>

<script>
import Song from '@/components/Song';
import SongEntity from '@/models/Song';
import { loadPage } from '@/services/rest';

export default {
    name: 'SongView',
    components: {
        Song,
    },
    data() {
        return {
            songs: [],
          currentPage: 1,
          maximumPages: 5,
          size: 4,
        }
    },
    created() {
        this.load()
    },
    methods: {
        load() {
            loadPage(SongEntity, this.currentPage - 1, this.size)
                .then(res => {
                    this.songs = res.entities;
                    this.maximumPages = res.page.totalPages;
                })
        }
    },
}
</script>

<style>
  .md-toolbar.md-pagination {
    justify-content: center;
    user-select: none;
    background-color: transparent !important;
  }
  .md-song-list-container {
    display: flex;
    align-items: center;
  }
  .md-song-list-container .md-list-item {
    width: min(50rem, 80vw);
  }
</style>