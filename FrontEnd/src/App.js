import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Menu from "./Pages/menu";
import HTP from "./Pages/HTP";
import NG from "./Pages/newGame";
import "./App.css";

const Home = () => {
  return (
    <div className="square twitter">
      <span></span>
      <span></span>
      <span></span>
      <div className="content">
        <h1>UPBEAT</h1>
        <a href="/menu">MENU</a>
      </div>
    </div>
  );
};

function App() {
  return (
    <Router>
      <div>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/menu" element={<Menu />} />
          <Route path="/newGame" element={<NG />} />
          <Route path="/howToPlay" element={<HTP />} />

          {/* เพิ่มเส้นทางอื่นๆ ตามต้องการ */}
        </Routes>
      </div>
    </Router>
  );
}

export default App;
