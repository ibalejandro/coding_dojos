class Redemption < ActiveRecord::Base
  belongs_to :promo
  validates :promo, presence: true
  validates :code, uniqueness: true
end
