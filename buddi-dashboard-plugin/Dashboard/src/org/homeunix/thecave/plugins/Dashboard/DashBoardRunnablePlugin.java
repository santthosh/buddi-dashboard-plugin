/**
 * 
 */
package org.homeunix.thecave.plugins.dashboard;

import org.homeunix.thecave.buddi.plugin.api.BuddiRunnablePlugin;
import org.homeunix.thecave.buddi.*;
import org.homeunix.thecave.moss.exception.WindowOpenException;
import org.homeunix.thecave.moss.util.Log;
import org.homeunix.drummer.model.DataInstance;
import org.homeunix.drummer.model.*; 

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
		//Retrieve the location preferences
        if(this!=null &&
           this.getPreference("org.homeunix.thecave.plugins.dashboard.LOCATION_X") != null &&
           this.getPreference("org.homeunix.thecave.plugins.dashboard.LOCATION_Y") != null)        		
        {
        	Integer X = Integer.parseInt(this.getPreference("org.homeunix.thecave.plugins.dashboard.LOCATION_X"));
        	Integer Y = Integer.parseInt(this.getPreference("org.homeunix.thecave.plugins.dashboard.LOCATION_Y"));        		        		        	
        	
        	if(X !=null && Y!= null && X>0 && Y>0)
        	{        		
        		dbFrame.setLocation(X, Y);
        	}        	
        }
	}
}
