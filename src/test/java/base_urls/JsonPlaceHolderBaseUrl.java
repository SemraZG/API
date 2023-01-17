package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.runner.Request;

public class JsonPlaceHolderBaseUrl {
   protected RequestSpecification spec;

    @Before//If you use @before annotation at the top of the method, it will work just before every test method.
    public void setUp(){

        spec= new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
    }
}
