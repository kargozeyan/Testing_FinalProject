package am.aua.utils;

import am.aua.Constants;

public class StringUtils {
    public static int extractPrice(String price) {
        return Integer.parseInt(price.replaceAll(Constants.CURRENCY, "")
                .replaceAll(",", "")
                .trim());
    }
}
