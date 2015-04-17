module NowerCore
  module UseCases
    class TakePromo
      def execute(redemption)
        promo_repo.save_redemption generate_redemption redemption
      end

      private
      def promo_repo
        Repository.for(:promo)
      end

      def generate_redemption(redemption)
        begin
          code = (SecureRandom.hex 3).upcase
        end while promo_repo.redemption_code_exists?(code)
        redemption.code = code
        redemption.redeemed = false
        redemption
      end
    end
  end
end
