import React from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Menu from "./Pages/menu";
import HTP from "./Pages/HTP";
import NG from "./Pages/newGame";
import QuitButton from "./Pages/QuitButton";
import "./App.css";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faBars } from "@fortawesome/free-solid-svg-icons";

const Home = () => {
  return (
    <div className="square homE">
      <span></span>
      <span></span>
      <span></span>
      <div className="content">
        <h1>UPBEAT</h1>
        <a href="/menu">
          <FontAwesomeIcon icon={faBars} />
          
          
        </a>
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
          <Route path="/quitButton" element={<QuitButton />} />

        </Routes>
      </div>
    </Router>
  );
}

export default App;
