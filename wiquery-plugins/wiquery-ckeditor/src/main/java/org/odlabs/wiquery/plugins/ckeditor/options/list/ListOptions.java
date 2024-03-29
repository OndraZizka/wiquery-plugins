/*
 * Copyright (c) 2009 WiQuery team
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.odlabs.wiquery.plugins.ckeditor.options.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import org.odlabs.wiquery.core.options.IComplexOption;

/**
 * $Id$
 * 
 * <p>
 * 	List of values to display like that: 'a,b,c'
 * </p>
 *
 * @author Julien Roche
 * @since 1.0
 */
public class ListOptions extends ArrayList<String> implements IComplexOption {
	// Constants
	/**	Constant of serialization */
	private static final long serialVersionUID = 1779802328333735627L;
	
	/**
	 * Method to construct a ListOptions
	 * @param a List
	 * @return the ListOptions
	 */
	public static ListOptions asList(String... a) {
		return new ListOptions(Arrays.asList(a));
	}
	
	/**
	 * Default constructor
	 */
	public ListOptions() {
		super();
	}

	/**
	 * Constructor
	 * @param c
	 */
	public ListOptions(Collection<? extends String> c) {
		super(c);
	}

	/**
	 * {@inheritDoc}
	 * @see org.odlabs.wiquery.core.options.IComplexOption#getJavascriptOption()
	 */
	public CharSequence getJavascriptOption() {
		StringBuffer javascript = new StringBuffer();
		javascript.append("'");
		
		if(!isEmpty()){
			Iterator<String> iterator = iterator();
			javascript.append(iterator.next());
			
			while(iterator.hasNext()){
				javascript.append("," + iterator.next());
			}
		}		
		
		javascript.append("'");
		return javascript;
	}
}
