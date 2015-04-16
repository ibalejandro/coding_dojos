class ChangeColumnNameSaleIdToPromoId < ActiveRecord::Migration
  def change
    rename_column :redemptions, :sale_id, :promo_id
  end
end
