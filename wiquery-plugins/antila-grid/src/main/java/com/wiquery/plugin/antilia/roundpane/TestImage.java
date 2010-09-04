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
		String shadowColor = "#b9c9cb";		
		String frontColor = "#5c9ccc";
		String frontColorActive = "#516a6d";
		drawImage(Mode.active, shadowColor, frontColorActive);		
		drawImage(Mode.inactive, shadowColor, frontColor);
	}
	
	private enum Mode {
		active,
		inactive;
	}

	public static void drawImage(Mode mode, String shadowColor, String frontColor) {
						
		int shadowWidth = 2;
		
		BufferedImage image = new BufferedImage(200+shadowWidth, 200+shadowWidth, BufferedImage.TYPE_INT_ARGB);
		createTansparency((Graphics2D)image.createGraphics(),shadowWidth);		
		renderCorners((Graphics2D)image.createGraphics(),shadowColor, frontColor, shadowWidth);
		// Write image using any matching ImageWriter
		try {
			final OutputStream out = new FileOutputStream("c:/temp/round/corners_"+mode+".png");
			if(!ImageIO.write(image, "png", out)) {
				System.out.println("Could not find writter");
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		image = new BufferedImage(200 +shadowWidth, 200, BufferedImage.TYPE_INT_ARGB);
		createTansparencyLeftRight((Graphics2D)image.createGraphics(), shadowWidth);		
		renderRectagleLeftRight((Graphics2D)image.createGraphics(), shadowColor, frontColor, shadowWidth);
		// Write image using any matching ImageWriter
		try {
			final OutputStream out = new FileOutputStream("c:/temp/round/leftright_"+mode+".png");
			if(!ImageIO.write(image, "png", out)) {
				System.out.println("Could not find writter");
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		image = new BufferedImage(200, 20+shadowWidth, BufferedImage.TYPE_INT_ARGB);
		createTansparencyTopBottom((Graphics2D)image.createGraphics(), shadowWidth);		
		renderRectagleTopBottom((Graphics2D)image.createGraphics(), shadowColor, frontColor, shadowWidth);
		// Write image using any matching ImageWriter
		try {
			final OutputStream out = new FileOutputStream("c:/temp/round/topbottom_"+mode+".png");
			if(!ImageIO.write(image, "png", out)) {
				System.out.println("Could not find writter");
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}

	protected static boolean createTansparency(Graphics2D graphics, int shadowWidth) {
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				 RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
		Rectangle2D.Double rect = new Rectangle2D.Double(0,0,200+shadowWidth,200+shadowWidth); 
		graphics.fill(rect);
		return true;
	}
	
	protected static boolean createTansparencyLeftRight(Graphics2D graphics, int shadowWidth) {
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				 RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
		Rectangle2D.Double rect = new Rectangle2D.Double(0,0,200+shadowWidth,200); 
		graphics.fill(rect);
		return true;
	}
	
	protected static boolean createTansparencyTopBottom(Graphics2D graphics, int shadowWidth) {
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				 RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
		Rectangle2D.Double rect = new Rectangle2D.Double(0,0,200,20+shadowWidth); 
		graphics.fill(rect);
		return true;
	}
	
	/* (non-Javadoc)
	 * @see org.apache.wicket.markup.html.image.resource.RenderedDynamicImageResource#render(java.awt.Graphics2D)
	 */
	protected static boolean renderCorners(Graphics2D graphics, String shadowColor, String frontColor, int shadowWidth) {
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				 RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setColor(ColorMapper.mapColor(shadowColor));
		graphics.fillRoundRect(shadowWidth, shadowWidth, 200, 200, 15, 15);		
		graphics.setColor(ColorMapper.mapColor(frontColor));
		graphics.fillRoundRect(0, 0, 200, 200, 15, 15);
		return true;
	}
	
	protected static boolean renderRectagleLeftRight(Graphics2D graphics, String shadowColor, String frontColor, int shadowWidth) {
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				 RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setColor(ColorMapper.mapColor(shadowColor));
		graphics.fillRect(shadowWidth, 0, 200, 200);		
		graphics.setColor(ColorMapper.mapColor(frontColor));
		graphics.fillRect(0, 0, 200, 200);
		return true;
	}
	
	protected static boolean renderRectagleTopBottom(Graphics2D graphics, String shadowColor, String frontColor, int shadowWidth) {
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				 RenderingHints.VALUE_ANTIALIAS_ON);
		graphics.setColor(ColorMapper.mapColor(shadowColor));
		graphics.fillRect(0, shadowWidth, 200, 20);		
		graphics.setColor(ColorMapper.mapColor(frontColor));
		graphics.fillRect(0, 0, 200, 20);
		return true;
	}
}
