package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01 {

    /*
    1) Postman is used for Manuel API Testing.
    2) We use Rest-Assured Library for Automation API Testing.
    3) To type automation script follow the steps:
        a) Understand the requirement
        b) Type the test cases
             To type cases we used 'Gherkin Language'
             The keyword are;
                  x) Given: It is used for pre-conditions
                  y) When: It is used for actions
                  z) Then: It is used for outputs
                  t) And: It is usd for multiple given, when and then

        c) Start to type Automation Script
            i) Set the URL
            ii) Set the expected data(Post, Put, Patch requests)
            iii) Send the Request and get the Response
            iiii) Do Assertion
     */


    /*
   Given
       https://restful-booker.herokuapp.com/booking/101
   When
       User sends a GET Request to the url
   Then
       HTTP Status Code should be 200
   And
       Content Type should be application/json
   And
       Status Line should be HTTP/1.1 200 OK
*/
   @Test
    public void get01(){
       //Set the URL
       String url= "https://restful-booker.herokuapp.com/booking/101";
       //Set the expected data
       //Send the request and get the response
       Response response=given().when().get(url);
        response.prettyPrint();

        //Do Assertion
       response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");

       //How to print status code on console
       System.out.println("Status code: "+response.getStatusCode());//Status code: 200
       //How to print Content type on console
       System.out.println("Content type: "+ response.contentType());//Content type: application/json; charset=utf-8
       //How to print Status line on console
       System.out.println("Status line: "+response.statusLine());//Status line: HTTP/1.1 200 OK
       //How to print "Header" on console
       System.out.println(response.getHeader("Server"));//Cowboy
       //How to print "Headers" on console
       System.out.println(response.getHeaders());
       //How to print time on console
       System.out.println(response.getTime());

   }
}
