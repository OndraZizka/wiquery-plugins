package com.wiquery.plugins.demo;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Navigation Panel
 */
public class NavigationPanel extends Panel {

	private static final long serialVersionUID = 1L;

    public NavigationPanel(String id) {
    	super(id);
    	
    	BookmarkablePageLink<Void> jqgrid = new BookmarkablePageLink<Void>("jqgrid", GridPage.class);
    	add(jqgrid);
    	
    	BookmarkablePageLink<Void> antilia = new BookmarkablePageLink<Void>("antilia", TablePage.class);
    	add(antilia);
    	
    	BookmarkablePageLink<Void> slider = new BookmarkablePageLink<Void>("slider", SliderPage.class);
    	add(slider);
    	
    	BookmarkablePageLink<Void> layout = new BookmarkablePageLink<Void>("layout", LayoutPage.class);
    	add(layout);
    	
    	BookmarkablePageLink<Void> buttons = new BookmarkablePageLink<Void>("buttons", ButtonsPage.class);
    	add(buttons);
    	
    	BookmarkablePageLink<Void> menu = new BookmarkablePageLink<Void>("menu", MenuPage.class);
    	add(menu);
    	
    	BookmarkablePageLink<Void> effects = new BookmarkablePageLink<Void>("effects", EffectsPage.class);
    	add(effects);
    	
    	BookmarkablePageLink<Void> tooltips = new BookmarkablePageLink<Void>("tooltips", ToolTipPage.class);
    	add(tooltips);
    	
    	BookmarkablePageLink<Void> uieffects = new BookmarkablePageLink<Void>("uieffects", UIEffectsPage.class);
    	add(uieffects);
    	
    	BookmarkablePageLink<Void> watermark = new BookmarkablePageLink<Void>("watermark", WatermarkPage.class);
    	add(watermark);
    	
    	BookmarkablePageLink<Void> newgrid = new BookmarkablePageLink<Void>("newgrid", NewGridPage.class);
    	add(newgrid);
    	
    	BookmarkablePageLink<Void> slidedeck = new BookmarkablePageLink<Void>("slidedeck", SlideDeckPage.class);
    	add(slidedeck);    	
    	
    	BookmarkablePageLink<Void> hzAccordion = new BookmarkablePageLink<Void>("hzAccordion", HorizontalAccordionPage.class);
    	add(hzAccordion);  	
    	
    }
    
}
