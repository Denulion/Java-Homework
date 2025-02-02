import axios from "axios";

const url = "http://localhost:5000/api/books";

export const getAllData = async () => {
    const response = await axios.get(url);
    return response.data;
}

export const getOne = async (id) => {
    const response = await axios.get(`${url}/${id}`);
    return response.data;
}

export const getCategory = async () => {
    try {
        const response = await axios.get(url)
        const categories = response.data.map((item) => item.category)
        return categories;
    } catch (error) {
        console.error("Error while getting categories", error);
        throw error;
    }
}