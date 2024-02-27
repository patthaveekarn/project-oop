import React from "react";
import { BrowserRouter as Router, Route, Routes, Link } from "react-router-dom";
import Menu from "./Pages/menu";
import HTP from "./Pages/HTP";
import NG from "./Pages/newGame";
const Home = () => {
  return (
    <div>
      <h1>...... Name Game ......</h1>
      <Link to="/menu">Go to Menu</Link>
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
