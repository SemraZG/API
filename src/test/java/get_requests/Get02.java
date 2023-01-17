package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.*;

public class Get02 {
    /*
   Given
       https://restful-booker.herokuapp.com/booking/1
   When
       User send a GET Request to the url
   Then
       HTTP Status code should be 404
   And
       Status Line should be HTTP/1.1 404 Not Found
   And
       Response body contains "Not Found"
   And
       Response body does not contain "TechProEd"
   And
       Server is "Cowboy"
*/
    @Test
    public void get02(){
//Set the url
String url="https://restful-booker.herokuapp.com/booking/1";
//Set the expected data
        //Set the request and get the response
        Response response= given().when().get(url);
        response.prettyPrint();
//Do Assertion
        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");

        assertTrue(response.asString().contains("Not Found"));
        //assertTrue(x) method passes if the x is true

        //  Response body does not contain "TechProEd"
        assertFalse(response.asString().contains("Tech ProEd"));
        //assertFalse(x) method passes if the x is false

        //Server is "Cowboy"
        assertEquals("Cowboy", response.getHeader("Server"));
        //assertEquels(x,y) method passes if the x and y are equal.
    }

}
