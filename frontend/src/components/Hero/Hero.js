import "./Hero.css";
import { Link } from "react-router-dom";
const Hero = (props) => {
  const darkMode = props.darkMode;
  return (
    <div className={darkMode ? "bg-dark text-light" : "bg-light text-dark"}>
      <div className="text-container">
        <h1>ByteCity.</h1>
        <div className="home-link-container">
          <Link className="home-link" to="/sign">
            Sign Up
          </Link>
          <Link className="home-link" to="/login">
            Login
          </Link>
        </div>
      </div>
    </div>
  );
};

export default Hero;
