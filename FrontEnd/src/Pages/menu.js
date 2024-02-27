import React from "react";
import "./style.css";
import HTP from "./HTP";
import { Routes, Route, Link } from "react-router-dom";
import NG from "./newGame";

const MenuGame = () => {
  return (
    <div className="menu">
      <ul className="shadow-button-set">
        <li>
          <Link to="/newGame">New Game</Link>
        </li>

        <li>
          <Link to="/howToPlay">How to Play</Link>
        </li>

        <li>
          <button id="quit">Quit</button>
        </li>
      </ul>
    </div>
  );
};

function Menu() {
  return (
    <div>
      <Routes>
        <Route path="/" element={<MenuGame />} />
        <Route path="/newGame" element={<NG />} />
        <Route path="/howToPlay" element={<HTP />} />
      </Routes>
    </div>
  );
}

export default Menu;
