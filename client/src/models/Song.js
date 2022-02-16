export default class Song {
    constructor(obj = { title: " ", artist: "", genre: "" }) {
        Object.assign(this, obj)
        this.genre = this.genre === "" ? [] : this.genre.split('|')
    }

    isNew() {
        return !this._links
    }
}

Song.path = 'songs'
