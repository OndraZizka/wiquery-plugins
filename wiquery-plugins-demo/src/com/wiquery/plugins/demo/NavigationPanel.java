package com.wiquery.plugins.demo;

import java.io.Serializable;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

/**
 * Navigation Panel
 */
public class NavigationPanel extends Panel {

	private static final long serialVersionUID = 1L;
	
	public static class PageLink implements Serializable {
		
		private static final long serialVersionUID = 1L;
		private Class<? extends Page> pageClass;		
		
		public Class<? extends Page> getPageClass() {
			return pageClass;
		}

		public void setPageClass(Class<? extends Page> pageClass) {
			this.pageClass = pageClass;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		private String text;
		
		public PageLink(Class<? extends Page> pageClass, String text) {
			super();
			this.pageClass = pageClass;
			this.text = text;
		}
		
	}
	
	private PageLink[] LINKS = new PageLink[]{
		new PageLink(GridPage.class, "jQgrid Example"),
		new PageLink(NewGridPage.class, "jQgrid Example (compound cells)"),
		new PageLink(TablePage.class, "Antilia grid"),
		new PageLink(SliderPage.class, "AJAX slider"),
		new PageLink(LayoutPage.class, "Layout on a DIV"),
		new PageLink(ButtonsPage.class, "jQuery UI based buttons"),
		new PageLink(MenuPage.class, "jQuery based Menu"),
		new PageLink(EffectsPage.class, "jQuery core effects"),
		new PageLink(ToolTipPage.class, "jQuery based Tooltip"),
		new PageLink(UIEffectsPage.class, "jQuery UI effects"),
		new PageLink(WatermarkPage.class, "Watermark"),		
		new PageLink(SlideDeckPage.class, "Slide Deck (classic)"),
		new PageLink(JQUISlideDeckPage.class, "Slide Deck (jQuery UI styled)"),
		new PageLink(HorizontalAccordionPage.class, "Horizontal Accordion"),
	};
	
	
    public NavigationPanel(String id) {
    	super(id);        	
    	RepeatingView repeater = new RepeatingView("repeater");
    	add(repeater);
    	for(PageLink link: LINKS) {
    		WebMarkupContainer parent = new WebMarkupContainer(repeater.newChildId());
    		parent.add(new BookmarkablePageLink<Void>("link", link.getPageClass()).add(new Label("text", link.getText())));
    		repeater.add(parent);
    	}
    }
    
}
