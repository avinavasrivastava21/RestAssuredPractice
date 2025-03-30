package utilities;

import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private  final Map<Object, Object> scenarioContext;

    public ScenarioContext() {
        scenarioContext = new HashMap<>();
    }

    public  void setScenarioContext(Object key, Object value) {
        scenarioContext.put(key, value);
    }

    public  Object getScenarioContext(Object key) {
        return scenarioContext.get(key);
    }

    public  void digestResponseAsMap(Response response) {
        // Parse JSON response into a Map
        System.out.println("Digesting Response as Map"+response.getBody().asString());
        Map<Object, Object> responseMap = response.jsonPath().getMap("");

        // Iterate over Map and put each key-value pair in the scenarioContext
        for (Map.Entry<Object, Object> entry : responseMap.entrySet()) {
            if(entry.getKey() != null){
            setScenarioContext(entry.getKey(), entry.getValue());}
        }
    }

    public  void printAllContextValues() {
        System.out.println("Scenario Context Values:");
        for (Map.Entry<Object, Object> entry : scenarioContext.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}

