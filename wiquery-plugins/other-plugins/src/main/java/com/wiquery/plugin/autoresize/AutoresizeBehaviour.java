package com.wiquery.plugin.autoresize;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.resources.JavascriptResourceReference;
import org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;

/**
 * <p>
 * This class implements the plugin: 
 * </p>
 * <p>
 * 	http://code.google.com/p/jquery-watermark/
 * </p>
 * 
 * @author Steve Mactaggart
 * @author Ernesto Reinaldo Barreiro 
 */
public class AutoresizeBehaviour extends WiQueryAbstractBehavior {

   private static final long serialVersionUID = 1L;
	
   private boolean animate = false;
    private int heightLimit = 100;
    private int extraSpace = 0;

    private Component component;
    
    public AutoresizeBehaviour() {
    }

    public AutoresizeBehaviour(int heightLimit) {
        this.heightLimit = heightLimit;
    }

    public AutoresizeBehaviour(boolean animate, int heightLimit, int extraSpace) {
        this.animate = animate;
        this.heightLimit = heightLimit;
        this.extraSpace = extraSpace;
    }

    @Override
    public void contribute(WiQueryResourceManager wiQueryResourceManager) {
        super.contribute(wiQueryResourceManager);
        wiQueryResourceManager.addJavaScriptResource(new JavascriptResourceReference(AutoresizeBehaviour.class, "autoresize.jquery.min.js"));
    }
    
    @Override
    public JsStatement statement() {
        StringBuilder sb = new StringBuilder(".autoResize({");
            sb.append("limit: ").append(getHeightLimit()).append(",");
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

    public AutoresizeBehaviour setHeightLimit(int limit) {
        this.heightLimit = limit;
        return this;
    }

    public int getHeightLimit() {
        return heightLimit;
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
