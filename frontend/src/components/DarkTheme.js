import { Fragment } from "react";
import { useContext } from "react";
import { ThemeContext } from "./ThemeContext";

export default function DarkTheme() {
  const theme = useContext(ThemeContext);
  const darkMode = theme.state.darkMode;
  return (
    <Fragment
      className={`dark-theme ${darkMode ? "theme-light" : "theme-dark"}`}
    >
      <svg
        stroke="currentColor"
        fill="none"
        stroke-width="2"
        viewBox="0 0 24 24"
        stroke-linecap="round"
        stroke-linejoin="round"
        class="h-4 w-4"
        height="1em"
        width="1em"
        xmlns="http://www.w3.org/2000/svg"
      >
        <path d="M21 12.79A9 9 0 1 1 11.21 3 7 7 0 0 0 21 12.79z"></path>
      </svg>
    </Fragment>
  );
}
