class ActiveSupport::TimeWithZone
  def as_json(options = {})
    strftime('%Y-%m-%d %H:%M:%S')
  end
end

#ActiveSupport::JSON::Encoding.time_precision = 0
