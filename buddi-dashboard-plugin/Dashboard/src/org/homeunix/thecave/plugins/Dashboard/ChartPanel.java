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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.ImageIcon;

import org.homeunix.thecave.buddi.plugin.BuddiPluginHelper.DateChoice;
import org.homeunix.thecave.buddi.plugin.api.PreferenceAccess;
import org.homeunix.thecave.buddi.view.MainFrame;
import org.homeunix.thecave.moss.swing.ApplicationModel;
import org.homeunix.thecave.moss.swing.MossFrame;
import org.homeunix.thecave.moss.swing.MossPanel;
import org.homeunix.thecave.moss.util.Log;

/**
 * ChartPanel where the actual chart is displayed 
 * 
 * @author Santthosh
 *
 */
public class ChartPanel extends MossPanel {
	public static final long serialVersionUID = 0;
	
	protected final DashBoardFrame parent;
	
	JPanel launchPanel;
	JLabel launchLabel;
	
	Timer timer;	
	ActivationListener activationListener;	
	
	/**
	 * Create the ChartPanel, do not paint it yet!
	 * 
	 * @param parent
	 */
	public ChartPanel(DashBoardFrame parent){
		super(true);
		
		this.parent = parent;
				
		activationListener = new ActivationListener(this,parent);
		timer = new Timer(8000,activationListener);		
						
		open();
	}
		
	/*
	 * (non-Javadoc)
	 * @see org.homeunix.thecave.moss.swing.MossPanel#init()
	 */
	public void init() {	
		super.init();		
		setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)));
		
		paintLaunchLabel();
		timer.start();		
   	}		
	
	/**
	 * Paint the launch label when necessary
	 */	
	public void paintLaunchLabel()
	{
		launchPanel = new JPanel();		
		launchLabel = new JLabel("Dashboard is initializing..!");
					
		launchPanel.add(launchLabel);
		this.add(launchPanel);
	}
	
	/**
	 * Timer event handler.
	 * 
	 * On a timer event, identifies the frame, instantiates the BuddiChart,
	 * sets the preferences for the chart and gets the chart painted by a BuddiChart
	 * instance and then sets the resulting image as icon to the launchLabel
	 * 
	 * @author Santthosh
	 *
	 */
	public static class ActivationListener implements ActionListener
	{
		private ChartPanel chartPanel;		
		private DashBoardFrame parent;
		static String CHART_STYLE = null;
		private MainFrame mainFrame = null;
		protected PreferenceAccess preferencesHandler;
		private BuddiChart buddiChart = null;
		
		public ActivationListener(ChartPanel chartPanel, DashBoardFrame parent)
		{
			this.chartPanel = chartPanel;			
			this.parent = parent;	
			this.preferencesHandler = parent.preferencesHandler;
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
										
					if(preferencesHandler == null)
					{
						Log.emergency("Preference handler is null!");
						return;
					}
					
					if(preferencesHandler.getPreference("org.homeunix.thecave.plugins.dashboard.REPORT")!=null)
					{
						if(preferencesHandler.getPreference("org.homeunix.thecave.plugins.dashboard.REPORT").equalsIgnoreCase("Income and expenses by category for"))
							buddiChart = new IncomeExpenseByCategory();  
						if(preferencesHandler.getPreference("org.homeunix.thecave.plugins.dashboard.REPORT").equalsIgnoreCase("Income for"))
							buddiChart = new Income();
						if(preferencesHandler.getPreference("org.homeunix.thecave.plugins.dashboard.REPORT").equalsIgnoreCase("Expenses for"))
							buddiChart = new Expenses();
					}
					
					if(buddiChart == null)
						buddiChart = new IncomeExpenseByCategory();

					if(preferencesHandler.getPreference("org.homeunix.thecave.plugins.dashboard.WINDOW_HEIGHT")!=null &&
				       preferencesHandler.getPreference("org.homeunix.thecave.plugins.dashboard.WINDOW_WIDTH")!=null)
					{
						buddiChart.CHART_HEIGHT = Integer.parseInt(preferencesHandler.getPreference("org.homeunix.thecave.plugins.dashboard.WINDOW_HEIGHT")) - 103;
						buddiChart.CHART_WIDTH = Integer.parseInt(preferencesHandler.getPreference("org.homeunix.thecave.plugins.dashboard.WINDOW_WIDTH")) - 35;	
						
						System.out.println("Height: " + buddiChart.CHART_HEIGHT + "Width: " + buddiChart.CHART_WIDTH); 								
					}
										
					//Find out which item was clicked on
					Object o = parent.dataPanel.dateSelect.getSelectedItem();
										
					//If the object was a date choice, access the encoded dates
					if (o instanceof DateChoice){
						DateChoice choice = (DateChoice) o;

						//As long as the choice is not custom, our job is easy
						if (!choice.isCustom()){
							//Repaint the chart		
							BufferedImage image = buddiChart.paintChart(mainFrame,CHART_STYLE, choice.getStartDate(), choice.getEndDate());
							chartPanel.launchLabel.setText("");
							chartPanel.launchLabel.setIcon(new ImageIcon(image));							
							chartPanel.launchLabel.repaint();
						}
						//If they want a custom window, it's a little harder... we need to open the custom date window, which then launches the plugin.
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
