require "nower_core/repositories/in_memory/promos"

module NowerCore
  class Repository
    def self.register(type, repo)
      repositories[type] = repo
    end

    def self.for(type)
      repositories[type]
    end

    def self.repositories
      @_repos ||= {}
    end
  end
end
