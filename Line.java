public class Line
{
	Point x1;
	Point x2;
	int length;
	String name;
	//boolean isvisible
	public Line(String name, Point x1, Point x2, int l)
	{this.name=name;this.x1=x1;this.x2=x2;this.length=l;}
	public String getName()
	{return name;}
	public Point getx1()
	{return x1;}
	public Point getx2()
	{return x2;}
	public int getLength()
	{return length;}
}