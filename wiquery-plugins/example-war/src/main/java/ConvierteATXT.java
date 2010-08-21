

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;

import com.wiquery.plugins.demo.BasePage;
import com.wiquery.plugins.demo.ButtonsPage;
import com.wiquery.plugins.demo.DatePickerPage;
import com.wiquery.plugins.demo.DatePickerPanel;
import com.wiquery.plugins.demo.GridPage;
import com.wiquery.plugins.demo.GridPanel;
import com.wiquery.plugins.demo.JQIconsPanel;
import com.wiquery.plugins.demo.code.FileUtils;

/**
 * @author  Ernesto Reinaldo Barreiro
 *
 */
public class ConvierteATXT {

	public static final Class<?>[] CLASSES = {
			BasePage.class,
			
			DatePickerPage.class,
			DatePickerPanel.class,
			
			GridPage.class,
			GridPanel.class,
			
			ButtonsPage.class,
			JQIconsPanel.class			
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
