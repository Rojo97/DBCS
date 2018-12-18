angular.module("practicaApp", [])
        .constant("baseUrl", "http://localhost:8080/Rest/webresources/Rest")
        .controller("appCtrl", function ($scope, $http, baseUrl) { // Inyectamos recursos
            $scope.estado = "login";

            $scope.login = function () { //devuelve el estado a el login
                $scope.estado = "login";
            }

            $scope.loginAbonado = function (id) { //Obtienene los vinos y referencias del abonado y cambia el estado de la pagina
                $http({
                    method: "GET",
                    url: baseUrl + "/abonado/"+id+"/preferencias"
                }).then(function (response) {
                    console.log("exito: " + response.status);
                    $scope.respuesta = response.data;
                }, function (response) {
                    console.log("error: " + response.status);
                    $scope.respuesta = response.statusText;
                });
                $http({
                    method: "GET",
                    url: baseUrl + "/abonado/"+id+"/preferencias/vinos"
                }).then(function (response) {
                    console.log("exito: " + response.status);
                    $scope.vinos = response.data;
                }, function (response) {
                    console.log("error: " + response.status);
                    $scope.vinos = response.statusText;
                });
                $scope.estado = "abonado";
            }

            $scope.loginEmpleado = function () { //Obtiene pedidos pendientes y cambia estado
                $http({
                    method: "GET",
                    url: baseUrl + "/empleado/pedidosPendientes"
                }).then(function (response) {
                    console.log("exito: " + response.status);
                    $scope.respuesta = response.data;
                }, function (response) {
                    console.log("error: " + response.status);
                    $scope.respuesta = response.statusText;
                });

                $scope.estado = "empleado";
            }

            $scope.editaPedido = function (estado, id) { //Edita el estado de un pedido y refresca la vista
                $http({
                    method: "PUT",
                    url: baseUrl + "/empleado/pedidos/" + id,
                    data: {
                        "estado": estado
                    }
                }).then(function (response) {
                    console.log("exito: " + response.status);
                    alert("Estado de pedido " + id + " a " + estado);
                    $scope.loginEmpleado();
                }, function (response) {
                    console.log("error: " + response.status);
                });
            }

            $scope.deletePedido = function (id) { //Elimina un pedido
                $http({
                    method: "DELETE",
                    url: baseUrl + "/empleado/pedidos/" + id
                }).then(function (response) {
                    console.log("exito: " + response.status);
                    alert("Pedido " + id + " borrado ");
                    $scope.loginEmpleado();
                }, function (response) {
                    console.log("error: " + response.status);
                    alert("Pedido borrardo, estado: " + response.status);
                });

            }
            
            $scope.wikipedia = function (nombre, item) { //Hace una consulta a la api de wikipedia y asigna al item un enlace de la misma si lo encientra
                $http({
                    method: "GET",
                    url: "https://en.wikipedia.org/w/api.php?action=opensearch&search="+nombre+"&limit=4&format=json"
                }).success(function (data) {
                    console.log("exito: " + data);
                    item.wiki = data[3][0];
                }, function (response) {
                    console.log("error: " + response.status);
                });
            }
            
            $scope.precioReferenciaVino = function (id, item) { //Obtinene el precio del vino y se lo asigna al item
                $http({
                    method: "GET",
                    url: baseUrl + "/vino/"+id+"/referencia"
                }).success(function (data) {
                    console.log("exito: " + data);
                    item.precio = data.precio;
                    item.referencia = data.codigo;
                }, function (response) {
                    console.log("error: " + response.status);
                    item.precio = "NOT_FOUND";
                });
            }
            
            $scope.comprar = function(id ,item){ //Crea un pedido del vino seleccionado
                $http({
                    method: "POST",
                    url: baseUrl + "/abonado/" + id + "/addPedido",
                    data: {
                        "referencia": item.referencia
                    }
                }).then(function (response) {
                    console.log("exito: " + response.status);
                    alert("Pedido realizado");
                }, function (response) {
                    console.log("error: " + response.status);
                });
            }
            
            
        });
