package backend.infrastructure.builder;

import backend.application.interfaces.in.authentication.IAuthorizer;
import backend.application.interfaces.in.items.IItemsGetter;
import backend.application.interfaces.in.orders.IOrdersDeleter;
import backend.application.interfaces.in.orders.IOrdersGetter;
import backend.application.interfaces.in.orders.IOrdersPoster;
import backend.application.interfaces.in.orders.IOrdersUpdater;
import backend.application.interfaces.in.registration.IRegistrator;
import backend.application.interfaces.out.repository.itemsRepository.IItemsRepository;
import backend.application.interfaces.out.repository.ordersRepository.IOrdersRepository;
import backend.application.interfaces.out.repository.usersRepository.IUsersRepository;
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