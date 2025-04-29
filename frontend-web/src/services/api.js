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

export const loginUser = async (username, password) => {
  try {
    const response = await API.post("/login", { username, password });
    return response.data; // This is your user info + maybe a token
  } catch (error) {
    throw new Error(error.response?.data?.message || "Login failed");
  }
};

export const registerUser = async (userData) => {
  try {
    const response = await API.post("/register", userData);
    return response.data;
  } catch (error) {
    throw new Error(error.response?.data?.message || "Registration failed");
  }
};