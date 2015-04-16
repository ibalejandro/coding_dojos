module NowerCore
  module Repositories
    module InMemory
      class Promos
        def initialize
          @promos = {}
          @next_id = 1
        end

        def all
          @promos.values
        end

        def find(id)
          @promos[id]
        end

        def save(promo)
          promo.id = @next_id
          @promos[@next_id] = promo
          @next_id += 1
          promo
        end

        def delete_all
          @promos = {}
          @next_id = 1
        end

        def delete(id)
          @promos.delete id
        end
      end
    end
  end
end
