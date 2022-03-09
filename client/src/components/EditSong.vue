<template>
    <div>
        <form class="md-layout" novalidate @submit.prevent>
            <md-card class="md-layout-item ">
                <md-card-header>
                    <div class="md-title">{{ song.title }}</div>
                </md-card-header>

                <md-card-content>
                    <md-field :class="getValidationClass('title')">
                        <label for="title">Title</label>
                        <md-input id="title" v-model="song.title" :disabled="sending" autocomplete="song-title"
                                  name="title"/>
                        <span v-if="!$v.song.title.required" class="md-error">The title is required</span>
                    </md-field>

                    <div class="md-layout md-gutter">
                        <div class="md-layout-item md-small-size-100">
                            <md-field :class="getValidationClass('artist')">
                                <label for="artist">Artist</label>
                                <md-input id="artist" v-model="song.artist" :disabled="sending" autocomplete="song-artist"
                                          name="artist"/>
                                <span v-if="!$v.song.artist.required"
                                      class="md-error">The artist name is required</span>
                                <span v-else-if="!$v.song.artist.minlength"
                                      class="md-error">Invalid first artist name</span>
                            </md-field>
                        </div>

                        <div class="md-layout-item md-small-size-100">
                            <md-chips v-model="song.genre" class="md-primary"
                                      md-check-duplicated md-placeholder="Add genre...">
                                <label>Genre</label>
                            </md-chips>
                        </div>
                    </div>

                </md-card-content>

                <md-progress-bar v-if="sending" md-mode="indeterminate"/>

                <md-card-actions>
                    <md-button :disabled="sending" class="md-secondary" to="../*">Cancel</md-button>
                    <md-button :disabled="sending" class="md-primary" @click="validateUser">Save</md-button>
                </md-card-actions>
            </md-card>

            <md-snackbar :md-active.sync="songSaved">{{ info }}</md-snackbar>
        </form>
    </div>
</template>

<script>
import {validationMixin} from 'vuelidate'
import {minLength, required,} from 'vuelidate/lib/validators'
import {addEntry, editEntry} from "@/services/rest";
import SongEntity from "@/models/Song";

export default {
    name: 'EditSong',
    mixins: [validationMixin],
    data: () => ({
        song: SongEntity,
        songSaved: false,
        sending: false,
        lastUser: null,
        info: null
    }),
    validations: {
        song: {
            title: {
                required,
            },
            artist: {
                required,
                minLength: minLength(3)
            }
        }
    },
    mounted() {
        this.song = this.$route.params.song === undefined ? new SongEntity() : this.$route.params.song;
    },
    methods: {
        getValidationClass(fieldName) {
            const field = this.$v.song[fieldName]

            if (field) {
                return {
                    'md-invalid': field.$invalid && field.$dirty
                }
            }
        },
        clearForm() {
            this.songSaved = true
            this.sending = false
            this.$v.$reset()
        },
        saveSong() {
            this.sending = true

            const data = {
                title: this.song.title,
                artist: this.song.artist,
                genre: this.song.genre.join('|')
            }

            if (this.$route.params.song === undefined) {
                addEntry(SongEntity, data).then(response => {
                    if (!response) {
                        this.info = "There was an error";
                    } else {
                        this.info = "The song has been added";
                    }
                }).finally(() => {
                    this.clearForm()
                })
            } else {
                editEntry(this.song, data).then(response => {
                    if (!response) {
                        this.info = "There was an error";
                    } else {
                        this.info = "The song has been updated";
                    }
                }).finally(() => {
                    this.clearForm()
                })
            }
        },
        validateUser() {
            this.$v.$touch()

            if (!this.$v.$invalid)
                this.saveSong()
        }
    }
}
</script>

<style scoped>
.md-progress-bar {
    position: absolute;
    top: 0;
    right: 0;
    left: 0;
}
</style>