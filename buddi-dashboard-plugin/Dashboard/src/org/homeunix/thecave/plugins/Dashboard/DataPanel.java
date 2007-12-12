/**
 * 
 */
package org.homeunix.thecave.plugins.dashboard;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;

import org.homeunix.thecave.moss.swing.MossPanel;
import org.homeunix.thecave.moss.swing.MossFrame;

/**
 * @author Santthosh
 *
 */
public class DataPanel extends MossPanel {
	public static final long serialVersionUID = 0;
	
	private final MossFrame parent;
	                   
	private JButton cancelButton;
	private JLabel chartTypeLabel;
	private JComboBox chartTypeSelect;
	private JComboBox dateSelect;
	private JLabel refreshDelay;
	private JButton saveButton;
	private JSpinner spinner;
	private JLabel style;
	private JComboBox styleSelect;
	
	public DataPanel(MossFrame parent){
		super(true);
		this.parent = parent;
		
		open();
	}
	
	public void init() {
		super.init();
		
		chartTypeLabel = new JLabel();
        chartTypeSelect = new JComboBox();
        dateSelect = new JComboBox();
        style = new JLabel();
        styleSelect = new JComboBox();
        refreshDelay = new JLabel();
        spinner = new JSpinner();
        saveButton = new JButton();
        cancelButton = new JButton();

        setBorder(BorderFactory.createEtchedBorder());
        chartTypeLabel.setFont(new java.awt.Font("Dialog", 0, 10));
        chartTypeLabel.setText("Chart Type: ");

        chartTypeSelect.setFont(new java.awt.Font("Dialog", 0, 10));
        chartTypeSelect.setModel(new DefaultComboBoxModel(new String[] { "Income and expenses by category for", "Average income and expenses by category for", "Income for", "Expenses for", "Networth for the past" }));
        chartTypeSelect.setMinimumSize(new java.awt.Dimension(252, 20));
        chartTypeSelect.setName("");
        chartTypeSelect.setPreferredSize(new java.awt.Dimension(252, 20));

        dateSelect.setFont(new java.awt.Font("Dialog", 0, 10));
        dateSelect.setModel(new DefaultComboBoxModel(new String[] { "Past Week", "Past Fortnight", "This Month", "Last Month", "This Quarter", "Last Quarter", "This Year", "This Year to Date", "Last Year" }));
        dateSelect.setMinimumSize(new java.awt.Dimension(112, 20));
        dateSelect.setPreferredSize(new java.awt.Dimension(112, 20));

        style.setFont(new java.awt.Font("Dialog", 0, 10));
        style.setText("Style: ");

        styleSelect.setFont(new java.awt.Font("Dialog", 0, 10));
        styleSelect.setModel(new DefaultComboBoxModel(new String[] { "Line Graph", "Pie Chart" }));
        styleSelect.setMinimumSize(new java.awt.Dimension(68, 20));
        styleSelect.setPreferredSize(new java.awt.Dimension(68, 20));

        refreshDelay.setFont(new java.awt.Font("Dialog", 0, 10));
        refreshDelay.setText("Refresh Rate:");

        spinner.setFont(new java.awt.Font("Dialog", 0, 10));
        spinner.setMinimumSize(new java.awt.Dimension(30, 30));
        spinner.setValue(5000);

        saveButton.setFont(new java.awt.Font("Dialog", 0, 10));
        saveButton.setMnemonic('s');
        saveButton.setText("Save");

        cancelButton.setFont(new java.awt.Font("Dialog", 0, 10));
        cancelButton.setMnemonic('C');
        cancelButton.setText("Cancel");

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
                            .addComponent(styleSelect, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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
                            .addComponent(styleSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(style))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(refreshDelay)))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(saveButton)
                            .addComponent(cancelButton))
                        .addContainerGap())))
        );
	}
}

