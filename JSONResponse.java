package org.example.model;

/*
 *http响应json数据，前后端统一约定的json格式
 * 响应状态码都是200，进入ajax的success来使用
 * (success:true,data:xxx)
 * (success:false,code:xxx,message:xxx)
 */

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JSONResponse {
    //业务操作是否成功
    private  boolean success;
    //业务操作消息码，出现错误时错误码才有意义，给开发人员看
    private String code;
    //业务操作错误信息，给用户看
    private String message;
    //业务数据：操作成功时给前端ajax success方法使用 解析响应json数据 渲染网页内
    private  Object data;

}
