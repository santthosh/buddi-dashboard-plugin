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

import java.awt.image.BufferedImage;
import java.util.Date;

import org.homeunix.thecave.buddi.view.MainFrame;

/**
 * Abstract super class for all the varieties of the chars
 * 
 * @author santthosh
 *
 */
public abstract class BuddiChart {
	
	public int CHART_HEIGHT = 0;
	public int CHART_WIDTH = 0;
	
	/**
	 * Every implementation class should have this method, so that the Chart Panel 
	 * can use it
	 * 
	 * @param mainFrame
	 * @param chartType
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public abstract BufferedImage paintChart(MainFrame mainFrame, String chartType, Date startDate, Date endDate);

}
