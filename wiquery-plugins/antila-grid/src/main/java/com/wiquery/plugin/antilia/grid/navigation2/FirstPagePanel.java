package com.wiquery.plugin.antilia.grid.navigation2;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import com.wiquery.plugin.antilia.grid.IPageableComponent;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public class FirstPagePanel<E extends Serializable> extends Panel {
	
	private static final long serialVersionUID = 1L;

	public FirstPagePanel(String id) {
		super(id);		
		setRenderBodyOnly(true);
	}
	
	@Override
	protected void onBeforeRender() {
		if(isFirstPage()) {
			addOrReplace(new Label("link","<b>1</b>").setEscapeModelStrings(false).setRenderBodyOnly(true));
		} else {
			addOrReplace(new FirstPageLink<E>("link"));
		}
		super.onBeforeRender();
	}
	
	public boolean isFirstPage() {
		IPageableComponent<E> component = findPageableComponent();
		if(component != null)
			return !component.hasPreviousPage();
		return true;
	}
	
	@SuppressWarnings("unchecked")
	protected final IPageableComponent<E> findPageableComponent() {
		return (IPageableComponent<E>)findParent(IPageableComponent.class);
	}

}
