export default class Song {
    constructor(obj) {
        Object.assign(this, obj)
    }
}

Song.path = 'songs'