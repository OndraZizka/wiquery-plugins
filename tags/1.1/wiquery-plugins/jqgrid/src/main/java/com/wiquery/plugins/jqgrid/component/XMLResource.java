package com.wiquery.plugins.jqgrid.component;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.html.DynamicWebResource;
import org.apache.wicket.protocol.http.WebRequest;
import org.apache.wicket.protocol.http.WebRequestCycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author  Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public abstract class XMLResource extends DynamicWebResource  {

	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(XMLResource.class);
	
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
					logger.error("Error generating GRID data: ", e.getMessage());					
					throw new WicketRuntimeException(e);
				}
			}
		};
	}
	
	protected abstract String getXml();
	
	
}