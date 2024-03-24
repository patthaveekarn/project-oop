import React, { useEffect, useRef } from "react";

const HexGrid = () => {
  const canvasRef = useRef(null);
  const a = (2 * Math.PI) / 6;
  const r = 30;

  useEffect(() => {
    const canvas = canvasRef.current;
    const ctx = canvas.getContext("2d");

    const drawGrid = (ctx, width, height) => {
      for (
        let y = r, j = 0;
        y + r * Math.sin(a) < height;
        y += 2 ** ((j + 1) % 2) * r * Math.sin(a), j = 0
      ) {
        for (
          let x = r;
          x + r * (1 + Math.cos(a)) < width;
          x += r * (1 + Math.cos(a)), y += (-1) ** j++ * r * Math.sin(a)
        ) {
          drawHexagon(ctx, x, y);
        }
      }
    };

    const drawHexagon = (ctx, x, y) => {
      ctx.beginPath();
      for (let i = 0; i < 6; i++) {
        ctx.lineTo(x + r * Math.cos(a * i), y + r * Math.sin(a * i));
      }
      ctx.closePath();

      ctx.stroke();
    };

    drawGrid(ctx, canvas.width, canvas.height);
  }, [a, r]);

  return <canvas ref={canvasRef} width={800} height={710} />;
};

export default HexGrid;

//------------------------have color---------------------//
// import React, { useEffect, useRef } from "react";

// const HexGrid = () => {
//   const canvasRef = useRef(null);
//   const a = (2 * Math.PI) / 6;
//   const r = 30;

//   useEffect(() => {
//     const canvas = canvasRef.current;
//     const ctx = canvas.getContext("2d");

//     const drawGrid = (ctx, width, height) => {
//       let index = 0;
//       for (
//         let y = r, j = 0;
//         y + r * Math.sin(a) < height;
//         y += 2 ** ((j + 1) % 2) * r * Math.sin(a), j = 0
//       ) {
//         for (
//           let x = r;
//           x + r * (1 + Math.cos(a)) < width;
//           x += r * (1 + Math.cos(a)), y += (-1) ** j++ * r * Math.sin(a)
//         ) {
//           if (index % 2 === 0) {
//             drawHexagon(ctx, x, y, "#cb4a3e"); // สีแดง
//             ctx.stroke();
//           } else {
//             drawHexagon(ctx, x, y, "rgb(8, 222, 154)"); // สีเขียว
//             ctx.stroke();
//           }
//           index++;
//         }
//       }
//     };

//     const drawHexagon = (ctx, x, y, color) => {
//       ctx.beginPath();
//       for (let i = 0; i < 6; i++) {
//         ctx.lineTo(x + r * Math.cos(a * i), y + r * Math.sin(a * i));
//       }
//       ctx.closePath();

//       if (color) {
//         ctx.fillStyle = color; // กำหนดสีเฉพาะเมื่อมีการระบุสี
//         ctx.fill();
//       }
//     };

//     drawGrid(ctx, canvas.width, canvas.height);
//   }, [a, r]);

//   return <canvas ref={canvasRef} width={800} height={710} />;
// };

// export default HexGrid;
