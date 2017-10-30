// Import the Quick Draw For Processing Library
import cbl.quickdraw.*;

// Initialize a QuickDraw object from the library
QuickDraw qd;

float end, x;

void setup() {
  size(400, 400);

  // We pass "this" to QuickDraw so that it can load files from the
  // data directory

  qd = new QuickDraw(this, "monkey.ndjson");
}

void draw() {
  background(255);

  float scale = height/2;

  endUpdate();
  qd.create(width/2, height/2, scale, scale, end);
}

// Update end back and forth between 0 and 1 

void endUpdate() {
  float inc = .01;
  end = abs(sin(x));
  x = x + inc;
}