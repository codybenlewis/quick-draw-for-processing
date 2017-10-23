QuickDraw apple;

void setup() {
  apple = new QuickDraw("apple.ndjson");
}

void draw() {
 apple.create(width/2,height/2,width/2,height/2);
 println(apple.info(0));
}