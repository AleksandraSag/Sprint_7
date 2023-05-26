package courier;


import org.apache.commons.lang3.RandomStringUtils;

public class CourierCustomData {

    private static final Courier courierNew = new Courier(RandomStringUtils.randomAlphabetic(5), "7777", "timur");
    private static final Courier courierDefault = new Courier("runner", "7777", "timur");
    private static final Courier courierWithoutLogin = new Courier("", RandomStringUtils.randomAlphabetic(5), RandomStringUtils.randomAlphabetic(5));

    public static Courier getCourierNew() {
        return courierNew;
    }

    public static Courier getCourierDefault() {
        return courierDefault;
    }

    public static Courier getCourierWithoutLogin() {
        return courierWithoutLogin;
    }

}
