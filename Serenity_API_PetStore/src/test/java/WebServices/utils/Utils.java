package WebServices.utils;


import io.cucumber.java.ParameterType;
import org.apache.commons.io.IOExceptionList;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;


public class Utils {

    @ParameterType("(?:[^,]*)(?:,\\s?[^,]*)*")
    public static List<String> exampleList(String arg){
        return Arrays.asList(arg.split(",\\s?"));
    }

    public static String getPropertyFrom(String key) {
        String string = "";
        try{
            Properties credentialsProperties = new Properties();
            credentialsProperties.load(new FileInputStream("user.properties"));
            string = credentialsProperties.getProperty(key);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return  string;
    }




}
