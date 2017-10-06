QuickDraw whales;

float amt = 1;
float sinTotal;
float sinIncrement = .002;
int count;
int rand1;

void setup() {
  size(400, 400);
  stroke(255);
  strokeWeight(2);
  whales = new QuickDraw("whale.ndjson");

  rand1 = int(random(whales.length()));
}

void draw() {
  background(100);

  if (whales.info(rand1, "countrycode").equals("HK") == false) {
    rand1 = 0;
    count++;
  } else {

    println(count);
  }

  halfSinWave();
 

  whales.create(width/2, height/2, height/2, height/2, 12020, amt);
  println(whales.info(rand1));
  println();
}

void halfSinWave() {
  amt = (sin(sinTotal)/2 + .5);
  sinTotal += sinIncrement;
}