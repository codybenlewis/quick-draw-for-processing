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

### Installing


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

## Documentation

The QuickDraw class currently has 4 main public functions for creating and manipulating the drawing data.

### create()

Draws a Google Quick, Draw! drawing to the screen. This function was modeled by the Processing's built in `ellipse()` and `rect()` functions. By default, the first two parameters set the location of the upper-left corner, the third sets the width, and the fourth sets the height. The way these parameters are interpreted, however, may be changed with the `mode()` function.

The fifth paramter sets the index of the drawing you want to pull data from and is 0 by default. The sixth and seventh paramters set the start and stop position of the drawing and are resptively 0.0 and 1.0 by default.

#### Syntax
```
qd.create(x1, y1, x2, y2)
qd.create(x1, y1, x2, y2, index)
qd.create(x1, y1, x2, y2, index, stop)
qd.create(x1, y1, x2, y2, index, start, stop)
```
#### Parameters
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
### mode()

Modifies the location from which drawings are drawn by changing the way in which parameters given to `create()` are intepreted. This function was modeled by the Processing's built in `ellipseMode()` and `rectMode()` functions.

The default mode is `rectMode(CENTER)`, interprets the first two parameters of `create()` as the shape's center point, while the third and fourth parameters are its width and height.

`rectMode(CORNER)` interprets the first two parameters of `rect()` as the upper-left corner of the shape, while the third and fourth parameters are its width and height.

`rectMode(CORNERS)` interprets the first two parameters of `rect()` as the location of one corner, and the third and fourth parameters as the location of the opposite corner.

The parameter must be written in ALL CAPS because Processing is a case-sensitive language. The built in variables CENTER, CORNER, and CORNERS equate to the integers 3, 0, and 1 repsetively, which can also be input as parameters.

#### Syntax
```
mode(mode)
```
#### Parameters
```
mode	int: either CENTER, CORNER, CORNERS
```
