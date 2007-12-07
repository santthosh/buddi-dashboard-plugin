package org.homeunix.thecave.plugins;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import org.homeunix.thecave.moss.swing.MossFrame;
import org.homeunix.thecave.moss.util.ClassLoaderFunctions;


public class DashBoardFrame extends MossFrame {
	
	public static final long serialVersionUID = 1;
		
	private JPanel mainPanel,titlePanel;
	private JTabbedPane tabPanel;
	private DataPanel dataPanel;
	private ChartPanel chartPanel;
	private JLabel dashLabel, hideLabel;
	
	public DashBoardFrame()
	{
		super();		
				  	      
	    MouseDragAdapter mouseAdapter = new MouseDragAdapter(this);
	    HideAdapter hideAdapter = new HideAdapter(this);
	      
	    //Main Panel
	    mainPanel = new JPanel();
	    mainPanel.setLayout(new BorderLayout());      
	      
	    //Tabbed Panel
	    tabPanel = new JTabbedPane();
	    tabPanel.setFont(new java.awt.Font("Dialog", 0, 10));
	      
	    chartPanel = new ChartPanel(this);      
	    chartPanel.addMouseListener(mouseAdapter);
	    chartPanel.addMouseMotionListener(mouseAdapter);
	    tabPanel.addTab("Chart", chartPanel);
	      
	    dataPanel = new DataPanel(this);
	    dataPanel.addMouseListener(mouseAdapter);
	    dataPanel.addMouseMotionListener(mouseAdapter);      
	    tabPanel.addTab("Options", dataPanel);            
	      
	    tabPanel.addMouseListener(mouseAdapter);
	    tabPanel.addMouseMotionListener(mouseAdapter);
	    mainPanel.add(tabPanel,BorderLayout.SOUTH);
	                               
	    //Title Panel
	    titlePanel = new JPanel();
	    titlePanel.setLayout(new BorderLayout());
	      
	    dashLabel = new JLabel();
	    dashLabel.setFont(new java.awt.Font("Dialog", 0, 11));
	    dashLabel.setForeground(java.awt.Color.BLUE);
	    dashLabel.setText(" Buddi Dashboard ");      
	    titlePanel.add(dashLabel,BorderLayout.WEST);        
	      
	    hideLabel = new JLabel();
	    hideLabel.setFont(new java.awt.Font("Dialog", 0, 10));
	    hideLabel.setText("Hide");      
	    titlePanel.add(hideLabel,BorderLayout.EAST);      
	    hideLabel.addMouseListener(hideAdapter);    

	    titlePanel.addMouseListener(mouseAdapter);
	    titlePanel.addMouseMotionListener(mouseAdapter);
	    mainPanel.add(titlePanel,BorderLayout.NORTH);	                                                               	    					
	}
		
	public void init() {
		super.init();
		
		//Moss frame properties
		this.setIconImage(ClassLoaderFunctions.getImageFromClasspath("img/BuddiFrameIcon.gif"));
		this.setUndecorated(true);
		this.setTitle("Dashboard");
		this.add(mainPanel);			
	}
	
	private static class HideAdapter implements MouseListener, MouseMotionListener{
	      	      
	      private MossFrame frame;
	      
	      public HideAdapter(MossFrame frame){
	         this.frame = frame;
	      }
	      
	      public void mousePressed(MouseEvent e) {}	      	      
	      
	      public void mouseDragged(MouseEvent e) {}	         	      
	      
	      public void mouseClicked(MouseEvent e) {
	    	  frame.closeWindow();
	      }
	      public void mouseReleased(MouseEvent e) {}	         
	      public void mouseEntered(MouseEvent e) {}
	      public void mouseExited(MouseEvent e) {}
	      public void mouseMoved(MouseEvent e) {}
	      
	   }
	
	private static class MouseDragAdapter implements MouseListener, MouseMotionListener{
	      
	      private static Point origin = new Point();
	      private MossFrame frame;
	      
	      public MouseDragAdapter(MossFrame frame){
	         this.frame = frame;
	      }
	      
	      public void mousePressed(MouseEvent e) {
	         origin.x = e.getPoint().x;
	         origin.y = e.getPoint().y;
	      }
	      
	      public void mouseDragged(MouseEvent e) {
	         final Point mp = e.getPoint();
	         final Point fp = frame.getLocationOnScreen();
	         SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	               frame.setLocation(fp.x + mp.x - origin.x,
	                                 fp.y + mp.y - origin.y);
	            }
	         });
	      }
	      
	      public void mouseClicked(MouseEvent e) {}
	      public void mouseReleased(MouseEvent e) {
	         origin.x = 0;
	         origin.y = 0;
	      }
	      public void mouseEntered(MouseEvent e) {}
	      public void mouseExited(MouseEvent e) {}
	      public void mouseMoved(MouseEvent e) {}
	      
	   }
}