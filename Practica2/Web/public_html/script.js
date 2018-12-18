angular.module("practicaApp", [])
        .constant("baseUrl", "http://localhost:8080/Rest/webresources/Rest")
        .controller("appCtrl", function ($scope, $http, baseUrl) { // Inyectamos recursos
            $scope.estado = "login";

            $scope.loginAbonado = function (id) {
                $scope.estado = "abonado";
            }
            
            $scope.login = function () {
                $scope.estado = "login";
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
                        alert("Estado de pedido "+ id +" a "+ estado);
                        $scope.loginEmpleado();
                    }, function (response) {
                        console.log("error: " + response.status);
                    });
            }
            
            $scope.deletePedido = function (id){
                $http({
                    method: "DELETE",
                    url: baseUrl + "/empleado/pedidos/" + id
                    }).then(function (response) {
                        console.log("exito: " + response.status);
                        alert("Pedido "+ id +" borrado ");
                        $scope.loginEmpleado();
                    }, function (response) {
                        console.log("error: " + response.status);
                    });
                /*$http.delete(baseUrl + "/empleado/pedidos/"+id
                        ).then(function (response) {
                        console.log("exito: " + response.status);
                        alert("Pedido "+ id +" borrado ");
                        $scope.loginEmpleado();
                    }, function (response) {
                        console.log("error: " + response.status);
                    });*/
            }
        });
