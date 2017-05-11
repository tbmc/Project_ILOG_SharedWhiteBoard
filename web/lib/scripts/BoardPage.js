/**
 * Created by tbmc on 04/05/2017.
 */

const VIRTUAL_SIZE = {
  width: 1000,
  height: 500,
};

let canvasInstance;

window.onload = function() {
  initCanvas();
  
};

function initCanvas() {
  canvasInstance = new Canvas();
}

class Canvas {
  
  constructor(canvasElement = document.getElementById("canvas")) {
    this.canvasElement = canvasElement;
    this.canvasCtx = canvasElement.getContext("2d");
    
    this.initCanvasEvents();
  }
  
  crossProduct(coordinate, virtualSize, size) {
    return (coordinate / virtualSize) * size;
  }
  
  getRealFromVirtual(virtualCoordinate, virtualSize = VIRTUAL_SIZE) {
    let out = {};
    out.x = this.crossProduct(virtualCoordinate.x, virtualSize.width, this.canvasElement.width);
    out.y = this.crossProduct(virtualCoordinate.y, virtualSize.height, this.canvasElement.height);
    return out;
  }
  
  getVirtualFromReal(realCoordinate, virtualSize = VIRTUAL_SIZE) {
    let out = {};
    out.x = this.crossProduct(realCoordinate.x, this.canvasElement.width, virtualSize.width);
    out.y = this.crossProduct(realCoordinate.y, this.canvasElement.height, virtualSize.height);
    return out;
  }
  
  initCanvasEvents() {
    // Values by default
    this.type = "line";
    this.color = "#000000";
    this.fill = false;
    
    
    this.clickPosition = null;
    this.mouseIsDown = false;
    let canvas = this.canvasElement;
    canvas.addEventListener("mousedown", e => this.mouseDown(e));
    window.addEventListener("mousemove", e => this.mouseMove(e));
    window.addEventListener("mouseup",   e => this.mouseUp(e));
  }
  
  mouseDown(event) {
    this.mouseIsDown = true;
    let image = new Image();
    image.src = this.canvasElement.toDataURL();
    let o = this.getXYFromEvent(event);
    this.clickPosition = {
      x: o.x,
      y: o.y,
      image,
    };
  }
  
  mouseMove(e) {
    if(!this.mouseIsDown)
      return;
    let canvas = this.canvasElement;
    this.canvasCtx.clearRect(0, 0, canvas.width, canvas.height);
    this.canvasCtx.drawImage(this.clickPosition.image, 0, 0);
    this.draw(e);
  }
  
  mouseUp(e) {
    if(!this.mouseIsDown)
      return;
    this.mouseIsDown = false;
    let p = {
      x: this.clickPosition.x,
      y: this.clickPosition.y,
    };
    newFigureDrew(this.type, p, this.getXYFromEvent(event));
  }
  
  draw(event) {
    let o = this.getXYFromEvent(event);
    let px = this.clickPosition.x;
    let py = this.clickPosition.y;
    this.selectDraw(px, py, o.x, o.y);
  }
  
  getXYFromEvent(event) {
    let rect = this.canvasElement.getBoundingClientRect();
    return {
      x: event.clientX - rect.left,
      y: event.clientY - rect.top,
    };
  }
  
  selectDraw(px, py, x, y) {
    let canvas = this.canvasElement;
    let ctx = this.canvasCtx;
    ctx.beginPath();
    ctx.fillStyle = this.color;
    ctx.strokeStyle = this.color;
    switch(this.type) {
      case "line":
        ctx.moveTo(px, py);
        ctx.lineTo(x, y);
        break;
      case "circle":
        this.drawCircle(px, py, x, y);
        break;
        
    }
    if(this.fill) {
      ctx.fill();
    }else {
      ctx.stroke();
    }
  }
  
  drawCircle(px, py, x, y) {
    let cx = (px + x) / 2;
    let cy = (py + y) / 2;
    let xx = Math.pow(x - px, 2);
    let yy = Math.pow(y - py, 2);
    let r = Math.sqrt(xx + yy) / 2;
    this.canvasCtx.arc(cx, cy, r, 0, 2 * Math.PI);
  }
  
  
  clear() {
    let canvas = this.canvasElement;
    this.canvasCtx.clearRect(0, 0, canvas.width, canvas.height);
  }
  
}

document.getElementById("buttonLine").onclick = () => {
  canvasInstance.type = "line";
};
document.getElementById("buttonCircle").onclick = () => {
  canvasInstance.type = "circle";
};
document.getElementById("buttonBlack").onclick = () => {
  canvasInstance.color = "#000000";
};
document.getElementById("buttonRed").onclick = () => {
  canvasInstance.color = "#ff0000";
};
document.getElementById("buttonClear").onclick = () => {
  canvasInstance.clear();
};


function newFigureDrew(type, beginPoint, endPoint) {

}

