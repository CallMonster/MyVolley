package com.tj.myvolley.vo;

/**
 * Created by Lee on 2015/12/11.
 */
public class WeatherInfo {

    private String city;//城市
    private String cityid;//城市编号
    private String temp;//温度
    private String WD;//风向
    private String WS;//风力
    private String SD;//湿度
    private String WSE;
    private String time;
    private String isRadar;
    private String Radar;
    private String njd;
    private String qy;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getWD() {
        return WD;
    }

    public void setWD(String WD) {
        this.WD = WD;
    }

    public String getWS() {
        return WS;
    }

    public void setWS(String WS) {
        this.WS = WS;
    }

    public String getSD() {
        return SD;
    }

    public void setSD(String SD) {
        this.SD = SD;
    }

    public String getWSE() {
        return WSE;
    }

    public void setWSE(String WSE) {
        this.WSE = WSE;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIsRadar() {
        return isRadar;
    }

    public void setIsRadar(String isRadar) {
        this.isRadar = isRadar;
    }

    public String getRadar() {
        return Radar;
    }

    public void setRadar(String radar) {
        Radar = radar;
    }

    public String getNjd() {
        return njd;
    }

    public void setNjd(String njd) {
        this.njd = njd;
    }

    public String getQy() {
        return qy;
    }

    public void setQy(String qy) {
        this.qy = qy;
    }
}


//{
//        "weatherinfo": {
//        "city": "天津",
//        "cityid": "101030100",
//        "temp": "9",
//        "WD": "东北风",
//        "WS": "2级",
//        "SD": "21%",
//        "WSE": "2",
//        "time": "10:35",
//        "isRadar": "1",
//        "Radar": "JC_RADAR_AZ9220_JB",
//        "njd": "暂无实况",
//        "qy": "1016"
//        }
//        }