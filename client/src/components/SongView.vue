<template>
  <div>
    <div>
        <page-nav :page="page" @navigated="load"/>
    </div>

    <song
        v-for="s in page.entities"
        :key="s._links.self.href"
        :song="s"
        @deleted="load(page.number)"
    />
  </div>
</template>

<script>
import Page from '@/models/Page'
import PageNav from '@/components/PageNav'
import Song from '@/components/Song'
import SongEntity from '@/models/Song'
import { loadPage } from '@/services/rest'

export default {
    name: 'SongView',
    components: {
        PageNav,
        Song,
    },
    data() {
        return {
            page: new Page(),
        }
    },
    created() {
        this.load()
    },
    methods: {
        load(pageNum = 0) {
            loadPage(SongEntity, pageNum, { size: 6 })
                .then(page => {
                    this.page = page
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