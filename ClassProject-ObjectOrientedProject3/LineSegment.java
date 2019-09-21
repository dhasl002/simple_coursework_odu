package Pictures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.awt.geom.Line2D;

public class LineSegment extends Shape
{
	public LineSegment()
	{
		super(Color.BLUE, Shape.Transparent);
		setCorners(new Point(0,0), new Point(0,10));
	}
	
	public LineSegment(java.awt.Point p1, java.awt.Point p2)
	{
		super(Color.BLUE, Shape.Transparent);
		setCorners(p1,p2);
	}
	
    public LineSegment(java.awt.Point p1, java.awt.Point p2, Color edgeColor, Color fillColor)
    {
    	super(edgeColor, fillColor);
    	setCorners(p1,p2);
    	
    }
    
    public Shape clone() 
    {
       Point p1 = new Point((int)_line.x1, (int)_line.y1);
       Point p2 = new Point((int)_line.x2, (int)_line.y2);
	LineSegment copy = new LineSegment(p1, p2, getEdgeColor(), getFillColor());
	return copy;
    }
    
    public java.awt.Rectangle boundingBox()
    {
    	java.awt.Rectangle _rect = new java.awt.Rectangle((int)_line.x1, (int)_line.y1, (int)_line.x2-(int)_line.x1, (int)_line.y2-(int)_line.y1);
    	return (java.awt.Rectangle)_rect.clone();
    }
    
   

   public void reflectVertically (int yReflectionPlane)
    {
	java.awt.Point p1
	    = Shape.reflectVertically(new java.awt.Point((int)_line.x1, (int)_line.y1),
					yReflectionPlane);
	
	java.awt.Point p2
	    = Shape.reflectVertically(new java.awt.Point
				        ((int)_line.x2, (int)_line.y2),
					yReflectionPlane);
	setCorners (p1, p2);
    }

    public LineSegment (Color edgeColor, Color fillColor, Scanner input)
            throws IllegalStateException, NoSuchElementException
    {
        Point p1 = Shape.readPoint(input);
        Point p2 = Shape.readPoint(input);
        setCorners (p1, p2);
    }
    
    public void plot(Graphics g)
    {      
        Color c = getEdgeColor();
        if (c.getAlpha() > 0.0f) {
            g.setColor(c);
            g.drawLine((int)_line.x1, (int)_line.y1, (int)_line.x2, (int)_line.y2);
            
            
        }

    }
    
    
    public String toStringDetails() {
        return "(" + _line.x1 + "," + _line.y1 + ") ("
                + (_line.x2 + _line.y2);
    }
    
    public void move(int x, int y)
    {
	_line.x1 += x;
	_line.x2 += x;
	_line.y1 += y;
	_line.y2 += y;
    }
    
    public void reflectHorizontally (int xReflectionPlane)
    {
	java.awt.Point p1
	    = Shape.reflectHorizontally(new java.awt.Point((int)_line.x1, (int)_line.y1),
					xReflectionPlane);

	java.awt.Point p2
	    = Shape.reflectHorizontally(new java.awt.Point
					  ((int)_line.x2,
					  (int) _line.y2),
					xReflectionPlane);
	setCorners (p1, p2);
    }

    public java.awt.Point getFirst()
    {
    	return new Point((int)_line.x1, (int)_line.y1);
    }
    
    public java.awt.Point getSecond()
    {
    	return new Point((int)_line.x2, (int)_line.y2);
    }
    
    
    private java.awt.geom.Line2D.Float   _line;
 
    
    private void setCorners (java.awt.Point p1, java.awt.Point p2)
    {
	_line = new Line2D.Float(p1.x, p1.y, p2.x, p2.y);
    }
    
    
};

