/**
 * 
 */
package com.wiquery.plugins.demo;

import java.util.Arrays;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.PropertyModel;

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
	
	private Speed speed = Speed.SLOW;
	
	private Boolean dropShadows = Boolean.TRUE;
	
	private Menu menu;
	
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
		
		menu = new Menu("menu");
		menu.setSpeed(getSpeed())
		.setDelay(300).setDropShadows(dropShadows);
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
		
		Form<Void> form = new Form<Void>("form");
		add(form);
		
		DropDownChoice<Speed> cspeed  = new  DropDownChoice<Speed>("speed", Arrays.asList(Speed.values()));
    	cspeed.setModel(new PropertyModel<Speed>(this,"speed"));
    	cspeed.setNullValid(false);
    	cspeed.add(new OnChangeAjaxBehavior() {
    		
			private static final long serialVersionUID = 1L;

			@Override
    		protected void onUpdate(AjaxRequestTarget target) {
				menu.setSpeed(speed);
				target.addComponent(SuperfishMenuPanel.this.context);
    		}
    	});
    	form.add(cspeed);
    	
    	BooleanDropDownChoice dropShadows = new BooleanDropDownChoice("dropShadows", new PropertyModel<Boolean>(this, "dropShadows"));
    	dropShadows.setNullValid(false);
    	dropShadows.add(new OnChangeAjaxBehavior() {
    		
			private static final long serialVersionUID = 1L;

			@Override
    		protected void onUpdate(AjaxRequestTarget target) {
				menu.setDropShadows(SuperfishMenuPanel.this.dropShadows);
				target.addComponent(SuperfishMenuPanel.this.context);
    		}
    	});
    	form.add(dropShadows);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Speed getSpeed() {
		return speed;
	}

	public void setSpeed(Speed speed) {
		this.speed = speed;
	}

	public Boolean getDropShadows() {
		return dropShadows;
	}

	public void setDropShadows(Boolean dropShadows) {
		this.dropShadows = dropShadows;
	}
}
