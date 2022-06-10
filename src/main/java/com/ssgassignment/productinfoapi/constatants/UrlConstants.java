package com.ssgassignment.productinfoapi.constatants;

public abstract class UrlConstants {
    public static final String API_PREFIX = "/api";
    public static final String ID = "/{id}";

    public static final String SAVE = "/save";
    public static final String DELETE = "/delete";

    public static final String USER="/user";
    public static final String ITEM="/item";
    public static final String PROMOTION="/promotion";

    /**
     * user uri
     */
    public static final String USER_BASE = API_PREFIX+"/users";
    public static final String USER_WITHDRAW = "/withdraw";
    public static final String LOGIN = "/login";

    /**
     * item uri
     */
    public static final String ITEM_BASE = API_PREFIX+"/items";

    /**
     * promotion uri
     */
    public static final String PROMOTION_BASE = API_PREFIX+"/promotions";


}
