package esendex.sdk.integration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EsendexTestProperties {

    public enum Key {
        USERNAME("esendex.username"),
        PASSWORD("esendex.password"),
        ACCOUNT("esendex.account"),
        DESTINATION_NUMBER("esendex.destination_number");

        String value;
        Key(String v) {
            value = v;
        }
    }

    private static final EsendexTestProperties instance = new EsendexTestProperties();
    private static final String PROPERTY_FILE_NAME = "test.properties";

    private Properties properties;

    public EsendexTestProperties() {
        properties = new Properties();
        try {
            InputStream is = getClass().getResourceAsStream("/" + PROPERTY_FILE_NAME);
            if (is == null) throw new FileNotFoundException();
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Could not load '" + PROPERTY_FILE_NAME + "' is it at the root of the classpath?");
        }
        for(String key : properties.stringPropertyNames()) {
            String value = System.getProperty(key);
            if (value == null) continue;
            properties.setProperty(key, value);
        }
    }

    public static EsendexTestProperties instance() {
        return instance;
    }

    public String getProperty(Key key) {
        String prop = properties.getProperty(key.value);
        if (prop == null) throw new NullPointerException("Key: '" + key
                + "' could not be found, this key is mandatory");
        return prop;
    }

}
