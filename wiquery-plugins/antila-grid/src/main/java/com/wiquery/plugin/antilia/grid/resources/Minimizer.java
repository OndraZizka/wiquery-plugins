/**
 * 
 */
package com.wiquery.plugin.antilia.grid.resources;

import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.yahoo.platform.yui.compressor.CssCompressor;
import com.yahoo.platform.yui.compressor.JavaScriptCompressor;

/**
 * Helper class useful to minimize resources.
 * 
 * @author Ernesto Reinaldo Barreiro
 *
 */
public class Minimizer {

	public static void main(String[] args) {
		minimizeJS("table");
		minimizeJS("common");
		
		minimizeCSS("main");
		minimizeCSS("table");
		minimizeCSS("menu");
	}
	
	public static void minimizeJS(String name) {
		JavaScriptCompressor compressor;

		try {
			compressor = new JavaScriptCompressor(new InputStreamReader(
					DefaultStyle.class.getResourceAsStream(name+".js")), null);
			FileOutputStream outstream = new FileOutputStream("C://temp/"+name+".min.js");
			OutputStreamWriter writer = new OutputStreamWriter(outstream,
					"UTF-8");
			compressor.compress(writer, -1, false, false, true, false);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void minimizeCSS(String name) {
		CssCompressor compressor;

		try {
			compressor = new CssCompressor(new InputStreamReader(DefaultStyle.class.getResourceAsStream(name+".css")));
			FileOutputStream outstream = new FileOutputStream("C://temp/"+name+".min.css");
			OutputStreamWriter writer = new OutputStreamWriter(outstream,
					"UTF-8");
			compressor.compress(writer, -1);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
