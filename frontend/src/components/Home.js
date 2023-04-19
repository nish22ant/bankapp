import Hero from "./Hero/Hero";
import { useContext } from "react";
import { ThemeContext } from "./ThemeContext";
export default function Home() {
  const theme = useContext(ThemeContext);
  const darkMode = theme.state.darkMode;
  return (
      <Hero darkMode={darkMode} />
  );
}
