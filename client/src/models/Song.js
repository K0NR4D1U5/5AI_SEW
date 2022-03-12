import Persistent from '@/models/Persistent'
import Artist from "@/models/Artist"

export default class Song extends Persistent {

    constructor(obj) {
        super(obj)

        if (this.isNew()) {
            this.artist = new Artist()
            this.genres = []
        }

    }

}

Song.path = 'songs'
