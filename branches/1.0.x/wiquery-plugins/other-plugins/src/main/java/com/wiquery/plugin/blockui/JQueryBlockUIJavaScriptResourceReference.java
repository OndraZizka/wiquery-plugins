package com.wiquery.plugin.blockui;

import org.apache.wicket.markup.html.resources.JavascriptResourceReference;

/**
 * 
 * @author Steve Mactaggart
 *
 */
public class JQueryBlockUIJavaScriptResourceReference extends JavascriptResourceReference {

    private static final long serialVersionUID = 1L;

    /** Singleton instance */
    static JQueryBlockUIJavaScriptResourceReference instance;

    private JQueryBlockUIJavaScriptResourceReference() {
        super(JQueryBlockUIJavaScriptResourceReference.class, "jquery.blockUI.js");
    }

    /**
     * @return the instance
     */
    public static JQueryBlockUIJavaScriptResourceReference get() {
        if (instance == null) {
            instance = new JQueryBlockUIJavaScriptResourceReference();
        }
        return instance;

    }
}