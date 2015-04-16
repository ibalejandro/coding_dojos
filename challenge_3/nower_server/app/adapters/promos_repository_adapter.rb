class PromosRepositoryAdapter
  def all
    Promo.all.map { |promo| serialize(promo) }
  end

  def count
    Promo.count
  end

  # TODO: handle case when some validation goes wrong
  def save(promo)
    serialize Promo.create(title: promo.title, description: promo.description,
                          latitude: promo.latitude, longitude: promo.longitude,
                          expiration_date: promo.expiration_date,
                          people_limit: promo.people_limit)
  end

  def save_redemption(redemption)
    serialize_redemption Redemption.create(promo_id: redemption.promo_id,
                                           code: redemption.code,
                                           redeemed: redemption.redeemed)
  end

  def find(id)
    promo = Promo.find(id)
    promo = serialize promo if promo
    promo
  end

  def redemption_code_exists?(code)
    return true if Redemption.find_by(code: code)
    return false
  end

  def delete_all
    Promo.delete_all
  end

  def delete(id)
    Promo.find(id).destroy
  end

  private

  def serialize(promo)
    entity = NowerCore::Entities::Promo.new(promo)
  end

  def serialize_redemption(redemption)
    entity = NowerCore::Entities::Redemption.new(redemption)
  end
end
