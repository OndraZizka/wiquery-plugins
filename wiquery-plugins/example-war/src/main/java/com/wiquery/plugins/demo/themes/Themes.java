package com.wiquery.plugins.demo.themes;

import java.util.ArrayList;
import java.util.List;


public class Themes {

	public static final List<UITheme> themes = new ArrayList<UITheme>();
	
	static {
		themes.add(RedmondTheme.getInstance());
		themes.add(LeFrogTheme.getInstance());
		//themes.add(MintChocTheme.getInstance());
		//themes.add(UILightnessTheme.getInstance());
		themes.add(CupertinoTheme.getInstance());
	}
}
