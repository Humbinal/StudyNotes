package com.humbinal.internationalization.resource;

import java.util.*;
import com.humbinal.internationalization.*;
public class Test {

	public static void main(String[] args) {		
		Locale currentLocale = Locale.getDefault();
		ResourceBundle rb = ResourceBundle.getBundle("com/humbinal/internationalization/resource/resource",currentLocale);  
		String rbString= rb.getString("name");
		System.out.println(rbString);
	}

}
