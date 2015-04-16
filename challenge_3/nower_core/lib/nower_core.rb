require "nower_core/version"
require "nower_core/entities/promo"
require "nower_core/repository"
#require "nower_core/use_cases/publish_promo"
#require "nower_core/use_cases/find_all_promos"

Dir[File.dirname(__FILE__) + "/nower_core/use_cases/*.rb"].each {|file|
  require file
}

module NowerCore
  class << self
    def publish_promo(promo)
      UseCases::PublishPromo.new.execute(promo)
    end

    def find_all_promos
      UseCases::FindAllPromos.new.execute
    end
  end
end
