package com.bryant.tilemap.bean;

import java.util.List;

public class FromBean {


    /**
     * status : 200
     * msg : 请求成功
     * data : [{"max_lat":25.246254745730973,"min_lng":115.00983818939284,"remark":"信丰-果园","id":1,"min_lat":25.2297424864891,"max_lng":115.02752709100358},{"max_lat":25.201604863735337,"min_lng":115.05784849389491,"remark":"信丰-橙之源","id":9,"min_lat":25.190824907138047,"max_lng":115.07822919590706},{"max_lat":25.458446870042444,"min_lng":114.87315450604528,"remark":"信丰-红金橙","id":10,"min_lat":25.444213396772735,"max_lng":114.88514252054742},{"max_lat":25.380864212447342,"min_lng":115.02194016507025,"remark":"信丰-杨氏2","id":11,"min_lat":25.368572858701217,"max_lng":115.03607705231961},{"max_lat":25.194671421095066,"min_lng":114.92365948661909,"remark":"信丰-坝高","id":12,"min_lat":25.180438705212367,"max_lng":114.94077453229907},{"max_lat":25.416293994471864,"min_lng":114.8007212646377,"remark":"信丰-绿荫农场","id":13,"min_lat":25.404486151520633,"max_lng":114.81437674571444},{"max_lat":26.417901480681994,"min_lng":115.3021282004065,"remark":"兴国-兰屋","id":14,"min_lat":26.402384232922067,"max_lng":115.31971673217255},{"max_lat":27.235004258685226,"min_lng":116.4828105419569,"remark":"抚州-南丰","id":15,"min_lat":27.22608400280909,"max_lng":116.49405050982851},{"max_lat":24.869929291737147,"min_lng":114.85094428102197,"remark":"龙南县虔龙红茶","id":16,"min_lat":24.858891819973735,"max_lng":114.86364802544195},{"max_lat":26.46422892899217,"min_lng":115.86770815636336,"remark":"宁都县信晟蔬菜","id":17,"min_lat":26.4343894660616,"max_lng":115.8899284841997},{"max_lat":26.28077662799613,"min_lng":115.9050870494067,"remark":"宁都县硕铭蔬菜","id":18,"min_lat":26.263689231578248,"max_lng":115.94321679622601},{"max_lat":25.93496811301535,"min_lng":114.4776718881652,"remark":"上犹县峻岭茶园","id":19,"min_lat":25.917496571986234,"max_lng":114.49582284767466}]
     */

    private String status;
    private String msg;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * max_lat : 25.246254745730973
         * min_lng : 115.00983818939284
         * remark : 信丰-果园
         * id : 1
         * min_lat : 25.2297424864891
         * max_lng : 115.02752709100358
         */

        private double max_lat;
        private double min_lng;
        private String remark;
        private int id;
        private double min_lat;
        private double max_lng;

        public double getMax_lat() {
            return max_lat;
        }

        public void setMax_lat(double max_lat) {
            this.max_lat = max_lat;
        }

        public double getMin_lng() {
            return min_lng;
        }

        public void setMin_lng(double min_lng) {
            this.min_lng = min_lng;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getMin_lat() {
            return min_lat;
        }

        public void setMin_lat(double min_lat) {
            this.min_lat = min_lat;
        }

        public double getMax_lng() {
            return max_lng;
        }

        public void setMax_lng(double max_lng) {
            this.max_lng = max_lng;
        }
    }
}
