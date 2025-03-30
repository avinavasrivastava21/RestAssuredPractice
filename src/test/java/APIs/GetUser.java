package APIs;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.io.*;
import java.util.Properties;

import static org.hamcrest.core.IsEqual.equalTo;
import static utilities.EndPoints.*;

public class GetUser {

    RequestSpecification request= RestAssured.given();
    ResponseSpecification responseSpec = RestAssured.expect();
    Response response;
    File file= new File(System.getProperty("user.dir")+"\\src\\main\\resources\\Configuration.properties");
    Properties prop= new Properties();
    String id;
    public void getExistingUserDetails(){
        try {
            FileInputStream fis=new FileInputStream(file);
            prop.load(fis);
            id=prop.getProperty("id");
            fis.close();
        } catch (Exception e) {
            throw new RuntimeException(e);

        }

        request.auth().oauth2(Token);
        request.baseUri(uri);
        request.basePath(getUserDetails);
        request.header("Content-Type", "application/json");
        request.header("Accept", "application/json");
        response=request.get("{id}",id);

        responseSpec.statusCode(200);
        responseSpec.body("id",equalTo(id));
        response.then().spec(responseSpec);
        System.out.println("Response :"+ response.getBody().asString());
    }

}
