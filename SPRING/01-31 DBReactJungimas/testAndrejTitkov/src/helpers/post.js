import axios from "axios";

const url = "http://localhost:5000/api/books";

export const postdata = async (data) => {
    let response = await axios.post(url, data);
    return response.data;
}