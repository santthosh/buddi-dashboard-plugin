package org.homeunix.thecave.plugins.dashboard;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

import org.homeunix.thecave.moss.swing.MossFrame;
import org.homeunix.thecave.buddi.plugin.api.PreferenceAccess;
import org.homeunix.thecave.moss.util.ClassLoaderFunctions;


public class DashBoardFrame extends MossFrame {
	
	public static final long serialVersionUID = 1;
		
	private JPanel mainPanel,titlePanel;
	private JTabbedPane tabPanel;
	private DataPanel dataPanel;
	private ChartPanel chartPanel;
	private JLabel dashLabel, freezeLabel, hideLabel;
	private MouseDragAdapter mouseAdapter;	
	private HideAdapter hideAdapter;
	private FreezeAdapter freezeAdapter;


	public DashBoardFrame(PreferenceAccess preferencesHandler)
	{
		super();	
		
	    mouseAdapter = new MouseDragAdapter(this);
	    mouseAdapter.setPreferences(preferencesHandler);
	    hideAdapter = new HideAdapter(this);
	    freezeAdapter = new FreezeAdapter(this);
	      
	    //Main Panel
	    mainPanel = new JPanel();
	    mainPanel.setLayout(new BorderLayout());      
	      
	    //Tabbed Panel
	    tabPanel = new JTabbedPane();
	    tabPanel.setFont(new java.awt.Font("Dialog", 0, 10));
	      
	    chartPanel = new IncomeExpenseByCategory(this);      
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
	    dashLabel.setText(" Buddi Dashboard                                                       ");      
	    titlePanel.add(dashLabel,BorderLayout.WEST);
	    
	    freezeLabel = new JLabel();
	    freezeLabel.setFont(new java.awt.Font("Dialog", 0, 10));
	    freezeLabel.setText("Freeze");      
	    titlePanel.add(freezeLabel,BorderLayout.CENTER);      
	    freezeLabel.addMouseListener(freezeAdapter);
	      
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
	
	private static class FreezeAdapter implements MouseListener, MouseMotionListener{
	      
	      private DashBoardFrame frame;
	      
	      public FreezeAdapter(DashBoardFrame frame){
	         this.frame = frame;
	      }
	      
	      public void mousePressed(MouseEvent e) {}	      	      
	      
	      public void mouseDragged(MouseEvent e) {}	         	      
	      
	      public void mouseClicked(MouseEvent e) {	  
	    	  
	    	  SwingUtilities.invokeLater(new Runnable() {
					public void run() {
	    	  if(frame.freezeLabel.getText().equalsIgnoreCase("Freeze"))
	    	  {
	    		  frame.chartPanel.timer.stop();
	    		  frame.freezeLabel.setText("Activate");
	    		  frame.freezeLabel.setForeground(java.awt.Color.RED);	    		  
	    	  }
	    	  else
	    	  {
	    		  frame.chartPanel.timer.start();	    		  
	    		  frame.freezeLabel.setText("Freeze");
	    		  frame.freezeLabel.setForeground(java.awt.Color.BLACK);
	    	  }
					}
	    	  });
	      }
	      public void mouseReleased(MouseEvent e) {}	         
	      public void mouseEntered(MouseEvent e) {}
	      public void mouseExited(MouseEvent e) {}
	      public void mouseMoved(MouseEvent e) {}
	      
	   }
	
	private static class HideAdapter implements MouseListener, MouseMotionListener{
	      	      
	      private MossFrame frame;
	      
	      public HideAdapter(MossFrame frame){
	         this.frame = frame;
	      }
	      
	      public void mousePressed(MouseEvent e) {}	      	      
	      
	      public void mouseDragged(MouseEvent e) {}	         	      
	      
	      public void mouseClicked(MouseEvent e) {
	    	  SwingUtilities.invokeLater(new Runnable() {
					public void run() {frame.dispose();} 
					});	    	  
	      }
	      public void mouseReleased(MouseEvent e) {}	         
	      public void mouseEntered(MouseEvent e) {}
	      public void mouseExited(MouseEvent e) {}
	      public void mouseMoved(MouseEvent e) {}
	      
	   }
	
	private static class MouseDragAdapter implements MouseListener, MouseMotionListener{

		private static Point origin = new Point();
		private DashBoardFrame frame;
		private PreferenceAccess preferences;

		public MouseDragAdapter(DashBoardFrame frame){	    	 
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
					
					//Update the preferences
					if(preferences!=null)
					{	            	   	            	   
						preferences.putPreference("org.homeunix.thecave.plugins.dashboard.LOCATION_X",String.valueOf(fp.x + mp.x - origin.x));
						preferences.putPreference("org.homeunix.thecave.plugins.dashboard.LOCATION_Y",String.valueOf(fp.y + mp.y - origin.y));												
					}						
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

		/**
		 * @return the preferences
		 */
		public PreferenceAccess getPreferences() {
			return preferences;
		}

		/**
		 * @param preferences the preferences to set
		 */
		public void setPreferences(PreferenceAccess preferences) {
			this.preferences = preferences;
		}


	}

	/**
	 * @return the chartPanel
	 */
	public ChartPanel getChartPanel() {
		return chartPanel;
	}

	/**
	 * @param chartPanel the chartPanel to set
	 */
	public void setChartPanel(ChartPanel chartPanel) {
		this.chartPanel = chartPanel;
	}
}
