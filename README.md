# API-REST-TPA-DdS
La API que implementamos esta desarrollada de dos formas:
* La primera se encuentra en esta branch
En esta branch, tenemos un metodo POST que debe recibir en el body de la request, una lista de entidades y en cada entidad una lista de incidentes.
* La segunda se encuentra en la branch swagger
En esta segunda branch, tenemos dos metodos GET que permiten buscar a un reporte por ID y por Semana de emision. La misma busca conectarse a la BDD de nuestro TP Anual para persistir y obtener dichos datos.