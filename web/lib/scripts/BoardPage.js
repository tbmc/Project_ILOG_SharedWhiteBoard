/**
 * Created by tbmc on 04/05/2017.
 */

const VIRTUAL_SIZE = {
  width: 1000,
  height: 500,
};

let canvasInstance;
let canvasList = [];

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
    this.thickness = 11
    
    
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
  
  mouseMove(event) {
    if(!this.mouseIsDown)
      return;
    let canvas = this.canvasElement;
    this.canvasCtx.clearRect(0, 0, canvas.width, canvas.height);
    this.canvasCtx.drawImage(this.clickPosition.image, 0, 0);
    this.draw(event);
  }
  
  mouseUp(event) {
    if(!this.mouseIsDown)
      return;
    this.mouseIsDown = false;

    let coord = {
      p : {px: this.clickPosition.x,
      py: this.clickPosition.y},
      c: this.getXYFromEvent(event)
    };

    let elmtJSON = newFigureDrew(this.type,coord,this.thickness,this.fill,this.color);
    canvasList.push(elmtJSON);
    submit(elmtJSON);
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
    ctx.lineWidth=this.thickness;

    switch(this.type) {
      case "line":
        ctx.moveTo(px, py);
        ctx.lineTo(x, y);
        break;
      case "circle":
        this.drawCircle(px, py, x, y);
        break;
      case "rectangle":
        this.drawRectangle(px, py, x, y);
        break;
    }
    //Fill doesn't work with line type
    if(this.fill && this.type != "line") {
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

  drawRectangle(px, py, x, y) {
    let rw = px - x
    let rh = py - y
    this.canvasCtx.rect(x, y, rw, rh);
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
document.getElementById("buttonRect").onclick = () => {
    canvasInstance.type = "rectangle";
};
document.getElementById("buttonBlack").onclick = () => {
  canvasInstance.color = "#000000";
};
document.getElementById("buttonWhite").onclick = () => {
    canvasInstance.color = "#ffffff";
};
document.getElementById("buttonRed").onclick = () => {
  canvasInstance.color = "#ce423e";
};
document.getElementById("buttonGreen").onclick = () => {
    canvasInstance.color = "#5cb85c";
};
document.getElementById("buttonBlue").onclick = () => {
    canvasInstance.color = "#337ab7";
};
document.getElementById("buttonOrange").onclick = () => {
    canvasInstance.color = "#f0ad4e";
};
document.getElementById("buttonFill").onchange = () => {
  if(canvasInstance.fill){
      canvasInstance.fill = false;
  } else {
      canvasInstance.fill = true;
  }
};
document.getElementById("buttonClear").onclick = () => {
  canvasInstance.clear();
};

//
function newFigureDrew(type, points, thickness, fill, color) {
    var obj = {
        type,
        points,
        thickness,
        fill,
        color
    }

    return JSON.stringify(obj);
}

//Submit infos needed to redraw the canvas element
function submit(elmt) {
    $.post(
        "/api/do-change",
        {
          data: elmt
        },
        submitReturn,
        'json'
    ).fail(function(data) {
        console.log(data);
    });

    function submitReturn(response){
        console.log(response);
    }
};

//Update canvas elements list
function update() {
    $.get(
        "/api/get-changes"
    ).done(function(data) {
        // Add data received to the list
        _.forEach(data, function(x) {
            //Check existence in the list
            var exist = false;
            canvasList.forEach(function(y){
              if(x == y){
                exist = true;
              }
            });
            if(exist){
              //Add an elmt to the list
              canvasList.push(x);
            }
        });
    }).fail(function(data) {
        console.log(data);
    });

    setTimeout(update(),5000);
};
