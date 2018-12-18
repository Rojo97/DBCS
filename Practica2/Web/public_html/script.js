angular.module("practicaApp", [])
        .constant("baseUrl", "http://localhost:8080/Rest/webresources/Rest")
        .controller("appCtrl", function ($scope, $http, baseUrl) { // Inyectamos recursos
            $scope.estado = "login";

            $scope.loginAbonado = function (id) {
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
        });
