package backend.domain;

import backend.domain.api.Priceable;
import backend.domain.impl.PriceCalculator;

public class Factory {
    public static Priceable createCalculator() {
        return new PriceCalculator();
    }
}
