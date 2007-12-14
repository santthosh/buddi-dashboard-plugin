/**
 * 
 */
package org.homeunix.thecave.plugins.dashboard;

import java.util.Date;
import java.awt.image.BufferedImage;

import org.homeunix.thecave.buddi.view.MainFrame;

/**
 * @author santthosh
 *
 */
public abstract class BuddiChart {
	
	public abstract BufferedImage paintChart(MainFrame mainFrame, String chartType, Date startDate, Date endDate);

}
