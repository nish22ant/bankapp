import { useContext } from "react";
import { ThemeContext } from "../../ThemeContext";
import "./footer.css";

const Footer = () => {
  const theme = useContext(ThemeContext);
  const darkMode = theme.state.darkMode;
  return (
    <footer className={darkMode ? "bg-dark text-light" : "bg-light text-dark"}>
      Copyright &copy; 2023 | Made with ❤️
    </footer>
  );
};

export default Footer;
