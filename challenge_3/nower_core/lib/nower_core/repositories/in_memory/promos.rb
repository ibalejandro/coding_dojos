module NowerCore
  module Repositories
    module InMemory
      class Promos
        def initialize
          @promos = {}
          @next_id = 1
          @redemptions = {}
          @next_id_redemptions = 1
        end

        def all
          @promos.values
        end

        def find(id)
          @promos[id]
        end

        def redemption_code_exists?(code)
          @redemptions.each do | id, redemption |
              return true if redemption.code == code
          end
          return false
        end

        def save(promo)
          promo.id = @next_id
          @promos[@next_id] = promo
          @next_id += 1
          promo
        end

        def save_redemption(redemption)
          redemption.id = @next_id_redemptions
          @redemptions[@next_id_redemptions] = redemption
          @next_id_redemptions += 1
          redemption
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
