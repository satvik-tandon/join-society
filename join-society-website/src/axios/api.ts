import axios from "axios";
import toast from "react-hot-toast";

const api = axios.create({
    baseURL: "http://localhost:8080",
    headers: {
        Accept: "application/json"
    }
});

interface ApiRequest {
    endpoint: string;
    params?: object;
    data?: object
}

export const post = async (request: ApiRequest) => {
    try {
        const response = await api.post(request.endpoint, request.data);
        return response;
    } catch (error: any) {
        if (error.response?.data?.error !== '') {
            toast.error(error.response.data.error);
        } else {
            toast.error("Something went wrong!");
        }
        return;
    }
}

export const get = async (request: ApiRequest) => {
    try {
        const response = await api.get(request.endpoint, { params: request.params });
        return response;
    } catch (error: any) {
        if (error.response?.data?.error !== '') {
            toast.error(error.response.data.error);
        } else {
            toast.error("Something went wrong!");
        }
        return;
    }
}

export default api;