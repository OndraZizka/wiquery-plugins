package org.wiquery.plugin.jquertytools;

import org.odlabs.wiquery.core.javascript.JsScope;
import org.odlabs.wiquery.core.javascript.JsScopeContext;


/**
 * This class represent a JsScope event for the JQuery UI components
 * The javascript representation will be like this:
 * <p>
 * 	function(event) { ... }
 * </p>
 * @author Ernesto Reinaldo Barreiro
 * @since 1.0
 */
public abstract class JQueryToolsUiEvent extends JsScope {
	//Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = 1L;
	
	/**Default constructor
	 */
	public JQueryToolsUiEvent() {
		super("event");
	}
	
	/**
	 * Creates a default {@link JQueryToolsUiEvent} to execute the given statement.
	 * 
	 * @param javascriptCode
	 *            the JavaScript statement to execute with the scope.
	 * @return the created {@link JQueryToolsUiEvent}.
	 */
	public static JQueryToolsUiEvent quickScope(final CharSequence javascriptCode) {
		return new JQueryToolsUiEvent() {
			private static final long serialVersionUID = 1L;

			/* (non-Javadoc)
			 * @see org.odlabs.wiquery.core.javascript.JsScope#execute(org.odlabs.wiquery.core.javascript.JsScopeContext)
			 */
			@Override
			protected void execute(JsScopeContext scopeContext) {
				scopeContext.append(javascriptCode);
			}

		};
	}
}
