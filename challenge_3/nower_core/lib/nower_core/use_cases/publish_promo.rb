module NowerCore
  module UseCases
    class PublishPromo
      def execute(promo)
        promo_repo.save promo
      end

      private
      def promo_repo
        Repository.for(:promo)
      end
    end
  end
end
