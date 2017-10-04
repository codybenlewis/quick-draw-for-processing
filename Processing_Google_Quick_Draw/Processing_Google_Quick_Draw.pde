QuickDraw whales;

float amt;
float x;
float dx = .05;

void setup() {
  size(400, 400);
  stroke(255);
  strokeWeight(2);
  whales = new QuickDraw("flamingo.json");
}

void draw() {
  background(0);
  halfSinWave();

  whales.mode(CENTER);
  rectMode(CENTER);
  rect(mouseX, mouseY, height/2, height/2);
  whales.create(mouseX, mouseY, height/2, height/2, 2, amt);


//  println(whales.length());

//  whales.mode(CORNER);
//  rectMode(CORNER);
//  whales.create(mouseX, mouseY, height/2, height/2, 2, amt);
//  rect(mouseX, mouseY, height/2, height/2);

//  whales.mode(CORNERS);
//  rectMode(CORNERS);
//  whales.create(mouseX, mouseY, height/2, height/2, 2, amt);
//  rect(mouseX, mouseY, height/2, height/2);
//  rect(mouseX, mouseY, height/2, height/2);
}

void halfSinWave() {
  amt = (sin(x)/2 + .5) / 2;
  x += dx;
}