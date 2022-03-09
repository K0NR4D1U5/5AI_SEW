<template>
    <div class="song-view">
        <div>
            <page-nav :page="page" @navigated="load"/>
        </div>
        <md-table v-model="page.entities" md-card @md-selected="onSelect">

            <md-speed-dial class="md-bottom-right">
                <md-speed-dial-target :to="{ name: 'song-editor' }">
                    <md-icon>add</md-icon>
                </md-speed-dial-target>
            </md-speed-dial>

            <song
                    v-for="s in page.entities"
                    :key="s._links.self.href"
                    :song="s"
                    @deleted="load(page.number)"
            />
        </md-table>
    </div>
</template>

<script>
import Page from '@/models/Page'
import PageNav from '@/components/PageNav'
import Song from '@/components/Song'
import SongEntity from '@/models/Song'
import {deleteSong, loadPage} from '@/services/rest'

export default {
    name: 'SongView',

    components: {
        PageNav,
        Song,
    },
    data() {
        return {
            page: new Page(),
            selected: [],
            entities: {
                mdCount: null,
                mdPage: null,
                mdData: []
            },
            rowsPerPage: 3,
        }
    },

    created() {
        this.load()
    },

    methods: {
        load(pageNum = 0) {
            loadPage(SongEntity, pageNum, {size: 6, projection: 'ohneAudio'})
                .then(page => {
                    this.page = page
                })
        }
    },
    del() {
        deleteSong(this.selected[0])
            .then(() => {
                this.load(0, 100)
            });
    }
}
</script>

<style scoped>
.song-view .page-nav {
    margin-bottom: 1em;
}

.song-view .song:nth-child(2n+1) {
    background-color: #f0f0f0;
}
</style>