module NowerCore
  module Entities
    class Promo
      attr_accessor :id, :title, :description, :latitude, :longitude,
                    :expiration_date, :people_limit

      def initialize(promo)
        @id = promo["id"]
        @title = promo["title"]
        @description = promo["description"]
        @latitude = promo["latitude"]
        @longitude = promo["longitude"]
        @expiration_date = promo["expiration_date"]
        @people_limit = promo["people_limit"]
      end
    end
  end
end
