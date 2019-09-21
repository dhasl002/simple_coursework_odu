package TEst;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 * @version 1.0 11/09/98
 */
public class JButtonTableExample extends JFrame implements ActionListener {

	private JButton jButton;
	private JButton jButton2;
	private JButton jButton3;
	public static Object[] obj;
	public static Object[][] obj2;
	public static String str;
	

  public JButtonTableExample() {
	
	super("CS Project");
	  
	int n1= 0;
	int n2 = 0;
	int n3 = 0;
	int n4 = 0;
	int n5 = 0;
	
	Random rand = new Random();
	n1 = (int )(Math.random() * 15 + 1);
	do{
		n2 = (int )(Math.random() * 15 + 1);
	}while(n2 == n1);
	do{
		n3 = (int )(Math.random() * 15 + 1);
	}while(n3 == n2 || n3 == n1);
	do{
		n4 = (int )(Math.random() * 15 + 1);
	}while(n4 == n3 || n4 == n2 || n4 == n1);
	do{
		n5 = (int )(Math.random() * 15 + 1);
	}while(n5 == n4 || n5 == n3 || n5 == n2 || n5 == n1);

    
    Object[] obj = { "ID", "Status", "Priority" };
    Object[][] obj2 = {{ "A", "Current Process", n1 },
        { "B", "Ready", n2 }, { "C", "Ready", n3 }, 
        { "D", "Ready", n4 }, { "E", "Ready", n5 }};
    
  
    
    
    //sort
    int largest = 1000;
    String largestString = "";
    //String state = "";
    int tempi = 0; 
    boolean temp = false;
    int largest2 = 1000;
    String largestString2 = "";
    //String state2 = "";
    for(int j = 0; j < 5; j++)
    {
    	largest = 1000;
        largestString = "";
        //state = "";
        tempi = 0; 
        temp = false;
        
	    for(int i = j; i < 5; i++)
	    {	
	    	if((int)obj2[i][2] < largest)
	    	{
	    		temp = true;
	    		tempi = i;
	    		largest = (int)obj2[i][2];
	    		largestString = obj2[i][0].toString();
	    		//state = obj2[i][1].toString();
	    	}
	    }
	    if(temp == true)
	    {
	    	largestString2 = obj2[j][0].toString();
		    //state2 = obj2[j][1].toString();
		    largest2 = (int)obj2[j][2];
		    
		    obj2[j][2] = obj2[tempi][2];
		    //obj2[j][1] = obj2[tempi][1];
		    obj2[j][0] = obj2[tempi][0];
		    
		    obj2[tempi][2] = largest2;
		    //obj2[tempi][1] = state2;
		    obj2[tempi][0] = largestString2;
	    }
	    
	    
    }
    str = "Queue: ";
    
    for(int i = 0; i < 5; i++)
    {
    	if(obj2[i][1].toString().equals("Current Process"))
    	{
    		str = str + obj2[i][0].toString();
    	}
    }
    
    for(int i = 0; i < 5; i++)
    {
    	if(obj2[i][1].toString().equals("Ready"))
    	{
    		str = str + obj2[i][0].toString();
    	}
    }
    
    for(int i = 0; i < 5; i++)
    {
    	if(obj2[i][1].toString().equals("Blocked"))
    	{
    		str = str + obj2[i][0].toString();
    	}
    }
    
    JButtonTableExample.obj = obj;
    JButtonTableExample.obj2 = obj2;

    DefaultTableModel dm = new DefaultTableModel();
    dm.setDataVector(obj2, obj);

    JTable table = new JTable(dm);
    
    JPanel btns = new JPanel();
    JButton jButton = new JButton("Ready");
    jButton.addActionListener(this);
    btns.add(jButton);
    add(btns,BorderLayout.SOUTH);
    JButton jButton2 = new JButton("Block");
    jButton2.addActionListener(this);
    btns.add(jButton2);
    add(btns,BorderLayout.SOUTH);
    JButton jButton3 = new JButton("Kill");
    jButton3.addActionListener(this);
    btns.add(jButton3);
    add(btns,BorderLayout.SOUTH);
    
    
    JLabel label = new JLabel(str);
    btns.add(label);
    
    
    JScrollPane scroll = new JScrollPane(table);
    getContentPane().add(scroll);
    setSize(1024, 256);
    setVisible(true);
  }
  
  public void actionPerformed(ActionEvent e) {
	  String command = e.getActionCommand();
	  String saved = "";
	  String temp = "";
	  boolean blocked = false;
	  boolean notBlocked = true;
	  if(command.equals("Ready"))
	  {
		  saved = JOptionPane.showInputDialog("What process should be ready? (i.e. a, B, etc.)");
		  saved = saved.toUpperCase();
		  
		  for(int i = 0; i < 5; i++)
		  {
			  if(!obj2[i][1].toString().equals("Blocked"))
			  {
				  notBlocked = false;
			  }
		  }
	  
		  for(int i = 0; i < 5; i++)
		  {
			  if(obj2[i][0].toString().equals(saved) && !obj2[i][1].toString().equals("Ready"))
			  {
				  
				  if(notBlocked)
				  {
					  obj2[i][1] = "Current Process";
				  }
				  else
				  {
					  obj2[i][1] = "Ready";
				  }
				  //loop to tell if there is already less than the current process
				  int current = -1;
				  int ready = 6;
				  boolean alreadyCurrent = false;
				  boolean alreadyReady = false;
				  for(int r = 0; r < 5; r++)
				  {
					  if(obj2[r][1].toString().equals("Current Process") && alreadyCurrent == false)
					  {
						  current = r;
						  alreadyCurrent = true;
						  
					  }
					  if(obj2[r][1].toString().equals("Ready") && alreadyReady == false)
					  {
						  ready = r;
						  alreadyReady = true;
					  }
				  }
				  if(ready < current)
				  {
					  obj2[ready][1] = "Current Process";
					  obj2[current][1] = "Ready";
				  }
				  
				  str = "Queue: ";
				    
				    for(int p = 0; p < 5; p++)
				    {
				    	if(obj2[p][1].toString().equals("Current Process"))
				    	{
				    		str = str + obj2[p][0].toString();
				    	}
				    }
				    
				    for(int p = 0; p < 5; p++)
				    {
				    	if(obj2[p][1].toString().equals("Ready"))
				    	{
				    		str = str + obj2[p][0].toString();
				    	}
				    }
				    
				    for(int p = 0; p < 5; p++)
				    {
				    	if(obj2[p][1].toString().equals("Blocked"))
				    	{
				    		str = str + obj2[p][0].toString();
				    	}
				    }
				  
				  
				  DefaultTableModel dm = new DefaultTableModel();
				    dm.setDataVector(obj2, obj);

				    JTable table = new JTable(dm);
				    
				    JPanel btns = new JPanel();
				    JButton jButton = new JButton("Ready");
				    jButton.addActionListener(this);
				    btns.add(jButton);
				    add(btns,BorderLayout.SOUTH);
				    JButton jButton2 = new JButton("Block");
				    jButton2.addActionListener(this);
				    btns.add(jButton2);
				    add(btns,BorderLayout.SOUTH);
				    JButton jButton3 = new JButton("Kill");
				    jButton3.addActionListener(this);
				    btns.add(jButton3);
				    add(btns,BorderLayout.SOUTH);
				    JLabel label = new JLabel(str);
				    btns.add(label);
				    
				    
				    JScrollPane scroll = new JScrollPane(table);
				    getContentPane().add(scroll);
				    setSize(1024, 256);
				    setVisible(true);
			  }
		  }
		  
		  
	
	  }
	  if(command.equals("Block"))
	  {
		  saved = JOptionPane.showInputDialog("What process should be Blocked? (i.e. a, B, etc.)");
		  saved = saved.toUpperCase();
		  for(int i = 0; i < 5; i++)
		  {
			  if(obj2[i][0].toString().equals(saved) && !obj2[i][1].toString().equals("Blocked"))
			  {
				  obj2[i][1] = "Blocked";
				  
				  DefaultTableModel dm = new DefaultTableModel();
				    dm.setDataVector(obj2, obj);

				    JTable table = new JTable(dm);
				    
				    JPanel btns = new JPanel();
				    JButton jButton = new JButton("Ready");
				    jButton.addActionListener(this);
				    btns.add(jButton);
				    add(btns,BorderLayout.SOUTH);
				    JButton jButton2 = new JButton("Block");
				    jButton2.addActionListener(this);
				    btns.add(jButton2);
				    add(btns,BorderLayout.SOUTH);
				    JButton jButton3 = new JButton("Kill");
				    jButton3.addActionListener(this);
				    btns.add(jButton3);
				    add(btns,BorderLayout.SOUTH);
				    JLabel label = new JLabel(str);
				    btns.add(label);
				    
				    
				    JScrollPane scroll = new JScrollPane(table);
				    getContentPane().add(scroll);
				    setSize(1024, 256);
				    setVisible(true);
			  }
			  if(!obj2[i][1].toString().equals("Current Process"))
			  {
				  //obj2[i][1] = "Blocked";
				  for(int y = 0; y < 5; y++)
				  {
					  if(!obj2[y][1].toString().equals("Blocked") && !obj2[y][1].toString().equals(""))
					  {
						  obj2[y][1] = "Current Process";
						  y=5;
					  }
				  }
			  }
				  
				  str = "Queue: ";
				    
				    for(int p = 0; p < 5; p++)
				    {
				    	if(obj2[p][1].toString().equals("Current Process"))
				    	{
				    		str = str + obj2[p][0].toString();
				    	}
				    }
				    
				    for(int p = 0; p < 5; p++)
				    {
				    	if(obj2[p][1].toString().equals("Ready"))
				    	{
				    		str = str + obj2[p][0].toString();
				    	}
				    }
				    
				    for(int p = 0; p < 5; p++)
				    {
				    	if(obj2[p][1].toString().equals("Blocked"))
				    	{
				    		str = str + obj2[p][0].toString();
				    	}
				    }
				  DefaultTableModel dm = new DefaultTableModel();
				    dm.setDataVector(obj2, obj);

				    JTable table = new JTable(dm);
				    
				    JPanel btns = new JPanel();
				    JButton jButton = new JButton("Ready");
				    jButton.addActionListener(this);
				    btns.add(jButton);
				    add(btns,BorderLayout.SOUTH);
				    JButton jButton2 = new JButton("Block");
				    jButton2.addActionListener(this);
				    btns.add(jButton2);
				    add(btns,BorderLayout.SOUTH);
				    JButton jButton3 = new JButton("Kill");
				    jButton3.addActionListener(this);
				    btns.add(jButton3);
				    add(btns,BorderLayout.SOUTH);
				    JLabel label = new JLabel(str);
				    btns.add(label);
				    
				    
				    JScrollPane scroll = new JScrollPane(table);
				    getContentPane().add(scroll);
				    setSize(1024, 256);
				    setVisible(true);
				    
				    //i = 5;
						  
			 
		  }
	  }
	  if(command.equals("Kill"))
	  {
		  saved = JOptionPane.showInputDialog("What process should be Killed? (i.e. a, B, etc.)");
		  saved = saved.toUpperCase();
		  for(int i = 0; i < 5; i++)
		  {
			  if(obj2[i][0].toString().equals(saved))
			  {
				  obj2[i][0] = "";
				  obj2[i][1] = "";
				  obj2[i][2] = "";
				  
				  DefaultTableModel dm = new DefaultTableModel();
				    dm.setDataVector(obj2, obj);

				    JTable table = new JTable(dm);
				    
				    JPanel btns = new JPanel();
				    JButton jButton = new JButton("Ready");
				    jButton.addActionListener(this);
				    btns.add(jButton);
				    add(btns,BorderLayout.SOUTH);
				    JButton jButton2 = new JButton("Block");
				    jButton2.addActionListener(this);
				    btns.add(jButton2);
				    add(btns,BorderLayout.SOUTH);
				    JButton jButton3 = new JButton("Kill");
				    jButton3.addActionListener(this);
				    btns.add(jButton3);
				    add(btns,BorderLayout.SOUTH);
				    JLabel label = new JLabel(str);
				    btns.add(label);
				    
				    
				    JScrollPane scroll = new JScrollPane(table);
				    getContentPane().add(scroll);
				    setSize(1024, 256);
				    setVisible(true);
			  }
			  if(!obj2[i][1].toString().equals("Current Process"))
			  {
				  //obj2[i][1] = "Blocked";
				  for(int y = 0; y < 5; y++)
				  {
					  if(!obj2[y][1].toString().equals("Blocked") && !obj2[y][1].toString().equals(""))
					  {
						  obj2[y][1] = "Current Process";
						  y=5;
					  }
				  }
			  }
			  
			  str = "Queue: ";
			    
			    for(int p = 0; p < 5; p++)
			    {
			    	if(obj2[p][1].toString().equals("Current Process"))
			    	{
			    		str = str + obj2[p][0].toString();
			    	}
			    }
			    
			    for(int p = 0; p < 5; p++)
			    {
			    	if(obj2[p][1].toString().equals("Ready"))
			    	{
			    		str = str + obj2[p][0].toString();
			    	}
			    }
			    
			    for(int p = 0; p < 5; p++)
			    {
			    	if(obj2[p][1].toString().equals("Blocked"))
			    	{
			    		str = str + obj2[p][0].toString();
			    	}
			    }
		  }
		  DefaultTableModel dm = new DefaultTableModel();
		    dm.setDataVector(obj2, obj);

		    JTable table = new JTable(dm);
		    
		    JPanel btns = new JPanel();
		    JButton jButton = new JButton("Ready");
		    jButton.addActionListener(this);
		    btns.add(jButton);
		    add(btns,BorderLayout.SOUTH);
		    JButton jButton2 = new JButton("Block");
		    jButton2.addActionListener(this);
		    btns.add(jButton2);
		    add(btns,BorderLayout.SOUTH);
		    JButton jButton3 = new JButton("Kill");
		    jButton3.addActionListener(this);
		    btns.add(jButton3);
		    add(btns,BorderLayout.SOUTH);
		    JLabel label = new JLabel(str);
		    btns.add(label);
		    
		    
		    JScrollPane scroll = new JScrollPane(table);
		    getContentPane().add(scroll);
		    setSize(1024, 256);
		    setVisible(true);
	  }
	 
	  
	  DefaultTableModel dm = new DefaultTableModel();
	    dm.setDataVector(obj2, obj);

	    JTable table = new JTable(dm);
	    
	    JPanel btns = new JPanel();
	    JButton jButton = new JButton("Ready");
	    jButton.addActionListener(this);
	    btns.add(jButton);
	    add(btns,BorderLayout.SOUTH);
	    JButton jButton2 = new JButton("Block");
	    jButton2.addActionListener(this);
	    btns.add(jButton2);
	    add(btns,BorderLayout.SOUTH);
	    JButton jButton3 = new JButton("Kill");
	    jButton3.addActionListener(this);
	    btns.add(jButton3);
	    add(btns,BorderLayout.SOUTH);
	    JLabel label = new JLabel(str);
	    btns.add(label);
	    
	    
	    JScrollPane scroll = new JScrollPane(table);
	    getContentPane().add(scroll);
	    setSize(1024, 256);
	    setVisible(true);
	  
}
  

  public static void main(String[] args) {
    JButtonTableExample frame = new JButtonTableExample();
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
  }
}
