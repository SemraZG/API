package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get03 extends JsonPlaceHolderBaseUrl {
    /*
       Given
           https://jsonplaceholder.typicode.com/todos/23
       When
           User send GET Request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response format should be “application/json”
       And
           “title” is “et itaque necessitatibus maxime molestiae qui quas velit”,
       And
           “completed” is false
       And
           “userId” is 2
    */
    @Test
    public void get03(){
        //Set the url
        spec.pathParams("first","todos","second", 23);
        //Set the expected data

        //Send the request and get the response
        Response response=given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do assertion
        //1.wayin this way if there is a error, remain codes dont execute, java stop the execution.-->Hard Assertion
        /*response.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("userId",equalTo(2)).
                body("completed",equalTo(false));*/

        //2.way-->in this way all codes are going on execute up to finish.-->Soft Asserrtion--Soft assertion is good Smoke Test.
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("userId",equalTo(2),
                        "title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed", equalTo(false));


    }

    /*
    Note 1) When you execute assertion, java stops execution after the first failure.
            It means assertions after the failure were not executed.
            But the assertion before the failure were passed, because before the assertions before the failure were executed.

    Note 2) If you type your code as execution will stop after the failure, it is called "Hard Assertion".
    Note 3) If you type your code as execution will not stop after the failure, it is called "Soft Assertion".
    Note 4) If you use multiple "body()" method, it will work like "Hard Assertion".
            If you use one single "body()" method with multiple assertions, it will work like "Soft Assertion".
     */








}










