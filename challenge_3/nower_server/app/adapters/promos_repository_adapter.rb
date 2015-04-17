class PromosRepositoryAdapter
  def all
    Sale.all.map { |promo| serialize(promo) }
  end

  def count
    Sale.count
  end

  # TODO: handle case when some validation goes wrong
  def save(promo)
    serialize Sale.create(title: promo.title, description: promo.description,
                          latitude: promo.latitude, longitude: promo.longitude,
                          expiration_date: promo.expiration_date,
                          people_limit: promo.people_limit)
  end

  def save_redemption(redemption)
    serialize_redemption Redemption.create(sale_id: redemption.sale_id, 
                                           code: redemption.code, 
                                           redeemed: redemption.redeemed)
  end

  def find(id)
    promo = Sale.find(id)
    promo = serialize promo if promo
    promo
  end

  def redemption_code_exists?(code)
    return true if Redemption.find_by(code: code)
    return false
  end

  def delete_all
    Sale.delete_all
  end

  def delete(id)
    Sale.find(id).destroy
  end

  private

  def serialize(promo)
    entity = NowerCore::Entities::Promo.new(promo)
  end

  def serialize_redemption(redemption)
    entity = NowerCore::Entities::Redemption.new(redemption)
  end
end
