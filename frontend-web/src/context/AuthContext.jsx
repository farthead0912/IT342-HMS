import { createContext, useContext, useState, useEffect } from "react";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Check if a user is stored in localStorage
    const storedUser = localStorage.getItem("user");
    if (storedUser) {
      try {
        setUser(JSON.parse(storedUser));
      } catch (e) {
        console.error("Failed to parse stored user:", e);
        setError("Invalid user data");
        localStorage.removeItem("user");
      }
    }
    setLoading(false);
  }, []);

  // Login function
  const login = async (username, password) => {
    try {
      const userData = await loginUser(username, password);
      setUser(userData);
      localStorage.setItem("user", JSON.stringify(userData));
      setError(null);
    } catch (e) {
      console.error("Login error:", e);
      setError(e.message);
    }
  };

  // Logout function
  const logout = () => {
    try {
      setUser(null);
      localStorage.removeItem("user");
      setError(null);
    } catch (e) {
      console.error("Logout error:", e);
      setError("Logout failed. Please try again.");
    }
  };

  // Register function (dummy)
  const register = async (userData) => {
    try {
      const response = await registerUser(userData);
      return { success: true, data: response };
    } catch (e) {
      console.error("Registration failed:", e);
      setError(e.message);
      return { success: false, error: e.message };
    }
  };

  const value = {
    user,
    setUser,
    isAuthenticated: !!user,
    loading,
    error,
    login,
    logout,
    register,
  };

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  );
};

// Custom hook
export const useAuth = () => useContext(AuthContext);