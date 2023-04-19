import { useRef } from "react";
import ReCAPTCHA from "react-google-recaptcha";
const ContactForm = (props) => {
  const captchaRef = useRef(null);
  const darkMode = props.darkMode;
  const handleSubmit = (event) => {
    event.preventDefault();
    const token = captchaRef.current.getValue();
  };
  return (
    <form
      className={`p-3 form-border ${
        !darkMode ? "border-dark" : "border-secondary"
      }`}
      onSubmit={handleSubmit}
    >
      <div className="form-group">
        <input
          type="text"
          id="name"
          name="name"
          placeholder="Name"
          className={`form-control ${
            darkMode ? "bg-dark text-light" : "bg-light"
          }`}
        />
      </div>
      <div className="form-group">
        <input
          type="text"
          id="email"
          name="email"
          placeholder="Email"
          className={`form-control ${
            darkMode ? "bg-dark text-light" : "bg-light"
          }`}
        />
      </div>
      <div className="form-group">
        <textarea
          placeholder="Message"
          className={`form-control ${
            darkMode ? "bg-dark text-light" : "bg-light"
          }`}
        />
      </div>
      <ReCAPTCHA
        sitekey="6LdEM5YlAAAAAHBv6pw1stXR2CNMzTW1Gj-H_Kki"
        ref={captchaRef}
        className="custom-recaptcha"
      />
      <div>
        {" "}
        <button
          type="submit"
          className={`btn ${
            !darkMode
              ? "btn-dark text-light btn-outline-light mx-auto"
              : "btn-light text-dark mx-auto"
          }`}
        >
          Submit
        </button>
      </div>
    </form>
  );
};

export default ContactForm;
