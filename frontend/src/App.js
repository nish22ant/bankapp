import "./App.css";
import Header from "./components/Header";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./components/Home";
import Deposit from "./components/Deposit/Deposit";
import PayBills from "./components/PayBills";
import Transfer from "./components/Transfer/Transfer";
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
      </Routes>
      <Footer />
    </BrowserRouter>
  );
}

export default App;
