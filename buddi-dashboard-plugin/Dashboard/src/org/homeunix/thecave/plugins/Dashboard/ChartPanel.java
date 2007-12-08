/**
 * 
 */
package org.homeunix.thecave.plugins.dashboard;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.image.BufferedImage;


import org.homeunix.thecave.moss.swing.MossFrame;
import org.homeunix.thecave.moss.swing.MossPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.PlotOrientation;


/**
 * @author Santthosh
 *
 */
public class ChartPanel extends MossPanel {
	public static final long serialVersionUID = 0;
	
	private final MossFrame parent;     
	JLabel lblChart;
	
	public ChartPanel(MossFrame parent){
		super(true);
		
		this.parent = parent;
		
		open();
	}
		
	public void init() {	
		super.init();		
		setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 102, 153)));		
		paintChart();
   	}
	
	public void paintChart()
	{
		DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();
		categoryDataset.addValue(5D, "Dinner","Food");
		categoryDataset.addValue(10.2D, "Lunch","Food");
		categoryDataset.addValue(11.3D, "BreakFast","Food");
		categoryDataset.addValue(15.4D, "Soap","Groceries");
		categoryDataset.addValue(10.4D, "Detergent","Groceries");
		JFreeChart chart = ChartFactory.createBarChart(				
				"", 
				"Category", 
				"Percentage", 
				categoryDataset, 
				PlotOrientation.VERTICAL,
				false,
				false,
				false);		                    
						
		BufferedImage image = chart.createBufferedImage(500,150);
		lblChart = new JLabel();
		lblChart.setIcon(new ImageIcon(image));	
		
		this.add(lblChart);
	}
}
