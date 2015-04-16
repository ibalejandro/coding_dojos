class CreateSales < ActiveRecord::Migration
  def change
    create_table :sales do |t|
      t.string :title
      t.string :description
      t.float :latitude
      t.float :logitude
      t.datetime :expiration_date
      t.integer :people_limit

      t.timestamps
    end
  end
end
