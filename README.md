# QuickDraw.pde
QuickDraw.pde is a class for the [Processing](https://www.procssing.org) Development Enviroment that makes it easy to create and manipulate drawings from Google's [Quick, Draw!](https://quickdraw.withgoogle.com) experiment in your own sketches. The methods were developed to work just like Processing's built in **rect()** and **ellipse()** functions and it takes is a few lines to get started.

# Getting Started
If you have not already done so, you should become briefly familiar with the Quick, Draw! [data](https://quickdraw.withgoogle.com/data) and resulting [dataset](https://github.com/googlecreativelab/quickdraw-dataset).

For our purposes, we'll be using [Simplified Drawing files](https://console.cloud.google.com/storage/browser/quickdraw_dataset/full/simplified), which have already been preprocessed by Google to become easier to work with.

>**Simplified Drawing files (.ndjson)**
>
>We've simplified the vectors, removed the timing information, and positioned and scaled the data into a 256x256 region. The data is exported in ndjson format with the same metadata as the raw format. The simplification process was:
>
>1. Align the drawing to the top-left corner, to have minimum values of 0.
>2. Uniformly scale the drawing, to have a maximum value of 255.
>3. Resample all strokes with a 1 pixel spacing.
>4. Simplify all strokes using the Ramer–Douglas–Peucker algorithm with an epsilon value of 2.0.

