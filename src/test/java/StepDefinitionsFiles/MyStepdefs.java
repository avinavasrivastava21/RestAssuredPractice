package StepDefinitionsFiles;


import APIs.CreateUser;
import APIs.GetUser;
import APIs.UpdateUser;
import io.cucumber.java.Before;
import io.cucumber.java8.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java8.En;



public class MyStepdefs implements En {

    Scenario scenario;
    CreateUser createUser;
    UpdateUser updateUser;
    GetUser getUser;
    //APIs


    public MyStepdefs() {

    Before(scenario ->  {
        this.scenario = scenario;
        System.out.println("Scenario-> " + scenario.getName());
        createUser=new CreateUser();
        updateUser=new UpdateUser();
        getUser=new GetUser();
    });

        Given("^User is authorized$", () -> {
            createUser.authorizeUser();
        });

        Given("^Generate User details$", () -> {
            System.out.println("Given done");
            createUser.generateNewUserDetails();
        });
        Then("^create User with the details$", () -> {
           createUser.newUser();
           System.out.println("Then new user done");
        });
        And("^Validate that user is created$", () -> {
            System.out.println("And done");
            createUser.ValidateNewUser();
        });
        Given("^Select created User$", () -> {
            updateUser.getCreatedUserId();
        });
        Then("^Update User details$", () -> {
            updateUser.getUserDetailsForUpdate();
        });
        And("^Validate that user is Updated$", () -> {
            updateUser.validatedUserDetails();
        });
        Given("^Get user details$", () -> {
            getUser.getExistingUserDetails();
        });
    }

}
