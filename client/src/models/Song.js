export default class Song {
    constructor(obj = { title: " ", artist: "", genre: "" }) {
        Object.assign(this, obj)
        this.genre = this.genre === "" ? [] : this.genre.split('|')
    }
}

Song.path = 'songs'
