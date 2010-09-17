package com.wiquery.plugin.blockui;

import org.apache.wicket.Component;
import org.odlabs.wiquery.core.behavior.WiQueryAbstractBehavior;
import org.odlabs.wiquery.core.commons.WiQueryResourceManager;
import org.odlabs.wiquery.core.javascript.JsQuery;
import org.odlabs.wiquery.core.javascript.JsStatement;
import org.odlabs.wiquery.core.options.Options;

/**
 * This Behaviour allows you to attach the BlockUI plugin to a component.<br/>
 * <br/>
 * The behaviour is configured to add to an element that can be clicked on, like a button or link.  It will
 * bind to the "click" event of jquery and then show the Block UI.<br/>
 * <br/>
 * At the moment there is no way to hide the message, it is expected that the form will submit and the new page will load
 * to remove the submitting message.
 * <br/>
 * Code Sample could work as below:<br/>
 * <code> saveButton.add(new BlockUIBehaviour().setMessage("&lt;div class=\"submit-block\"&gt;Saving your request&lt;/div&gt;"));</code>
 * 
 * @author steve.mactaggart
 */
public class BlockUIBehaviour extends WiQueryAbstractBehavior {

    private static final long serialVersionUID = 1L;
    
    private Options options = new Options();

    public BlockUIBehaviour() {
    }

    @Override
    public void contribute(WiQueryResourceManager wiQueryResourceManager) {
        super.contribute(wiQueryResourceManager);
        wiQueryResourceManager.addJavaScriptResource(JQueryBlockUIJavaScriptResourceReference.get());
    }
    
    @Override
    public void bind(Component component) {
        super.bind(component);
    }
    
    @Override
    public JsStatement statement() {
        String blockUIString = "function() { $.blockUI(" + options.getJavaScriptOptions() +"); }";
        return new JsQuery(getComponent()).$().chain("click", blockUIString);
    }
    
    public BlockUIBehaviour setMessage(String message) {
        options.putLiteral("message", message);
        return this;
    }

    public String getMessage() {
        String message = options.getLiteral("message");
        return message;
    }

}
