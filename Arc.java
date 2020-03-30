public class Arc
{
	int xPos;
	int yPos;
	int radius;
	int startAngle;
	int arcAngle;
	//boolean isvisible
	public Arc(int x, int y, int radius, int sangle, int aangle)
	{xPos=x;yPos=y;this.radius=radius;startAngle=sangle;arcAngle=aangle;}
	public int getX()
	{return xPos;}
	public int getY()
	{return yPos;}
	public int getRadius()
	{return radius;}
	public int getStartAngle()
	{return startAngle;}
	public int getArcAngle()
	{return arcAngle;}
}