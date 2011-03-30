package com.wiquery.plugin.antilia.grid.navigation2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

import com.wiquery.plugin.antilia.grid.IPageableComponent;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public class ExtraLinksPanel<E extends Serializable> extends Panel {

	private static final long serialVersionUID = 1L;

	private Label initialDots;
	/**
	 * @param id
	 */
	public ExtraLinksPanel(String id) {
		super(id);			
		setRenderBodyOnly(true);
	}
	
	@Override
	protected void onBeforeRender() {
		if(initialDots == null) {
			initialDots = new Label("initialDots","...") {
				
				private static final long serialVersionUID = 1L;

				@Override
				public boolean isVisible() {
					IPageableComponent<E> pc = findPageableComponent();
					int current = pc.currentPageNumber()+1;
					return current -5 > 1;
				}
			};
			add(initialDots);
			
			
			
			Label finalDots  = new Label("finalDots","...") {
				
				private static final long serialVersionUID = 1L;

				@Override
				public boolean isVisible() {
					IPageableComponent<E> pc = findPageableComponent();
					int current = pc.currentPageNumber()+1;
					int pages =  pc.getNumberOfPages();
					return current + 5 < pages;
				}
			};
			add(finalDots);
		}	
		
		IPageableComponent<E> pc = findPageableComponent();
		int current = pc.currentPageNumber()+1;
		int pages =  pc.getNumberOfPages();
		
		List<Integer> pageNumbers = new ArrayList<Integer>();
		
		int start = (current -5);		
		int end = current +5;
		end = Math.min(end, pages);
		
		for(int i=start; i <= end;i++) {
			if(i>0) {
				pageNumbers.add(new Integer(i));
			}
		}
		
		RepeatingView pageLinks = new RepeatingView("pageLinks");
		addOrReplace(pageLinks);
		for(Integer integer: pageNumbers) {
			pageLinks.add(new NumberedPagePanel<E>(pageLinks.newChildId(), integer));
		}				
		super.onBeforeRender();
	}
	
	@Override
	public boolean isVisible() {
		IPageableComponent<E> pc = findPageableComponent();
		return (pc.getNumberOfPages() > 2);
	}
	
	
	@SuppressWarnings("unchecked")
	protected final IPageableComponent<E> findPageableComponent() {
		return (IPageableComponent<E>)findParent(IPageableComponent.class);
	}
}
