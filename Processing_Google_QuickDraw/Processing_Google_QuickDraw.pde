QuickDraw whales;

float amt = 1;
float sinTotal;
float sinIncrement = .05;
int count;
int rand1;

void setup() {
  size(400, 400);
  stroke(255);
  strokeWeight(2);
  whales = new QuickDraw("flamingo.json");

  rand1 = int(random(whales.length()));
}

void draw() {
  background(100);
  whales.noCurves();

  if (whales.info(rand1, "countrycode").equals("HK") == false) {
    rand1 = 0;
    count++;
  } else {
    println(count);
  }

  halfSinWave();

  println(whales.info(0));
  whales.create(width/2, height/2, height/2, height/2, 0, map(mouseX, 0, width, 0, 1));
  println();
}

void halfSinWave() {
  amt = (sin(sinTotal)/2 + .5);
  sinTotal += sinIncrement;
}