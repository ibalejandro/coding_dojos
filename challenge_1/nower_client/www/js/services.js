angular.module('App.services', [])

//Este servicio almacena los valores de latitud y longitud registradas por
//el marcador del mapa.
.service('SharedVars', function () {
  var lat;
  var lng;

  return {
    getLat: function () {
      return lat;
    },
    setLat: function(value) {
      lat = value;
    },
    getLng: function () {
      return lng;
    },
    setLng: function(value) {
      lng = value;
    }
  };
})

//Servicio para gestionar la publicación de una nueva promoción
.service('PromoService', ['$http', function ($http) {
  return {
    sendPromo: function(promo) {
      //Se construye la URL a manera de GET debido a grandes problemas con
      //CORS (Cross Origin Resource Sharing) que no pudieron ser resueltos y
      //que impidieron el uso del método POST.
      url = "http://castofo.tk/sales/";
      url += promo.sale.title + '/';
      url += promo.sale.description + '/';
      url += promo.sale.latitude + '/';
      url += promo.sale.longitude + '/';
      url += promo.sale.expiration_date + '/';
      url += promo.sale.people_limit;
      return $.get(url, function(response) {
        console.log(JSON.stringify(response));
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
}]);
