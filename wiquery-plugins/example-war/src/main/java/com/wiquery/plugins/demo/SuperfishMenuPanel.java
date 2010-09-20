/**
 * 
 */
package com.wiquery.plugins.demo;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;

import com.wiquery.plugin.superfish.AjaxMenuItem;
import com.wiquery.plugin.superfish.BookMarkablePageMenuItem;
import com.wiquery.plugin.superfish.Menu;
import com.wiquery.plugin.superfish.SubMenu;
import com.wiquery.plugin.superfish.Menu.Speed;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class SuperfishMenuPanel extends Panel {

	private static final long serialVersionUID = 1L;
	
	private WebMarkupContainer context; 
	
	private String message = "";
	
	/**
	 * @param id
	 */
	public SuperfishMenuPanel(String id) {
		super(id);
		
		context = new WebMarkupContainer("context");
		context.setOutputMarkupId(true);
		add(context);
		
		context.add(new Label("message", new AbstractReadOnlyModel<String>(){
			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return getMessage();
			}
		}));
		
		Menu menu = new Menu("menu");
		menu.setSpeed(Speed.FAST)
		.setDelay(300);
		context.add(menu);
		
		menu.addItem(new BookMarkablePageMenuItem<HomePage>("Home") {
			
			private static final long serialVersionUID = 1L;

			@Override
			protected Class<HomePage> getPageClass() {
				return HomePage.class;
			}
		});

		SubMenu subMenu = new SubMenu("Sub Menu 1");
		menu.addItem(subMenu);
		
		subMenu.addItem(new AjaxMenuItem("Very very very long link!") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setMessage(getDefaultModel().getObject().toString());
				target.addComponent(SuperfishMenuPanel.this.context);
			}
		});
		subMenu.addItem(new AjaxMenuItem("Link 1.1") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setMessage(getDefaultModel().getObject().toString());
				target.addComponent(SuperfishMenuPanel.this.context);
			}
		});
		subMenu.addItem(new AjaxMenuItem("Link 1.2") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setMessage(getDefaultModel().getObject().toString());
				target.addComponent(SuperfishMenuPanel.this.context);
			}
		});
		
		subMenu = new SubMenu("Sub Menu 2");
		menu.addItem(subMenu);
		
		
		SubMenu susSubMenu = new SubMenu("Sub Menu 2.1");
		subMenu.addItem(susSubMenu);
		
		susSubMenu.addItem(new AjaxMenuItem("link 2.1.1") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setMessage(getDefaultModel().getObject().toString());
				target.addComponent(SuperfishMenuPanel.this.context);
			}
		});
		susSubMenu.addItem(new AjaxMenuItem("link 2.1.2") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setMessage(getDefaultModel().getObject().toString());
				target.addComponent(SuperfishMenuPanel.this.context);
			}
		});
		
		susSubMenu.addItem(new AjaxMenuItem("link 2.1.3") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setMessage(getDefaultModel().getObject().toString());
				target.addComponent(SuperfishMenuPanel.this.context);
			}
		});
		
		susSubMenu.addItem(new AjaxMenuItem("link 2.1.4") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setMessage(getDefaultModel().getObject().toString());
				target.addComponent(SuperfishMenuPanel.this.context);
			}
		});
		
		SubMenu subSubSubMenu  = new SubMenu("Sub menu 2.1.5");
		susSubMenu.addItem(subSubSubMenu);
		
		subSubSubMenu.addItem(new AjaxMenuItem("link 2.1.5.1") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setMessage(getDefaultModel().getObject().toString());
				target.addComponent(SuperfishMenuPanel.this.context);
			}
		});
		
		subSubSubMenu.addItem(new AjaxMenuItem("link 2.1.5.2") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setMessage(getDefaultModel().getObject().toString());
				target.addComponent(SuperfishMenuPanel.this.context);
			}
		});
		
		subMenu.addItem(new AjaxMenuItem("Link 2.2") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setMessage(getDefaultModel().getObject().toString());
				target.addComponent(SuperfishMenuPanel.this.context);
			}
		});
		subMenu.addItem(new AjaxMenuItem("Link 2.3") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setMessage(getDefaultModel().getObject().toString());
				target.addComponent(SuperfishMenuPanel.this.context);
			}
		});
		
		subMenu.addItem(new AjaxMenuItem("Link 2.4") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setMessage(getDefaultModel().getObject().toString());
				target.addComponent(SuperfishMenuPanel.this.context);
			}
		});

		menu.addItem(new AjaxMenuItem("Link 3") {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setMessage(getDefaultModel().getObject().toString());
				target.addComponent(SuperfishMenuPanel.this.context);
			}
		});
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
