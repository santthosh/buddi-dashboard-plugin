/**
 * 
 */
package org.homeunix.thecave.plugins.dashboard;

import org.homeunix.thecave.buddi.plugin.api.BuddiRunnablePlugin;

import org.homeunix.thecave.moss.exception.WindowOpenException;
import org.homeunix.thecave.moss.util.Log;
 

/**
 * @author Santthosh
 *
 */
public class DashBoardRunnablePlugin extends BuddiRunnablePlugin {
	
	DashBoardFrame dbFrame;

	/* (non-Javadoc)
	 * @see org.homeunix.thecave.moss.plugin.MossPlugin#getDescription()
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub		
		return "DashBoard plugin for Buddi 1.0";
	}

	/* (non-Javadoc)
	 * @see org.homeunix.thecave.moss.plugin.MossPlugin#getName()
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "DashBoard";
	}

	/* (non-Javadoc)
	 * @see org.homeunix.thecave.moss.plugin.MossPlugin#isPluginActive()
	 */
	@Override
	public boolean isPluginActive() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {				
		
		try {
			dbFrame = new DashBoardFrame(this);			
			dbFrame.openWindow();
			setPreferences();		
						
		}				
		catch (WindowOpenException woe){
			woe.printStackTrace(Log.getPrintStream());
		}			
	}
	
	/**
	 * 
	 */
	public void setPreferences()
	{
		//Retrieve and update the location preferences
        if(this.getPreference("org.homeunix.thecave.plugins.dashboard.LOCATION_X") != null &&
           this.getPreference("org.homeunix.thecave.plugins.dashboard.LOCATION_Y") != null)        		
        {
        	Integer X = Integer.parseInt(this.getPreference("org.homeunix.thecave.plugins.dashboard.LOCATION_X"));
        	Integer Y = Integer.parseInt(this.getPreference("org.homeunix.thecave.plugins.dashboard.LOCATION_Y"));        		        		        	
        	
        	if(X !=null && Y!= null && X>0 && Y>0)
        	{        		
        		dbFrame.setLocation(X, Y);
        	}        	
        }
        
        //Retrieve and update the REPORT preferences
        if(this.getPreference("org.homeunix.thecave.plugins.dashboard.REPORT") != null)
        {
        	dbFrame.dataPanel.chartTypeSelect.setSelectedItem(this.getPreference("org.homeunix.thecave.plugins.dashboard.REPORT"));
        }
        
        //Retrieve and update the DATE preferences
        if(this.getPreference("org.homeunix.thecave.plugins.dashboard.DATE") != null)
        {
        	dbFrame.dataPanel.dateSelect.setSelectedItem(this.getPreference("org.homeunix.thecave.plugins.dashboard.DATE"));
        }
        
        //Retrieve and update the CHART_TYPE preferences
        if(this.getPreference("org.homeunix.thecave.plugins.dashboard.CHART_TYPE") != null)
        {
        	dbFrame.dataPanel.styleSelect.setSelectedItem(this.getPreference("org.homeunix.thecave.plugins.dashboard.CHART_TYPE"));
        }
        
        //Retrieve and update the REFRESH_RATE preferences
        if(this.getPreference("org.homeunix.thecave.plugins.dashboard.REFRESH_RATE") != null)
        {
        	dbFrame.dataPanel.refreshRate.setValue(Integer.parseInt(this.getPreference("org.homeunix.thecave.plugins.dashboard.REFRESH_RATE")));
        	
        }        
	}
}
