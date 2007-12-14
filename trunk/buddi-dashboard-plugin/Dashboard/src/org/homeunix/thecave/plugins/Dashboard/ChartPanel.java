/**
 * 
 */
package org.homeunix.thecave.plugins.dashboard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import java.util.Date;

import org.homeunix.thecave.buddi.plugin.BuddiPluginHelper.DateChoice;
import org.homeunix.thecave.buddi.view.MainFrame;
import org.homeunix.thecave.moss.swing.ApplicationModel;
import org.homeunix.thecave.moss.swing.MossFrame;
import org.homeunix.thecave.moss.swing.MossPanel;
import org.homeunix.thecave.moss.util.Log;

/**
 * @author Santthosh
 *
 */
public abstract class ChartPanel extends MossPanel {
	public static final long serialVersionUID = 0;
	
	protected final DashBoardFrame parent;
	
	JPanel launchPanel;
	JLabel launchLabel;
	
	Timer timer;	
	ActivationListener activationListener;	
	
	public ChartPanel(DashBoardFrame parent){
		super(true);
		
		this.parent = parent;
				
		activationListener = new ActivationListener(this,parent);
		timer = new Timer(8000,activationListener);		
						
		open();
	}
		
	public void init() {	
		super.init();		
		setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)));
		
		paintLaunchLabel();
		timer.start();		
   	}
	
	public abstract void paintChart(MainFrame mainFrame, String chartType, Date startDate, Date endDate);
	
	public void paintLaunchLabel()
	{
		launchPanel = new JPanel();		
		launchLabel = new JLabel("Dashboard is initializing..!");
					
		launchPanel.add(launchLabel);
		this.add(launchPanel);
	}
	
	public static class ActivationListener implements ActionListener
	{
		private ChartPanel chartPanel;		
		private DashBoardFrame parent;
		static String CHART_STYLE = null;
		private MainFrame mainFrame = null;
		
		public ActivationListener(ChartPanel chartPanel, DashBoardFrame parent)
		{
			this.chartPanel = chartPanel;			
			this.parent = parent;			
		}
		
		public void actionPerformed(ActionEvent e) 
		{
			refreshChart();			
		}	
		
		public void refreshChart()
		{
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
			///Check if we have opened at least one window...						
			for (MossFrame mossFrame : ApplicationModel.getInstance().getOpenFrames()) {
				if (mossFrame instanceof MainFrame){
					mainFrame = (MainFrame) mossFrame;
					break;
				}
			}
			if (mainFrame == null)
			{
				Log.emergency("No Buddi main windows were open!");
				return;
			}			
			
			CHART_STYLE = parent.dataPanel.chartTypeSelect.getSelectedItem().toString();					
			
			//Find out which item was clicked on
			Object o = parent.dataPanel.dateSelect.getSelectedItem();

			//If the object was a date choice, access the encoded dates
			if (o instanceof DateChoice){
				DateChoice choice = (DateChoice) o;

				//As long as the choice is not custom, our job is easy
				if (!choice.isCustom()){
					//Repaint the chart		
					chartPanel.paintChart(mainFrame,CHART_STYLE, choice.getStartDate(), choice.getEndDate());					
				}
				//If they want a custom window, it's a little 
				// harder... we need to open the custom date
				// window, which then launches the plugin.
				else{
					javax.swing.JOptionPane.showMessageDialog(
							parent, 
							"Custom date ranges not supported yet!", 
							"Custom date range not supported!", 
							javax.swing.JOptionPane.INFORMATION_MESSAGE);
				}
			}									
			parent.pack();					
			}
			});
		}				
	}

}
