module NowerCore
  module UseCases
    class PublishPromo
      def execute(promo)
        promo_repo.save new_promo(promo)
      end

      private

      def new_promo(promo)
        Entities::Promo.new(promo)
      end

      def promo_repo
        Repository.for(:promo)
      end
    end
  end
end
