// api.jsx
import axios from "axios";

// Create an Axios instance with default settings
const API = axios.create({
  baseURL: "https://it342-hms-medisync.onrender.com/api", // Ensure the correct base URL here
  headers: {
    "Content-Type": "application/json",
  },
});

// Add a response interceptor for better error handling
API.interceptors.response.use(
  response => response,
  error => {
    console.error("API Error:", error);
    return Promise.reject(error);
  }
);

// Function to handle login request
export const login = async (formData) => {
  try {
    console.log("Login request data:", formData);

    const response = await API.post("/auth/login", formData); // Ensure /auth/login is correct
    console.log("Login response:", response.data);
    return response.data; // Returns the login response data (e.g., token, role)
  } catch (error) {
    console.error("Login error:", error);

    if (error.response) {
      throw new Error(error.response?.data?.message || "Login failed. Please try again.");
    } else if (error.request) {
      throw new Error("Server not responding. Please check your connection.");
    } else {
      throw new Error("An unexpected error occurred. Please try again.");
    }
  }
};

// Function to handle register request
export const register = async (userData, role) => {
  try {
    let endpoint = "/auth";

    // Determine the correct endpoint based on role
    if (role === "Doctor") endpoint += "/doctor/register";
    else if (role === "Patient") endpoint += "/register";
    else if (role === "Staff") endpoint += "/staff/register";
    else throw new Error("Invalid role selected");

    console.log("Register request data:", userData);
    console.log("Register endpoint:", endpoint);

    const response = await API.post(endpoint, userData);
    console.log("Register response:", response.data);
    return response.data;
  } catch (error) {
    console.error("Registration error:", error);

    if (error.response) {
      throw new Error(error.response?.data?.message || "Registration failed. Please try again.");
    } else if (error.request) {
      throw new Error("Server not responding. Please check your connection.");
    } else {
      throw new Error(error.message || "An unexpected error occurred. Please try again.");
    }
  }
};

// Function to fetch services (optional example)
export const fetchServices = async () => {
  try {
    const response = await API.get("/services");
    return response.data;
  } catch (error) {
    console.error("Error fetching services:", error);
    throw new Error(error.response?.data?.message || "Failed to fetch services");
  }
};

export default API;
