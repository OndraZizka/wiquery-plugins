/**
 * 
 */
package com.wiquery.plugin.antilia.grid.navigation2;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import com.wiquery.plugin.antilia.grid.IPageableComponent;
import com.wiquery.plugin.antilia.menu.IMenuItem;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public class NumberOfRecordsPanel<E extends Serializable> extends Panel implements IMenuItem {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 */
	public NumberOfRecordsPanel(String id) {
		super(id);		
		
	}
	
	@Override
	protected void onBeforeRender() {
		addOrReplace(new Label("numberOfRecordsMsg",new StringResourceModel("numberOfRecordsMsg", new Model<NavigationBean>(getNumberOfRecords())))
		.setRenderBodyOnly(true));
		super.onBeforeRender();
	}
	
	private NavigationBean getNumberOfRecords() {
		NavigationBean navigationBean = new NavigationBean();
		IPageableComponent<E> pc = findPageableComponent();
		if(pc != null) {
			navigationBean.setNumberOfRecords(pc.getDataProvider().size());
			navigationBean.setCurrentPage(pc.currentPageNumber());
			navigationBean.setRecordsPerPage(pc.getPageSize());
		}
		return navigationBean;
	}

	@SuppressWarnings("unchecked")
	protected final IPageableComponent<E> findPageableComponent() {
		return (IPageableComponent<E>)findParent(IPageableComponent.class);
	}
}
