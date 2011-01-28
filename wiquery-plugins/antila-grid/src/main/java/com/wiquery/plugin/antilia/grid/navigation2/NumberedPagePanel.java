/**
 * 
 */
package com.wiquery.plugin.antilia.grid.navigation2;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import com.wiquery.plugin.antilia.grid.IPageableComponent;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public class NumberedPagePanel<E extends Serializable> extends Panel {
	
	private static final long serialVersionUID = 1L;

	int number;
	public NumberedPagePanel(String id, int number) {
		super(id);		
		this.number = number;
		setRenderBodyOnly(true);
	}
	
	@Override
	protected void onBeforeRender() {
		if(isCurrentPage()) {
			addOrReplace(new Label("link","<b>"+number+"</b>").setEscapeModelStrings(false).setRenderBodyOnly(true));
		} else {
			addOrReplace(new NumberedPageLink<E>("link", number));
		}
		super.onBeforeRender();
	}
	
	public boolean isCurrentPage() {
		IPageableComponent<E> component = findPageableComponent();
		if(component != null)
			return (component.currentPageNumber()+1)==number;
		return false;
	}
	
	@SuppressWarnings("unchecked")
	protected final IPageableComponent<E> findPageableComponent() {
		return (IPageableComponent<E>)findParent(IPageableComponent.class);
	}

}
