package com.PetStore;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class APITest extends PetStoreBase {

    @Test
    public void getPets() {

        Response response = given().accept(ContentType.JSON)
                .when().queryParam("status", "pending")
                .get("/pet/findByStatus");

        String firstPetName = response.jsonPath().getString("[0].name");

        System.out.println("firstPetName = " + firstPetName);

        System.out.println("response.statusCode() = " + response.statusCode());
        response.prettyPrint();
    }

    @Test
    public void verifyName() {


        ValidatableResponse petID = given().accept(ContentType.JSON)
                .when().pathParam("petID", 9223372036854646672L)
                .get("/pet/{petID}")
                .then()
                .statusCode(200);

        Assert.assertEquals(configurationReader.getProperty("firstPendingPetName"),
                petID.extract().jsonPath().getString("name"));


    }

    @Test
    public void postPet(){

        given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"pinky\"\n" +
                        "  },\n" +
                        "  \"name\": \"tinky\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"pending\"\n" +
                        "}")
                .when()
                .post("/pet")
                .then()
                .statusCode(200);
    }

    @Test
    public void getAllPets(){

        ValidatableResponse response = given().accept(ContentType.JSON)
                .queryParam("status", "sold")
                .when()
                .get("/pet/findByStatus")
                .then().statusCode(200);

        List<Object> soldPets = response.extract().jsonPath().getList("");

        System.out.println("soldPets = " + soldPets);

        Object[] objects = soldPets.toArray();
        System.out.println("objects[0] = " + objects[0]);


    }

    @Test
    public void getAllPetsCategoryName(){

        ValidatableResponse response = given().accept(ContentType.JSON)
                .queryParam("status", "sold")
                .when()
                .get("/pet/findByStatus")
                .then().statusCode(200);

        List<Object> soldPetsCatName = response.extract().jsonPath().getList("category.name");

        System.out.println("soldPetsCatName = " + soldPetsCatName);

        Object[] objects = soldPetsCatName.toArray();
        System.out.println("objects[0] = " + objects[0]);


    }
}
