import Persistent from '@/models/Persistent'

export default class Song extends Persistent {

    constructor(obj) {
        super(obj)
    }

}

Song.path = 'songs'
