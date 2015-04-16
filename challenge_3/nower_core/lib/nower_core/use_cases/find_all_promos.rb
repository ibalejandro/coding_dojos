module NowerCore
  module UseCases
    class FindAllPromos
      def execute
        promo_repo.all
      end

      private

      def promo_repo
        Repository.for(:promo)
      end
    end
  end
end
