package com.utils.testcase;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.Test;

import com.generic.testcase.Framework_Constant;
 
public class Property implements Framework_Constant
{

	@Test
	public String  get(String key) throws IOException
	{
		FileInputStream fis =new FileInputStream(property_Path);
		Properties p=new Properties();
		p.load(fis);
		String value = p.getProperty(key);
		return value;
	}
	@Test
	public Object store(String key,String value) throws IOException
	{
		FileOutputStream fos =new FileOutputStream(property_Path,true);
		Properties p=new Properties();
		Object store = p.setProperty(key, value);
		p.store(fos, "Stored");
		return store;
	}
}
