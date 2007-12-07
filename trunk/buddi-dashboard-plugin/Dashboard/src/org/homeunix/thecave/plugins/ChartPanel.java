/**
 * 
 */
package org.homeunix.thecave.plugins;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;

import org.homeunix.thecave.moss.swing.MossFrame;
import org.homeunix.thecave.moss.swing.MossPanel;


/**
 * @author Santthosh
 *
 */
public class ChartPanel extends MossPanel {
	public static final long serialVersionUID = 0;
	
	private final MossFrame parent;     	 
	
	public ChartPanel(MossFrame parent){
		super(true);
		
		this.parent = parent;
		
		open();
	}
		
	public void init() {	
		super.init();
		
		setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)));
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGap(0, 350, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGap(0, 100, Short.MAX_VALUE)
		);
   	}
}
