/**
 * 
 */
package org.homeunix.thecave.plugins.dashboard;

import org.homeunix.thecave.buddi.model.Document;
import org.homeunix.thecave.buddi.model.impl.ModelFactory;
import org.homeunix.thecave.buddi.model.prefs.PrefsModel;
import org.homeunix.thecave.buddi.plugin.api.BuddiRunnablePlugin;
import org.homeunix.thecave.buddi.plugin.api.exception.ModelException;
import org.homeunix.thecave.buddi.view.MainFrame;
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
		}				
		catch (WindowOpenException woe){
			woe.printStackTrace(Log.getPrintStream());
		}			
	}

}
