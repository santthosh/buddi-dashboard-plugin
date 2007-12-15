/**
 * 
 * THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, 
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL 
 * THE CONTRIBUTORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR 
 * OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS 
 * WITH THE SOFTWARE.
 *  
 */
package org.homeunix.thecave.plugins.dashboard;

import org.homeunix.thecave.buddi.plugin.api.BuddiRunnablePlugin;
import org.homeunix.thecave.moss.exception.WindowOpenException;
import org.homeunix.thecave.moss.util.Log;
 

/**
 * Entry point for the Dashboard plugin
 * 
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
		return "DashBoard plugin for Buddi 1.0";
	}

	/* (non-Javadoc)
	 * @see org.homeunix.thecave.moss.plugin.MossPlugin#getName()
	 */
	@Override
	public String getName() {
		return "DashBoard";
	}

	/* (non-Javadoc)
	 * @see org.homeunix.thecave.moss.plugin.MossPlugin#isPluginActive()
	 */
	@Override
	public boolean isPluginActive() {
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * 
	 * Create an instance of the DashBoardFrame and launch the dashboard
	 * Also preload the necessary settings saved to the preferences file
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
	 * This methods retrieves preferences saved to the buddi file and sets 
	 * it to the recently launced dashboard
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
        
        //Retrieve and update the REFRESH_RATE preferences
        if(this.getPreference("org.homeunix.thecave.plugins.dashboard.REFRESH_RATE") != null)
        {
        	dbFrame.dataPanel.refreshRate.setValue(Integer.parseInt(this.getPreference("org.homeunix.thecave.plugins.dashboard.REFRESH_RATE")));
        	
        }
	}
}
