package com.wiquery.plugin.antilia.grid;

import java.io.Serializable;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.data.IDataProvider;

import com.wiquery.plugin.antilia.grid.navigation2.NumberOfRecordsPanel;
import com.wiquery.plugin.antilia.grid.navigation2.PaginatorPanel;
import com.wiquery.plugin.antilia.menu.IMenu;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public class ClassicNavigationTable<E extends Serializable> extends Table<E> {

	private static final long serialVersionUID = 1L;

	/**
	 * @param id
	 * @param tableModel
	 * @param dataProvider
	 */
	public ClassicNavigationTable(String id, ITableModel<E> tableModel,
			IDataProvider<E> dataProvider) {
		super(id, tableModel, dataProvider);
	}

	@Override
	protected Panel newTableHeader(String id) {
		return new DefaultTableHeader<E>(id, this) {					
			private static final long serialVersionUID = 1L;

			@Override
			protected void addNavigation(IMenu menu) {
				menu.addMenuItem(new NumberOfRecordsPanel<E>(menu.newItemId()));
				menu.addMenuItem(new PaginatorPanel<E>(menu.newItemId()));				
			}
			
			@Override
			protected void onConfigure() {
				setVisible(containsData());
				super.onConfigure();
			}
			
			public boolean containsData() {
				IPageableComponent<E> component = findPageableComponent();
				if(component != null)
					return component.containsData();
				return false;
			}
			
			@SuppressWarnings("unchecked")
			protected final IPageableComponent<E> findPageableComponent() {
				return (IPageableComponent<E>)findParent(IPageableComponent.class);
			}
		};
	}
}
