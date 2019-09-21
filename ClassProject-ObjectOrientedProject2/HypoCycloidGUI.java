import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Dimension;


public class HypoCycloidGUI {

	private JFrame mainWindow;
	private PlotCanvas plotCanvas;
	private CurveDrawing drawingThread;
	private IntegerInput outerRadiusIn;
	private IntegerInput innerRadiusIn;
	private IntegerInput nRevIn;
	private IntegerInput nPointsIn;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HypoCycloidGUI window = new HypoCycloidGUI();
					window.mainWindow.setVisible(true);
					window.drawingThread.setPriority (Thread.MIN_PRIORITY);
					window.drawingThread.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public HypoCycloidGUI() {
		plotCanvas = new PlotCanvas();
		initialize();
		drawingThread = new CurveDrawing(plotCanvas);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainWindow = new JFrame();
		mainWindow.setTitle("CS330, Fall 2015");
		mainWindow.setBounds(100, 100, 600, 450);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setLayout(new BorderLayout());
		mainWindow.getContentPane().add(plotCanvas, BorderLayout.CENTER);
		JPanel northPanel = new JPanel();
		JPanel southPanel = new JPanel();
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		mainWindow.getContentPane().add(northPanel, BorderLayout.NORTH);
		mainWindow.getContentPane().add(southPanel, BorderLayout.SOUTH);
		JButton colorButton = new JButton("Color");
		northPanel.add(colorButton);
		colorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					Color color = JColorChooser.showDialog(mainWindow, "Choose Color", 
							plotCanvas.getColor());
					if (color != null) {
						plotCanvas.setColor(color);
					}
				} catch (Exception ex) {}
				
			}
		});
		//
		
		//
		outerRadiusIn = new IntegerInput("outer radius:", 
				plotCanvas.getOuterRadius(), 3);
		northPanel.add(outerRadiusIn);
		outerRadiusIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					int value = outerRadiusIn.getInt();
					plotCanvas.setOuterRadius(value);
				} catch (Exception ex) {}
				
			}
		});
		
		innerRadiusIn = new IntegerInput("inner radius:", 
				plotCanvas.getInnerRadius(), 3);
		northPanel.add(innerRadiusIn);
		innerRadiusIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					int value = innerRadiusIn.getInt();
					plotCanvas.setInnerRadius(value);
				} catch (Exception ex) {}
				
			}
		});
		
				nRevIn = new IntegerInput("# rev:", 
						plotCanvas.getNumberOfRevolutions(), 3);
				southPanel.add(nRevIn);
				nRevIn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						try {
							int value = nRevIn.getInt();
							plotCanvas.setNumberOfRevolutions(value);
						} catch (Exception ex) {}
						
					}
				});
		
				nPointsIn = new IntegerInput("# pts/rev:", 
						plotCanvas.getNumPoints(), 5);
				southPanel.add(nPointsIn);
				nPointsIn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent ev) {
						try {
							int value = nPointsIn.getInt();
							plotCanvas.setNumPoints(value);
						} catch (Exception ex) {}
						
					}
				});
	}

}
