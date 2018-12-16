angular.module("practicaApp", [])
        .constant("baseUrl", "http://localhost:8080/ServicioVino2/webresources/paraAngular")
        .controller("appCtrl", function ($scope, $http, baseUrl) { // Inyectamos recursos
            $scope.estado = "login";

            $scope.loginAbonado = function (id) {
                $scope.estado = "abonado";
                console.log(id);
            }

            $scope.loginEmpleado = function (id) {
                $scope.estado = "empleado";
                console.log(id);
            }
        });
