class ChangeColumnName < ActiveRecord::Migration
  def change
  	rename_column :sales, :logitude, :longitude
  end
end
