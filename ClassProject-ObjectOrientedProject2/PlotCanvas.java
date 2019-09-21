import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Point2D;


public class PlotCanvas extends JPanel {

	/** 
	 * The color used drawing. 
	 */
	private Color color = Color.black;

	/** radius of the outer circle */
	private int outerRadius = 105;

	/** radius of the inner circle */
	private int innerRadius = 22;

	/** # of revolutions to keep */
	private int nRevolutions = 10;
	
	/** The number of points plotted per revolution. */
	private int nPoints = 100; 


	private HypoCycloid curve;
	
	private PlottedCurve plot;
	
	/**
	 * Create the panel.
	 */
	public PlotCanvas() {
		setBackground(Color.WHITE);
		curve = new HypoCycloid();
		curve.setInnerRadius(innerRadius);
		curve.setOuterRadius(outerRadius);
		plot = new PlottedCurve(curve, 0.0, 2.0 * Math.PI / nPoints, nPoints * nRevolutions);
		plot.fillCurve();
	}

	/**
	 * @return the color used for plotting
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @param color the color to be used for plotting
	 */
	public void setColor(Color color) {
		this.color = color;
		repaint();
	}

	/**
	 * @return the radius of the outer circle
	 */
	public int getOuterRadius() {
		return outerRadius;
	}

	/**
	 * @param outerRadius the radius of the outer circle
	 */
	public synchronized void setOuterRadius(int outerRadius) {
		this.outerRadius = outerRadius;
		curve.setOuterRadius(outerRadius);
		plot.reset();
		repaint();
	}

	/**
	 * @return the radius of the inner circle
	 */
	public int getInnerRadius() {
		return innerRadius;
	}

	/**
	 * @param innerRadius the radius of the inner circle
	 */
	
	public synchronized void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
		curve.setInnerRadius(innerRadius);
		plot.reset();
		repaint();
	}

	/**
	 * @return the number of revolutions to plot
	 */
	public int getNumberOfRevolutions() {
		return nRevolutions;
	}

	/**
	 * @param nRevolutions the number of revolutions to plot.
	 */
	public void setNumberOfRevolutions(int nRevolutions) {
		this.nRevolutions = nRevolutions;
		plot = new PlottedCurve(curve, 0.0, 2.0 * Math.PI / nPoints, nPoints * nRevolutions);
		repaint();
	}
	
	
	/**
	 * @return the nPoints
	 */
	public int getNumPoints() {
		return nPoints;
	}

	/**
	 * @param nPoints the nPoints to set
	 */
	public void setNumPoints(int nPoints) {
		this.nPoints = nPoints;
		plot = new PlottedCurve(curve, 0.0, 2.0 * Math.PI / nPoints, nPoints * nRevolutions);
		repaint();
	}

	/**
	 * Extend the current plot by one point.
	 */
	public synchronized void addPoint() {
		plot.addAPoint();
		repaint();
	}
	
	
	/**
	 * Draw the curve
	 */
	@Override
	public synchronized void paint(Graphics g) {
		super.paint(g);
		int w = getSize().width;
		int h = getSize().height;
		double r = (w < h)? (double)w : (double)h;
		r = r / 2.0;
		double scale = 1.0;
		if (outerRadius < innerRadius) {
			scale = ((double)outerRadius) / (innerRadius + outerRadius); 
		}
		r *= scale;

		Point2D.Double prev = null;
		for (Point2D.Double current: plot.getPlot()) { 
			if (prev != null) {
				int x1 = (int)(w / 2 + prev.x *  r);
				int y1 = (int)(h / 2 + prev.y *  r);
				int x2 = (int)(w / 2 + current.x *  r);
				int y2 = (int)(h / 2 + current.y *  r);
				g.setColor (color);
				g.drawLine(x1, y1, x2, y2);
			}
			prev = current;
		}
	}

}
