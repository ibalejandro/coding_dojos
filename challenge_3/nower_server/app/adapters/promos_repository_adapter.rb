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

  def find(id)
    serialize Sale.find(id)
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
    entity.id = promo.id
    entity
  end
end
