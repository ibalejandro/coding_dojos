class CreateRedemptions < ActiveRecord::Migration
  def change
    create_table :redemptions do |t|
      t.string :code
      t.boolean :redeemed
      t.integer :sale_id

      t.timestamps
    end
  end
end
