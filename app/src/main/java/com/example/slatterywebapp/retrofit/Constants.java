package com.example.slatterywebapp.retrofit;


public class Constants {

    /*business rules which are to be applied on classes and/or fields (properties) of the class.
     * Rules that will never change, fields that always must stay the same*/

        public static final String Key = "57c8ab4731ae54d48badbe0a62e924dd";
        public static final String BASE_URL = "https://api.darksky.net/forecast/";

        public static String apiRequest(String lat, String lng) {
            StringBuilder sb = new StringBuilder(BASE_URL);
            sb.append(String.format("?lat-%&lon-%&AAPID-%&units-metric",lat,lng,Key));
            return sb.toString();
        }

    }



