package cn.luojh.reagion.render;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.TreeMap;

public class AjaxRender {

    public static final int CODE_SUCCESS = 200;//正常返回
    public static final int CODE_TOKEN_EXPIRED = 301;//Token失效
    public static final int CODE_NO_PERMISSION = 401;//无权访问
    public static final int CODE_FAILURE = 500;//服务器故障

    private int code;//状态码
    private String msg;//消息
    private Map<String,Object> data = new TreeMap<>();//数据域

    private AjaxRender(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public static AjaxRender build(){
        return new AjaxRender(CODE_SUCCESS,"");
    }

    public AjaxRender putData(String key, Object value){
        data.put(key,value);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String ok(){
        this.code = CODE_SUCCESS;
        this.msg = "操作成功";
        return toString();
    }

    public String ok(String msg){
        this.code = CODE_SUCCESS;
        this.msg = msg;
        return toString();
    }

    public String fail(){
        this.code = CODE_FAILURE;
        this.msg = "操作失败";
        return toString();
    }

    public String fail(String msg){
        this.code = CODE_FAILURE;
        this.msg = msg;
        return toString();
    }

    public String noPermission(){
        this.code = CODE_NO_PERMISSION;
        this.msg = "无权访问";
        return toString();
    }

    public String noPermission(String msg){
        this.code = CODE_NO_PERMISSION;
        this.msg = msg;
        return toString();
    }

    public String expire(){
        this.code = CODE_TOKEN_EXPIRED;
        this.msg = "token失效";
        return toString();
    }

    public String expire(String msg){
        this.code = CODE_TOKEN_EXPIRED;
        this.msg = msg;
        return toString();
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
