#language:es
##CREADOR: Victor Carpio
##APP: PARABANK
##MODULO:
##FUNCIONALIDAD:
##ESTADO:
##CODIGO:
##GDAP:
##SPRINT CREADO:
##FRECUENCIA:
##TAG :
##DATA:
##ENCARGADO: Victor Carpio
##FECMOD: 02/04/2024

Característica: Registro de usuario en Parabank
  Como usuario
  Desearia registrarme en Parabank
  y luego acceder de manera exitosa a mi cuenta

  Antecedentes:
    Dado que abro la pagina de Parabank

  @RegistroCliente
  Escenario: Registro y login de usuario exitoso
    Cuando   presiono el boton Iniciar Registro
    Y relleno el formulario con los datos del cliente a registrar
      | nombre | apellido    | direccion    | ciudad     | estado | zipCode | telefono  | SSN  |
      | Juan    | Lopez      | Alameda Sur  | Chorrillos | Lima   | 123     | 9771944488| 1234 |
    Y presiono el boton Registrar
    Entonces deberia ver el mensaje de exito "Your account was created successfully. You are now logged in."
    Y cierro sesion con la cuenta creada
    Y navego de regreso a la pagina de inicio de Parabank
    E inicio sesion con la nueva cuenta creada

