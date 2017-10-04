QuickDraw whales;

float amt;
float sinTotal;
float sinIncrement = .05;

void setup() {
  size(400, 400);
  stroke(255);
  strokeWeight(2);
  whales = new QuickDraw("flamingo.json");
}

void draw() {
  background(0);
  halfSinWave();
  whales.create(mouseX, mouseY, height/2, height/2, 0, amt);
  println(whales.info(2, "hey"));
  println();
}

void halfSinWave() {
  amt = (sin(sinTotal)/2 + .5);
  sinTotal += sinIncrement;
}