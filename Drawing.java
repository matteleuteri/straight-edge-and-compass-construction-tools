import java.awt.Canvas;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.util.Scanner;
import java.util.Vector;

public class Drawing extends Canvas 
{
    Vector<Point> pts = new Vector<Point>();
    Vector<Point[]> lines = new Vector<Point[]>();
    Vector<Arc> curves = new Vector<Arc>();//this one will be complicated...
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
        canvas.getCMD(scanmain.nextLine());
        frame.setVisible(true);//paint is called when frame set to visible
        while(true)
        {canvas.getCMD(scanmain.nextLine());}
    }
    public void paint(Graphics g) 
    {
    	super.paint(g);
    	for(int j = 0; j < pts.size(); j++)
    	{
    		Point tolabel = pts.get(j);
    		g.fillOval(tolabel.getX()-2, tolabel.getY()-2, 4, 4);
    	}
    	for(int k = 0; k < lines.size(); k++)
    	{
    		//TODO draw all these lines in vector lines
    		Point p0 = lines.get(k)[0];
    		Point p1 = lines.get(k)[1];
    		g.drawLine((int)p0.getX(), (int)p0.getY(), (int)p1.getX(), (int)p1.getY());
    	}  
    	for(int m = 0; m < curves.size(); m++)
    	{
    		//invoke drawArc method
    		Arc myarc = curves.get(m);
    		g.drawArc(myarc.getX(), myarc.getY(), 2*myarc.getRadius(), 2*myarc.getRadius(), myarc.getStartAngle(), myarc.getArcAngle());
    	}
    }
    public void getCMD(String cmd)
    {
    	//determine what kind of command cmd is and add construct to proper vector
    	String[] tokens = cmd.split(" ");
    	if(tokens[0].equals("label"))
    		{pts.add(new Point(tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3])));}
    	else if(tokens[0].equals("line"))
    		{makeLine(tokens);}
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
    		if(pts.get(l).getName().equals(tokens[1]))
    			{endpoint1 = pts.get(l);}
    		if(pts.get(l).getName().equals(tokens[2]))
    			{endpoint2 = pts.get(l);}
    	}
    	if(endpoint1==null || endpoint2==null)
    		{System.out.println("point name not recognized");}
    	else
    		{Point[] newline = {endpoint1,endpoint2};lines.add(newline);} 
    }

    public void makeArc(String[] tokens)
    {
    	//here is our expected tokens:
    	//"arc point1 point2 degcw degccw"
		Point endpoint1=null;
    	Point endpoint2=null;
    	for(int l = 0; l < pts.size(); l++)
    	{
    		if(tokens[1].equals(pts.get(l).getName()))
    			{endpoint1 = pts.get(l);}
    		if(tokens[2].equals(pts.get(l).getName()))
    			{endpoint2 = pts.get(l);}
    	}
    	if(endpoint1==null || endpoint2==null)
    		{System.out.println("point name not recognized");}
    	else
    	{
    		int xDist = endpoint1.getX()-endpoint2.getX();
    		int yDist = endpoint1.getY()-endpoint2.getY();
    		int radius = (int)Math.sqrt((xDist*xDist) + (yDist*yDist));
    		//now we get the data for the square the circle will lie within
    		int x = endpoint1.getX()-radius;
    		int y = endpoint1.getY()-radius;
    		//below is startAngle corresponds to the 3o'clock position
    		//as is defined for the drawArc method in java
    		int startAngle = (int)Math.toDegrees(Math.atan2(yDist, xDist))-45;
    		//we draw cwangle degrees clockwise 
    		int cwAngle = Integer.parseInt(tokens[3]);
    		//and ccwangle degrees counter-clockwise 
    		int ccwAngle = Integer.parseInt(tokens[4]);
    		//to draw as a single angle, we start from minuss the cwangle
    		curves.add(new Arc(x, y, radius, startAngle, ccwAngle));
    		curves.add(new Arc(x, y, radius, startAngle, -1*cwAngle));
    	}
    }
}





