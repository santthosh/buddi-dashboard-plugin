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
	
	public int CHART_HEIGHT = 0;
	public int CHART_WIDTH = 0;
	
	public abstract BufferedImage paintChart(MainFrame mainFrame, String chartType, Date startDate, Date endDate);

}
