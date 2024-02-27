import React from "react";
import "./style.css";
import HTP from "./HTP";
import { Routes, Route,useNavigate } from "react-router-dom";
import NG from "./newGame";

const MenuGame = () => {
  const navigate = useNavigate();
  return (
    <div className="menu">
      <ul className="shadow-button-set">
        <li>
          <button onClick={() => navigate("/newGame")}>New Game</button>
        </li>
        <li>
          <button onClick={() => navigate("/howToPlay")}>How To Play</button>    
        </li>
        <li>
          <button>Quit</button>
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
