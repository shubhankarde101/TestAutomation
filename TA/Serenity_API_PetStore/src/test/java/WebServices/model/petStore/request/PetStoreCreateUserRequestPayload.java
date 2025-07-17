package WebServices.model.petStore.request;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class PetStoreCreateUserRequestPayload {

    @SerializedName("id")
    private int id;

    @SerializedName("username")
    private String username;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("phone")
    private String phone;

    @SerializedName("userStatus")
    private int userStatus;


}