import axios from "axios";
import { APIURL } from "./client/constants/GlobalConstants";

export const REQUEST_TYPE = {
    POST: 'POST',
    GET: 'GET',
    DELETE: 'DELETE',
    PATCH: 'PATCH',
    PUT: 'PUT',
}

export const serverRequest = (method, url, data) => {
    console.log(APIURL + url)
    return axios({
        method,
        url: APIURL + url,
        data
    })
}