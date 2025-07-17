package WebServices.steps.game;

import WebServices.api.petStore.PetStoreEndPoints;
import WebServices.model.petStore.request.PetStoreCreateUserRequestPayload;

import java.io.IOException;
import java.net.MalformedURLException;

import static net.serenitybdd.rest.SerenityRest.given;
import static net.serenitybdd.rest.SerenityRest.then;


public class PetStoreSteps {
    public void createUser(PetStoreCreateUserRequestPayload requestBody) {
        try {
            given()
                    .contentType("application/json")
                    .header("accept", "application/json")
                    .body(requestBody)
                    .post(PetStoreEndPoints.createUserUrl());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void getUser(String username){
        try {
            given()
                    .contentType("application/json")
                    .header("accept", "application/json")
                    .get(PetStoreEndPoints.getUserUrl(username));
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void deleteUser(String username){
        try {
            given()
                    .contentType("application/json")
                    .header("accept", "application/json")
                    .delete(PetStoreEndPoints.deleteUserUrl(username));
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

