import "./Login.css";
import { useContext } from "react";
import { ThemeContext } from "../ThemeContext";
import LoginForm from "./LoginForm";
const Login = () => {
  const theme = useContext(ThemeContext);
  const darkMode = theme.state.darkMode;

  return (
    <div id="transfer" className={darkMode ? "bg-dark" : "bg-light"}
    style={{paddingTop: "5rem"}}>
      <div>
        <h1
          className={`title text-center mx-auto ${
            darkMode ? "text-light" : "text-dark"
          }`}
        >
          <i style={{ fontSize: "2rem", fontWeight: "700" }}>L</i>ogin._
        </h1>
        <div className="container">
          <LoginForm darkMode={darkMode}/>
        </div>
      </div>
    </div>
  );
};

export default Login;
