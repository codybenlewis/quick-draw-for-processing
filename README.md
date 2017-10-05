# QuickDraw.pde
QuickDraw.pde is a class for the [Processing](https://www.procssing.org) Development Enviroment that makes it easy to create and manipulate drawings from [Google's Quick, Draw! Experiment](https://quickdraw.withgoogle.com) in your own Processing sketches.

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

For this example I've downloaded and will be using the [apple.ndjson](https://storage.googleapis.com/quickdraw_dataset/full/simplified/apple.ndjson) file.

### Installing

Once you have your file downloaded, begin by adding it to your sketch's data folder.
(Sketch > Add File)

Then all you have to do is initialize an object
```
QuickDraw drawing;
```
and call it within void setup to construct it.

```
void setup() {
  drawing = new QuickDraw("apple.ndjson");
}
```
