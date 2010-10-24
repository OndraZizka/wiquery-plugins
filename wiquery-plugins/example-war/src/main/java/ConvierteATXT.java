

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;

import com.wiquery.plugins.demo.BasePage;
import com.wiquery.plugins.demo.ButtonsPage;
import com.wiquery.plugins.demo.DatePickerPage;
import com.wiquery.plugins.demo.DatePickerPanel;
import com.wiquery.plugins.demo.DialogPage;
import com.wiquery.plugins.demo.DialogPanel;
import com.wiquery.plugins.demo.EffectSpeedPanel;
import com.wiquery.plugins.demo.EffectsPage;
import com.wiquery.plugins.demo.EffectsPanel;
import com.wiquery.plugins.demo.ExampleCellLink;
import com.wiquery.plugins.demo.GridPage;
import com.wiquery.plugins.demo.GridPanel;
import com.wiquery.plugins.demo.HorizontalAccordionPage;
import com.wiquery.plugins.demo.IndicatorPanel;
import com.wiquery.plugins.demo.JQIconsPanel;
import com.wiquery.plugins.demo.JQUISlideDeckPage;
import com.wiquery.plugins.demo.JQUISlideDeckPanel;
import com.wiquery.plugins.demo.LayoutPage;
import com.wiquery.plugins.demo.LayoutPanel;
import com.wiquery.plugins.demo.MenuPage;
import com.wiquery.plugins.demo.NewGridPage;
import com.wiquery.plugins.demo.NewGridPanel;
import com.wiquery.plugins.demo.ResizablePage;
import com.wiquery.plugins.demo.ResizablePanel;
import com.wiquery.plugins.demo.RoundPanelPage;
import com.wiquery.plugins.demo.RoundPanelPanel;
import com.wiquery.plugins.demo.SlideDeckPage;
import com.wiquery.plugins.demo.SlideDeckPanel;
import com.wiquery.plugins.demo.SliderPage;
import com.wiquery.plugins.demo.SortablePage;
import com.wiquery.plugins.demo.SortablePanel;
import com.wiquery.plugins.demo.SuperfishMenuPage;
import com.wiquery.plugins.demo.SuperfishMenuPanel;
import com.wiquery.plugins.demo.TablePage;
import com.wiquery.plugins.demo.TablePanel;
import com.wiquery.plugins.demo.TestLinkEffectsPage;
import com.wiquery.plugins.demo.TestLinkEffectsPanel;
import com.wiquery.plugins.demo.ToolTipPage;
import com.wiquery.plugins.demo.ToolTipPanel;
import com.wiquery.plugins.demo.UIEffectsPage;
import com.wiquery.plugins.demo.UIEffectsPanel;
import com.wiquery.plugins.demo.WatermarkPage;
import com.wiquery.plugins.demo.code.FileUtils;
import com.wiquery.plugins.demo.watermark.WatermarkPanel;

/**
 * @author  Ernesto Reinaldo Barreiro
 *
 */
public class ConvierteATXT {

	public static final Class<?>[] CLASSES = {
			BasePage.class,
			
			ButtonsPage.class,
			JQIconsPanel.class,
			
			DatePickerPage.class,
			DatePickerPanel.class,
			
			GridPage.class,
			GridPanel.class,
			
			EffectsPage.class,
			EffectsPanel.class,
			EffectSpeedPanel.class,
			
			GridPage.class,
			GridPanel.class,
			
			ExampleCellLink.class,
			
			HorizontalAccordionPage.class,
			IndicatorPanel.class,
			
			JQUISlideDeckPage.class,
			JQUISlideDeckPanel.class,
			
			LayoutPage.class,
			LayoutPanel.class,
			
			MenuPage.class,
			
			NewGridPage.class,
			NewGridPanel.class,
			
			ResizablePage.class,
			ResizablePanel.class,
			
			RoundPanelPage.class,
			RoundPanelPanel.class,
			
			SlideDeckPage.class,
			SlideDeckPanel.class,
			
			SliderPage.class,
			
			SortablePage.class,
			SortablePanel.class,
			
			TablePage.class,
			TablePanel.class,
			
			TestLinkEffectsPage.class,
			TestLinkEffectsPanel.class,
			
			ToolTipPage.class,
			ToolTipPanel.class,
			
			UIEffectsPage.class,
			UIEffectsPanel.class,
			
			WatermarkPage.class,
			WatermarkPanel.class,
			
			SuperfishMenuPage.class,
			SuperfishMenuPanel.class,
			
			DialogPage.class,
			DialogPanel.class,
			
		};
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		URL url = ConvierteATXT.class.getResource("README.txt");
		System.out.println(url.getPath());
		String path = url.getPath();
		path = path.substring(1, path.indexOf("/webapp"))+"/java/";
		System.out.println(path);
		for(Class<?> clazz: CLASSES) {
			String classFolder = path + packageToFolder(clazz.getPackage());
			System.out.println(clazz.getPackage());
			System.out.println(classFolder);
			String classFileName = classFolder +"/"+ clazz.getSimpleName() + ".java";
			String txtFileName = classFolder +"/"+ clazz.getSimpleName() + ".txt";
			System.out.println(classFileName);
			File classFile = new File(classFileName);
			File txtFile = new File(txtFileName);
			try {
				FileInputStream in = new FileInputStream(classFile);
				FileOutputStream os = new FileOutputStream(txtFile);
				FileUtils.copy(in, os);
				in.close();
				os.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
		}
	}
	
	private static String packageToFolder(Package package1) {
		return package1.getName().replace('.', '/');
	}
}
