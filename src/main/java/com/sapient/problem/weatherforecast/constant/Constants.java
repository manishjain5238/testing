package com.sapient.problem.weatherforecast.constant;

public class Constants {

    public static final String EXTERNAL_WEATHER_SERVICE_HOST = "${weather.external_weather_api.host}";
    public static final String EXTERNAL_WEATHER_SERVICE_PATH = "${weather.external_weather_api.path}";
    public static final String EXTERNAL_WEATHER_SERVICE_APPID = "${weather.external_weather_api.appid}";
    public static final String EXTERNAL_WEATHER_SERVICE_UNITS = "${weather.external_weather_api.units}";
    public static final String EXTERNAL_WEATHER_SERVICE_MODE = "${weather.external_weather_api.mode}";
    public static final String EXTERNAL_WEATHER_SERVICE_SCHEME = "${weather.external_weather_api.scheme}";

    public static final String SAPIENT_API_URL = "/sapient/api";
    public static final String FORECAST_URL = "/forecast";

    public static final String EXTERNAL_WEATHER_SERVICE_RETRY = "${weather.external_weather_api.service-retry}";
    public static final String EXTERNAL_WEATHER_SERVICE_RETRY_BACKOFF =
                    "${weather.external_weather_api.service-backoff}";

    public static final int DEFAULT_REQUEST_PARTITION_SIZE = 300;

    public static final int DEFAULT_PARALLELISM_COUNT = 2;
    public static final int RETRY_COUNT = 3;
    public static final int RETRY_BACKOFF_TIME = 200;
    public static final int RETRY_FETCH_BACKOFF_TIME = 300;
    public static final String VERSION = "version";
}
