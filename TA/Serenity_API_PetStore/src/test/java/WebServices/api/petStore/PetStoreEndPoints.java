package WebServices.api.petStore;

import java.net.MalformedURLException;
import java.net.URL;

import static java.lang.String.format;

public class PetStoreEndPoints {

    private static final String GATEWAY_BASE_URI = "https://gateway.v8.commerce.mi9cloud.com/";

    private static final String BASE_URL = "https://petstore.swagger.io/v2";

    private static final String CREATE_USER = BASE_URL+"/user";

    private static final String GET_USER = BASE_URL+"/user/{placeholder}";

    private static final String DELETE_USER = BASE_URL+"/user/{placeholder}";

    public static URL createUserUrl() throws MalformedURLException {
        return new URL(format(CREATE_USER));
    }

    public static URL getUserUrl(String username) throws MalformedURLException {
        return new URL(format(GET_USER.replace("{placeholder}",username)));
    }

    public static URL deleteUserUrl(String username) throws MalformedURLException {
        return new URL(format(DELETE_USER.replace("{placeholder}",username)));
    }

}