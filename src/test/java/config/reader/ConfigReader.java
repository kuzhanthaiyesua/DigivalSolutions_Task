package config.reader;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    Properties prop;
    public ConfigReader(){
        loadProperties();}
    public void loadProperties(){
        try {
            String path = System.getProperty("user.dir") + "//data//config.properties";
            prop = new Properties();
            InputStream file = new FileInputStream(path);
            prop.load(file);
        }
        catch(Exception e){
            System.out.println("Unable to read property file" +e);
        }
    }

    /**
     * This method is used to get property value
     * @param key
     * @return
     */
    public String getProperty(String key){
        String value = prop.getProperty(key);
        return value;
    }
}
