import "./styleBTQ.css";
import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCheck } from "@fortawesome/free-solid-svg-icons";
import { faXmark } from "@fortawesome/free-solid-svg-icons";

function QuitButton() {
  return (
    <body>
      <div className="squareBT Z">
        <span></span>
        <span></span>
        <span></span>
        <div className="content">
          <h1>YES </h1>

          <a href="about:blank">
            <FontAwesomeIcon icon={faCheck} />
          </a>
        </div>
      </div>
      <div className="squareFBT  square B">
        <span></span>
        <span></span>
        <span></span>
        <div className="content">
          <h1>Are You Sure? </h1>
        </div>
      </div>
      <div className="squareBT A">
        <span></span>
        <span></span>
        <span></span>
        <div className="content">
          <h1>NO </h1>
          <a href="/menu">
            <FontAwesomeIcon icon={faXmark} />
          </a>
        </div>
      </div>
    </body>
  );
}

export default QuitButton;
