class SalesController < ApplicationController

  # GET /sales
  def index
    @sales = NowerCore.find_all_promos
    render json: @sales
  end

  # GET /sales/1
  def show
    # TODO Not implemented yet.
    #response = DescribePromo.call id: params[:id]
    #@sale = response.sale
    #render json: @sale
  end

  # POST /sales
  def create
    #response = PublishPromo.call sale_params
    #@sale = response.sale
    #if response.success?
    #  render json: {
    #    success: true,
    #    sale: @sale
    #  }
    #else
    #  render json: {
    #    success: false,
    #    errors: @sale.errors
    #  },
    #  status: :unprocessable_entity
    #end
    @sale = NowerCore.publish_promo(sale_params)
    render json: {
      sale: @sale
    }
  end

  private
    # Never trust parameters from the scary internet,
    # only allow the white list through.
    def sale_params
      params.require(:sale).permit(:title, :description, :latitude, :longitude,
                                   :expiration_date, :people_limit)
    end
end