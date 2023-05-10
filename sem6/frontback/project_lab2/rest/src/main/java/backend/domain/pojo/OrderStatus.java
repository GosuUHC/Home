package backend.domain.pojo;

public enum OrderStatus {
    processing, delivering, completed;

    public static OrderStatus getValue(String value) {
        return switch (value.toLowerCase()) {
            case "processing" -> processing;
            case "delivering" -> delivering;
            case "completed" -> completed;
            case "default" -> processing;
            default -> throw new IllegalArgumentException("Unknown type: " + value);
        };
    }
}
