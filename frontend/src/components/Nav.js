import { Link, useLocation } from "react-router-dom";
import { useContext } from "react";
import { ThemeContext } from "./ThemeContext";
import "../../node_modules/bootstrap/dist/css/bootstrap.css";
import "../styles/nav.css";
import Theme from "./Theme";

export default function Nav() {
  const theme = useContext(ThemeContext);
  const location = useLocation();
  const darkMode = theme.state.darkMode;
  return (
    <nav
      className={`navbar navbar-light bg-light ${
        darkMode ? "navbar-dark bg-dark" : ""
      }`}
    >
      <ul className="nav-bar container-fluid">
        <Link id="logo" to="/">
          <i style={{ fontSize: "2.5rem", fontWeight: "bolder" }}>B</i>yteCity._
        </Link>
        <Link
          className={`nav-link ${
            location.pathname === "/"
              ? darkMode
                ? "active-dark"
                : "active-light"
              : ""
          }`}
          to="/"
        >
          Home
        </Link>
        <Link
          className={`nav-link ${
            location.pathname === "/pay"
              ? darkMode
                ? "active-dark"
                : "active-light"
              : ""
          }`}
          to="/pay"
        >
          Pay Bills
        </Link>
        <Link
          className={`nav-link ${
            location.pathname === "/deposit"
              ? darkMode
                ? "active-dark"
                : "active-light"
              : ""
          }`}
          to="/deposit"
        >
          eDeposit
        </Link>
        <Link
          className={`nav-link ${
            location.pathname === "/transfer"
              ? darkMode
                ? "active-dark"
                : "active-light"
              : ""
          }`}
          to="/transfer"
        >
          Transfer
        </Link>
        <Link
          className={`nav-link ${
            location.pathname === "/add"
              ? darkMode
                ? "active-dark"
                : "active-light"
              : ""
          }`}
          to="/add"
        >
          Add Account
        </Link>
        <Link
          className={`nav-link ${
            location.pathname === "/mini"
              ? darkMode
                ? "active-dark"
                : "active-light"
              : ""
          }`}
          to="/analytics"
        >
          Analytics
        </Link>
        <Link
          className={`nav-link ${
            location.pathname === "/login"
              ? darkMode
                ? "active-dark"
                : "active-light"
              : ""
          }`}
          to="/login"
        >
          Login
        </Link>
        <Link
          className={`nav-link ${
            location.pathname === "/sign"
              ? darkMode
                ? "active-dark"
                : "active-light"
              : ""
          }`}
          to="/sign"
        >
          SignUp
        </Link>
        <Link
          className={`nav-link ${
            location.pathname === "/contact"
              ? darkMode
                ? "active-dark"
                : "active-light"
              : ""
          }`}
          to="/contact"
        >
          Contact
        </Link>
        <Link
          className={`nav-link ${
            location.pathname === "/about"
              ? darkMode
                ? "active-dark"
                : "active-light"
              : ""
          }`}
          to="/about"
        >
          About
        </Link>
        <Theme />
      </ul>
    </nav>
  );
}
