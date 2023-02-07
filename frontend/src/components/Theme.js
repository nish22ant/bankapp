import { useContext } from "react";
import { ThemeContext } from "./ThemeContext";
import LightTheme from "./LightTheme";
import DarkTheme from "./DarkTheme";

export default function Theme() {
  const theme = useContext(ThemeContext);
  const darkMode = theme.state.darkMode;

  const onClick = () => {
    if (darkMode) {
      theme.dispatch({ type: "LIGHTMODE" });
    } else {
      theme.dispatch({ type: "DARKMODE" });
    }
  };

  return (
    <div id={darkMode ? "theme-light" : "theme-dark"} onClick={onClick}>
      {darkMode ? <LightTheme /> : <DarkTheme />}
    </div>
  );
}
