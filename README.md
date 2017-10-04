# QuickDraw.pde
QuickDraw.pde is a class for the [Processing](https://www.procssing.org) Development Enviroment that makes it easy to create and manipulate drawings from [Google's Quick, Draw! Experiment](https://quickdraw.withgoogle.com) in your own Processing sketches.

## Getting Started
If you have not already done so, you should become briefly familiar with the Quick, Draw! [data](https://quickdraw.withgoogle.com/data) and resulting [dataset](https://github.com/googlecreativelab/quickdraw-dataset).

For our purposes, we'll be using the [Simplified Drawing files](https://console.cloud.google.com/storage/browser/quickdraw_dataset/full/simplified) from the dataset, which have been preprocessed by Google to be easier to work with.

>**Simplified Drawing files (.ndjson)**
>
>We've simplified the vectors, removed the timing information, and positioned and scaled the data into a 256x256 region. The data is exported in ndjson format with the same metadata as the raw format. The simplification process was:
>
>1. Align the drawing to the top-left corner, to have minimum values of 0.
>2. Uniformly scale the drawing, to have a maximum value of 255.
>3. Resample all strokes with a 1 pixel spacing.
>4. Simplify all strokes using the [Ramer–Douglas–Peucker algorithm](https://en.wikipedia.org/wiki/Ramer%E2%80%93Douglas%E2%80%93Peucker_algorithm) with an epsilon value of 2.0.

