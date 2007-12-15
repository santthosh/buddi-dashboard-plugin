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
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;

import org.homeunix.thecave.buddi.plugin.BuddiPluginHelper;
import org.homeunix.thecave.buddi.plugin.BuddiPluginHelper.DateChoice;
import org.homeunix.thecave.buddi.plugin.api.PreferenceAccess;
import org.homeunix.thecave.moss.swing.MossPanel;

/**
 * @author Santthosh
 *
 */
public class DataPanel extends MossPanel {
	public static final long serialVersionUID = 0;
	                   
	private JButton cancelButton;
	private JLabel chartTypeLabel;
	protected JComboBox chartTypeSelect;
	protected JComboBox dateSelect;
	private JLabel refreshDelay;
	private JButton saveButton;
	protected JSpinner refreshRate;
	private JLabel style;	
	
	private CancelListener cancelListener;
	private SaveListener saveListener;
	
	/**
	 * Load the datapanel, do not paint it yet!
	 * 
	 * @param parent
	 */
	public DataPanel(DashBoardFrame parent){
		super(true);		
		
		cancelListener = new CancelListener(this,parent);
		saveListener = new SaveListener(this,parent);
		
		open();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.homeunix.thecave.moss.swing.MossPanel#init()
	 */
	@Override
	public void init() {
		super.init();
		
		chartTypeLabel = new JLabel();
        chartTypeSelect = new JComboBox();                
        dateSelect = new JComboBox();
        style = new JLabel();
        refreshDelay = new JLabel();
        refreshRate = new JSpinner();
        saveButton = new JButton();
        cancelButton = new JButton();

        setBorder(BorderFactory.createEtchedBorder());
        chartTypeLabel.setFont(new java.awt.Font("Dialog", 0, 10));
        chartTypeLabel.setText("Chart Type: ");

        chartTypeSelect.setFont(new java.awt.Font("Dialog", 0, 10));
        
        /*
         * Change this COMBO BOX for adding more report types 
         */        
        chartTypeSelect.setModel(new DefaultComboBoxModel(new String[] { "Income and expenses by category for", "Income for", "Expenses for"}));
        chartTypeSelect.setMinimumSize(new java.awt.Dimension(252, 20));
        chartTypeSelect.setName("");
        chartTypeSelect.setPreferredSize(new java.awt.Dimension(252, 20));

        /*
         * Change this COMBO BOX for adding date ranges 
         */
        dateSelect.setFont(new java.awt.Font("Dialog", 0, 10));
        Vector<DateChoice> dateChoices = BuddiPluginHelper.getInterval();
        dateSelect.setModel(new DefaultComboBoxModel(dateChoices));        					
        dateSelect.setMinimumSize(new java.awt.Dimension(112, 20));
        dateSelect.setPreferredSize(new java.awt.Dimension(112, 20));

        style.setFont(new java.awt.Font("Dialog", 0, 10));        

        refreshDelay.setFont(new java.awt.Font("Dialog", 0, 10));
        refreshDelay.setText("Refresh Rate:");

        refreshRate.setFont(new java.awt.Font("Dialog", 0, 10));
        refreshRate.setMinimumSize(new java.awt.Dimension(30, 30));
        refreshRate.setValue(5000);

        saveButton.setFont(new java.awt.Font("Dialog", 0, 10));
        saveButton.setMnemonic('s');
        saveButton.setText("Save");
        saveButton.addActionListener(saveListener);

        cancelButton.setFont(new java.awt.Font("Dialog", 0, 10));
        cancelButton.setMnemonic('C');
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(cancelListener);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(refreshDelay)
                    .addComponent(style)
                    .addComponent(chartTypeLabel))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(chartTypeSelect, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateSelect, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))                         
                        ).addContainerGap())
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(refreshRate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                        .addComponent(saveButton)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton)
                        .addGap(24, 24, 24))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(dateSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(chartTypeSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(chartTypeLabel))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)                        		
                        		.addComponent(style))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(refreshRate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(refreshDelay)))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(saveButton)
                            .addComponent(cancelButton))
                        .addContainerGap())))
        );
	}
	
	/**
	 * Listens for the cancel button event and switches the tab panel to the
	 * chart tab
	 * 
	 * @author Santthosh
	 *
	 */
	public static class CancelListener implements ActionListener
	{ 
		private DashBoardFrame parent;		
		
		public CancelListener(DataPanel dataPanel, DashBoardFrame parent)
		{	
			this.parent = parent;			
		}
		
		public void actionPerformed(ActionEvent e) 
		{
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					parent.tabPanel.setSelectedIndex(0);
				}
			});					
		}
	}
	
	/**
	 * 
	 * Listens for the Save buttons action event and puts all the preferences on
	 * to the preferences file for future  use.
	 * 
	 * @author Santthosh
	 *
	 */
	public static class SaveListener implements ActionListener
	{ 
		private DataPanel dataPanel;
		private DashBoardFrame parent;		
		private PreferenceAccess preferences;
		
		public SaveListener(DataPanel dataPanel, DashBoardFrame parent)
		{	
			this.dataPanel = dataPanel;
			this.parent = parent;
			preferences = parent.preferencesHandler;
		}
		
		public void actionPerformed(ActionEvent e) 
		{
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
										
					preferences.putPreference("org.homeunix.thecave.plugins.dashboard.REPORT",dataPanel.chartTypeSelect.getSelectedItem().toString());
					preferences.putPreference("org.homeunix.thecave.plugins.dashboard.DATE", dataPanel.dateSelect.getSelectedItem().toString());					
					preferences.putPreference("org.homeunix.thecave.plugins.dashboard.REFRESH_RATE",dataPanel.refreshRate.getValue().toString());
					
					javax.swing.JOptionPane.showMessageDialog(
							parent, 
							"Following preferences were saved! \n" +
							"Chart: " + dataPanel.chartTypeSelect.getSelectedItem().toString() + "\n" + 
							"Scope:" + dataPanel.dateSelect.getSelectedItem().toString() +  "\n" +							
							"Refresh rate:" + dataPanel.refreshRate.getValue().toString(), 
							"Preferences Saved!", 
							javax.swing.JOptionPane.INFORMATION_MESSAGE);
										
					parent.tabPanel.setSelectedIndex(0);
				}
			});					
		}
	}
}

