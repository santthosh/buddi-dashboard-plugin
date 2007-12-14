/**
 * 
 */
package org.homeunix.thecave.plugins.dashboard;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Date;
import java.util.Map;

import org.homeunix.thecave.buddi.plugin.api.model.*;
import org.homeunix.thecave.buddi.plugin.api.model.impl.*;
import org.homeunix.thecave.buddi.plugin.api.util.TextFormatter;
import org.homeunix.thecave.buddi.plugin.builtin.report.BuddiPieSectionLabelGenerator;
import org.homeunix.thecave.buddi.model.Document;
import org.homeunix.thecave.buddi.view.MainFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.plot.PiePlot;

/**
 * @author santthosh
 *
 */
public class Income extends BuddiChart {
	
	public static final long serialVersionUID = 0;


	public Income(){				
	}
	/* (non-Javadoc)
	 * @see org.homeunix.thecave.plugins.dashboard.ChartPanel#paintChart(org.homeunix.thecave.moss.swing.MossDocumentFrame, java.util.Date, java.util.Date)
	 */
	@Override
	public BufferedImage paintChart(MainFrame mainFrame,String chartType, Date startDate,Date endDate) {
					
		ImmutableDocument document = new ImmutableDocumentImpl((Document)mainFrame.getDocument());
		if(document != null)
		{																
			DefaultPieDataset pieData = new DefaultPieDataset();
			
			Map<ImmutableBudgetCategory, Long> categories = getIncomeBetween(document, startDate, endDate);
			
			List<ImmutableBudgetCategory> cats = new LinkedList<ImmutableBudgetCategory>(categories.keySet());
			Collections.sort(cats);
			
			long totalIncome = 0;
			
			for (ImmutableBudgetCategory c : cats) {
				totalIncome += categories.get(c);
				if (categories.get(c) > 0)
					pieData.setValue(TextFormatter.getTranslation(c.toString()), (double) categories.get(c));
			}
					
			JFreeChart chart = ChartFactory.createPieChart(
					"",
					pieData,            // data
					true,               // include legend
					true,
					false
			);
			
			chart.setBackgroundPaint(Color.WHITE);
			chart.setBorderStroke(new BasicStroke(0));
			((PiePlot) chart.getPlot()).setLabelGenerator(new BuddiPieSectionLabelGenerator());
					
			BufferedImage image = chart.createBufferedImage(500,150);
			return image;									
		}
		return null;								                    						
	}
	
	private Map<ImmutableBudgetCategory, Long> getIncomeBetween(ImmutableDocument model, Date startDate, Date endDate){
		List<ImmutableTransaction> transactions =   model.getImmutableTransactions(startDate, endDate);
		Map<ImmutableBudgetCategory, Long> categories = new HashMap<ImmutableBudgetCategory, Long>();
		
		//This map is where we store the totals for this time period.
		for (ImmutableBudgetCategory category : model.getImmutableBudgetCategories()) {
			if (category.isIncome())
				categories.put(category, Long.valueOf(0));
		}
		
		for (ImmutableTransaction transaction : transactions) {
				//Sum up the amounts for each category.
				if (transaction.getFrom() instanceof ImmutableBudgetCategory){
					ImmutableBudgetCategory c = (ImmutableBudgetCategory) transaction.getFrom();
					if (c.isIncome()){
						Long l = categories.get(c);
						l += transaction.getAmount();
						categories.put(c, l);
					}
				}
				else if (transaction.getTo() instanceof ImmutableBudgetCategory){
					ImmutableBudgetCategory c = (ImmutableBudgetCategory) transaction.getTo();
					if (c.isIncome()){
						Long l = categories.get(c);
						l += transaction.getAmount();
						categories.put(c, l);
					}
				}			
		}
				
		return categories;
	}

}
