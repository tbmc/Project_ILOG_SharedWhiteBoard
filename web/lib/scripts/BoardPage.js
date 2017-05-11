/**
 * Created by tbmc on 04/05/2017.
 */

const VIRTUAL_SIZE = {
  width: 1000,
  height: 500,
};

let canvasInstance;
let canvasList = [];
let lastId = null;

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

    let coord = [
        { x: this.clickPosition.x, y: this.clickPosition.y },
        this.getXYFromEvent(event)
    ];

    coord[0] = this.getVirtualFromReal(coord[0]);
    coord[1] = this.getVirtualFromReal(coord[1]);

    let elmtJSON = newFigureDrew(this.type,coord,this.thickness,this.fill,this.color);
    canvasList.push(elmtJSON);
    submit(elmtJSON);

    this.clear();
    _.forEach(canvasList,function(e) {
        drawListElmt(e);
    })

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
    ctx.lineWidth = this.thickness;

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
    canvasList = [];
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
document.getElementById("colorPicker").onchange = () => {
    canvasInstance.color = document.getElementById("colorPicker").value;
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
        "/api/get-changes",
        {
            lastId: lastId
        }
    ).done(function(data) {
        // Add data received to the list
        _.forEach(data, function(x) {
            canvasList.push(x);
        });
        lastId = data[data.length - 1].id;
    }).fail(function(data) {
        console.log(data);
    });

    setTimeout(update(),1000);
};

//Draw on canvas a canvas list element
function drawListElmt(JSONelmt){
    let elmt = JSON.parse(JSONelmt);

    //Keeping old user parameters in memory
    let oldThickness = canvasInstance.thickness;
    let oldColor = canvasInstance.color;
    let oldFill = canvasInstance.fill;
    let oldType = canvasInstance.type;


    canvasInstance.thickness = elmt.thickness;
    canvasInstance.color = elmt.color;
    canvasInstance.fill = elmt.fill;
    canvasInstance.type = elmt.type;

    console.log(elmt);

    elmt.points[0] = canvasInstance.getRealFromVirtual(elmt.points[0]);
    elmt.points[1] = canvasInstance.getRealFromVirtual(elmt.points[1]);

    let px = elmt.points[0].x;
    let py = elmt.points[0].y;
    let x = elmt.points[1].x;
    let y = elmt.points[1].y;

    canvasInstance.selectDraw(px, py, x, y);

    //Recovering old parameters
    canvasInstance.thickness = oldThickness;
    canvasInstance.color = oldColor;
    canvasInstance.fill = oldFill;
    canvasInstance.type = oldType;
}