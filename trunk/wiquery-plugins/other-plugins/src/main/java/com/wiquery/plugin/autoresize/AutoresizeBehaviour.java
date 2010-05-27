package com.wiquery.plugin.autoresize;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.TextArea;
import org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;

/**
 * <p>
 * This class implements the plugin: 
 * </p>
 * <p>
 * 	http://james.padolsey.com/javascript/jquery-plugin-autoresize/
 * </p>
 * 
 * @author Steve Mactaggart
 * @author Ernesto Reinaldo Barreiro 
 */
public class AutoresizeBehaviour extends WiQueryAbstractBehavior {

	private static final long serialVersionUID = 1L;
	
    private Component component;
    
    private Options options = new Options();
    
    /**
     * Constructor.
     */
    public AutoresizeBehaviour() {
    }

    /**
     * Constructor.
     * 
     * @param limit Limit the height of the text area.
     */
    public AutoresizeBehaviour(int limit) {
        setLimit(limit);
    }

    /**
     * Constructor.
     * 
     * @param animate Should resizing be animated.
     * @param limit Limit the height of the text area.
     * @param extraSpace The extra space added to teh text area.
     */
    public AutoresizeBehaviour(boolean animate, int limit, int extraSpace) {
    	setAnimate(animate);
        setLimit(limit);
        setExtraSpace(extraSpace);
    }

    @Override
    public void contribute(WiQueryResourceManager wiQueryResourceManager) {
        super.contribute(wiQueryResourceManager);
        wiQueryResourceManager.addJavaScriptResource(AutoresizeJavaScriptResourceReference.get());
    }
    
    @Override
    public JsStatement statement() {
    	return new JsQuery(getComponent()).$().chain("autoResize", options.getJavaScriptOptions());
    }

    /**
     * Bind this handler to the given component.
     * 
     * @param hostComponent
     *            the component to bind to
     */
    @Override
    public void bind(final Component hostComponent) {
        super.bind(hostComponent);
        
        if (hostComponent == null) {
            throw new IllegalArgumentException("Argument hostComponent must be not null");
        }

        if (!(hostComponent instanceof TextArea<?>)) {
            throw new IllegalArgumentException("Argument hostComponent must be of type TextArea.");
        }

        if (component != null) {
            throw new IllegalStateException("this kind of handler cannot be attached to " + 
                    "multiple components; it is already attached to component " + component
                    + ", but component " + hostComponent + " wants to be attached too");
        }

        component = hostComponent;
        component.setOutputMarkupId(true);
    }

    /**
     *  Once the textarea reaches this height it will stop 
     *  expanding. By default it's set to 1000.
     * @param limit The height limit.
     * @return this behavior.
     */    
    public AutoresizeBehaviour setLimit(int limit) {
        options.put("limit", limit);
        return this;
    }

    /**
     * @return The (height) limit.
     */
    public int getLimit() {
        Integer limit = options.getInt("limit");
    	return limit!= null? limit.intValue(): 1000;
    }
    
    /**
     * A pixel value to be added to the total necessary height when 
     * applied to the textarea. By default it's set to 20. The idea 
     * behind this is to reassure users that they have more space to 
     * continue writing.
     * 
     * @param extraSpace The extra space (in pixels).
     * @return This behavior.
     */
    public AutoresizeBehaviour setExtraSpace(int extraSpace) {
        this.options.put("extraSpace", extraSpace);
        return this;
    }

    /**
     * @return The extra space to be added to the text area (in pixels).
     */
    public int getExtraSpace() {
    	Integer extraSpace = options.getInt("extraSpace");
     	return extraSpace!= null? extraSpace.intValue(): 20;
    }

    /**
     * If set to false no animation will take place, the height will immediately 
     * change when necessary. By default it's set to true.
     * 
     * @param animate The animate parameter
     * @return
     */
    public AutoresizeBehaviour setAnimate(boolean animate) {
        options.put("animate",animate);
        return this;
    }

    /**
     * @return The animate property.
     */
    public boolean isAnimate() {
        Boolean animate = options.getBoolean("animate");
    	return animate!= null?animate.booleanValue():true;
    }
    
    /**
     * Millisecond duration of animation, by default it's set to 150.
     * 
     * @param animateDuration The duration (in miliseconds)
     * @return
     */
    public AutoresizeBehaviour setAnimateDuration(int animateDuration) {
        options.put("animateDuration", animateDuration);
        return this;
    }

    /**
     * @return The (height) limit.
     */
    public int getAnimateDuration() {
        Integer animateDuration = options.getInt("animateDuration");
    	return animateDuration!= null? animateDuration.intValue(): 150;
    }
    
}
