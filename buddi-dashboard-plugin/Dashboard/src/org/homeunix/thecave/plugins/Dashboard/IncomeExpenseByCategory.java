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


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Date;

import org.homeunix.thecave.buddi.Const;
import org.homeunix.thecave.buddi.plugin.api.model.*;
import org.homeunix.thecave.buddi.plugin.api.model.impl.*;
import org.homeunix.thecave.buddi.model.Document;
import org.homeunix.thecave.buddi.view.MainFrame;
import org.homeunix.thecave.moss.util.Log;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.TextTitle;

/**
 * Mimics the income and expenses report by category of Buddi reports to generate a bar chart
 * 
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
			
			//Sort the collections
			Collections.sort(categories, new Comparator<ImmutableBudgetCategory>(){
				public int compare(ImmutableBudgetCategory o1, ImmutableBudgetCategory o2) {
					//First we sort by income
					if (o1.isIncome() != o2.isIncome()){
						if (o1.isIncome()){
							return -1;
						}
						else {
							return 1;
						}
					}
									
					//Finally, we sort by Category Name
					return o1.toString().compareTo(o2.toString());
				}
			});
			
			long totalActual = 0, totalBudgeted = 0;
			
			DefaultCategoryDataset categoryDataset = new DefaultCategoryDataset();			
								
			/*for (ImmutableBudgetCategory c : categories){
				List<ImmutableTransaction> transactions = document.getImmutableTransactions(c, startDate, endDate);
				for (ImmutableTransaction transaction : transactions) {							
						categoryDataset.addValue((double)transaction.getAmount()/100,transaction.getDescription(),c.getName());
				}											
			}*/ 
			
			for (ImmutableBudgetCategory c : categories){
				List<ImmutableTransaction> transactions = document.getImmutableTransactions(c, startDate, endDate);
				long actual = 0;
				for (ImmutableTransaction transaction : transactions) {
						actual += transaction.getAmount();

						if (transaction.getTo() instanceof ImmutableBudgetCategory){
							totalActual -= transaction.getAmount();
						}
						else if (transaction.getFrom() instanceof ImmutableBudgetCategory){
							totalActual += transaction.getAmount();
						}
						else {
							if (Const.DEVEL) Log.debug("For transaction " + transaction + ", neither " + transaction.getTo() + " nor " + transaction.getFrom() + " are of type Category.");
						}					
				}
				
				long budgeted = c.getAmount(startDate, endDate);
				if (c.isIncome()){
					totalBudgeted += budgeted;					
				}
				else {
					totalBudgeted -= budgeted;
				}
				

				if (budgeted != 0 || transactions.size() > 0){												
					long difference = actual - budgeted;
					
					if(c.isIncome())
						categoryDataset.addValue((double)difference/100,c.getName(),"Income");
					else
						categoryDataset.addValue((double)difference/100,c.getName(),"Expense");
				}
			}
			
			long totalDifference = totalActual - totalBudgeted;
									
			JFreeChart chart = ChartFactory.createBarChart(				
					"", 
					"Category", 
					"Amount", 
					categoryDataset, 
					PlotOrientation.VERTICAL,
					true,
					true,
					false);		    
			
			Font font = new Font(Font.DIALOG,Font.PLAIN,10);
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
			TextTitle textTitle = new TextTitle("Income/Expenses by category from "+ dateFormat.format(startDate) + " to " + dateFormat.format(endDate) +
					" : Net Worth: " + (double)totalDifference/100,font);
			chart.setTitle(textTitle);
			chart.setBackgroundPaint(Color.WHITE);
			chart.setBorderStroke(new BasicStroke(0));
				
			BufferedImage image = null;
			if(CHART_HEIGHT > 150  && CHART_WIDTH > 500)
				image = chart.createBufferedImage(CHART_WIDTH,CHART_HEIGHT);
			else
				image = chart.createBufferedImage(500,150);
			return image;
		}
		return null;							                    							
	}
}
