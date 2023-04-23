import "./AboutUs.css";
import { useContext } from "react";
import { ThemeContext } from "../ThemeContext";
const AboutUs = () => {

  const theme = useContext(ThemeContext);
  const darkMode = theme.state.darkMode
  return (
    <div id="about-us" className={darkMode ? "bg-dark text-light" : "bg-light text-dark"}>
      <p>
        <b><i>W</i></b>elcome to ByteCity Bank, a Toronto-based financial institution
        committed to helping you achieve your financial goals. We believe in
        building strong relationships with our customers and communities, and
        our team of experienced professionals is dedicated to delivering
        personalized solutions tailored to your unique needs.
      </p>
      <p>
        We offer a wide range of financial products and services including
        personal and business banking, investment management, mortgage and
        lending, and wealth management. Whether you're just starting out or
        looking to grow your business, we have the tools and expertise to help
        you succeed.
      </p>
      <p>
        At ByteCity Bank, we also believe in giving back to our community.
        That's why we support various charitable initiatives and organizations
        through our corporate social responsibility programs. We believe that
        together, we can make a positive impact in the lives of those around us.
      </p>
      <p>
        What sets us apart is our commitment to leveraging technology to provide
        the best possible banking experience for our customers. We are built and
        powered using leading technology to ensure that you have access to the
        latest and most innovative financial solutions.
      </p>
      <p>Our goal is to help our customers achieve their financial objectives and to build a strong and prosperous community. Thank you for considering ByteCity Bank as your financial partner. We look forward to serving you and building a long-lasting relationship with you.</p>
    </div>
  );
};

export default AboutUs;
