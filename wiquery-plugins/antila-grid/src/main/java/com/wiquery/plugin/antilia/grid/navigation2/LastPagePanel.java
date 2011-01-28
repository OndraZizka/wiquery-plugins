package com.wiquery.plugin.antilia.grid.navigation2;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;

import com.wiquery.plugin.antilia.grid.IPageableComponent;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public class LastPagePanel<E extends Serializable> extends Panel {
	
	private static final long serialVersionUID = 1L;

	public LastPagePanel(String id) {
		super(id);		
		setRenderBodyOnly(true);
	}
	
	@Override
	protected void onBeforeRender() {
		if(isLastPage()) {
			addOrReplace(new Label("link",new AbstractReadOnlyModel<String>(){
				
				private static final long serialVersionUID = 1L;

				@Override
				public String getObject() {
					IPageableComponent< E> component = LastPagePanel.this.findPageableComponent();
					if(component  == null)
						return "<b>0</b>";
					if(!component.containsData())
						return "<b>1</b>";
					return "<b>"+Integer.toString(component.getNumberOfPages())+"</b>";
				}
			}).setEscapeModelStrings(false).setRenderBodyOnly(true));
		} else {
			addOrReplace(new LastPageLink<E>("link"));
		}
		super.onBeforeRender();
	}
	
	public boolean isLastPage() {
		IPageableComponent<E> component = findPageableComponent();
		if(component != null)
			return !component.hasNextPage();
		return true;
	}
	
	@SuppressWarnings("unchecked")
	protected final IPageableComponent<E> findPageableComponent() {
		return (IPageableComponent<E>)findParent(IPageableComponent.class);
	}

}
