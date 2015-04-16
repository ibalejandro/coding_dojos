class Redemption < ActiveRecord::Base
  belongs_to :sale
  validates :sale, presence: true
  validates :code, uniqueness: true
end
