/**
 * 
 */
package org.homeunix.thecave.plugins;

import javax.swing.JComboBox;
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
	                   
    private JLabel chartTypeLabel;
    private JComboBox chartTypeSelect;
    private JComboBox dateSelect;
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

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(chartTypeLabel)
                        .addGap(1, 1, 1))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(style)
                        .addGap(3, 3, 3)))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(chartTypeSelect, 0, 220, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateSelect, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
                    .addComponent(styleSelect, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(chartTypeLabel)
                    .addComponent(dateSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(chartTypeSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(styleSelect, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(style))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
	}
}

