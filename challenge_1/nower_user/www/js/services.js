angular.module('starter.services', [])


.service("RefreshingPromos", ["$http", "$log", RefreshingPromos]);

function RefreshingPromos($http, $log) {
  this.getPromos = function($scope, $rootScope, $compile, $ionicPopup) {
    //Se ejecuta una petición HTTP-GET para obtener todas las promociones
    //disponibles y mostrarlas dentro del mapa.
    $http({
      url: "http://castofo.tk/sales.json",
      dataType: "json",
      method: "GET",
      headers: {
        "Content-Type": "application/json"
      }
    }).success(function(result) {
      //El resultado es almacenado dentro de la variable $scope y se ejecuta
      //el método showPromos para mostrar todas las promociones traídas dentro
      //del mapa.
      $scope.promos = result;
      $log.info(JSON.stringify(result));
      showPromos($scope, $rootScope, $compile, $ionicPopup);
    }).
    error(function(data, status, headers, config) {
      //Se muestra cuando ocurre algún error dentro del servidor
      //tratando de obtener todas las promociones.
      alert("Error refreshing promos");
    });
  };
};