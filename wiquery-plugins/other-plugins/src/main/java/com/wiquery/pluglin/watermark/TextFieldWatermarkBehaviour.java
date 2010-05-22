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

/**
 * <p>
 * This class implements the plugin: 
 * </p>
 * 
 * http://code.google.com/p/jquery-watermark/
 * 
 * 
 * @author Steve Mactaggart
 * @author Ernesto Reinaldo Barreiro 
 */
public class TextFieldWatermarkBehaviour extends WiQueryAbstractBehavior {
	private static final long serialVersionUID = 1L;
	private final String text;

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
		return new JsQuery(getComponent()).$().append(".watermark('"+text+"')");
	}

}
