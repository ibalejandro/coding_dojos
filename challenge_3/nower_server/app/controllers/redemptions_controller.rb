class RedemptionsController < ApplicationController

  # POST /redemptions
  def create
    # TODO Not implemented yet.
    #response = TakePromo.call redemption_params
    #@redemption = response.redemption
    #if response.success?
    #  render json: {
    #    success: true,
    #    redemption: @redemption
    #  }
    #else
    #  render json: {
    #    success: false,
    #    errors: @redemption.errors
    #  },
    #  status: :unprocessable_entity
    #end
    redemption = NowerCore.take_promo(build_entity(redemption_params))
    render json: {
      success: true,
      redemption: redemption
    }
  end

  private
    # Never trust parameters from the scary internet,
    # only allow the white list through.
    def build_entity(redemption_hash)
      NowerCore::Entities::Redemption.new(redemption_hash)
    end

    def redemption_params
      params.require(:redemption).permit(:promo_id)
    end
end
