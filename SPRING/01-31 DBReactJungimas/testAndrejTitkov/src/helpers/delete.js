import axios from "axios";
import { getOne } from "./get"

const url = "http://localhost:5000/api/books";

export const deleteData = async (id) => {
    const { title } = await getOne(id);
    const confirmed = window.confirm(
        `Do you really want to delete book ${title}?`
    )

    if(!confirmed) return;

    const response = await axios.delete(`${url}/${id}`);
    return response.data;
}