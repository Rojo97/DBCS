angular.module("practicaApp", [])
        .constant("baseUrl", "http://localhost:8080/ServicioVino2/webresources/paraAngular")
        .controller("appCtrl", function ($scope, $http, baseUrl) { // Inyectamos recursos
            $scope.estado = "login";

            $scope.loginAbonado = function () {
                $scope.estado = "abonado";
            }

            $scope.loginEmpleado = function () {
                $scope.estado = "empleado";
            }
        });
