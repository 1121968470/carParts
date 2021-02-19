package com.carmold.vo;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CallResult<T> implements Serializable {
    /**
     * @date 2018年6月19日 下午4:02:02
     * @author linxinqiang
     */
    private static final long serialVersionUID = 5994341267981914884L;
    boolean succee = true;
    int code = 200;
    String msg = "操作成功";
    T data;
    Map<String, Object> datas;

    public Map<String, Object> getDatas() {
        return datas;
    }

    public void setDatas(Map<String, Object> datas) {
        this.datas = datas;
    }

    public void setData(T data) {
        this.succee = true;
        this.data = data;
    }

    public CallResult() {
    }

    private static CallResult create() {
        return new CallResult();
    }

    public CallResult(boolean succee, String msg) {
        this.msg = msg;
        this.succee = succee;
    }

    public boolean isSuccee() {
        return succee;
    }

    public void setSuccee(boolean succee) {
        this.succee = succee;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public CallResult fail(int code, String msg) {
        this.succee = false;
        this.msg = msg;
        this.code = code;
        return this;
    }

    public CallResult fail(String msg) {
        this.succee = false;
        this.msg = msg;
        return this;
    }

    public CallResult fail(int code) {
        this.succee = false;
        this.code = code;
        return this;
    }

    private CallResult code(int code) {
        this.code = code;
        return this;
    }

    public CallResult succee(String msg) {
        this.succee = true;
        this.msg = msg;
        return this;
    }

    public static CallResult ok() {
        return CallResult.create();
    }

    public static CallResult ok(Object data) {
        CallResult callResult = CallResult.create();
        callResult.setData(data);
        return callResult;
    }


    public static CallResult ok(String msg) {
        return CallResult.create()
                .succee(msg);
    }

    public static CallResult ok(int code, String msg) {
        return CallResult.create()
                .code(code)
                .succee(msg);
    }

    public static CallResult error(String msg) {
        return CallResult.create()
                .fail(msg);
    }

    public static CallResult error(int code, String msg) {
        return CallResult.create()
                .code(code)
                .fail(msg);
    }

    public T getData() {
        return data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void putData(String key, Object value) {
        if (datas == null) {
            datas = new HashMap<String, Object>();
        }
        datas.put(key, value);
    }

    public Object getData(String key) {
        if (datas != null) {
            return datas.get(key);
        }
        return null;
    }

}
