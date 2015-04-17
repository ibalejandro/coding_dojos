module NowerCore
  module Entities
    class Redemption
      attr_accessor :id, :sale_id, :code, :redeemed

      def initialize(redemption)
        @id = redemption["id"]
        @sale_id = redemption["sale_id"]
        @code = redemption["code"]
        @redeemed = redemption["redeemed"]
      end
    end
  end
end
