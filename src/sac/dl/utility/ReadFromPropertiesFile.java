package sac.dl.utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadFromPropertiesFile {

	protected static Properties prop;
	
	public void loadConfigFile() throws IOException
	{
		InputStream inputStream;
		prop = new Properties();
		String propFileName = "config.properties";
		inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
		
		if(inputStream!=null)
		{
			prop.load(inputStream);
		}
		
		else
		{
			throw new FileNotFoundException("Property file not found.");
		}
	}
}
