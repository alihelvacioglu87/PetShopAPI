package com.PetStore;

import io.restassured.RestAssured;

public abstract class PetStoreBase {

    static ConfigurationReader configurationReader;
static {
    configurationReader = new ConfigurationReader();
    RestAssured.baseURI = configurationReader.getProperty("baseURI");
}

}
