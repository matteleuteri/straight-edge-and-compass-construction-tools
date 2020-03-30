import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.util.Scanner;
import java.util.Vector;

public class Drawing extends Canvas 
{
    Vector<Point> pts = new Vector<Point>();
    Vector<Line> lines = new Vector<Line>();
    Vector<Arc> curves = new Vector<Arc>();
	public static void main(String[] args) 
	{
        JFrame frame = new JFrame("My Construction");
        Drawing canvas = new Drawing();
        canvas.setSize(400, 400);
        frame.add(canvas);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Scanner scanmain = new Scanner(System.in);
        System.out.println("enter some commands...");
        //paint is called when frame set to visible
        frame.setVisible(true);
        while(true)
        {canvas.getCMD(scanmain.nextLine());}
    }
    public void paint(Graphics g) 
    {
    	super.paint(g);
    	for(int j = 0; j < pts.size(); j++)
    	{
    		//mark all the points
    		Point tolabel = pts.get(j);
    		//adjust size of point below
    		int pRadius = 2;
    		g.fillOval(tolabel.getX()-pRadius, tolabel.getY()-pRadius, 2*pRadius, 2*pRadius);
    	}
    	for(int k = 0; k < lines.size(); k++)
    	{
    		//draw all the lines
    		Point p0 = lines.get(k).getx1();
    		Point p1 = lines.get(k).getx2();
    		g.drawLine((int)p0.getX(), (int)p0.getY(), (int)p1.getX(), (int)p1.getY());
    	}  
    	for(int m = 0; m < curves.size(); m++)
    	{
    		//draw all the arcs
    		Arc myarc = curves.get(m);
    		g.drawArc(myarc.getX(), myarc.getY(), 2*myarc.getRadius(), 2*myarc.getRadius(), myarc.getStartAngle(), myarc.getArcAngle());
    	}
    }
    public void getCMD(String cmd)
    {
    	//determine what kind of command cmd is and add construct to proper vector
    	String[] tokens = cmd.split(" ");
    	//label a single point
    	if(tokens[0].equals("label"))
    		{pts.add(new Point(tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3])));}
    	//draw straight line between two points
    	else if(tokens[0].equals("line"))
    		{makeLine(tokens);}
    	//draw arc about p1 through p2
    	else if(tokens[0].equals("arc"))
    		{makeArc(tokens);}
    	else
    		{System.out.println("command not supported");}
    	repaint();
    }
    public void makeLine(String[] tokens)
    {
    	Point endpoint1=null;
    	Point endpoint2=null;
    	for(int l = 0; l < pts.size(); l++)
    	{
    		//order is not important here, so no consideration is made here
    		if(pts.get(l).getName().equals(tokens[1]))
    			{endpoint1 = pts.get(l);}
    		if(pts.get(l).getName().equals(tokens[2]))
    			{endpoint2 = pts.get(l);}
    	}
    	if(endpoint1==null || endpoint2==null)
    		{System.out.println("point name not recognized");}
    	else
    	{
    		//find line length
    		int xDist = endpoint1.getX()-endpoint2.getX();
    		int yDist = endpoint1.getY()-endpoint2.getY();
    		int len = (int)Math.sqrt((xDist*xDist) + (yDist*yDist));
    		Line newline = new Line("neame not defined yet...", endpoint1, endpoint2, len);
    		lines.add(newline);
    	} 
    }

    public void makeArc(String[] tokens)
    {
    	//expected tokens: "arc point1 point2 degcw degccw"
    	//the pin point of the compass
		Point endpoint1=null;
		//the pencil tip is placed here initially
    	Point endpoint2=null;
    	for(int l = 0; l < pts.size(); l++)
    	{
    		//order is important here, we have explicit start and end points
    		if(tokens[1].equals(pts.get(l).getName()))
    			{endpoint1 = pts.get(l);}
    		if(tokens[2].equals(pts.get(l).getName()))
    			{endpoint2 = pts.get(l);}
    	}
    	if(endpoint1==null || endpoint2==null)
    		{System.out.println("point name not recognized");}
    	else
    	{
    		//this is basic calculations for orienting ourselves
    		int xDist = endpoint1.getX()-endpoint2.getX();
    		int yDist = endpoint1.getY()-endpoint2.getY();
    		int radius = (int)Math.sqrt((xDist*xDist) + (yDist*yDist));
    		//now we get the data for the square the circle will lie within
    		//below is the x,y coords of top left corner of the square
    		int x = endpoint1.getX()-radius;
    		int y = endpoint1.getY()-radius;
    		//below is startAngle corresponds to the 3o'clock position
    		//as is defined for the drawArc method in java
    		int startAngle = (int)Math.toDegrees(Math.atan2(-1*yDist, xDist))+180;
    		//we draw cwangle degrees clockwise 
    		int cwAngle = Integer.parseInt(tokens[3]);
    		//and ccwangle degrees counter-clockwise 
    		int ccwAngle = Integer.parseInt(tokens[4]);
    		//to draw as a single angle, we start from minuss the cwangle
    		//and then add the two together for a total continuous arc
    		//the hope is this will save memory when only 1 arc is stored 
    		curves.add(new Arc(x, y, radius, startAngle-cwAngle, cwAngle+ccwAngle));
    		//below is how it could be done with two arcs (I like my way better)
    		//curves.add(new Arc(x, y, radius, startAngle, ccwAngle));
    		//curves.add(new Arc(x, y, radius, startAngle, -1*cwAngle));
    	}
    }
}





