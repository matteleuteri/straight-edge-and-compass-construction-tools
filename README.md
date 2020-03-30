# matteleuteri
graphics program to simulate constructions with straight edge and compass.

Point, Line, Arc classes:

I implemented three classes to create points, lines, and arcs so as to not be limited by java swing's libraries. This is most noticeable in the Arc class. Java.Swing.drawArc() has parameters that are not ideal for constructing arcs from arbitrary locations on a canvass. What this default method does is constructs an arc as part of a circle inscribed inside a square as specified by some coordinate pair representing the top left corner. The arc is drawn starting from an offset from the 3 o'clock position of the square and follows a length as described by some counter clockwise degree. My implementation is more true to the postulates of geometric construction. Provide my program with two points (just like on a compass) and then two degrees specifying angle-distance from the second point. In this sense, the first point is a pivot representing the point of the compass. The second point is where the pencil starts. The two angles represent how far from the pencil point in each direction the arc is to be drawn.

How to use:

The java file "Drawing.java" should already be compiled as "Drawing.class" and can be ran with "java Drawing". 

Commands:

There are three commands. We can label a point on the canvass, draw a line between two points on the canvass, and draw arcs about some point along a curve including another point. 

to label a point, use "label point1name xcoordinate ycoordinate"

to draw a line, use "line point1name point2name"

to draw an arc use "arc pivotpointname arcstartpoint clockwisedegrees counterclockwisedegrees"



