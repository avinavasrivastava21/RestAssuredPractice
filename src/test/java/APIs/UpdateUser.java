package APIs;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import post_pojo.CreateUserPojo;
import utilities.ScenarioContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static utilities.EndPoints.*;


public class UpdateUser {


Faker faker = new Faker();
CreateUserPojo requestBody;
RequestSpecification request= RestAssured.given();
ResponseSpecification responseSpec= RestAssured.expect();
Response response;
ScenarioContext scenarioContext = new ScenarioContext();
Properties prop=new Properties();
File file= new File(System.getProperty("user.dir")+"\\src\\main\\resources\\Configuration.properties");
String id=null;
//Object id=null;
    public void getCreatedUserId() {
        try {
            FileInputStream fis=new FileInputStream(file);
            prop.load(fis);
            id=prop.getProperty("id");
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //id=scenarioContext.getScenarioContext("id");
     //   id="7803076";

    }

    public CreateUserPojo getUpdatedUserBody() {
        return CreateUserPojo.builder().name(faker.name().fullName()).email(faker.internet().emailAddress()).build();
    }
    public void getUserDetailsForUpdate() {
        requestBody=getUpdatedUserBody();
        System.out.println("RequestBody :"+requestBody.toString());
        request.body(requestBody);
        request.header("Content-Type", "application/json");
        request.header("Accept", "application/json");
        request.baseUri(uri);
        request.basePath(putUriUpdateUser);
        request.auth().oauth2(Token);
        response=request.put("{id}",id);
        scenarioContext.digestResponseAsMap(response);
        scenarioContext.printAllContextValues();
    }

    public void validatedUserDetails() {
//        responseSpec.statusCode(200);
//        responseSpec.contentType(ContentType.JSON);
//        response.then().spec(responseSpec);


        Assert.assertEquals(scenarioContext.getScenarioContext("name"), requestBody.getName());
        Assert.assertEquals(scenarioContext.getScenarioContext("email"), requestBody.getEmail());
    }
}
