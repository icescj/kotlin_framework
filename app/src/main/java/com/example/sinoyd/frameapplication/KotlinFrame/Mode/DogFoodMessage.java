package com.example.sinoyd.frameapplication.KotlinFrame.Mode;

/**
 * 作者： scj
 * 创建时间： 2018/7/11
 * 版权： 美好明天机器人
 * 描述： com.example.sinoyd.frameapplication.KotlinFrame.Mode
 */
public class DogFoodMessage {

    public DogFoodMessage(String message) {
        Message = message;
    }

    private String Message;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
