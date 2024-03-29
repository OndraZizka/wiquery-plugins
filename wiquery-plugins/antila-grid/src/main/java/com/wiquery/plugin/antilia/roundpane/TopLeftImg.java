/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.wiquery.plugin.antilia.roundpane;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class TopLeftImg extends TransparentImageResource {

	private static final long serialVersionUID = 1L;

	private Color color;

	/**
	 * 
	 * @param color
	 */
	public TopLeftImg(final Color color, boolean ie6) {
		super(18, 18, ie6?"gif":"png");
		this.color = color;
	}
	
	/**
	 * 
	 * @param color
	 */
	public TopLeftImg(String color, boolean ie6) {
		this(ColorMapper.mapColor(color), ie6);
	}
	

	/* (non-Javadoc)
	 * @see org.apache.wicket.markup.html.image.resource.RenderedDynamicImageResource#render(java.awt.Graphics2D)
	 */
	@Override
	protected boolean render(Graphics2D graphics) {
		graphics.setColor(getColor());		
		graphics.fillArc(0, 0, 36, 36, 90, 90);
		return true;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
