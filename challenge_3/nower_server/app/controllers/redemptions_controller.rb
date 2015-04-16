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
  end

  private
    # Never trust parameters from the scary internet,
    # only allow the white list through.
    def redemption_params
      params.require(:redemption).permit(:sale_id)
    end
end
