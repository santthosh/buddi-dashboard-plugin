/**
 * 
 */
package org.homeunix.thecave.plugins.dashboard;


import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Date;

import org.homeunix.thecave.buddi.plugin.api.model.*;
import org.homeunix.thecave.buddi.plugin.api.model.impl.*;
import org.homeunix.thecave.buddi.model.Document;
import org.homeunix.thecave.buddi.view.MainFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.PlotOrientation;

/**
 * @author santthosh
 *
 */
public class IncomeExpenseByCategory extends BuddiChart {
	
	public static final long serialVersionUID = 0;
	
	public IncomeExpenseByCategory(){				
	}
	
	/* (non-Javadoc)
	 * @see org.homeunix.thecave.plugins.dashboard.ChartPanel#paintChart(org.homeunix.thecave.moss.swing.MossDocumentFrame, java.util.Date, java.util.Date)
	 */
	@Override
	public BufferedImage paintChart(MainFrame mainFrame,String chartType, Date startDate,Date endDate) {
					
		ImmutableDocument document = new ImmutableDocumentImpl((Document)mainFrame.getDocument());
		if(document != null)
		{
			
			List<ImmutableBudgetCategory> categories = document.getImmutableBudgetCategories();	
			
			for(int i=0;i< categories.size();i++)
			{
				System.out.println(categories.get(i).getFullName());
			}
		}
		else
		{
			return null;
		}
				
		DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
		categoryDataset.addValue(5D, "Dinner","Food");
		categoryDataset.addValue(10.2D, "Lunch","Food");
		categoryDataset.addValue(11.3D, "BreakFast","Food");
		categoryDataset.addValue(15.4D, "Soap","Groceries");
		categoryDataset.addValue(10.4D, "Detergent","Groceries");
		JFreeChart chart = ChartFactory.createBarChart(				
				"Income and Expenses by Category", 
				"Category", 
				"Percentage", 
				categoryDataset, 
				PlotOrientation.VERTICAL,
				false,
				false,
				false);		                    
						
		BufferedImage image = chart.createBufferedImage(500,150);
		return image;
	}
}
