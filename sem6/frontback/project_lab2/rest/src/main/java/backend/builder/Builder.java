package backend.builder;

import backend.model.interfaces.items.IItemsGetter;
import backend.model.interfaces.orders.IOrdersDeleter;
import backend.model.interfaces.orders.IOrdersGetter;
import backend.model.interfaces.orders.IOrdersPoster;
import backend.model.interfaces.orders.IOrdersUpdater;
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
    private IOrdersUpdater ordersUpdater;

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
        return ordersDeleter;
    }

    @Produces
    @Built
    public IOrdersUpdater buildOrdersUpdater() {
        ordersUpdater.injectOrdersRepository(ordersRepository);
        return ordersUpdater;
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
