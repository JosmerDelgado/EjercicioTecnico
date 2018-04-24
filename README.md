# EjercicioTecnico
**Herramientas Utilizadas:**
- TestNG
- SeleniumHQ

**Modelo Utilizado**

- Se utilizo el modelo Page Object Model(POM).

**Antes de Ejecutar**

Se deben editar los parametros del archivo testng.xml con los parametros deseados
- from (lugar de partida del vuelo)
- to (lugar de llegada del vuelo)
- month-from (mes de llegada)
- day-from (dia de llegada)
- month-to (mes de regreso)
- day-to (dia de regreso) 

**Como Ejecutar**
- Se debe utilizar testng y ejecutar el archivo testng.xml



# Descripcion Funcional
Ejercicio Nro1:

**Dado** un posible comprador de vuelos

**Cuando** 
- Ingresa al sitio https://www.despegar.com.ar
- Selecciona la opcion vuelos 
- Selecciona un lugar de partida y lugar de llegada 
- Selecciona dia de partida y dia de regreso 
- luego presiona en el boton de buscar

**Entonces** puede ver en una nueva pagina, las opciones de vuelos listada correspondientes a los datos ingresados  

Ejercicio Nro2:

**Dado** un posible comprador de vuelos

**Cuando** 
- Ingresa al sitio https://www.despegar.com.ar
- Selecciona la opcion vuelos 
- Selecciona un lugar de partida y lugar de llegada 
- Selecciona dia de partida y dia de regreso 
- luego presiona en el boton de buscar
- Espera a que se listen las opciones de vuelos
- Busca la opcion de vuelo mas costosa y presiona comprar.

**Entonces** puede ver en una nueva pagina, puede ver los detalles del vuelo y las secciones:
- Pasajeros
- Forma de Pago
- Datos para la emision de la factura
- Informacion de contacto

Ejercicio Nro3:

**Dado** un posible huesped de hotel

**Cuando** 
- Ingresa al sitio https://www.despegar.com.ar
- Selecciona la opcion Alojamiento 
- Selecciona como destino "Montevideo" 
- Selecciona dia de partida en 10 dias, con una duracion de 3 dias 
- Selecciona una habitacion para 2 adultos y un ninio de 12 anios
- Presiona en el boton buscar
- Espera a que cargue la paguina
- Selecciona el filtro de 5 estrellas
- Espera a que se refresquen los resultados
- Selecciona ver los detalles de la habitacion mas economica

**Entonce** puede ver en una nueva pestania, el sector de habitaciones disponibles