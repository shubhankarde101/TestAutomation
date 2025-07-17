package WebServices.model.petStore.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class PetStoreCreateUserResponsePayload {

	@SerializedName("code")
	private int code;

	@SerializedName("type")
	private String type;

	@SerializedName("message")
	private String message;

}