class Sale < ActiveRecord::Base
  has_many :redemptions
end
