public class Point
{
	int xPos;
	int yPos;
	String name;
	//boolean isvisible
	public Point(String name, int x, int y)
	{this.name=name;xPos=x;yPos=y;}
	public String getName()
	{return name;}
	public int getX()
	{return xPos;}
	public int getY()
	{return yPos;}
}