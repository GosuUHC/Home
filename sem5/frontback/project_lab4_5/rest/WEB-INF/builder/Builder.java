package builder;

import jakarta.enterprise.inject.Default;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import model.interfaces.delete.IOrdersDeleter;
import model.interfaces.get.IItemsGetter;
import model.interfaces.get.IOrdersGetter;
import model.interfaces.post.IOrdersPoster;
import model.interfaces.repositories.itemsRepository.IItemsRepository;
import model.interfaces.repositories.ordersRepository.IOrdersRepository;
import other.interfaces.IAuthorizer;
import other.interfaces.IRegistrator;
import other.interfaces.IUsersRepository;

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
    public IOrdersDeleter buildOrdersDeleter() {
        ordersDeleter.injectOrdersRepository(ordersRepository);
        return ordersDeleter;
    }

    @Produces
    @Built
    public IOrdersGetter buildOrdersGetter() {
        ordersGetter.injectOrdersRepository(ordersRepository);
        return ordersGetter;
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
