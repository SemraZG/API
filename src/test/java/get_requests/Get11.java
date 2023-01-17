package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.awt.geom.RectangularShape;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.reset;
import static org.hamcrest.Matchers.*;
import static org.testng.AssertJUnit.assertTrue;

public class Get11 extends GoRestBaseUrl {
     /*
       Given
           https://gorest.co.in/public/v1/users
       When
           User send GET Request
       Then
       Status code must be 200
       And
           The value of "pagination limit" is 10
       And
           The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
       And
           The number of users should  be 10
       And
           We have at least one "active" status
       And
           "Chitraksh Talwar DDS", "Aishani Varrier", "Rohit Shukla" are among the users
       And
           The female users are less than or equal to male users
    */
    @Test
    public void get11(){
        //Set the url
        spec.pathParam("first", "users");
        //Set the expected data


        //Send the request and get the response
      Response response= given().spec(spec).when().get("/{first}");
      response.prettyPrint();

      //Do Assertion
        response.
                then().
                assertThat().
                statusCode(200).
                body("meta.pagination.limit", equalTo(10),
                        "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data.name", hasSize(10),
                        "data.status", hasItem("active"),
                        "data.name", hasItems("Chitraksh Talwar DDS", "Aishani Varrier", "Rohit Shukla"));
        // The female users are less than or equal to male users
        //I will compare the number of female and males in 2 ways

        //1.way: I will get all the genders and count the female users then compare it will males users.
      JsonPath jsonPath= response.jsonPath();
     List<String> genderList= jsonPath.getList("data.gender");
        System.out.println("genderList = "+genderList);
        int numberOfFemales=0;
        for (String w: genderList){
            if (w.equals("female")){
                numberOfFemales++;
            }
        }
        System.out.println("numberOfFemales = "+numberOfFemales);
        int numberOfMales= genderList.size()-numberOfFemales;
        assertTrue(numberOfFemales<=numberOfMales);

        //2.way: I will get all female users by using 'Groovy Language' then compare it with males
       List<String> femalelist= jsonPath.getList("data.findAll{it.gender=='female'}.gender");
        System.out.println("  femalelist = "+femalelist);

        List<String> malelist= jsonPath.getList("data.findAll{it.gender=='male'}.gender");
        System.out.println(" malelist = "+malelist);

        assertTrue(femalelist.size()<=malelist.size());
    }
}
