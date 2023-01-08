package com.PetStore;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PetShopAPIWithPOJO extends PetStoreBase{

    @Test
    public void getAllPets(){

        ValidatableResponse response = given().accept(ContentType.JSON)
                .queryParam("status", "sold")
                .when()
                .get("/pet/findByStatus")
                .then().statusCode(200);

        List<PetPOJO> petPOJOS = response.extract().jsonPath().getList("");

        System.out.println("petPOJOS = " + petPOJOS);


        System.out.println((petPOJOS.toArray())[1]);

        System.out.println("petPOJOS.get(1).getName() = " + petPOJOS.get(1).getName());

    }
}
