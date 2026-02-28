const canvas = document.getElementById("gameCanvas");
const ctx = canvas.getContext("2d");

const TILE_SIZE = 120;
const TILE_MARGIN = 15;
const RADIUS = 15;

const COLORS = {
    0: "rgba(238,228,218,0.35)",
    2: "#EEE4DA",
    4: "#EDE0C8",
    8: "#F2B179",
    16: "#F59563",
    32: "#F67C5F",
    64: "#F65E3B",
    128: "#EDCF72",
    256: "#EDCC61",
    512: "#EDC850",
    1024: "#EDC53F",
    2048: "#EDC22E"
};

const IMAGES = {
    2: "assets/Spermicide.png",
    4: "assets/fertility-awarenessBasedMethods.png",
    8: "assets/Withdrawl.png",
    16: "assets/femaleCondom.png",
    32: "assets/maleCondom.png",
    64: "assets/Ring.png",
    128: "assets/Patch.png",
    256: "assets/Pill.png",
    512: "assets/Injectable.png",
    1024: "assets/IUD.png",
    2048: "assets/Implant.png"
};

let board = [
    [0,0,0,0],
    [0,0,0,0],
    [0,0,0,0],
    [0,0,0,0]
];

let score = 0;

function drawRoundedRect(x, y, w, h, r, color) {
    ctx.fillStyle = color;
    ctx.beginPath();
    ctx.moveTo(x+r, y);
    ctx.arcTo(x+w, y, x+w, y+h, r);
    ctx.arcTo(x+w, y+h, x, y+h, r);
    ctx.arcTo(x, y+h, x, y, r);
    ctx.arcTo(x, y, x+w, y, r);
    ctx.fill();
}

function drawBoard() {
    ctx.clearRect(0,0,canvas.width,canvas.height);

    for (let r=0; r<4; r++) {
        for (let c=0; c<4; c++) {
            let value = board[r][c];
            let x = c * (TILE_SIZE + TILE_MARGIN) + TILE_MARGIN;
            let y = r * (TILE_SIZE + TILE_MARGIN) + TILE_MARGIN;

            drawRoundedRect(x, y, TILE_SIZE, TILE_SIZE, RADIUS, COLORS[value]);

            if (value !== 0) {
                let img = new Image();
                img.src = IMAGES[value];
                img.onload = () => {
                    ctx.drawImage(img, x, y, TILE_SIZE, TILE_SIZE);
                };
            }
        }
    }

    document.getElementById("score-value").textContent = score;
}

function addRandomTile() {
    let empty = [];
    for (let r=0; r<4; r++)
        for (let c=0; c<4; c++)
            if (board[r][c] === 0) empty.push([r,c]);

    if (empty.length === 0) return;

    let [r,c] = empty[Math.floor(Math.random()*empty.length)];
    board[r][c] = 2;
}

function slide(row) {
    row = row.filter(v => v !== 0);
    for (let i=0; i<row.length-1; i++) {
        if (row[i] === row[i+1]) {
            row[i] *= 2;
            score += row[i];
            row[i+1] = 0;
        }
    }
    row = row.filter(v => v !== 0);
    while (row.length < 4) row.push(0);
    return row;
}

function moveLeft() {
    for (let r=0; r<4; r++) board[r] = slide(board[r]);
}

function moveRight() {
    for (let r=0; r<4; r++) board[r] = slide(board[r].reverse()).reverse();
}

function moveUp() {
    for (let c=0; c<4; c++) {
        let col = [board[0][c], board[1][c], board[2][c], board[3][c]];
        col = slide(col);
        for (let r=0; r<4; r++) board[r][c] = col[r];
    }
}

function moveDown() {
    for (let c=0; c<4; c++) {
        let col = [board[0][c], board[1][c], board[2][c], board[3][c]];
        col = slide(col.reverse()).reverse();
        for (let r=0; r<4; r++) board[r][c] = col[r];
    }
}

document.addEventListener("keydown", e => {
    let old = JSON.stringify(board);

    if (e.key === "ArrowLeft") moveLeft();
    if (e.key === "ArrowRight") moveRight();
    if (e.key === "ArrowUp") moveUp();
    if (e.key === "ArrowDown") moveDown();

    if (JSON.stringify(board) !== old) {
        addRandomTile();
        drawBoard();
    }
});

addRandomTile();
addRandomTile();
drawBoard();
