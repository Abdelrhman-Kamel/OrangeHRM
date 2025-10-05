import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigLoader {
    Properties properties;
    public ConfigLoader(String filePath){
        properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream(new File(filePath));
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String getValue(String key){
        return properties.getProperty(key);
    }
}
