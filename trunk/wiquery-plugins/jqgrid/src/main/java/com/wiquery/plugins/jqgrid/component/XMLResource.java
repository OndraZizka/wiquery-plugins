package com.wiquery.plugins.jqgrid.component;

import org.apache.wicket.markup.html.DynamicWebResource;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebRequestCycle;

/**
 * @author  Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class XMLResource extends DynamicWebResource  {

	private static final long serialVersionUID = 1L;
	
	

	
	/**
	 * 
	 */
	public XMLResource() {
	}

	/* (non-Javadoc)
	 * @see org.apache.wicket.markup.html.DynamicWebResource#getResourceState()
	 */
	@Override
	protected ResourceState getResourceState() {
		return new ResourceState() {

			private byte[] data;
			
			@Override
			public String getContentType() {
				return "text/xml";
			}
			
			@Override
			public byte[] getData() {
				try {
					if( data == null)
						data = getXml().getBytes(((WebRequest)(WebRequestCycle.get().getRequest())).getHttpServletRequest().getCharacterEncoding());
					return data;
				} catch (Exception e) {
					return null;
				}
			}
		};
	}
	
	protected abstract String getXml();
	
	
}