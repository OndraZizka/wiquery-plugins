/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.wiquery.plugin.antilia.roundpane;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class TestImage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		drawImage();
	}

	public static void drawImage() {
		final BufferedImage image = new BufferedImage(210, 210, BufferedImage.TYPE_INT_ARGB);
		createTansparency((Graphics2D)image.createGraphics());		
		render((Graphics2D)image.createGraphics());
		// Write image using any matching ImageWriter
		try {
			final OutputStream out = new FileOutputStream("c:/temp/round/top_left.png");
			if(!ImageIO.write(image, "png", out)) {
				System.out.println("Could not find writter");
			}
			out.close();
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	protected static boolean createTansparency(Graphics2D graphics) {
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				 RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
		Rectangle2D.Double rect = new Rectangle2D.Double(0,0,200,200); 
		graphics.fill(rect);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see org.apache.wicket.markup.html.image.resource.RenderedDynamicImageResource#render(java.awt.Graphics2D)
	 */
	protected static boolean render(Graphics2D graphics) {
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				 RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setColor(ColorMapper.mapColor("#aaaaaa"));
		graphics.fillRoundRect(1, 1, 201, 201, 15, 15);		
		graphics.setColor(ColorMapper.mapColor("#ff9900"));
		graphics.fillRoundRect(0, 0, 200, 200, 15, 15);
		return true;
	}
}
