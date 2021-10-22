<template>
  <div>
    <md-toolbar class="md-pagination" md-elevation="0">
      <md-button :disabled="curPage === 1" @click="curPage = 1; load()" class="md-icon-button"><md-icon>first_page</md-icon></md-button>
      <md-button :disabled="curPage === 1" @click="curPage--; load()" class="md-icon-button"><md-icon>navigate_before</md-icon></md-button>
      <span>{{ curPage }} / {{ maxPages }}</span>
      <md-button :disabled="curPage === maxPages" @click="curPage++; load()" class="md-icon-button"><md-icon>navigate_next</md-icon></md-button>
      <md-button :disabled="curPage === maxPages" @click="curPage = maxPages; load()" class="md-icon-button"><md-icon>last_page</md-icon></md-button>
    </md-toolbar>
    <md-list
        class="md-song-list-container">
      <song
          v-for="s in songs"
          :key="s._links.self.href"
          :song="s"
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
          curPage: 1,
          maxPages: 5,
          size: 4,
        }
    },
    created() {
        this.load()
    },
    methods: {
        load() {
            loadPage(SongEntity, this.curPage - 1, this.size)
                .then(res => {
                    this.songs = res.entities;
                    this.maxPages = res.page.totalPages;
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