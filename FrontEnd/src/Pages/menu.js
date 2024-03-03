import React from "react";
import "./style.css";
import { Routes, Route } from "react-router-dom";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faPlay } from "@fortawesome/free-solid-svg-icons";
import { faLightbulb } from "@fortawesome/free-solid-svg-icons";
import { faRightFromBracket } from '@fortawesome/free-solid-svg-icons';
import QuitButton from "./QuitButton";
import HTP from "./HTP";
import NG from "./newGame";
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
          <h1>QUIT </h1>
          <a href="/quitButton">
            <FontAwesomeIcon icon={faRightFromBracket} />
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
        <Route path="/quitButton" element={<QuitButton />} />
      </Routes>
    </div>
  );
}

export default Menu;
