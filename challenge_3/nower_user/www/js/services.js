angular.module('starter.services', [])

.service('PromoTaker', ['$http', '$log', PromoTaker])

.service("RefreshingPromos", ["$http", "$log", "PromoTaker", RefreshingPromos]);

function RefreshingPromos($http, $log, PromoTaker) {
  this.getPromos = function($scope, $rootScope, $compile, $ionicPopup) {
    //Se ejecuta una petición HTTP-GET para obtener todas las promociones
    //disponibles y mostrarlas dentro del mapa.
    $http({
      url: "http://castofo.tk/promos",
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
      $scope.PromoTaker = PromoTaker;
      $log.info(JSON.stringify(result));
      showPromos($scope, $rootScope, $compile, $ionicPopup);
    }).
    error(function(data, status, headers, config) {
      //Se muestra cuando ocurre algún error dentro del servidor
      //tratando de obtener todas las promociones.
      alert("Error refreshing promos");
    });
  };
}

function PromoTaker ($http, $log) {
  return {
    takePromo: function(redemption) {
      // Se envía la petición POST para crear la promoción
      return $.post("http://castofo.tk/redemptions", redemption,
      function(response) {
        //Se responde de acuerdo a lo que el servidor retorna.
        var data = {success: response.success};
        return data;
      })
      .fail(function(xhr, text, exception) {
        console.log(xhr.responseText);
        //Se tuvieron problemas con el método.
        var data = {success: false};
        return data;
      });
    }
  };
};
