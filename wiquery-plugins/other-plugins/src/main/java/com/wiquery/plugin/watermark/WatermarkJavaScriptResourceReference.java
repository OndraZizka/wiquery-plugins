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
package com.wiquery.plugin.watermark;

import org.apache.wicket.markup.html.resources.JavascriptResourceReference;


/**
 * 
 * @author Ernesto Reinaldo Barreiro 
 */
public class WatermarkJavaScriptResourceReference extends
		JavascriptResourceReference {

	private static final long serialVersionUID = 1;

	/** Singleton instance */
	private static WatermarkJavaScriptResourceReference instance;

	/**
	 * Builds a new instance of {@link WatermarkJavaScriptResourceReference}.
	 */
	private WatermarkJavaScriptResourceReference() {
		super(WatermarkJavaScriptResourceReference.class, "jquery.watermark.min.js");
	}
	
	/**
	 * @return the instance
	 */
	public static WatermarkJavaScriptResourceReference get() {
		if (instance == null) {
			instance = new WatermarkJavaScriptResourceReference();
		}
		return instance;
	}
}
