package com.jinkme.framtest.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * @ClassName RstUtil
 * @Description TODO
 * @Author zhouhui
 * @Date 2019/5/7 09:18
 * @Version 1.0
 */
public class RstUtil implements Serializable {

    private static final long serialVersionUID = -2057520166273713027L;

    public static final int SUCCESS = 101;
    public static final int SYS_ERROR =201;
    public static final int PARAM_NULL= 301;
    public static final int param_error =302;
    public static final int SERVICE_ERROR = 401;
    public static final int USER_LOGINOUT = 501;

    private boolean success = true;
    private int errorCode;
    private JSONObject data = new JSONObject();
    private String errorMsg = "";


    public static RstUtil getRstUtil(boolean success){
        return new RstUtil(success);
    }

    public static RstUtil getRstUtil(boolean success, int errorCode){
        return new RstUtil(success, errorCode);
    }

    public static RstUtil getRstUtil(boolean success, JSONObject data) {
        return new RstUtil(success, data);
    }

    public static RstUtil getRstUtil(boolean success, int errorCode, String errorMsg) {
        return new RstUtil(success,errorCode,errorMsg);
    }

    public static RstUtil getRstUtil(boolean success, int errorCode, String errorMsg, JSONObject data) {
        return new RstUtil(success,errorCode,errorMsg,data);
    }

    public static RstUtil getRstUtil(boolean success, Object object) {
        JSONObject json = new JSONObject();
        json.put("data",object);
        return new RstUtil(success,json);
    }

    public static RstUtil getRstUtil(boolean success,int errorCode, String errorMsg, Object object){
        JSONObject json = new JSONObject();
        json.put("data",object);
        return new RstUtil(success,errorCode,errorMsg,json);
    }

    public RstUtil(){}

    private RstUtil(boolean success) {
        this.success = success;
    }

    private RstUtil(boolean success, JSONObject data){
        this.success = success;
        this.data = data;
    }

    private RstUtil(boolean success, int errorCode){
        this.success = success;
        this.data = data;
    }
    private RstUtil(boolean success, int errorCode,String errorMsg) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    private RstUtil(boolean success, int errorCode,String errorMsg,JSONObject data) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.data = data;
    }

    public static void sendMsgClient(HttpServletResponse response, RstUtil rstUtil) {
        try {
            response.setContentType("Application/json");
            PrintWriter out = response.getWriter();
            JSON.writeJSONStringTo(rstUtil, out, new SerializerFeature[0]);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
