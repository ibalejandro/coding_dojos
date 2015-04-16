module NowerCore
  module Entities
    class Redemption
      attr_accessor :id, :promo_id, :code, :redeemed

      def initialize(redemption)
        @id = redemption["id"]
        @promo_id = redemption["promo_id"]
        @code = redemption["code"]
        @redeemed = redemption["redeemed"]
      end
    end
  end
end
