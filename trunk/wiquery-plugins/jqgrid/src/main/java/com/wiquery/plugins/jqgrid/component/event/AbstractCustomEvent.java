/**
 * 
 */
package com.wiquery.plugins.jqgrid.component.event;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.wiquery.plugins.jqgrid.component.Grid;
import com.wiquery.plugins.jqgrid.component.IGridAware;

/**
 * @author Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class AbstractCustomEvent<B extends Serializable> implements IAjaxGridEvent<B>, IGridEvent<B>, IGridAware<B>, Serializable {

	private static final long serialVersionUID = 1L;

	private final String event;
	private Grid<B> grid;

	public AbstractCustomEvent() {
	    this.event = "onClickButton";
	}

	public String getGridEvent() {
        return event;
    }

    /* (non-Javadoc)
     * @see com.wijqgrid.component.IGridEvent#statement(java.lang.String)
     */
    public String statement(String callBackURL) {
        StringBuilder sb = new StringBuilder();
        //function(id){\n
        sb.append(getFunctionSignature());
        sb.append("var url = '"); 
        sb.append(callBackURL);
//      sb.append("&gridEvent="+getGridEvent());
        sb.append("&eventType="+getClass().getName());
        Map<String, String> params = new HashMap<String, String>(); 
        encodeAdditionalParams(params);
        Iterator<String> keys = params.keySet().iterator();
        sb.append("';\n");
        while(keys.hasNext()) {
            sb.append("url += ");
            String key = keys.next();
            String value = params.get(key);
            sb.append("'&" + key + "=' + "+value + ";\n");
        }
        sb.append(";"); 
        sb.append("wicketAjaxGet(url)");
        return sb.toString();
    }
    
    protected abstract String getFunctionSignature();
    
    
    protected void encodeAdditionalParams(Map<String, String> params) {
        
    }

    public Grid<B> getGrid() {
		return grid;
	}

	public void setGrid(Grid<B> grid) {
		this.grid = grid;
	}
}
