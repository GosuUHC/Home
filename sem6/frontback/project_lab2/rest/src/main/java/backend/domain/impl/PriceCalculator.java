package backend.domain.impl;

import backend.domain.api.Priceable;

public class PriceCalculator implements Priceable {

    @Override
    public Integer calculatePrice(Integer price, Integer count) {
        return price * count;
    }

}
