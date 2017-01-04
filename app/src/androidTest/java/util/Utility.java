package util;

import android.text.TextUtils;

import db.CoolWeatherDB;
import model.City;
import model.County;
import model.Province;

/**
 * Created by chiqifeng on 2017/1/4.
 */

public class Utility {
    /**
     * 解析和处理服务器返回的升级数据
     */
    public synchronized static boolean handleProvincesResponce(CoolWeatherDB coolWeatherDB,String response){
        if (!TextUtils.isEmpty(response)){
            String[] allProvinces=response.split(",");
            if (allProvinces !=null && allProvinces.length >0){

                for (String p :allProvinces){
                    String[] array=p.split("\\|");
                    Province province=new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    //将解析出来的数据存储到Province表
                    coolWeatherDB.saveProvince(province);

                }
                return true;
            }
        }

        return false;

    }
    /**
     *  解析和处理服务器返回的市级数据
     */
    public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,String response,int provinceId){
        if (! TextUtils.isEmpty(response)){
            String[] allcities=response.split(",");
            if (allcities != null && allcities.length >0){
                for (String c :allcities){
                    String[] array=c.split("\\|");
                    City city=new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[1]);
                    city.setProvinceId(provinceId);
                    //将解析出来的数据存储到City表
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }
    return false;
    }
    /**
     *  解析和处理服务器返回的先机数据
     */
    public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB,String response,int cityId){
        if (!TextUtils.isEmpty(response)){
            String[] allCounties=response.split(",");
            if (allCounties != null && allCounties.length >0){
                for (String c :allCounties){
                    String[] array=c.split("\\|");
                    County county=new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    //将解析数据存储到County表
                    coolWeatherDB.saveCounty(county);
                }
                return true;
            }

        }
        return false;
    }

}
