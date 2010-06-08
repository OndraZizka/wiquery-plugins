package org.wiquery.plugin.jquertytools.tooltip;

import org.apache.wicket.Component;
import org.apache.wicket.ResourceReference;
import org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.IComplexOption;
import org.odlabs.wiquery.core.options.Options;
import org.wiquery.plugin.jquertytools.JQueryToolsJavaScriptResourceReference;
import org.wiquery.plugin.jquertytools.JQueryToolsUiEvent;

/**
 * 
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 */
public class TooltipBehavior extends WiQueryAbstractBehavior {
	
	private static final long serialVersionUID = 1L;
	
	public static final ResourceReference CSS =  new ResourceReference(TooltipBehavior.class, "tooltip.css");
	
	public static enum Effect {		
		toggle,
		fade,		
	}
	
	/**
	 * See http://flowplayer.org/tools/tooltip/index.html#positioning
	 * 
	 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
	 *
	 */
	public static enum Position implements IComplexOption {
		top_left,
		top_center,
		top_right,
		center_left,
		center_right,
		bottom_left,
		bottom_center,
		bottom_right;
		
		@Override
		public String toString() {
			return "'"+name().replace('_', ' ')+"'";
		}
		
		public CharSequence getJavascriptOption() {
			return toString();
		}
		
		
	}
	
	/**
	 * The offsset of the positioning. See
	 * 
	 * http://flowplayer.org/tools/tooltip/index.html#positioning
	 * 
	 * For an explanation.
	 * 
	 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
	 */
	public static final class Offset implements IComplexOption {
		
		private static final long serialVersionUID = 1L;

		private int top;
		
		private int left;
		
		public Offset(int top, int left) {
			this.top = top;
			this.left = left;
		}
		
		public CharSequence getJavascriptOption() {
			return "["+top+","+left+"]";
		}

		public int getTop() {
			return top;
		}

		public void setTop(int top) {
			this.top = top;
		}

		public int getLeft() {
			return left;
		}

		public void setLeft(int left) {
			this.left = left;
		}		
	}
	
	public static final Offset DEFAULT_OFFSET = new Offset(0,0);
	
	// Properties
	private Options options;
		
	
	/**
	 * Default constructor
	 */
	public TooltipBehavior() {
		super();
		options = new Options();
	}
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior#contribute(org.odlabs.wiquery.core.commons.WiQueryResourceManager)
	 */
	@Override
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		wiQueryResourceManager
				.addJavaScriptResource(JQueryToolsJavaScriptResourceReference.get());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior#statement()
	 */
	@Override
	public JsStatement statement() {
		return new JsQuery(getComponent()).$().chain("tooltip", 
				options.getJavaScriptOptions());
	}
	
	/**Method retrieving the options of the component
	 * @return the options
	 */
	protected Options getOptions() {
		return options;
	}
	
	
	
	/** 
	 * When tooltip content is fetched from the title attribute of the trigger element this property cancels the 
	 * default tooltip behaviour executed by the browser. This is achieved by simply removing the 
	 * title attribute from the trigger. 
	 * 
	 * You can still retrieve/modify the title value by calling 
	 * jQuery's data("title") method for the trigger. 
	 * 
	 * @param cancelDefault The cancelDefault option (default true).
	 * @return instance of the current behavior
	 */
	public TooltipBehavior setCancelDefault(boolean cancelDefault) {
		this.options.put("cancelDefault", cancelDefault);
		return this;
	}
	
	/**
	 * @return The cancelDefault option
	 */
	public boolean getCancelDefault() {
		Boolean cancelDefault = this.options.getBoolean("cancelDefault");
		return cancelDefault == null ? true : cancelDefault;
	}
	
	
	/** Set's effect.
	 * @param effect The effect
	 * @return instance of the current behavior
	 */
	public TooltipBehavior setEffect(Effect effect) {
		this.options.putLiteral("effect", effect.name());
		return this;
	}
	
	/**
	 * @return The effect option
	 */
	public Effect getEffect() {
		String effect = this.options.getLiteral("effect");
		return effect == null ? Effect.toggle : Effect.valueOf(effect);
	}
	
	/** Set's delay (default of 30).
	 * @param delay The delay to set
	 * @return instance of the current behavior
	 */
	public TooltipBehavior setDelay(int delay) {
		if(delay >= 0)
			this.options.put("delay", delay);
		return this;
	}
	
	/**
	 * @return The delay
	 */
	public int getDelay() {
		Integer delay = this.options.getInt("delay");
		return delay!= null?delay:30;
	}
	
		

	/** 
	 * Since 1.2.0 the HTML layout for the generated tooltip. The HTML can 
	 * be as complex a layout as you wish. You can, for example, add a nested 
	 * span element as a placeholder for an arrow. 
	 * 
	 * @param layout The layout.
	 * @return instance of the current behavior
	 */
	public TooltipBehavior setLayout(String layout) {
		this.options.putLiteral("layout", layout);
		return this;
	}
	
	/**
	 * @return The layout
	 */
	public String getLayout() {
		String layout = this.options.getLiteral("layout");
		return layout!= null? layout: "'<div/>'";
	}
	
	
	/** 
	 * Specifies the delay (in milliseconds) before the tooltip is shown. 
	 * By default there is no delay. 
	 * 
	 * @param predelay The delay to set
	 * @return instance of the current behavior
	 */
	public TooltipBehavior setPredelay(int predelay) {
		if(predelay >= 0)
			this.options.put("predelay", predelay);
		return this;
	}
	
	/**
	 * @return The predelay
	 */
	public int getPredelay() {
		Integer delay = this.options.getInt("predelay");
		return delay!= null?delay:0;
	}
	
	/** 
	 * The transparency of the tooltip. For example, 0 means invisible, 1 means no 
	 * transparency (fully visible) and 0.4 means that 40% of the tooltip is shown. 
	 * If your tooltip uses a CSS background image, you can set the transparency of 
	 * the image if it has been saved in the PNG24 graphics format. Remember that 
	 * Internet Explorer 6 does not natively support PNG transparency.
	 * 
	 * @param opacity The opacity to set (default 1)
	 * @return instance of the current behavior
	 */
	public TooltipBehavior setOpacity(float opacity) {
		if(opacity >= 0 && opacity <= 1)
			this.options.put("opacity", opacity);
		return this;
	}
	
	/**
	 * @return The opacity
	 */
	public float getOpacity() {
		Float opacity = this.options.getFloat("opacity");
		return opacity!= null?opacity:0;
	}
	
	
	/** Set's the position.
	 * @param effect The position
	 * @return instance of the current behavior
	 */
	public TooltipBehavior setPosition(Position position) {
		this.options.put("position", position);
		return this;
	}
	
	/**
	 * @return The position option
	 */
	public Position getPosition() {
		Position position = (Position)this.options.getComplexOption("position");
		return position == null ? Position.top_center : position;
	}
	
	
	/** Set's the position.
	 * @param effect The position
	 * @return instance of the current behavior
	 */
	public TooltipBehavior setOffset(Offset offset) {
		this.options.put("offset", offset);
		return this;
	}
	
	/**
	 * @return The offset option
	 */
	public Offset getOffset() {
		Offset offset = (Offset)this.options.getComplexOption("offset");
		return offset != null ? offset : DEFAULT_OFFSET;
	}
	
	/** 
	 * Since 1.1.1. by default the tooltip position is now determined relative 
	 * to the document (by using the offset() method of jQuery). By enabling this 
	 * property the tooltip position is determined relative to the parent element  
	 * 
	 * @param relative The relative option (default false).
	 * @return instance of the current behavior
	 */
	public TooltipBehavior setRelative(boolean relative) {
		this.options.put("relative", relative);
		return this;
	}
	
	/**
	 * @return The relative option
	 */
	public boolean getRelative() {
		Boolean relative = this.options.getBoolean("relative");
		return relative == null ? true : relative;
	}
	
	/** 
	 * A jQuery selector for a single tooltip element. 
	 * For example #mytip. This option is only valid if you want to manually 
	 * define a single tooltip for multiple trigger elements.   
	 * 
	 * @param tip The tip.
	 * @return instance of the current behavior
	 */
	public TooltipBehavior setTip(String tip) {
		this.options.putLiteral("tip", tip);
		return this;
	}
	
	/**
	 * Use a component as tip.
	 * 
	 * @param tip The component to use as tip.
	 * @return
	 */
	public TooltipBehavior setTip(Component tip) {
		this.options.putLiteral("tip", "#"+tip.getMarkupId());
		return this;
	}
	
	
	/**
	 * @return The tip option
	 */
	public String getTip() {
		String tip = this.options.getLiteral("tip");
		return tip;
	}
	
	
	/** 
	 * A jQuery selector for a single tooltip element. 
	 * For example #mytip. This option is only valid if you want to manually 
	 * define a single tooltip for multiple trigger elements.   
	 * 
	 * @param tipClass The tipClass option.
	 * @return instance of the current behavior
	 */
	public TooltipBehavior setTipClass(String tipClass) {
		this.options.putLiteral("tipClass", tipClass);
		return this;
	}
	
	/**
	 * @return The relative option
	 */
	public String getTipClass() {
		String tipClass = this.options.getLiteral("tipClass");
		return tipClass!= null?tipClass: "'tooltip'";
	}
	
	
	/**
	 *  Triggered after the tooltip is shown.
	 * 
	 * @param onShow The onShow event handler.
	 * @return instance of the current behavior
	 */
	public TooltipBehavior setOnShowEvent(JQueryToolsUiEvent onShow) {
		this.options.put("onShow", onShow);
		return this;
	}
	
	/**
	 * Triggered  before the tooltip is hidden.
	 * 
	 * @param onBeforeHide The on onBeforeHide event handler.
	 * @return instance of the current behavior
	 */
	public TooltipBehavior setOnBeforeHideEvent(JQueryToolsUiEvent onBeforeHide) {
		this.options.put("onBeforeHide", onBeforeHide);
		return this;
	}
	
	/**
	 * Triggered when the tooltip is hidden.
	 * 
	 * @param onHide  The on onHide event handler.
	 * @return instance of the current behavior
	 */
	public TooltipBehavior setOnHideEvent(JQueryToolsUiEvent onHide) {
		this.options.put("onHide", onHide);
		return this;
	}
	
	/**
	 * Triggered before the tooltip is shown. second argument is the tooltip 
	 * position to be used. This is an object with values 
	 * {top: integer, left: integer}
	 * 
	 * @param onBeforeShow The on onHide event handler.
	 * @return instance of the current behavior
	 */
	public TooltipBehavior setOnBeforeShow(JQueryToolsOnBeforeShowUiEvent onBeforeShow) {
		this.options.put("onBeforeShow", onBeforeShow);
		return this;
	}
	
}
