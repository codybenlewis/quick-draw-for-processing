# QuickDraw.pde

QuickDraw.pde is a [Processing](https://www.procssing.org) library that makes it easy to interface with drawings from [Google's Quick, Draw! Experiment](https://quickdraw.withgoogle.com) data set in your own sketches. 

I hope that it enables you to new types of open source art and design as it will for me. If you create something with this dataset, please let me know [by e-mail](mailto:cblewisnj@gmail.com?subject=I%20Made%20Something%20With%20Quick%20Draw%20for%20Processing) and consider passing it along to [Google](https://aiexperiments.withgoogle.com/submit).

## Content

- [Getting Started](#getting-started)
  - [Installing](#installing)
  - [Using the Library](#using-the-library)
- [Reference](#reference)
  - [create()](#create)
  - [mode()](#mode)
  - [info()](#info)
  - [length()](#length)
  - [points()](#points)
  - [curves()](#curves)
  - [noCurves()](#nocurves)
- [Licenses](#licenses)

# Getting Started

To begin, you'll have to first make sure you have the latest of Processing installed, which you can download from https://www.processing.org/download/. You'll also need a copy of this library on your desktop, which you can get by clicking on the download button at https://github.com/codybenlewis/Quick-Draw-for-Processing and selecting one of the packages provided.

Lastly, you'll need [Simplified Drawing Files](https://console.cloud.google.com/storage/browser/quickdraw_dataset/full/simplified) from the Google Quick, Draw! [dataset](https://github.com/googlecreativelab/quickdraw-dataset). Because of how the data is structured, this library will only properly work with these types of files.

>**Simplified Drawing files (.ndjson)**
>
>We've simplified the vectors, removed the timing information, and positioned and scaled the data into a 256x256 region. The data is exported in ndjson format with the same metadata as the raw format. The simplification process was:
>
>1. Align the drawing to the top-left corner, to have minimum values of 0.
>2. Uniformly scale the drawing, to have a maximum value of 255.
>3. Resample all strokes with a 1 pixel spacing.
>4. Simplify all strokes using the [Ramer–Douglas–Peucker algorithm](https://en.wikipedia.org/wiki/Ramer%E2%80%93Douglas%E2%80%93Peucker_algorithm) with an epsilon value of 2.0.

***Note** that previews of the data avaiable on [the Quick, Draw site](https://quickdraw.withgoogle.com/data/) exclude drawings that were not recognized by machine learning or that may have been flagged as inappropriate. Those drawings compromise are still available within the downloadable files, so trying to index specific drawings by referencing the site will potentially return different results or inappropriate content. For closer results you will have to manually edit local copies of the files to exclude falsely recognized drawings.*

For the provided library examples I've downloaded and included the [monkey.ndjson](https://storage.googleapis.com/quickdraw_dataset/full/simplified/monkey.ndjson) data file :monkey:.


## Installing

Processing's official [library installation instructions](https://github.com/processing/processing/wiki/How-to-Install-a-Contributed-Library):

>Contributed libraries may be downloaded separately and manually placed within the libraries folder of your Processing sketchbook. As a reminder, the sketchbook is where your sketches are saved. To find (and change) the Processing sketchbook location on your computer, open the Preferences window from the Processing application (PDE) and look for the "Sketchbook location" item at the top.
>
>Copy the contributed library's folder into the libraries folder at this location. You will need to create the libraries folder if this is your first contributed library.
>
>By default the following locations are used for your sketchbook folder. For Mac users the sketchbook folder is located inside `~/Documents/Processing`. For Windows users the sketchbook folder is located inside folder `Documents/Processing`.
>
>Let's say you downloaded a library with name theLibrary. Then the folder structure of this library inside the libraries folder should look like the one below. The top folder of a library must have the same name as the .jar file located inside a library's library folder (minus the .jar extension):
>
>```
>     Documents
>           Processing
>                 your sketch folders
>                 libraries
>                       theLibrary
>                             examples
>                             library
>                                   theLibrary.jar
>                             reference
>                             src
>```
>
>Some folders like examples or src might be missing. After a library has been successfully installed, **restart Processing application**.

Please read the [the full documentation](https://github.com/processing/processing/wiki/How-to-Install-a-Contributed-Library) on how to install a contributed library for additional information and troubleshooting tips.

## Using the Library

Once you have finished downloading and installing the library, begin by opening up a new sketch (⌘-n) and importing the library to it.

```java
import cbl.quickdraw.*;
```

Afterwards, initialize a QuickDraw object from the class

```java
QuickDraw qd;
```

and call it within `void setup()` in order to construct it.

```java
void setup() {
  qd = new QuickDraw(this, "filename.ndjson");
}
```

Between the double quotes, replace `filename.ndjson` with the actual name of your data file. In order to prevent a *NullPointerException* you'll need to make sure the data file you specify been added to your sketch's data folder before running the program.

- From the menubar choose File → Add File...
- Find and open your simplified data file

By default, constructing the object will change the your sketch's `fill()` settings to `noFill()`. If you want to use filled shapes within your sketch, call the `fill()` function anywhere within `void setup()` or `void draw()` below the object's construction.

That's it! You should be ready to start drawing.

Now, let's create a simple test using the QuickDraw specific `create()` function within `void draw()`.

```
void draw() {
  monkey.create(width/2, height/2, width/2, height/2);
}
```

Run the program (⌘-r).

Using the [monkey.ndjson](https://storage.googleapis.com/quickdraw_dataset/full/simplified/monkey.ndjson) data file, without any additional lines of code, it should look like:

![example](example.png) Success! There are at least 127,000 more monkeys to choose from. What you do next with this library is completely up to you.

If you're having trouble getting to this point please make sure you've properly followed all the above and check your machine settings for any compatibility issues. If it's still not working, contact me [by e-mail](mailto:cblewisnj@gmail.com?subject=Quick%20Draw%20for%20Processing%20Troubleshooting) with specififc details for help with troubleshooting.

# Reference

The QuickDraw class has 7 functions for intefacing with the drawing data.

- [create()](#create)
- [mode()](#mode)
- [info()](#info)
- [length()](#length)
- [points()](#points)
- [curves()](#curves)
- [noCurves()](#nocurves)

## create()

Draws a Google Quick, Draw! drawing to the screen. This function was modeled after Processing's built in `ellipse()` and `rect()` functions. By default, the first two parameters set the location of the upper-left corner, the third sets the width, and the fourth sets the height. The way these parameters are interpreted, however, may be changed with the `mode()` function.

The fifth parameter sets the index of the drawing you want to pull data from and is 0 by default. The sixth and seventh parameters set the start and stop position of the drawing and are respectively 0.0 and 1.0 by default.

#### Syntax

```
qd.create(x1, y1, x2, y2)
qd.create(x1, y1, x2, y2, index)
qd.create(x1, y1, x2, y2, index, stop)
qd.create(x1, y1, x2, y2, index, start, stop)
```

#### Parameters

```
 qd         QuickDraw: a QuickDraw object
 x1         float: x-coordinate of the drawing by default
 y1         float: y-coordinate of the drawing by default
 x2         float: width of the drawing's bounding box by default
 y2         float: height of the drawing's bounding box by default
 index      int: int between 0 and the object's source file length
 start      float: float between 0.0 and 1.0
 stop       float: float between 0.0 and 1.0
```

## mode()

Modifies the location from which drawings are drawn by changing the way in which parameters given to `create()` are interpreted. This function was modeled after Processing's built in `ellipseMode()` and `rectMode()` functions.

The default mode is `rectMode(CENTER)`, which interprets the first two parameters of `create()` as the shape's center point, while the third and fourth parameters are its width and height.

`rectMode(CORNER)` interprets the first two parameters of `rect()` as the upper-left corner of the shape, while the third and fourth parameters are its width and height.

`rectMode(CORNERS)` interprets the first two parameters of `rect()` as the location of one corner, and the third and fourth parameters as the location of the opposite corner.

The parameter must be written in ALL CAPS because Processing is a case-sensitive language. The built in variables CENTER, CORNER, and CORNERS equate to the integers 3, 0, and 1 respectively, which can also be input as parameters.

#### Syntax

```
qd.mode(mode)
```

#### Parameters

```
qd          QuickDraw: a QuickDraw object
mode        int: CENTER, CORNER, or CORNERS
```

## info()

Returns a String of information about a specified drawing. By default, the function will return all available data on the drawing across multiple lines. Data points include what source file the drawing is found in, what index of the dataset the drawing is found on, how many points the drawing is made from, what word was the drawing is based on, what country the drawing is from, what date and time the drawing was originally created at, and whether or not the drawing was recognized by the machine when it was created.

When using the String `"length"` as the data point, the function will return the amount of points in the drawing as a String. When using the String `"index"` as the data point, the function will return the index parameter as a String.

#### Syntax

```
qd.info(index)
qd.info(index, "dataPoint")
```

#### Parameters

```
qd          QuickDraw: a QuickDraw object
index       int: int between 0 and the object's source file length
dataPoint   String: "source", "index", "length", "word", "countrycode", "timestamp", or "recognized"
```

## length()

Returns an integer amount of drawings in the dataset or amount of drawn lines in an index. Used in `info()` to create the data point output as `"length"`.

#### Syntax

```
qd.length()
qd.info(index)
```

#### Parameters

```
qd        QuickDraw: a QuickDraw object
index     int: int between 0 and the object's source file length
```

## points()

Returns an integer amount of points in a drawing index or one of the drawn lines within that index. Used in `info()` to create the data point output as `"length"`.

#### Syntax

```
qd.info(index)
qd.info(index, line)
```

#### Parameters

```
qd          QuickDraw: a QuickDraw object
index       int: int between 0 and the object's source file length
line        int: int between 0 and the value of (qd.info(index) - 1)
```

## curves()

Enables the default geometry used to smooth the lines drawn on screen within `create()`. Note that this behavior is active by default, so it only necessary to call the function to reactivate the behavior after calling `noCurves()`.

#### Syntax

```
qd.noCurves()
```

#### Parameters

```
qd          QuickDraw: a QuickDraw object
```

## noCurves()

Disables the default geometry used to smooth the lines drawn on screen via `create()`. Note that `curves()` is active by default, so it is necessary to call `noCurves()` to disable smoothing of lines.

#### Syntax

```
qd.noCurves()
```

#### Parameters

```
qd          QuickDraw: a QuickDraw object
```

# Licenses
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.


The Google Quick, Draw! data has been made available by Google, Inc. under the [Creative Commons Attribution 4.0 International license.](https://creativecommons.org/licenses/by/4.0/)
