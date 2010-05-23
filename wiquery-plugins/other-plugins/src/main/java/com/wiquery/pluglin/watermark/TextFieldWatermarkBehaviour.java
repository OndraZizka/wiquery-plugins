/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.wiquery.pluglin.watermark;

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
 * 	http://code.google.com/p/jquery-watermark/
 * </p>
 * 
 * @author Steve Mactaggart
 * @author Ernesto Reinaldo Barreiro 
 */
public class TextFieldWatermarkBehaviour extends WiQueryAbstractBehavior {
	private static final long serialVersionUID = 1L;
	private final String text;

	private Options options = new  Options();
	
	public TextFieldWatermarkBehaviour(String text) {
       this.text = text;
	}

	@Override
	public void contribute(WiQueryResourceManager wiQueryResourceManager) {
		super.contribute(wiQueryResourceManager);
		wiQueryResourceManager.addJavaScriptResource(WatermarkJavaScriptResourceReference.get());
	}
	
	@Override
	public JsStatement statement() {
		return new JsQuery(getComponent()).$().chain("watermark", "'"+text+"'", options.getJavaScriptOptions());
	}

	/** Set's the className.
	 * @param className The className to set
	 * @return instance of the current behavior
	 */
	public TextFieldWatermarkBehaviour setClassName(String className) {
		this.options.putLiteral("className", className);
		return this;
	}
	
	/**
	 * @return The ClassName
	 */
	public String getClassName() {
		return this.options.getLiteral("className");
	}
	
	/** Set's the useNative.
	 * @param useNative The useNative to set
	 * @return instance of the current behavior
	 */
	public TextFieldWatermarkBehaviour setUseNative(boolean useNative) {
		this.options.put("useNative", useNative);
		return this;
	}
	
	/**
	 * @return The useNative
	 */
	public boolean isUseNative() {
		Boolean  useNative = this.options.getBoolean("useNative");
		return (useNative != null)? useNative : true;
	}
}
