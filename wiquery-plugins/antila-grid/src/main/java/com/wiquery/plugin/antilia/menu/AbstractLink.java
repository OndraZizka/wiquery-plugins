/**
 * This software is provided as IS by Antilia-Soft SL.
 * Copyright 2006-2007.
 */
package com.wiquery.plugin.antilia.menu;

import org.apache.commons.lang.StringUtils;
import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ResourceReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;

/**
 *
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public abstract class AbstractLink extends Panel implements IMenuItem {

	private static final long serialVersionUID = 1L;

	private WebMarkupContainer link;

	private String linkClass = "smallbutton";

	public static final String LABEL_ID = "label";

	private Image image;

	Label text;
	/**
	 * Constructor.
	 * @param id
	 */
	public AbstractLink(String id) {
		super(id);
		setOutputMarkupId(true);

		link = newLink("link");
		link.add(new AttributeModifier("class", true, new Model<String>() {

			private static final long serialVersionUID = 1L;

			@Override
			public String getObject() {
				return AbstractLink.this.getLinkClass();
			}
		}));
		IModel<String> title = getTitleModel();
		if(title != null) {
			link.add(new AttributeModifier("title", title));
		}
		add(link);
		image = newImage("image");
		link.add(image);

	}


	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();

		if(text == null) {
			text = newLabel(LABEL_ID);
			link.add(text);
		}
	}

	/**
	 * Creates a new title model.
	 *
	 * @return
	 */
	protected IModel<String> getTitleModel() {
		String key = getTitleKey();
		if(key != null)
			return new ResourceModel(key, getLabel());
		return null;
	}


	protected String getTitleKey() {
		return null;
	}

	protected WebMarkupContainer newLink(String id) {
		return new AjaxFallbackLink<Void>(id) {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				AbstractLink.this.onClick(target);
			}

			@Override
			protected IAjaxCallDecorator getAjaxCallDecorator() {
				return null;//AbstractLink.this.getAjaxCallDecorator();
			}

		};
	}


	protected Label newLabel(String id) {
		IModel<String> model = getLabelModel();
		if(model != null)
			return new Label(id, model);
		else {
			String text = getLabel();
			Label label = new Label(id, text);
			if(StringUtils.isEmpty(text))
				label.setVisible(false);
			return label;
		}
	}

	/**
	 * Override this method to get a different resource model.
	 * @return
	 */
	protected IModel<String> getLabelModel() {
		String key = getLabelKey();
		if(key != null)
			return new ResourceModel(key, getLabel());
		return null;
	}

	protected String getLabelKey() {
		return null;
	}

	protected abstract void onClick(AjaxRequestTarget target);

	/**
	 * Override this method  to provide your own image.
	 *
	 * @param id
	 * @return
	 */
	protected Image newImage(String id) {
		Image image = new Image(id) {
			private static final long serialVersionUID = 1L;

			@Override
			protected ResourceReference getImageResourceReference() {
				if(isEnabled()) {
					return getImage();
				}
				ResourceReference reference = getDisabledImage();
				if(reference != null) {
					return reference;
				}
				return getImage();
			}

			@Override
			public boolean isVisible() {
				return (getImage() != null);
			}
		};
		return image;
	}

	protected abstract String getLabel();

	protected abstract ResourceReference getImage();

	/**
	 * Returns the image of the disable button. By default is null which means
	 * image will be used.
	 *
	 * @return
	 */
	protected  ResourceReference getDisabledImage() {
		return null;
	}

	/**
	 * @return the link
	 */
	public WebMarkupContainer getLink() {
		return link;
	}

	/**
	 * @param link the link to set
	 */
	public void setLink(WebMarkupContainer link) {
		this.link = link;
	}

	/**
	 * @return the linkClass
	 */
	public String getLinkClass() {
		return linkClass;
	}

	/**
	 * @param linkClass the linkClass to set
	 */
	public void setLinkClass(String linkClass) {
		this.linkClass = linkClass;
	}
}
