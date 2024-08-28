package com.example.demo.utils;

import java.text.SimpleDateFormat;

public class Formatter {
	
	public String StringToDate(String string) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		return sf.format(string);
	}
}
