package com.wiquery.plugins.jqgrid.experiment;

import org.apache.wicket.IResourceListener;
import org.apache.wicket.markup.html.WebMarkupContainer;

/**
 * This class can be used to create a markup container capable of 
 * servicing resources. We use it to stream back grids data.
 * 
 * @author  Ernesto Reinaldo Barreiro (reiern70@gmail.com)
 *
 */
public class DocumentResourceListener extends WebMarkupContainer implements IResourceListener 
{
	private static final long serialVersionUID = 1L;
	
	
	private IResourceListener resourceListener;

	/**
	 * Constructor receiving an IResourceListener..
	 * 
	 * @param id
	 * @param resourceListener
	 */
	public DocumentResourceListener(final String id, IResourceListener resourceListener)
	{
		super(id);
		this.resourceListener = resourceListener;
	}

	/**
	 * Gets the url to use for this link.
	 * 
	 * @return The URL that this link links to
	 */
	public CharSequence getURL()
	{
		return urlFor(IResourceListener.INTERFACE);
	}

	

	@Override
	protected boolean getStatelessHint()
	{	
		return false;
	}
	
	public void onResourceRequested() {
		this.resourceListener.onResourceRequested();
	}
}