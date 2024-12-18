Feature: Validar la funcionalidad del API Store en PetStore

  Background: Configuración inicial
    Given que el servicio del API Store de PetStore está activo y accesible

  @PetStore
  Scenario Outline: Registrar un pedido en la tienda mediante el método POST
    When realizo una solicitud POST para registrar un pedido con id '<id>' y el id de la mascota '<petID>'
    Then el sistema debe devolver un status code <requestCode>
    And el estado del pedido registrado debe ser '<tipo>'

    Examples:
      | id  | petID | requestCode | tipo   |
      | 201 | 801   | 200         | placed |
      | 202 | 905   | 200         | placed |
      | 203 | 707   | 200         | placed |

  @Consultar
  Scenario Outline: Obtener detalles de un pedido registrado mediante el método GET
    When realizo una solicitud GET para obtener los detalles del pedido con id '<id>'
    Then el sistema debe devolver un status code <requestCode>
    And el estado del pedido en la respuesta debe ser '<status>'


    Examples:
      | id  | requestCode | status |
      | 201 | 200         | placed |
      | 202 | 200         | placed |
      | 203 | 200         | placed |
