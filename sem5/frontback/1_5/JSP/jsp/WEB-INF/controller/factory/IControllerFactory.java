package controller.factory;

import controller.IBack;

public interface IControllerFactory {
    public IBack createController(ControllerType name);

}
