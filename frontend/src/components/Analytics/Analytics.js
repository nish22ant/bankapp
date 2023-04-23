import "./Analytics.css";
import { useContext } from "react";
import { ThemeContext } from "../ThemeContext";
const Analytics = () => {
  const theme = useContext(ThemeContext);
  const darkMode = theme.state.darkMode;
  return (
    <div
      className={`analytics ${
        darkMode ? "bg-dark text-light" : "bg-light text-dark"
      }`}
    >
      <h1>Under Development</h1>
    </div>
  );
};

export default Analytics;
