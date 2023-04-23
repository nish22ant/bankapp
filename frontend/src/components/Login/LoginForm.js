import { useRef, useState } from "react";
import ReCAPTCHA from "react-google-recaptcha";
const LoginForm = (props) => {
  const darkMode = props.darkMode;
  const captchaRef = useRef(null);
  const [captchaCompleted, setCaptchaCompleted] = useState(false);

  const handleCaptchaChange = (value) => {
    if(value) {
        setCaptchaCompleted(true);
    }
}
const handleSubmit = (event) => {
    event.preventDefault();
    console.log("Submit Handled");
}

  return (
    <form
      className={`p-3 form-border ${
        !darkMode ? "border-dark" : "border-secondary"
      }`}
      onSubmit={handleSubmit}
    >
      <div className="form-group">
        <input placeholder="Username" className={`form-control ${
            darkMode ? "bg-dark text-light" : "bg-light"
          }`}/>
      </div>
      <div className="form-group">
        <input placeholder="Password" className={`form-control ${
            darkMode ? "bg-dark text-light" : "bg-light"
          }`}/>
      </div>
      <ReCAPTCHA
        sitekey="6LdEM5YlAAAAAHBv6pw1stXR2CNMzTW1Gj-H_Kki"
        ref={captchaRef}
        className="custom-recaptcha"
        onChange={handleCaptchaChange}
      />
      <button
        type="submit"
        className={`btn ${
          !darkMode
            ? "btn-dark text-light btn-outline-light mx-auto"
            : "btn-light text-dark mx-auto"
        }`}
        disabled={!captchaCompleted}
      >
        Submit
      </button>
    </form>
  );
};

export default LoginForm;
