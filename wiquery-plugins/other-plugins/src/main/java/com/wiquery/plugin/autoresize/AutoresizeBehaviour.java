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
	
   private boolean animate = false;
    private int extraSpace = 0;

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
     * @param limit
     */
    public AutoresizeBehaviour(int limit) {
        setLimit(limit);
    }

    /**
     * Constructor.
     * 
     * @param animate
     * @param limit
     * @param extraSpace
     */
    public AutoresizeBehaviour(boolean animate, int limit, int extraSpace) {
        this.animate = animate;
        setLimit(limit);
        this.extraSpace = extraSpace;
    }

    @Override
    public void contribute(WiQueryResourceManager wiQueryResourceManager) {
        super.contribute(wiQueryResourceManager);
        wiQueryResourceManager.addJavaScriptResource(AutoresizeJavaScriptResourceReference.get());
    }
    
    @Override
    public JsStatement statement() {
        StringBuilder sb = new StringBuilder(".autoResize({");
            sb.append("limit: ").append(getLimit()).append(",");
            sb.append("animate: ").append(isAnimate()).append(",");
            sb.append("extraSpace: ").append(getExtraSpace());
            sb.append("})");

        JsQuery query = new JsQuery(getComponent());
        query.$().append(sb.toString());
        return query.getStatement();
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

    public AutoresizeBehaviour setExtraSpace(int extraSpace) {
        this.extraSpace = extraSpace;
        return this;
    }

    public int getExtraSpace() {
        return extraSpace;
    }

    public AutoresizeBehaviour setAnimate(boolean animate) {
        this.animate = animate;
        return this;
    }

    public boolean isAnimate() {
        return animate;
    }
}
