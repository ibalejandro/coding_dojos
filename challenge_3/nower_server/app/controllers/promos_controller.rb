class PromosController < ApplicationController

  # GET /promos
  def index
    @promos = NowerCore.find_all_promos
    render json: @promos
  end

  # GET /promos/1
  def show
    # TODO Not implemented yet.
    #response = DescribePromo.call id: params[:id]
    #@sale = response.sale
    #render json: @sale
    promo_hash = Hash.new
    promo_hash["id"] = params[:id].to_i
    @promo = NowerCore.describe_promo(build_entity(promo_hash))
    render json: {
      promo: @promo
    }
  end

  # POST /promos
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
    @promo = NowerCore.publish_promo(build_entity(promo_params))
    render json: {
      success: true,
      promo: @promo
    }
  end

  private
    # Never trust parameters from the scary internet,
    # only allow the white list through.
    def build_entity(promo_hash)
      NowerCore::Entities::Promo.new(promo_hash)
    end

    def promo_params
      params.require(:promo).permit(:title, :description, :latitude, :longitude,
                                   :expiration_date, :people_limit)

    end
end
