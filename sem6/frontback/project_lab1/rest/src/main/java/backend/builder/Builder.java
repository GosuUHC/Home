package backend.builder;

import java.util.logging.Level;
import java.util.logging.Logger;

import backend.model.interfaces.delete.IOrdersDeleter;
import backend.model.interfaces.get.IItemsGetter;
import backend.model.interfaces.get.IOrdersGetter;
import backend.model.interfaces.post.IOrdersPoster;
import backend.model.interfaces.repositories.itemsRepository.IItemsRepository;
import backend.model.interfaces.repositories.ordersRepository.IOrdersRepository;
import backend.other.interfaces.IAuthorizer;
import backend.other.interfaces.IRegistrator;
import backend.other.interfaces.IUsersRepository;
import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

public class Builder {

    @Inject
    @Default
    private IItemsRepository itemsRepository;

    @Inject
    @Default
    private IUsersRepository usersRepository;

    @Inject
    @Default
    private IOrdersRepository ordersRepository;

    @Inject
    @Default
    private IOrdersDeleter ordersDeleter;

    @Inject
    @Default
    private IOrdersGetter ordersGetter;

    @Inject
    @Default
    private IItemsGetter itemsGetter;

    @Inject
    @Default
    private IOrdersPoster ordersPoster;

    @Inject
    @Default
    private IAuthorizer authorizer;

    @Inject
    @Default
    private IRegistrator registrator;

    @Produces
    @Built
    public IOrdersGetter buildOrdersGetter() {
        ordersGetter.injectOrdersRepository(ordersRepository);
        return ordersGetter;
    }

    @Produces
    @Built
    public IOrdersDeleter buildOrdersDeleter() {
        ordersDeleter.injectOrdersRepository(ordersRepository);
        Logger.getLogger(Builder.class.getName()).log(Level.INFO, ordersRepository.toString());
        return ordersDeleter;
    }

    @Produces
    @Built
    public IItemsGetter buildItemsGetter() {
        itemsGetter.injectItemsRepository(itemsRepository);
        return itemsGetter;
    }

    @Produces
    @Built
    public IOrdersPoster buildOrdersPoster() {
        ordersPoster.injectItemsRepository(itemsRepository);
        ordersPoster.injectOrdersRepository(ordersRepository);
        return ordersPoster;
    }

    @Produces
    @Built
    public IAuthorizer buildAuthorizer() {
        authorizer.injectUsersRepository(usersRepository);
        return authorizer;
    }

    @Produces
    @Built
    public IRegistrator buildRegistrator() {
        registrator.injectUsersRepository(usersRepository);
        return registrator;
    }

}
