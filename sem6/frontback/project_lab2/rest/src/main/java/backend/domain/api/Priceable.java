package backend.domain.api;

public interface Priceable {
    public Integer calculatePrice(Integer price, Integer count);
}
