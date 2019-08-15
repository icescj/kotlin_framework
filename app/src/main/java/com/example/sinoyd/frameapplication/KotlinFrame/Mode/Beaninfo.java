package com.example.sinoyd.frameapplication.KotlinFrame.Mode;

import com.google.gson.annotations.SerializedName;

/**
 * 作者： scj
 * 创建时间： 2018/6/27
 * 版权： 美好明天机器人
 * 描述： com.example.sinoyd.frameapplication.KotlinFrame.Uitl.http
 */
public class Beaninfo {


    @SerializedName("msg")
    private MsgBean _$Msg136; // FIXME check this code
    private int state;

    public MsgBean get_$Msg136() {
        return _$Msg136;
    }

    public void set_$Msg136(MsgBean _$Msg136) {
        this._$Msg136 = _$Msg136;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public static class MsgBean {
        @SerializedName("id")
        private int _$Id68; // FIXME check this code
        private String user_name;
        private String nickname;
        private String phone;
        private String avater;

        public int get_$Id68() {
            return _$Id68;
        }

        public void set_$Id68(int _$Id68) {
            this._$Id68 = _$Id68;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAvater() {
            return avater;
        }

        public void setAvater(String avater) {
            this.avater = avater;
        }
    }
}
