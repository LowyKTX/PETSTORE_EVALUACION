package com.nttdata.steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.hamcrest.CoreMatchers;

import static io.cucumber.messages.internal.com.google.common.base.Predicates.equalTo;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.CoreMatchers.equalTo;
public class PetStoreDef {
    Response response;
    private static String urlBase;
    public void validarservicio() {
        urlBase = "https://petstore.swagger.io/v2/store/order";
    }

    public void CrearOrden(String id, String petID) {
        SerenityRest
                .given()
                .contentType("application/json")
                .body("{\n" +
                        "  \"id\": \""+id+"\",\n" +
                        "  \"petId\": \""+petID+"\",\n" +
                        "  \"quantity\": 0,\n" +
                        "  \"shipDate\": \"2024-12-17T02:34:01.486Z\",\n" +
                        "  \"status\": \"placed\",\n" +
                        "  \"complete\": true\n" +
                        "}")
                .log().all()
                .post(urlBase)
                .then()
                .log().all();
    }

    public void ValidarRespuesta(int requestCode) {
        restAssuredThat(response -> response.statusCode(requestCode));
    }

    public void VerificarOrder(String tipo) {
        restAssuredThat(response -> response.body("'status'", CoreMatchers.equalTo(tipo)));
        System.out.println("status: " + SerenityRest.lastResponse().body().path("status").toString());
        System.out.println(SerenityRest.lastResponse().print());
    }

    public void ConsultaGET(String id) {
        response= RestAssured.given().baseUri(urlBase).get("/"+id).then().log().all().extract().response();
    }

    public void ValidarEstado(String status) {
        restAssuredThat(response -> response.body("'status'", CoreMatchers.equalTo(status)));
        System.out.println("status: " + SerenityRest.lastResponse().body().path("status").toString());
        System.out.println(SerenityRest.lastResponse().print());
    }
}
