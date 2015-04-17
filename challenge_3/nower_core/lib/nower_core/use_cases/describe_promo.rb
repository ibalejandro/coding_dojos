module NowerCore
  module UseCases
    class DescribePromo
      def execute(promo)
        promo_repo.find promo.id
      end

      private

      def promo_repo
        Repository.for(:promo)
      end
    end
  end
end
