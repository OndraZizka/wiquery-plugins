/**
 * 
 */
package com.wiquery.plugins.demo;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxIndicatorAware;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.panel.Panel;

import com.wiquery.plugins.antilia.link.JQIcon;
import com.wiquery.plugins.antilia.link.JqAjaxLink;
import com.wiquery.plugins.demo.code.CodeTabPanel;
import com.wiquery.plugins.demo.code.SourceInfo;

/**
 * @author Ernesto Reinaldo Barreiro
 *
 */
public abstract class IndicatorPanel extends Panel implements IAjaxIndicatorAware {

	private static final long serialVersionUID = 1L;

	private ModalWindow modalWindow;
	
	/**
	 * @param id
	 */
	public IndicatorPanel(String id, List<SourceInfo> code) {
		super(id);
		JqAjaxLink link = new JqAjaxLink("link", JQIcon.ui_icon_help, "See source code") {
			private static final long serialVersionUID = 1L;

			
			@Override
			public void onClick(AjaxRequestTarget target) {
				IndicatorPanel.this.modalWindow.show(target);
			}
		};
		add(link);
		modalWindow = new ModalWindow("modalWindow");
		modalWindow.setInitialHeight(600);
		modalWindow.setInitialWidth(1150);
		modalWindow.setResizable(false);
		modalWindow.setOutputMarkupPlaceholderTag(true);
		modalWindow.setTitle("Source Code");
		modalWindow.addOrReplace(new CodeTabPanel(modalWindow.getContentId(), code));
		add(modalWindow);
		
		add(newContents("contents").setRenderBodyOnly(true));
	}

	protected abstract Component newContents(String id);
	
	/* (non-Javadoc)
	 * @see org.apache.wicket.ajax.IAjaxIndicatorAware#getAjaxIndicatorMarkupId()
	 */
	public String getAjaxIndicatorMarkupId() {
		return "veil";
	}

}
