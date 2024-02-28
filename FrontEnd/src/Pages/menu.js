import React from "react";
import "./style.css";
import HTP from "./HTP";
import { Routes, Route } from "react-router-dom";
import NG from "./newGame";
import About from "./About";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlay } from "@fortawesome/free-solid-svg-icons";
import { faLightbulb } from "@fortawesome/free-solid-svg-icons";
import { faAddressCard } from "@fortawesome/free-solid-svg-icons";

const MenuGame = () => {
  return (
    <body>
      <div className="square A">
        <span></span>
        <span></span>
        <span></span>
        <div className="content">
          <h1>NEW GAME </h1>
          <a href="/newGame">
            <FontAwesomeIcon icon={faPlay} />
          </a>
        </div>
      </div>

      <div className="square B">
        <span></span>
        <span></span>
        <span></span>
        <div className="content">
          <h1>HOW TO PLAY </h1>
          <a href="/howToPlay">
            <FontAwesomeIcon icon={faLightbulb} />
          </a>
        </div>
      </div>

      <div className="square C">
        <span></span>
        <span></span>
        <span></span>
        <div className="content">
          <h1>INFO </h1>
          <a href="/about">
            <FontAwesomeIcon icon={faAddressCard} />
          </a>
        </div>
      </div>
    </body>
  );
};

function Menu() {
  return (
    <div>
      <Routes>
        <Route path="/" element={<MenuGame />} />
        <Route path="/newGame" element={<NG />} />
        <Route path="/howToPlay" element={<HTP />} />
        <Route path="/about" element={<About />} />
      </Routes>
    </div>
  );
}

export default Menu;
