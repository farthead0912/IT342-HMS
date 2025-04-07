import axios from "axios";

const API = axios.create({
  baseURL: "https://your-api-url.com",
  headers: {
    "Content-Type": "application/json",
  },
});

// Secure API call example
export const fetchServices = async () => {
  try {
    const response = await API.get("/services");
    return response.data;
  } catch (error) {
    throw new Error("Failed to fetch services");
  }
};