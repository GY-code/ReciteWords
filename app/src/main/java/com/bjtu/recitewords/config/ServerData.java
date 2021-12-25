package com.bjtu.recitewords.config;

public class ServerData {

    public final static String SERVER_ADDRESS = "http://106.14.105.16:8000";
//    public final static String SERVER_ADDRESS = "http://192.168.31.35:8000";

    public final static String SERVER_LOGIN_ADDRESS = SERVER_ADDRESS + "/Login.php";
    public final static String SERVER_UPLOAD_RECORD_ADDRESS = SERVER_ADDRESS + "/artapp/Record/";
    public final static String SERVER_UPLOAD_INFO_ADDRESS = SERVER_ADDRESS + "/artapp/InforFiles/";
    public final static String SERVER_RETURN_BOOKS_ADDRESS = SERVER_ADDRESS + "/artapp/GetAllFiles/";

    public final static String LOGIN_SINA_NUM = "sinaNum";
    public final static String LOGIN_SINA_NAME = "sinaName";
    public final static String TYPE_NAME = "updateType";
    public final static String UPLOAD_FILE = "uploadedFile";
    public final static String UPLOAD_TYPE = "1";
    public final static String RECOVER_TYPE = "2";

}
