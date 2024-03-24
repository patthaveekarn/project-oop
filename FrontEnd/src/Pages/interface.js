import React, { useEffect } from "react";
import "./styleInT.css";
import feather from "feather-icons";
import CHO from "./pic/AAA.png";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faSackDollar } from "@fortawesome/free-solid-svg-icons";
import { faHouseFlag } from "@fortawesome/free-solid-svg-icons";
import { faCircleInfo } from "@fortawesome/free-solid-svg-icons";

import HexGrid from "./HexGrid";

function InterF() {
  useEffect(() => {
    feather.replace();
  }, []);

  return (
    
    <body className="tp">
      <div className="cardD">
        <div className="additional">
          <div className="user-card">
            <div className="level">turn P1</div>
            <img src={CHO} alt="" />
          </div>

          <div className="more-info ">
            <h1>Stan Marsh</h1>
            <div className="stats">
              <div>
                <div className="title">Money</div>
                <i>
                  <FontAwesomeIcon icon={faSackDollar} />
                </i>
                <div className="value">5,312 $</div>
              </div>
              <div>
                <div className="title">City</div>
                <i>
                  <FontAwesomeIcon icon={faHouseFlag} />
                </i>
                <div className="value">28</div>
              </div>
            </div>
          </div>
        </div>
        <div className="general">
          <h1>Stan Marsh</h1>
          <p>
            You are an average all-American person, and despite your crazy
            surroundings, you remain levelheaded. You may have a strange way of
            expressing your love for others, often not being able to control
            what comes out of your mouth.
          </p>
          <span className="more">
            <FontAwesomeIcon
              icon={faCircleInfo}
              size="3x"
              color="#ee786e"
              style={{ marginRight: "10px" }}
            />
          </span>
        </div>
      </div>

      <div className="sixS">
        <HexGrid />
      </div>

      {/* -----------------------------------------------------------------------------------*/}
      <form>
        <h1 className="title">Construction plan</h1>
        <div className="form-group message">
          <textarea
            id="formMessage"
            className="form-control form-control-lg"
            rows="7"
            placeholder="plan here..."
          ></textarea>
        </div>
        <div className="text-center">
          <button type="submit" className="btn btn-primary" tabIndex="-1">
            Submit
          </button>
        </div>
      </form>
    </body>
    
  );
}

export default InterF;
