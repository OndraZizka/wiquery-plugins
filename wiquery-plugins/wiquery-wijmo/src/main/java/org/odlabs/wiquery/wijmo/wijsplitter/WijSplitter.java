/*
 * Copyright (c) 2011 WiQuery team
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.odlabs.wiquery.wijmo.wijsplitter;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.odlabs.wiquery.core.commons.IWiQueryPlugin;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;
import org.odlabs.wiquery.ui.commons.WiQueryUIPlugin;
import org.odlabs.wiquery.ui.mouse.MouseJavascriptResourceReference;
import org.odlabs.wiquery.ui.resizable.ResizableJavaScriptResourceReference;
import org.odlabs.wiquery.ui.widget.WidgetJavascriptResourceReference;
import org.odlabs.wiquery.wijmo.wijutil.WijUtilJavascriptResourceReference;
import org.odlabs.wiquery.wijmo.wijutil.WijUtilStyleSheetResourceReference;

/**
 * $Id$
 * 
 * <p>
 * 	Bind the Wijmo Splitter
 * </p>
 * 
 * <p>
 * Examples of use:
 * 
 * HTML:
 * <div id="splitter">
        <div>
            panel1
        </div>
        <div>
            panel2
        </div>
    </div>

 * 
 * Java:
 * add(new WijSplitter("splitter"));
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
@WiQueryUIPlugin
public class WijSplitter extends WebMarkupContainer implements IWiQueryPlugin {
	// Constants
	/** Constant of serialization */
	private static final long serialVersionUID = 1289770835244095202L;
	
	/**
	 * <p>
	 * 	Enumeration for the orientation
	 * </p>
	 *
	 * @author Julien Roche
	 * @since 1.0
	 */
	public enum WijSplitterOrientation {
		HORIZONTAL,
		VERTICAL;

		/**
		 * {@inheritDoc}
		 * @see java.lang.Enum#toString()
		 */
		@Override
		public String toString() {
			return super.toString().toLowerCase();
		}
	}
	
	// Properties
	private Options options;

	/**
	 * @see Component#Component(String)
	 */
	public WijSplitter(String id) {
		super(id);
		this.options = new Options();
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager.addJavaScriptResource(WidgetJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(MouseJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(ResizableJavaScriptResourceReference.get());
		
		wiQueryResourceManager.addJavaScriptResource(WijUtilJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(WijUtilStyleSheetResourceReference.get());
		
		wiQueryResourceManager.addJavaScriptResource(WijSplitterJavascriptResourceReference.get());
		wiQueryResourceManager.addJavaScriptResource(WijSplitterStyleSheetResourceReference.get());
	}
	
	/**
	 * Destroys wijlist.
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement destroy() {
		return new JsQuery(this).$().chain("wijsplitter", "'destroy'");
	}
	
	/**
	 * Destroys wijlist.
	 * @param ajaxRequestTarget
	 */
	public void destroy(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.destroy().render().toString());
	}
	
	/**
	 * @return the options of the list
	 */
	protected Options getOptions() {
		return this.options;
	}

	/**
	 * Invalidates the entire surface of the control and causes the control to be redrawn.
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement invalidate() {
		return new JsQuery(this).$().chain("wijsplitter", "'invalidate'");
	}
	
	/**
	 * Invalidates the entire surface of the control and causes the control to be redrawn.
	 * @param ajaxRequestTarget
	 */
	public void invalidate(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.invalidate().render().toString());
	}

	/**
	 * Refresh layout for Splitter
	 * This will return the element back to its pre-init state.
	 * @return the associated JsStatement
	 */
	public JsStatement refresh() {
		return new JsQuery(this).$().chain("wijsplitter", "'refresh'");
	}
	
	/**
	 * Refresh layout for Splitter
	 * @param ajaxRequestTarget
	 */
	public void refresh(AjaxRequestTarget ajaxRequestTarget) {
		ajaxRequestTarget.appendJavascript(this.refresh().render().toString());
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.commons.IWiQueryPlugin#statement()
	 */
	public JsStatement statement() {
		return new JsQuery(this).$().chain("wijsplitter", getOptions().getJavaScriptOptions());
	}
	
	/**
	 * A value determines whether the expander of Splitter is allowed to be shown
	 * @param showExpander
	 * @return the current instance
	 */
	public WijSplitter setShowExpander(boolean showExpander) {
		getOptions().put("showExpander", showExpander);
		return this;
	}
	
	/**
	 * @return the showExpander option
	 */
	public boolean isShowExpander() {
		Boolean bool = getOptions().getBoolean("showExpander");
		return bool == null ? true : bool;
	}
	
	/**
	 * A value that indicates whether or not the control is full of document. 
	 * @param fullSplit
	 * @return the current instance
	 */
	public WijSplitter setFullSplit(boolean fullSplit) {
		getOptions().put("fullSplit", fullSplit);
		return this;
	}
	
	/**
	 * @return the fullSplit option
	 */
	public boolean isFullSplit() {
		Boolean bool = getOptions().getBoolean("fullSplit");
		return bool == null ? false : bool;
	}
	
	/**
	 * A value indicates the location of the splitter, in pixels, from the left or top edge of the splitter. 
	 * @param splitterDistance
	 * @return the current instance
	 */
	public WijSplitter setSplitterDistance(int splitterDistance) {
		getOptions().put("splitterDistance", splitterDistance);
		return this;
	}
	
	/**
	 * @return the splitterDistance option
	 */
	public int getSplitterDistance() {
		Integer number = getOptions().getInt("splitterDistance");
		return number == null ? 100 : number;
	}
	
	/**
	 * @return the orientation option
	 */
	public WijSplitterOrientation getOrientation() {
		String str = getOptions().getLiteral("orientation");
		return str == null ? WijSplitterOrientation.VERTICAL : WijSplitterOrientation.valueOf(str.toUpperCase());
	}
	
	/**
	 * A value indicating the horizontal or vertical orientation of the splitter panels.
	 * @param orientation
	 * @return the current instance
	 */
	public WijSplitter setOrientation(WijSplitterOrientation orientation) {
		getOptions().putLiteral("orientation", orientation.toString());
		return this;
	}
}
