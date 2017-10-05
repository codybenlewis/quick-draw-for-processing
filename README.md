# QuickDraw.pde
QuickDraw.pde is a class for the [Processing](https://www.procssing.org) Development Enviroment that makes it easy to create and manipulate drawings from [Google's Quick, Draw! Experiment](https://quickdraw.withgoogle.com) in your own sketches. 

With time, the goal of this project is to turn the class into a proper library and begin to use it in production of new types of open source art and design. I hope it enables you do the same.

## Getting Started

### Prerequisites

The [latest version](https://www.processing.org/download/) of Processing.

[Simplified Drawing Files](https://console.cloud.google.com/storage/browser/quickdraw_dataset/full/simplified) from the Google Quick, Draw! [dataset](https://github.com/googlecreativelab/quickdraw-dataset).

>**Simplified Drawing files (.ndjson)**
>
>We've simplified the vectors, removed the timing information, and positioned and scaled the data into a 256x256 region. The data is exported in ndjson format with the same metadata as the raw format. The simplification process was:
>
>1. Align the drawing to the top-left corner, to have minimum values of 0.
>2. Uniformly scale the drawing, to have a maximum value of 255.
>3. Resample all strokes with a 1 pixel spacing.
>4. Simplify all strokes using the [Ramer–Douglas–Peucker algorithm](https://en.wikipedia.org/wiki/Ramer%E2%80%93Douglas%E2%80%93Peucker_algorithm) with an epsilon value of 2.0.

For this example I've ironicly downloaded and will be using the [apple.ndjson](https://storage.googleapis.com/quickdraw_dataset/full/simplified/apple.ndjson) data file.

### "Installing"


Once you have finished downloading your file, begin by adding it to your sketch's data folder.
(Sketch > Add File)

The following lines have already been included in the example file. Only if you decide to start from a new sketch, proceed by initializing a QuickDraw object from the class
```java
QuickDraw qd;
```
and calling it within `void setup()` in order to construct it.
```java
void setup() {
  qd = new QuickDraw("apple.ndjson");
}
```
That's it! You're now ready to create drawings.

### Documentation

The class currently has 4 public functions, one main function for creating drawings

#### Create

##### syntax
```
qd.create(x1, y1, x2, y2)
qd.create(x1, y1, x2, y2, index)
qd.create(x1, y1, x2, y2, index, stop)
qd.create(x1, y1, x2, y2, index, start, stop)
```
##### syntax
```
 qd       QuickDraw: a QuickDraw object
 
 x1       float: x-coordinate of the drawing by default
 
 y1       float: y-coordinate of the drawing by default
 
 x2       float: width of the drawing's bounding box by default
 
 y2       float: height of the drawing's bounding box by default
 
 index    int: int between 0 and the object's source file length
 
 start    float: float between 0.0 and 1.0
 
 stop     float: float between 0.0 and 1.0
```
