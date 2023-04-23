const SignUpFormPartOne = (props) => {
  const darkMode = props.darkMode;
  return (
    <form
      className={`p-3 form-border ${
        !darkMode ? "border-dark" : "border-secondary"
      }`}
    >
      <div className="form-group">
        <div className="row">
          <div className="col">
            <input
              type="text"
              className={`form-control ${
                darkMode ? "bg-dark text-light" : "bg-light"
              }`}
              placeholder="Username"
              name="userName"
            />
          </div>
        </div>
      </div>
      <div className="form-group">
        <div className="row">
          <div className="col">
            <input
              type="text"
              className={`form-control ${
                darkMode ? "bg-dark text-light" : "bg-light"
              }`}
              placeholder="First Name"
              name="firstName"
            />
          </div>
          <div className="col">
            <input
              type="text"
              className={`form-control ${
                darkMode ? "bg-dark text-light" : "bg-light"
              }`}
              placeholder="Last Name"
              name="lastName"
            />
          </div>
        </div>
      </div>
      <div className="form-group">
        <div className="row">
          <div className="col">
            <input
              type="text"
              className={`form-control ${
                darkMode ? "bg-dark text-light" : "bg-light"
              }`}
              placeholder="Password"
              name="password"
            />
          </div>
          <div className="col">
            <input
              type="text"
              className={`form-control ${
                darkMode ? "bg-dark text-light" : "bg-light"
              }`}
              placeholder="Retype-Password"
              name="passwordAgain"
            />
          </div>
        </div>
      </div>
      <div className="form-group">
        <div className="row">
          <div className="col">
            <input
              type="text"
              className={`form-control ${
                darkMode ? "bg-dark text-light" : "bg-light"
              }`}
              onChange={(e) => console.log(e.target.value)}
              onFocus={(e) => (e.target.type = "date")}
              onBlur={(e) => (e.target.type = "text")}
              placeholder="Date of Birth"
              name="dob"
            />
          </div>
        </div>
      </div>
      <div className="form-group">
        <div className="row">
          <div className="col">
            <input
              type="email"
              className={`form-control ${
                darkMode ? "bg-dark text-light" : "bg-light"
              }`}
              placeholder="Email"
              name="email"
            />
          </div>
          <div className="col">
            <input
              type="number"
              className={`form-control ${
                darkMode ? "bg-dark text-light" : "bg-light"
              }`}
              placeholder="Phone Number"
              name="phoneNumber"
            />
          </div>
        </div>
      </div>
      <div className="form-group">
        <div className="row">
          <div className="col">
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
        </div>
      </div>
    </form>
  );
};

export default SignUpFormPartOne;
