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
	
	private int steps;
	
	/**
	 * @param id
	 */
	public ExtraLinksPanel(String id, int steps) {
		super(id);	
		if(steps < 1)
			throw new IllegalArgumentException("Steps have to be greater or equal to 1");
		this.steps = steps;
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
					return current -steps > 1;
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
					return current + steps < pages;
				}
			};
			add(finalDots);
		}	
		
		IPageableComponent<E> pc = findPageableComponent();
		int current = pc.currentPageNumber()+1;
		int pages =  pc.getNumberOfPages();
		
		List<Integer> pageNumbers = new ArrayList<Integer>();
		
		int start = (current -steps);		
		int end = current + steps;
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
	
	
	@SuppressWarnings("unchecked")
	protected final IPageableComponent<E> findPageableComponent() {
		return (IPageableComponent<E>)findParent(IPageableComponent.class);
	}
}
