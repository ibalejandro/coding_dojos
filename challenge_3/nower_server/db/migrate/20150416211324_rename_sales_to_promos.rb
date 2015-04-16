class RenameSalesToPromos < ActiveRecord::Migration
  def change
    rename_table :sales, :promos
  end
end
