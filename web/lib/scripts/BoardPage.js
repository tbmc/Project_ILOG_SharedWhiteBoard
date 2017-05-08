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
    
    console.log(canvasElement.width, canvasElement.height);
    let t = { x: 75, y: 0 };
    let s = this.getRealFromVirtual(t);
    let r = this.getVirtualFromReal(s);
    console.log(t, s, r);
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
  
}

