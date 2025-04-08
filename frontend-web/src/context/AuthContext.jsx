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
  const login = (userData) => {
    try {
      setUser(userData);
      localStorage.setItem("user", JSON.stringify(userData));
      setError(null);
    } catch (e) {
      console.error("Login error:", e);
      setError("Login failed. Please try again.");
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
      console.log("Registering user:", userData);
      // Replace with real registration API logic
      return { success: true };
    } catch (e) {
      console.error("Registration failed:", e);
      setError("Registration failed. Please try again.");
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