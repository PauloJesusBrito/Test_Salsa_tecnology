package com.meuteste;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReqResApiTest {

    private static final String BASE_URL = "https://reqres.in/api";

    @Test
    public void testListUsers() {
        Response response = RestAssured.get(BASE_URL + "/users?page=1");

        // Verificando se o status da resposta é 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status code is not 200");

        // Verificando se a resposta contém uma lista de usuários
        int totalPages = response.jsonPath().getInt("total_pages");
        Assert.assertTrue(totalPages > 0, "Total pages is zero or negative");

        int userId = response.jsonPath().getInt("data[0].id");
        String firstName = response.jsonPath().getString("data[0].first_name");
        String lastName = response.jsonPath().getString("data[0].last_name");

        Assert.assertTrue(userId > 0, "User ID is not valid");
        Assert.assertNotNull(firstName, "First name is null");
        Assert.assertNotNull(lastName, "Last name is null");

    }
}