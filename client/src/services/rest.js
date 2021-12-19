import axios from 'axios'
import Page from '@/models/Page'

// Basis-URL aller REST-API-Endpunkte
const API_BASE = 'http://localhost:8080/api';

export function loadPage(Song, pageNum = 0, params = {}) {
    return axios
        .get(
            `${API_BASE}/songs`,
            {params: {page: pageNum, ...params}}
        )
        .then(response => {
            console.log(response)
            const page = new Page(Song, response)
            if(page.entities.length || (pageNum === 0)) {
                console.log('rest.load() OK', page)
                return page
            } else {
                return loadPage(Song, pageNum - 1, params)
            }
        })
        .catch(response => {
            console.error('rest.load() error', response)
            return false;
        })
}

export function deleteSong(Song) {
    return axios
        .delete(Song._links.self.href)
}

export function addEntry(Entity, data) {
    return axios
        .post(
            `${API_BASE}/${Entity.path}`,
            data,
            {}
        ).catch(() => {
            console.log("Axios not returnable")
            return false;
        })
}
