package com.nttdata.glue;

import com.nttdata.steps.PetStoreDef;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class PetStoreStepDef {
    @Steps
    PetStoreDef mascota;
    @Given("que el servicio del API Store de PetStore está activo y accesible")
    public void queElServicioDelAPIStoreDePetStoreEstaActivoYAccesible() {
        mascota.validarservicio();
    }

    @When("realizo una solicitud POST para registrar un pedido con id {string} y el id de la mascota {string}")
    public void realizoUnaSolicitudPOSTParaRegistrarUnPedidoConIdYElIdDeLaMascota(String id, String petID) {
        mascota.CrearOrden(id,petID);
    }

    @Then("el sistema debe devolver un status code {int}")
    public void elSistemaDebeDevolverUnStatusCode(int requestCode) {
        mascota.ValidarRespuesta(requestCode);
    }

    @And("el estado del pedido registrado debe ser {string}")
    public void elEstadoDelPedidoRegistradoDebeSer(String tipo) {
        mascota.VerificarOrder(tipo);
    }

    @When("realizo una solicitud GET para obtener los detalles del pedido con id {string}")
    public void realizoUnaSolicitudGETParaObtenerLosDetallesDelPedidoConId(String id) {
        mascota.ConsultaGET(id);
    }

    @And("el estado del pedido en la respuesta debe ser {string}")
    public void elEstadoDelPedidoEnLaRespuestaDebeSer(String status) {
        mascota.ValidarEstado(status);
    }

}
