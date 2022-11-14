package db.repository.factory;

import model.interfaces.repository.IRepository;

public interface IRepositoryFactory {
    public IRepository createRepository(String type);
}
