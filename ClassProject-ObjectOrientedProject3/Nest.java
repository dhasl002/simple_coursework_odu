package Pictures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Nest extends Shape
{

    public Nest(Collection<LineSegment> segments, Color edgeColor, Color fillColor)
    {
        super(edgeColor, fillColor);
       
        	_list = segments;
        
        LineSegment first = segments.iterator().next();
        _line = first;
        setCorners(first.getFirst(), first.getSecond());
        //System.out.println(_list.size() + "d");
    }

	public Nest(Color edgeColor, Color fillColor, Scanner input)
            throws IllegalStateException, NoSuchElementException
    {
		
	       Point p1 = Shape.readPoint(input);
	       Point p2 = Shape.readPoint(input);
	       setCorners (p1, p2);
    }
    
	   public String toStringDetails() {
	       return "(";
	   }
	   
	   public void move(int x, int y)
	    {
		   System.out.println(_list.size());
		   _rect.x += x;
		   _rect.y += y;
		   _line.getFirst().x += x;
		   _line.getFirst().y += y;
		   _line.getSecond().x += x;
		   _line.getSecond().y += y;
		   Iterator<LineSegment> tempIt = _list.iterator();
		   LineSegment tempLine = new LineSegment();
		   for(int i = 0; i < _list.size(); i++)
		   {
			   tempLine = tempIt.next(); 
			   tempLine.getFirst().x += x;
			   tempLine.getFirst().y += y;
			   tempLine.getSecond().x += x;
			   tempLine.getSecond().y += y;
			   
		   }
	    }

	   public void plot(Graphics g)
	    {
		   
		   LineSegment first = _line;
		   Color c = getFillColor();
	        if (c.getAlpha() > 0.0f) {
	            g.setColor(c);
	            g.fillRect(_rect.x, _rect.y, _rect.width, _rect.height);}
		   c = getEdgeColor();
	        if (c.getAlpha() > 0.0f) {
	            g.setColor(c);
	            g.drawLine((int)first.getFirst().getX(), (int)first.getFirst().getY(), (int)first.getSecond().getX(), (int)first.getSecond().getY());
	            g.drawRect(_rect.x, _rect.y,(int)_rect.getWidth(),(int)_rect.getHeight());
	            
	        }
	        
	        for(int i = 0; i < _list.size()+1; i++)
	        {
	        	System.out.println(_list.size());
	        	
	        	Iterator<LineSegment> tempIt = _list.iterator();
	        	LineSegment tempLine = new LineSegment();
	        	for(int j = 0; j < i; j++)
	        	{
	        		System.out.println(i + " " + j);
	        		tempLine = tempIt.next();	
	        		
	        	}
	        	g.drawLine((int)tempLine.getFirst().getX(), (int)tempLine.getFirst().getY(), (int)tempLine.getSecond().getX(), (int)tempLine.getSecond().getY());
	        	System.out.println("----------------------------------");
	        }
	    }

	   public void reflectHorizontally (int xReflectionPlane)
	    {
		   
	    }

	   public void reflectVertically (int yReflectionPlane)
	    {
		
	    }

	   public java.awt.Rectangle boundingBox()
	    {
		return (java.awt.Rectangle)_rect.clone();
	    }

	   public Shape clone() 
	    {
		Nest copy = new Nest(_list, getEdgeColor(), getFillColor());
		return copy;
	    }
	    
	    private LineSegment _line;
	    private Collection<LineSegment> _list;
	    private java.awt.Rectangle _rect;
	 
	    private void setCorners (java.awt.Point p1, java.awt.Point p2)
	    {
	    	
	    	if (p1.x > p2.x) {
	    	    int temp = p1.x;
	    	    p1.x = p2.x;
	    	    p2.x = temp;
	    	}
	    	if (p1.y > p2.y) {
	    	    int temp = p1.y;
	    	    p1.y = p2.y;
	    	    p2.y = temp;
	    	}_rect = new java.awt.Rectangle(p1.x, p1.y, p2.x-p1.x, p2.y-p1.y);
	    }


	};