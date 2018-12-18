angular.module("practicaApp", [])
        .constant("baseUrl", "http://localhost:8080/Rest/webresources/Rest")
        .controller("appCtrl", function ($scope, $http, baseUrl) { // Inyectamos recursos
            $scope.estado = "login";

            $scope.login = function () {
                $scope.estado = "login";
            }

            $scope.loginAbonado = function (id) {
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

            $scope.loginEmpleado = function () {
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

            $scope.editaPedido = function (estado, id) {
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

            $scope.deletePedido = function (id) {
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
            
            $scope.wikipedia = function (nombre, item) {
                $http({
                    method: "GET",
                    url: "https://en.wikipedia.org/w/api.php?action=opensearch&search="+nombre+"&limit=4&format=json"
                }).success(function (data) {
                    console.log("exito: " + data);
                    item.wiki = data[3][0];
                    alert(data);
                }, function (response) {
                    console.log("error: " + response.status);
                });
            }
            
            
        });
