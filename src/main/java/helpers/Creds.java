package helpers;

import java.io.InputStream;
import java.util.Properties;

public class Creds {
    private static final Properties CREDS;

    static {
        CREDS = new Properties();
        try {
            InputStream in = Creds.class.getResourceAsStream("/Creds.properties");
            CREDS.load(in);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String[] getUser(String role){
        String str = CREDS.getProperty(role);
        String[] user = str.split(",", 2);
        return user;
    }
}
