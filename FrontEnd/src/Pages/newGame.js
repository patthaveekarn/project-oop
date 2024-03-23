import React, { useState } from "react";
import "./styleCH.css";

import CH1 from "./pic/AAA.png";
import CH2 from "./pic/BBB.png";
import CH3 from "./pic/CCC.png";
import CH4 from "./pic/DDD.png";

function NG() {
  const [selected, setSelected] = useState(false);
  const [selected1, setSelected1] = useState(false);
  const [selected2, setSelected2] = useState(false);
  const [selected3, setSelected3] = useState(false);
  const [selectedCount, setSelectedCount] = useState(0);

  const handleClick = () => {
    if (!selected) {
      setSelected(!selected);
      setSelectedCount(selectedCount + 1);
    }
  };

  const handleClick1 = () => {
    if (!selected1) {
      setSelected1(!selected1);
      setSelectedCount(selectedCount + 1);
    }
  };

  const handleClick2 = () => {
    if (!selected2) {
      setSelected2(!selected2);
      setSelectedCount(selectedCount + 1);
    }
  };

  const handleClick3 = () => {
    if (!selected3) {
      setSelected3(!selected3);
      setSelectedCount(selectedCount + 1);
    }
  };

  return (
    <div className="container">
      <div className={`card ${selected ? "selected" : ""}`}>
        <img src={CH1} alt="Person" className="card__imageA" />
        <p className="card__name">Stan Marsh</p>
        <button
          className={`btn draw-border ${selected ? "selected-btn" : ""}`}
          onClick={handleClick}
          disabled={selectedCount === 2}
        >
          {selected ? (selectedCount === 1 ? "P1" : "P2") : "SELECT"}
        </button>
      </div>

      <div className={`card ${selected1 ? "selected1" : ""}`}>
        <img src={CH2} alt="Person" className="card__imageB" />
        <p className="card__name">Eric Cartman</p>
        <button
          className={`btn draw-border ${selected1 ? "selected1-btn" : ""}`}
          onClick={handleClick1}
          disabled={selectedCount === 2}
        >
          {selected1 ? (selectedCount === 1 ? "P1" : "P2") : "SELECT"}
        </button>
      </div>

      <div className={`card ${selected2 ? "selected2" : ""}`}>
        <img src={CH3} alt="Person" className="card__imageC" />
        <p className="card__name">Wendy Testaburger</p>
        <button
          className={`btn draw-border ${selected2 ? "selected2-btn" : ""}`}
          onClick={handleClick2}
          disabled={selectedCount === 2}
        >
          {selected2 ? (selectedCount === 1 ? "P1" : "P2") : "SELECT"}
        </button>
      </div>

      <div className={`card ${selected3 ? "selected3" : ""}`}>
        <img src={CH4} alt="Person" className="card__imageD" />
        <p className="card__name">Tweek Tweek </p>
        <button
          className={`btn draw-border ${selected3 ? "selected3-btn" : ""}`}
          onClick={handleClick3}
          disabled={selectedCount === 2}
        >
          {selected3 ? (selectedCount === 1 ? "P1" : "P2") : "SELECT"}
        </button>
      </div>

      {selectedCount === 2 && (
        <div className="card">
          <div className="card__imageG"> </div>
          <p className="card__name">Are You Ready?</p>
          <a href="/interface">
            <button className="btn draw-border">YES</button>
          </a>

          <a href="/newGame">
            <button className="btn draw-border">NO</button>
          </a>
        </div>
      )}
    </div>
  );
}

export default NG;
