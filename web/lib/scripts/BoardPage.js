/**
 * Created by tbmc on 04/05/2017.
 */

/**
 * Canvas virtual size
 * Size of the canvas server sided (static size)
 * Helps to get corresponding coordinates when resizing the canvas client sided (dynamic size)
 */
const VIRTUAL_SIZE = {
  width: 1000,
  height: 500,
};

//Instance of Canvas (not initialized)
let canvasInstance;

//Array to stock all the changes made on the canvas
let canvasList = [];

//Array to stock the id of the last element server sided added to the canvasList
let lastId = null;


window.onload = function() {
  initCanvas();
  setInterval(function() {
    update();
  }, 500);
  update();
};

//Canvas initialisation
function initCanvas() {
  canvasInstance = new Canvas();
}

class Canvas {
  //Canvas Constructor
  constructor(canvasElement = document.getElementById("canvas")) {
    this.canvasElement = canvasElement;
    this.canvasCtx = canvasElement.getContext("2d");
    
    this.initCanvasEvents();
  }

  crossProduct(coordinate, virtualSize, size) {
    return (coordinate / virtualSize) * size;
  }

  //Calculate real cordinates out of virtual coordinates using cross product
  getRealFromVirtual(virtualCoordinate, virtualSize = VIRTUAL_SIZE) {
    let out = {};
    out.x = this.crossProduct(virtualCoordinate.x, virtualSize.width, this.canvasElement.width);
    out.y = this.crossProduct(virtualCoordinate.y, virtualSize.height, this.canvasElement.height);
    return out;
  }

  //Calculate virtual cordinates out of real coordinates using cross product
  getVirtualFromReal(realCoordinate, virtualSize = VIRTUAL_SIZE) {
    let out = {};
    out.x = this.crossProduct(realCoordinate.x, this.canvasElement.width, virtualSize.width);
    out.y = this.crossProduct(realCoordinate.y, this.canvasElement.height, virtualSize.height);
    return out;
  }

  //Canvas Events and Options Initialisation
  initCanvasEvents() {
    // Values by default
    this.canvasCtx.lineCap = "round";
    this.type = "LINE";
    this.color = "#000000";
    this.fill = false;
    this.thickness = 5;
    
    this.clickPosition = null;
    this.mouseIsDown = false;
    let canvas = this.canvasElement;
    canvas.addEventListener("mousedown", e => this.mouseDown(e));
    window.addEventListener("mousemove", e => this.mouseMove(e));
    window.addEventListener("mouseup", e => this.mouseUp(e));
  }

  //Actions on mouse down
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
    this.pencilArray = [{ x: o.x, y: o.y }];
  }

  //Actions on mouse move
  mouseMove(event) {
    if(!this.mouseIsDown)
      return;
    let canvas = this.canvasElement;
    if(this.type != "PENCIL") {
      this.canvasCtx.clearRect(0, 0, canvas.width, canvas.height);
      this.canvasCtx.drawImage(this.clickPosition.image, 0, 0);
    }
    this.draw(event);
  }

  //Actions on mouse up
  mouseUp(event) {
    if(!this.mouseIsDown)
      return;
    this.mouseIsDown = false;
    
    let coordinates;
    if(this.type == "PENCIL") {
      coordinates = this.pencilArray;
    } else {
      coordinates = [
        { x: this.clickPosition.x, y: this.clickPosition.y },
        this.getXYFromEvent(event)
      ];
      coordinates[0] = this.getVirtualFromReal(coordinates[0]);
      coordinates[1] = this.getVirtualFromReal(coordinates[1]);
    }
    
    let elementJSON = newFigureDrew(this.type, coordinates, this.thickness, this.fill, this.color);
    canvasList.push(elementJSON);
    submit(elementJSON);
    
    this.clear();
    _.forEach(canvasList, function(e) {
      drawListElmt(e);
    })
  }

  //Drawing function
  draw(event) {
    let o = this.getXYFromEvent(event);
    let px = this.clickPosition.x;
    let py = this.clickPosition.y;
    this.selectDraw(px, py, o.x, o.y);
    
    if(this.type == "PENCIL") {
      // let image = new Image();
      // image.src = this.canvasElement.toDataURL();
      let p = this.clickPosition;
      let d = Math.sqrt(Math.pow(o.x - p.x, 2) + Math.pow(o.y - p.y, 2));
      if(d > this.thickness) {
        this.clickPosition = {
          x: o.x,
          y: o.y,
          // image,
        };
        this.pencilArray.push(o);
      }
    }
  }

  //Get correct current x,y position of the mouse depending on the size of the canvas
  getXYFromEvent(event) {
    let rect = this.canvasElement.getBoundingClientRect();
    let size = { width: rect.width, height: rect.height };
    let pos = {
      x: event.clientX - rect.left,
      y: event.clientY - rect.top,
    };
    return this.getRealFromVirtual(pos, size);
  }

  //Draw depending of the type of drawing chosen for the canvas
  selectDraw(px, py, x, y) {
    let canvas = this.canvasElement;
    let ctx = this.canvasCtx;
    ctx.beginPath();
    ctx.fillStyle = this.color;
    ctx.strokeStyle = this.color;
    ctx.lineWidth = this.thickness;
    
    switch(this.type) {
      case "LINE":
        ctx.moveTo(px, py);
        ctx.lineTo(x, y);
        break;
      case "CIRCLE":
        this.drawCircle(px, py, x, y);
        break;
      case "RECTANGLE":
        this.drawRectangle(px, py, x, y);
        break;
      case "PENCIL":
        ctx.moveTo(px, py);
        ctx.lineTo(x, y);
        break;
    }
    //Fill doesn't work with line and pencil types
    if(this.fill && this.type != "LINE" && this.type != "PENCIL") {
      ctx.fill();
    } else {
      ctx.stroke();
    }
  }

  //Formula used to draw a circle
  drawCircle(px, py, x, y) {
    let cx = (px + x) / 2;
    let cy = (py + y) / 2;
    let xx = Math.pow(x - px, 2);
    let yy = Math.pow(y - py, 2);
    let r = Math.sqrt(xx + yy) / 2;
    this.canvasCtx.arc(cx, cy, r, 0, 2 * Math.PI);
  }

  //Formula used to draw a rectangle
  drawRectangle(px, py, x, y) {
    let rw = px - x
    let rh = py - y
    this.canvasCtx.rect(x, y, rw, rh);
  }

  //Clearing the canvas
  clear() {
    let canvas = this.canvasElement;
    this.canvasCtx.clearRect(0, 0, canvas.width, canvas.height);
  }

  //Clearing the canvas and adding it as a new change to the server
  changeClear() {
    let canvas = this.canvasElement;
    this.canvasCtx.clearRect(0, 0, canvas.width, canvas.height);
    
    let elmtJSON = newFigureDrew("CLEAR", null, null, null, null);
    canvasList.push(elmtJSON);
    submit(elmtJSON);
  }
}
//Changing type of drawing of the canvas to Line
$("#buttonLine").click(() => {
  canvasInstance.type = "LINE";
});

//Changing type of drawing of the canvas to Circle
$("#buttonCircle").click(() => {
  canvasInstance.type = "CIRCLE";
});

//Changing type of drawing of the canvas to Rectangle
$("#buttonRect").click(() => {
  canvasInstance.type = "RECTANGLE";
});

//Changing type of drawing of the canvas to Pencil
$("#buttonPencil").click(() => {
  canvasInstance.type = "PENCIL";
});

//Enable/Disable Fill option of the canvas
$("#buttonFill").on("change", () => {
  if(canvasInstance.fill) {
    canvasInstance.fill = false;
  } else {
    canvasInstance.fill = true;
  }
});

//Clearing the canvas and adding it as a new change to the server
$("#buttonClear").click(() => {
  canvasInstance.changeClear();
});

//Changing line size of the canvas
$("#inputLineSize").on("change", () => {
  let v = $("#inputLineSize").val();
  let n = parseInt(v);
  if(!isNaN(n)) {
    canvasInstance.thickness = n;
  }
});

$(function() {
  $("#inputLineSize").val(canvasInstance.thickness);
});

//Changing color of what is drawn on the canvas
$("#colorPicker").on("change", () => {
  console.log($("#colorPicker").val());
  canvasInstance.color = $("#colorPicker").val();
});


$("#canvas").on("selectionstart", preventDefault);
$("#canvas").on("dragstart", preventDefault);

//Default event of an action is blocked
function preventDefault(event) {
  if(event && event.preventDefault) {
    event.preventDefault();
  }
  return false;
}

//Creating a json object out of the infos of the canvas element to add as change
function newFigureDrew(type, points, thickness, fill, color) {
  var obj = {
    type,
    points,
    thickness,
    fill,
    color
  };
  
  return obj;
}

//Submit infos needed to redraw the canvas element
function submit(elmt) {
  $.post(
    "api/do-change",
    { data: JSON.stringify(elmt) }
  ).fail(function(data) {
    console.log(data);
  }).done(function(data) {
    console.log(data);
  });
  
};

//Update canvas elements list
function update() {
  $.get(
    "api/get-changes",
    {
      lastId: lastId
    }
  ).done(function(data) {
    // Add data received to the list
    _.forEach(data, function(x) {
      canvasList.push(x);
      drawListElmt(x);
    });
    if(data.length > 0) {
      lastId = data[data.length - 1].id;
    }
  }).fail(function(data) {
    console.log(data);
  });
};

//Draw on canvas a canvas list element
function drawListElmt(elmt) {
  if(elmt.type == "CLEAR") {
    canvasInstance.clear();
    return;
  }
  
  //Keeping old user parameters in memory
  let oldThickness = canvasInstance.thickness;
  let oldColor = canvasInstance.color;
  let oldFill = canvasInstance.fill;
  let oldType = canvasInstance.type;
  
  
  canvasInstance.thickness = elmt.thickness;
  canvasInstance.color = elmt.color;
  canvasInstance.fill = elmt.fill;
  canvasInstance.type = elmt.type;
  
  if(elmt.type != "PENCIL") {
    let pointBegin = canvasInstance.getRealFromVirtual(elmt.points[0]);
    let pointEnd = canvasInstance.getRealFromVirtual(elmt.points[1]);
    canvasInstance.selectDraw(pointBegin.x, pointBegin.y, pointEnd.x, pointEnd.y)
  } else {
    for(let i = 0, l = elmt.points.length; i < l - 1; i++) {
      let pointBegin = canvasInstance.getRealFromVirtual(elmt.points[i]);
      let pointEnd = canvasInstance.getRealFromVirtual(elmt.points[i + 1]);
      canvasInstance.selectDraw(pointBegin.x, pointBegin.y, pointEnd.x, pointEnd.y);
    }
  }
  
  //Recovering old parameters
  canvasInstance.thickness = oldThickness;
  canvasInstance.color = oldColor;
  canvasInstance.fill = oldFill;
  canvasInstance.type = oldType;
  
}


/**
 * Refresh user list
 */
$(function() {
  setInterval(refreshUserList, 5000);
  refreshUserList();
  
  /**
   * Initialize modal error
   */
  $("#modalErrorNotConnected").modal({
    backdrop: "static",
    keyboard: false,
  });
  $("#btnModalRedirectToConnectionPage").click(function() {
    location.href = "index.jsp";
  });
});

function refreshUserList() {
  $.get("api/user-list").done(function(data) {
    $("#boardName").html(data.boardName);
    let list = $("#userList");
    list.html("");
    _.forEach(data.list, function(e) {
      let active = e == data.pseudo ? "active" : "";
      let item = `<li class="list-group-item ${active}">${e}</li>`;
      list.append(item);
    });
    $("#modalErrorNotConnected").modal("hide");
  }).fail(function() {
    $("#modalErrorNotConnected").modal("show");
  });
}

/**
 * Initialize tooltips
 */
$(function() {
  $('[data-toggle="tooltip"]').tooltip();
});














