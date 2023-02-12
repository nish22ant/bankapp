import { useContext } from "react";
import { ThemeContext } from "../../ThemeContext";
import "./footer.css";

const Footer = () => {
  const theme = useContext(ThemeContext);
  const darkMode = theme.state.darkMode;
  return (
    <footer
      className={`custom-border ${
        darkMode
          ? "bg-dark text-light footer-border"
          : "bg-light text-dark footer-border"
      }`}
    >
      Copyright &copy; 2023 | Made with ❤️
    </footer>
  );
};

export default Footer;
