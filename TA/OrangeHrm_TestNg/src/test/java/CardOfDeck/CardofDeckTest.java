package CardOfDeck;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import listeners.AnnotationTransformers;
import listeners.ListenersClass;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


@Listeners({ListenersClass.class, AnnotationTransformers.class})
public class CardofDeckTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://deckofcardsapi.com";
    }

    @Test
    public void DeckOfCardAPI() {
        // Confirm the site is up
        Response response = RestAssured.get("https://deckofcardsapi.com/");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200, "Site is not up");

        //Get a new deck
        response = RestAssured.get("/api/deck/new/");
        response.then()
                .statusCode(200)
                .contentType("application/json")
                .body("success", equalTo(true));

        String deckId = response.path("deck_id");
        System.out.println(deckId);

        //Shuffle the deck
        response = RestAssured.get("/api/deck/"+deckId+"/draw/?count=2");
        response.then()
                .statusCode(200)
                .contentType("application/json")
                .body("success", equalTo(true));

        // Deal three cards to each player
        response = given()
                .queryParam("count", 3)
                .get("/api/deck/" + deckId + "/draw/");
        response.then()
                .statusCode(200)
                .contentType("application/json")
                .body("success", equalTo(true))
                .body("cards.size()", equalTo(3));

        // Check whether either has blackjack
        String player1Hand = response.path("cards[0].value") + " " + response.path("cards[1].value") + " " + response.path("cards[2].value");
        String player2Hand = response.path("cards[3].value") + " " + response.path("cards[4].value") + " " + response.path("cards[5].value");

        boolean player1HasBlackjack = checkBlackjack(player1Hand);
        boolean player2HasBlackjack = checkBlackjack(player2Hand);

        // If either has, write out which one does
        if (player1HasBlackjack && player2HasBlackjack) {
            System.out.println("Both players have blackjack!");
        } else if (player1HasBlackjack) {
            System.out.println("Player 1 has blackjack!");
        } else if (player2HasBlackjack) {
            System.out.println("Player 2 has blackjack!");
        } else {
            System.out.println("Neither player has blackjack.");
        }
    }
    private boolean checkBlackjack(String hand) {
        return (hand.contains("ACE") && (hand.contains("KING") || hand.contains("QUEEN") || hand.contains("JACK")));
    }


}
