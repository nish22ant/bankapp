import "./SignUp.css";
import { useContext } from "react";
import { ThemeContext } from "../ThemeContext";
import React from "react";
import SignUpForm from "./SignUpForm";
const SignUp = (props) => {
  const theme = useContext(ThemeContext);
  const darkMode = theme.state.darkMode;
  return (
    <div id="transfer" className={darkMode ? "bg-dark" : "bg-light"}>
      <h1
        className={`title text-center mx-auto ${
          darkMode ? "text-light" : "text-dark"
        }`}
      >
        <i style={{ fontSize: "2rem", fontWeight: "700" }}>S</i>ignup._
      </h1>
      <div className="container" style={{ width: "40%" }}>
        <SignUpForm darkMode={darkMode} />
      </div>
    </div>
  );
};

export default SignUp;
