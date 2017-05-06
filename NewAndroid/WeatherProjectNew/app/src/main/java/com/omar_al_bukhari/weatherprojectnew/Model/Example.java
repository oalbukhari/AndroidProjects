package com.omar_al_bukhari.weatherprojectnew.Model;

/**
 * Developer: Omar Al Bukhari
 * Username: fedom
 * Date: 04/05/2017
 * Any copy of this code is forbidden.
 */




public class Example
{
    public Response response;
    public CurrentObservation current_observation;


    public class Features
    {
        public int conditions;
    }

    public class Response
    {
        public String version;
        public String termsofService;
        public Features features;
    }

    public class Image
    {
        public String url;
        public String title;
        public String link;
    }
    public class DisplayLocation
    {
        public String full;
        public String city;
        public String state;
        public String state_name;
        public String country;
        public String country_iso3166;
        public String zip;
        public String magic;
        public String wmo;
        public String latitude;
        public String longitude;
        public String elevation;
    }
    public class ObservationLocation
    {
        public String full;
        public String city;
        public String state;
        public String country;
        public String country_iso3166;
        public String latitude;
        public String longitude;
        public String elevation;
    }
    public class Estimated
    {
    }
    public class CurrentObservation
    {
        public Image image;
        public DisplayLocation display_location;
        public ObservationLocation observation_location;
        public Estimated estimated;
        public String station_id;
        public String observation_time;
        public String observation_time_rfc822;
        public String observation_epoch;
        public String local_time_rfc822;
        public String local_epoch;
        public String local_tz_short;
        public String local_tz_long;
        public String local_tz_offset;
        public String weather;
        public String temperature_string;
        public double temp_f;
        public double temp_c;
        public String relative_humidity;
        public String wind_string;
        public String wind_dir;
        public int wind_degrees;
        public double wind_mph;
        public String wind_gust_mph;
        public double wind_kph;
        public String wind_gust_kph;
        public String pressure_mb;
        public String pressure_in;
        public String pressure_trend;
        public String dewpoint_string;
        public int dewpoint_f;
        public int dewpoint_c;
        public String heat_index_string;
        public String heat_index_f;
        public String heat_index_c;
        public String windchill_string;
        public String windchill_f;
        public String windchill_c;
        public String feelslike_string;
        public String feelslike_f;
        public String feelslike_c;
        public String visibility_mi;
        public String visibility_km;
        public String solarradiation;
        public String uV;
        public String precip_1hr_string;
        public String precip_1hr_in;
        public String precip_1hr_metric;
        public String precip_today_string;
        public String precip_today_in;
        public String precip_today_metric;
        public String icon;
        public String icon_url;
        public String forecast_url;
        public String history_url;
        public String ob_url;
        public String nowcast;
    }
}