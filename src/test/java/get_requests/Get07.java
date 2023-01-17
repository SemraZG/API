package get_requests;


import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Get07 extends JsonPlaceHolderBaseUrl {

    /*
        Given
	   	    https://jsonplaceholder.typicode.com/todos
		When
			 I send GET Request to the URL
		Then
			 1)Status code is 200
			 2)Print all ids greater than 190 on the console
			   Assert that there are 10 ids greater than 190
			 3)Print all userIds whose ids are less than 5 on the console
			   Assert that the number of userIds whose ids are less than 5 is 4
			 4)Print all titles whose ids are less than 5
			   Assert that "delectus aut autem" is one of the titles whose id is less than 5
     */

@Test
    public void get07(){
    //Set the url
    spec.pathParam("first", "todos");
    Response response= given().spec(spec).when().get("/{first}");
    response.prettyPrint();
    //Do Assertion
    //1)Status code is 200
    assertEquals(200, response.statusCode());
    JsonPath jsonpath=response.jsonPath();

    //2)Print all ids greater than 190 on the console
    //Assert that there are 10 ids greater than 190
    List<Integer> ids= jsonpath.getList("id");
    System.out.println("ids= "+ids);
    List<Integer> idsGreaterThan190= new ArrayList<>();
    for (int w : ids){
        if (w>190){
           idsGreaterThan190.add(w);
        }
    }
    System.out.println("idsGreaterThan190 = "+idsGreaterThan190);
    assertEquals(10, idsGreaterThan190.size());

    //2.way
   List<Integer> idsGreaterThan190Groovy=  jsonpath.getList("findAll{it.id>190}.id"); //--> Groovy Language--> 'it' is like 't->' in Lambda
    System.out.println("idsGreaterThan190Groovy = "+ idsGreaterThan190Groovy);
    assertEquals(10, idsGreaterThan190Groovy.size());

    //3)Print all userIds whose ids are less than 5 on the console
    //Assert that the number of userIds whose ids are less than 5 is 4
   List<Integer> userıds= jsonpath.getList("findAll{it.id<5}.userId");
    System.out.println("userıds = "+ userıds);
    assertEquals(4, userıds.size());

    //4)Print all titles whose ids are less than 5
    // Assert that "delectus aut autem" is one of the titles whose id is less than 5
    List<String> title=jsonpath.getList("findAll{it.id<5}.title");
    System.out.println("title = "+ title);
    //1.way
    assertTrue("delectus aut autem does not exist", title.contains("delectus aut autem"));
    //2.way
    assertTrue("delectus aut autem does not exist", title.stream().anyMatch(t->t.equals("delectus aut autem")));



}
}