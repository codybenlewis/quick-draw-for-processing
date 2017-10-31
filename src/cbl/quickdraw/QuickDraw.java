package cbl.quickdraw;
import processing.core.*;
import processing.data.*;


/////////////////////////////////////////////////////////////////


/**
 * A Processing library that makes it easy to interface with drawings from Google's
 * Quick, Draw! Experiment dataset in your own sketches.
 * 
 * @example 
 */

public class QuickDraw {
	
  // myParent is a reference to the parent sketch
	
  PApplet parent;	

  String[] source;
  String path;

  JSONObject object;
  JSONArray array;

  /* An integer CORNER, CORNERS, CENTER (0, 1, or 3) that specifies what how to
   * interpret the x1, y1, x2, and y2 data from the "create" function
   */

  int modeSet;

  /* An integer not found in any the Google QuickDraw data used to determine the end
   * of a stroke in a drawing
   */

  int marker = -1;

  /* A boolean used to maintain the way the lines are drawn with or without the
   * curveVertex() function. Updated by the curves() and noCurves() functions
   */

  boolean curves = true;

  public QuickDraw (PApplet p, String s) {  
	  
	parent = p;
	
    // A Google QuickDraw data file path
	
    path = s;

    /* Loads all the lines in the file as strings so that each object can be processed
     * individually.
     */

    source = parent.loadStrings(path);

    // Makes noFill the default drawing mode if no no other is specified in the sketch

    parent.noFill();

    // Sets the default position interpretation to CENTER

    modeSet = PConstants.CENTER;

    // Loads the data for the default index;

    load(0);
  } 


  /////////////////////////////////////////////////////////////////


	/**
	 * Draws a Google Quick, Draw! drawing to the screen. This function was modeled
	 * after Processing's built in ellipse() and rect() functions. By default, the
	 * first two parameters set the location of the upper-left corner, the third sets
	 * the width, and the fourth sets the height. The way these parameters are interpreted,
	 * however, may be changed with the mode() function.
	 * 
	 * The fifth parameter sets the index of the drawing you want to pull data from and
	 * is 0 by default. The sixth and seventh parameters set the start and stop position
	 * of the drawing and are respectively 0.0 and 1.0 by default.
	 * 
	 * @example
	 * 
	 * @param x1         float: x-coordinate of the drawing by default
	 * @param y1         float: y-coordinate of the drawing by default
	 * @param x2         float: width of the drawing's bounding box by default
 	 * @param y2         float: height of the drawing's bounding box by default
 	 * @param index      int: int between 0 and the object's source file length
 	 * @param start      float: float between 0.0 and 1.0
 	 * @param stop       float: float between 0.0 and 1.0
	 */
  
  public void create(float x1, float y1, float x2, float y2, int index, float start, float stop) { 

    // Loads the data for the specified index of the QuickDraw data

    load(index);

    /* Updates the x1, x2, y1, and y2 values adjust for different drawing modes.
     * The default mode is CENTER.
     */

    if (modeSet != PConstants.CENTER) {
      if (modeSet == PConstants.CORNER) {
        x1 += x2/2;
        y1 += y2/2;
      } else if (modeSet == PConstants.CORNERS) {
        x2 = x2 - x1; 
        y2 = y2 - y1;
        x1 += x2/2;
        y1 += y2/2;
      }
    }

    /* Creates arrays to store the combined coordinates of a drawing. This allows the
     * drawing loop to simulate a more natural drawing process: One complete line at
     * a time at a time instead of piece by piece of every line all at once.
     */

    int[] xConcat = new int[0];
    int[] yConcat = new int[0];

    // Loops through all the strokes of an individual drawing

    for (int j=0; j < array.size(); j++) {

      /* Creates an JSONarray containing the x and y values of a specified stroke within
       * of the the outer array data, "array".
       */

      JSONArray values = array.getJSONArray(j);

      /* Creates an integer array out of the x values (0 index) and the y values
       * (1 index) of the JSON array "values"
       */

      int[] xint = values.getJSONArray(0).getIntArray();
      int[] yint = values.getJSONArray(1).getIntArray();

      /* Adds an identifying character/integer after every stroke's completion. This
       * character acts as a marker for splitting the drawing of the concatenated array
       * into multiple lines.
       */

      xint = PApplet.append(xint, marker);
      yint = PApplet.append(yint, marker);

      // Adds the finished data to the "xConcat" and "yConcat" array

      xConcat = PApplet.concat(xConcat, xint);
      yConcat = PApplet.concat(yConcat, yint);
      
    }

    /* Modifies the start and end position of the drawing based on a 0.0 - 1.0 float
     * value percentage provided by the user.
     */

    int startAmt = PApplet.round(PApplet.map(start, 0, 1, 0, xConcat.length));
    int stopAmt = PApplet.round(PApplet.map(stop, 0, 1, 0, xConcat.length));

    // Iterates a continuous shapes from every point of the concatenated arrays.

    parent.beginShape();

    for (int k = startAmt; k < stopAmt; k++) {
      
    	/* Stores the x and y values and re-map them, centered to the input scale. The
    	 * simplified data files come stored in 256 by 256 coordinate squares.
    	 */

      float x;
      x = xConcat[k];
      if (x != -1) {
        x = PApplet.map(x, 0, 256, -x2/2, x2/2);
      }

      // Stores the y value and re-map it to a different scale

      float y; 
      y = yConcat[k];
      y = PApplet.map(y, 0, 256, -y2/2, y2/2);

      /* Closes the current drawing and begins a new one for the next line when the
       * x coordinate or y coordinate is the marker character/integer. Also Draws
       * 2 curveVertex() points at the start and end of a line since the first and last
       * points in a series of curveVertex() lines are be used to guide the beginning
       * and end of a curve. Prevents out of bounds errors by excluding the start
       * and end of the xConcat array which by default is the start and end of a line
       */
      
      if (xConcat[k] != marker) {
        if (curves == false) {
        	parent.vertex(x + x1, y + y1);
        } else {
          if (k == startAmt || k == stopAmt) {
        	  parent.curveVertex(x + x1, y + y1);
        	  parent.curveVertex(x + x1, y + y1);
          } else {
            if (xConcat[k+1] == marker || xConcat[k-1] == marker) {
            	parent.curveVertex(x + x1, y + y1);
            	parent.curveVertex(x + x1, y + y1);
            } else {
            	parent.curveVertex(x + x1, y + y1);
            }
          }
        }
      } else {
    	  parent.endShape();
    	  parent.beginShape();
      }
    }

    // Finishes drawing whatever the last stroke was
    
    parent.endShape();
  }

  
  /* The 5 overload additional functions below provide more convenient options for the
   * user so that not every input of the main create() function is required. They take
   * the same types of inputs as the main function. The minimum input one can provide in
   * any circumstance is x1, y1, x2 (width), and y2 (height)*/
  
  public void create(float x1, float y1, float x2, float y2, 
    int index, float stop) {
    create(x1, y1, x2, y2, index, 0, stop);
  }

  
  public void create(float x1, float y1, float x2, float y2, 
    float start, float stop) {
    create(x1, y1, x2, y2, 0, start, stop);
  }

  
  public void create(float x1, float y1, float x2, float y2, 
    int index) {
    create(x1, y1, x2, y2, index, 0, 1);
  }

  
  public void create(float x1, float y1, float x2, float y2, 
    float stop) {
    create(x1, y1, x2, y2, 0, 0, stop);
  }

  
  public void create(float x1, float y1, float x2, float y2) {
    create(x1, y1, x2, y2, 0, 0, 1);
  }


  /////////////////////////////////////////////////////////////////


  /**
   * Modifies the location from which drawings are drawn by changing the way in which
   * parameters given to create() are interpreted. This function was modeled after
   * Processing's built in ellipseMode() and rectMode() functions. The default mode
   * is rectMode(CENTER), which interprets the first two parameters of create() as
   * the shape's center point, while the third and fourth parameters are its width and
   * height.
   * 
   * rectMode(CORNER) interprets the first two parameters of rect() as the upper-left
   * corner of the shape, while the third and fourth parameters are its width and height.
   * 
   * rectMode(CORNERS) interprets the first two parameters of rect() as the location
   * of one corner, and the third and fourth parameters as the location of the opposite
   * corner.
   * 
   * The parameter must be written in ALL CAPS because Processing is a case-sensitive
   * language. The built in variables CENTER, CORNER, and CORNERS equate to the integers
   * 3, 0, and 1 respectively, which can also be input as parameters.
   * 
   * @param mode			int: CENTER, CORNER, or CORNERS
   */

  
  public void mode(int mode) {
    modeSet = mode;
  }

  
/**
 * Returns a String of information about a specified drawing. By default, the function
 * will return all available data on the drawing across multiple lines. Data points
 * include what source file the drawing is found in, what index of the dataset the
 * drawing is found on, how many points the drawing is made from, what word was the
 * drawing is based on, what country the drawing is from, and what date and time the
 * drawing was originally created atwhat date and time the drawing was originally created
 * at, and whether or not the drawing was recognized by the machine when it was created.
 * 
 * When using the string "length" as the data point, the function will return the
 * amount of points in the drawing as a String. When using the string "index" as the data
 * point, the function will return the index parameter as a String.
 * 
 * @param index				int: int between 0 and the object's source file length
 * @param dataPoint		str: "source", "index", "length", "word", "countrycode", "timestamp", or "recognized"
 * @return String
 */
  
  
 /* Java was used in String conversations within the info() function to prevent
  * warnings created from using non-static methods.
  */

public String info(int index, String dataPoint) {

    // Loads the data information of the input index

    object = parent.parseJSONObject(source[index]);

    String word = object.getString("word");
    String countrycode = object.getString("countrycode");
    String timestamp = object.getString("timestamp");
    String recognized = String.valueOf(object.getBoolean("recognized"));
    
    String d = dataPoint.toLowerCase();

    if (d.equals("") == true
      || d.equals(" ") == true
      || d.equals("all") == true) {
      return ("source: " + path
        + "\nindex: " + index + " of " + length()
        + "\nlength: " + length(index) + " lines / " + points(index) + " points"   
        + "\nword: " + word
        + "\ncountrycode: " + countrycode
        + "\ntimestamp: " + timestamp
        + "\nrecognized: " + recognized);
    } else if (d.equals("index") == true
      || d.equals("line") == true
      || d.equals("0") == true) {
      return(String.valueOf(index));
    } else if (d.equals("source") == true
      || d.equals("path") == true
      || d.equals("file") == true
      || d.equals("1") == true) {
      return(path);
    } else if (d.equals("word") == true
      || d.equals("2") == true) {
      return(word);
    } else if (d.equals("countrycode") == true
      || d.equals("country") == true
      || d.equals("code") == true
      || d.equals("3") == true) {
      return(countrycode);
    } else if (d.equals("timestamp") == true
      || d.equals("time") == true
      || d.equals("stamp") == true
      || d.equals("4") == true) {
      return(timestamp);
    } else if (d.equals("length") == true
      || d.equals("5") == true) {
      return(String.valueOf(length((index))));
    }  else if (d.equals("recognized") == true
    	  || d.equals("6") == true) {
      return(recognized);
    } else {
      return ("Unrecognized String Parameter"
        + "\nTry using \"source\", "
        + "\"index\", "
        + "\"length\", "
        + "\"word\", "
        + "\"countrycode\", "
        + "\"timestamp\", "
        + "or \"recognized\"");
    }
  }


  public String info(int index) {
    return(info(index, " "));
  }

  
  /////////////////////////////////////////////////////////////////


  /**
   * Returns an integer amount of drawings in the dataset file or returns amount of
   * lines used within a specific drawing. Used in `info()` to create the data point
   * output as `"length"`.
   * 
   * @param index 		int: int between 0 and the object's source file length
   * 
   * @return int
   */

  public int length(int index) {

    /* Repeats logic from the create() and length() functions in order to build arrays
     * of x coordinates that can be used to measure the length of drawing "i" without
     * actually drawing it on screen.
     */

    load(index);

    int[] concat = new int[0];

    for (int j=0; j < array.size(); j++) {

      JSONArray values = array.getJSONArray(j);
      int[] xint = values.getJSONArray(0).getIntArray();
      concat = PApplet.concat(concat, xint);
    }
    
    return array.size();
  }

  
  public int length() {
    return(source.length -1);
  }
  

  /////////////////////////////////////////////////////////////////


  /**
   * Returns an integer amount of points in a specific drawing or returns the amount of
   * points in one of the lines of a specific drawing. Used in `info()` to create the
   * data point output as `"length"`.
   * 
   * @param index	 int: int between 0 and the object's source file length
   * @param line		 int: int between 0 and the value of (qd.info(index) - 1)
   * 
   * @return int
   */
  
  public int points(int index, int line) {

    load(index);

    // Obtains data directly from the specified line and measures the size of the line.

    JSONArray values = array.getJSONArray(line);
    int[] xint = values.getJSONArray(0).getIntArray();
    return xint.length;
  }
  
  
  public int points(int index) {

	    load(index);

	    int[] concat = new int[0];

	    for (int j=0; j < array.size(); j++) {

	      JSONArray values = array.getJSONArray(j);
	      int[] xint = values.getJSONArray(0).getIntArray();
	      concat = PApplet.concat(concat, xint);
	    }

	    return concat.length;
	  }


  /////////////////////////////////////////////////////////////////


  /**
   * Enables the default geometry used to smooth the lines drawn on screen within
   * create(). Note that this behavior is active by default, so it only necessary to call
   * the function to reactivate the behavior after calling noCurves().
   */
  
  void curves() {
    curves = true;
  }


  /////////////////////////////////////////////////////////////////


  /**
   * Disables the default geometry used to smooth the lines drawn on screen via create().
   * Note that curves() is active by default, so it is necessary to call noCurves() to
   * disable smoothing of lines.
   */
  
  void noCurves() {
    curves = false;
  }

  
  /////////////////////////////////////////////////////////////////

  
 /**
  * Resets and reloads data information from the source file in order
  * 
  * @param index
  */

  private void load(int index) {

    // Loads the data information of the input index

    object = parent.parseJSONObject(source[index]);

    // Processes each line's JSON object and isolate the "drawing" data from the array

    array = object.getJSONArray("drawing");
  }
}
