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

import static utilities.ScenarioContext.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static utilities.EndPoints.*;



public class CreateUser {

    Properties properties = new Properties();
    RequestSpecification request= RestAssured.given();
    ResponseSpecification responseSpec= RestAssured.expect();
    Response response;

   ScenarioContext scenarioContext = new ScenarioContext();

    Faker faker = new Faker();
    //create Object so we can store request body for later validation
    CreateUserPojo requestBody;
    File file=new File(System.getProperty("user.dir")+"\\src\\main\\resources\\Configuration.properties");

    public void authorizeUser(){
        try {
            FileInputStream configFile=new FileInputStream(file);
            properties.load(configFile);
        } catch (Exception e) {
            System.out.println("Error loading application settings: Property file ");
        }
          System.out.println("Given Background" + properties.getProperty("Token"));
          request.auth().oauth2(properties.getProperty("Token"));
    }


    public CreateUserPojo getNewUserBody(){
       return CreateUserPojo.builder()
               .name(faker.name().fullName()).email(faker.internet().emailAddress())
                .gender(faker.options().option("Male", "Female"))
                .status("Active")
                .build();
    }

    public void generateNewUserDetails() {
        //Assign CreatePojo in an object so we can pull values
        requestBody=getNewUserBody();
        request.body(requestBody);
    System.out.println(getNewUserBody().toString());

        }


    public void newUser() {
        request.baseUri(uri);
        request.header("Content-Type", "application/json");//'Content-type:application/json;charset=utf-8'
        request.header("Accept", "application/json");
        response=request.post(postUriCreateUser);
    }


    public void ValidateNewUser() {
        responseSpec.contentType(ContentType.JSON);
        responseSpec.statusCode(201);
        scenarioContext.digestResponseAsMap(response);
        response.then().spec(responseSpec);
        scenarioContext.printAllContextValues();
    //Assert username and email matches with Request Body
        Assert.assertEquals(scenarioContext.getScenarioContext("name"), requestBody.getName());
        Assert.assertEquals(scenarioContext.getScenarioContext("email"), requestBody.getEmail());

        try {
            FileOutputStream fWrite=new FileOutputStream(file);
            properties.setProperty("id",String.valueOf(scenarioContext.getScenarioContext("id")));
            properties.store(fWrite,"");
        } catch (IOException e) {

            System.out.println("issue with writing");
        }
    }
}
