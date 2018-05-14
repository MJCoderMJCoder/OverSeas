package com.ltt.overseas.base;

public class Constants {

//    public static final String BASE_URL = "https://popmach.com/api/";
    public static final String BASE_URL = "https://dev.popmach.com/api/";

    public static final String FROM_REGISTER = "from_register";

    //聊天信息数据字段
    public static String CREATEAT = "createdAt";
    public static String MESSAGE = "message";
    public static String SENDERID = "senderId";
    public static String SENDERNAME = "senderName";
    public static String TYPE = "type";
    public static String REQUESTER = "requester";
    public static String SERVICE_PROVIDER = "service_provider";

    //聊天信息数据类型
    public static int FROMLEFTTXT = 0x111;
    public static int FROMRIGHTTXT = 0x222;
    public static int FROMLEFTPIC = 0x333;
    public static int FROMRIGHTPIC = 0x444;
    public static int FROMLEFTFILE = 0x555;
    public static int FROMRIGHTFILE = 0x666;

    public static String TYPETXT = "text";
    public static String TYPEPIC = "image";
    public static String TYPEFILE = "file";
}
