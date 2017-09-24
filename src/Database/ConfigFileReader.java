package Database;

import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
	private static ConfigFileReader configFileReader;
	private static Properties configFileProperties;

	private ConfigFileReader() {
		try {
			configFileProperties = new Properties();
			configFileProperties.load(getClass().getClassLoader().getResourceAsStream("../sql.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ConfigFileReader getInstance() {
		
		if(configFileReader == null){
			synchronized (ConfigFileReader.class) {
				configFileReader = new ConfigFileReader();
			}
		}
		return configFileReader;
	}
	
	public String getValue(String key){
		return configFileProperties.getProperty(key);
	}
}
