import "./App.css";
import Header from "./components/Header";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./components/Home";
import Deposit from "./components/Deposit/Deposit";
import PayBills from "./components/PayBills/PayBills";
import Transfer from "./components/Transfer/Transfer";
import AddAccount from "./components/AddAccount/AddAccount";
import Contact from "./components/Contact/Contact";
import AboutUs from "./components/AboutUs/AboutUs";
import Footer from "./components/UI/Footer/Footer";

function App() {
  return (
    <BrowserRouter>
      <Header />
      <Routes>
        <Route exact path="/" element={<Home />} />
        <Route exact path="/deposit" element={<Deposit />} />
        <Route exact path="/pay" element={<PayBills />} />
        <Route exact path="/transfer" element={<Transfer />} />
        <Route exact path="/add" element={<AddAccount />} />
        <Route exact path="/contact" element={<Contact />} />
        <Route exact path="/about" element={<AboutUs />} />
      </Routes>
      <Footer />
    </BrowserRouter>
  );
}

export default App;
