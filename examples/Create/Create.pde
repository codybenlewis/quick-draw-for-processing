// Import the Quick Draw For Processing Library
import cbl.quickdraw.*;

// Initialize a QuickDraw object from the library
QuickDraw qd;

void setup() {
  size(400, 400);

  // We pass "this" to QuickDraw so that it can load files from the
  // data directory

  qd = new QuickDraw(this, "monkey.ndjson");
}

void draw() {
  background(255);

  int divisor = 8;
  float scale = width/divisor*.75;
  int index = 0;

  // Increments the coordinates of the drawing
  // Draws the drawing to the screen
  // Increments the index for which drawing's data is used

  for (int i = 1; i < divisor; i++) {
    for (int j = 1; j < divisor; j++) {
      qd.create(width/divisor*i, width/divisor*j, scale, scale, index);
      index++;
    }
  }
}