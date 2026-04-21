package am.agro_trade.notification_service.model.enums;

public enum EmailType {

    ORDER_OPENED(false),
    VERIFY(true),
    RESET_PASSWORD(true),
    WELCOME(false);

    private final boolean requiresCode;

    EmailType(boolean requiresCode) {
        this.requiresCode = requiresCode;
    }

    public boolean requiresCode() {
        return requiresCode;
    }
}
