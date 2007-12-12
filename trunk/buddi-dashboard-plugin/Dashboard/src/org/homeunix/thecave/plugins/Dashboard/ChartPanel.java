/**
 * 
 */
package org.homeunix.thecave.plugins.dashboard;

import javax.swing.BorderFactory;

import java.util.Date;

import org.homeunix.thecave.moss.swing.MossDocumentFrame;
import org.homeunix.thecave.moss.swing.MossFrame;
import org.homeunix.thecave.moss.swing.MossPanel;

/**
 * @author Santthosh
 *
 */
public abstract class ChartPanel extends MossPanel {
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
   	}
	
	public abstract void paintChart(Date startDate, Date endDate);

}
