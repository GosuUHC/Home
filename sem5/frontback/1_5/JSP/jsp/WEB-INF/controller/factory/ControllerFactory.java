package controller.factory;

import controller.IBack;
import controller.backController.AddingController;
import controller.backController.AuthController;
import controller.backController.DelController;
import controller.backController.MainController;

public class ControllerFactory implements IControllerFactory {

    public IBack createController(ControllerType name) {

        if (name == null) {
            return null;
        }

        switch (name) {
            case main:
                return new MainController();

            case auth:
                return new AuthController();

            case adding:
                return new AddingController();

            case del:
                return new DelController();

            default:
                throw new IllegalArgumentException("Unknown type = " + name);
        }

    }

    public static ControllerFactory getFactoryObject() {
        return new ControllerFactory();

    }
}
