package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHandler {

    private Properties properties;

    public PropertiesHandler () {
        FileInputStream file;
        try{
            file = new FileInputStream("src/test/resources/config.properties");
            properties = new Properties();
            properties.load(file);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public String getProperty (String key) {
        return properties.getProperty(key);
    }
}
