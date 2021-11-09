package com.adi.weatherapiapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model {
    private Current current;
    private Forecast forecast;

    public class Current{
        private String last_updated;
        private float temp_c;
        private Condition condition;
        private float wind_kph;
        private float pressure_mb;
        private float precip_mm;
        private int humidity;
        private int cloud;
        private float gust_kph;
        @SerializedName("air_quality")
        private AirQuality air_quality;

        public AirQuality getAir_quality() {
            return air_quality;
        }

        public void setAir_quality(AirQuality air_quality) {
            this.air_quality = air_quality;
        }

        public class Condition{
            private String text;
            private String icon;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }

        public class AirQuality{
            private float co;
            private float no2;
            private float o3;
            private float so2;
            private float pm2_5;
            private float pm10;
            @SerializedName( "us-epa-index")
            private int us_epa_index;
            @SerializedName("gb-defra-index")
            private int gb_defra_index;


            public float getCo() {
                return co;
            }

            public void setCo(float co) {
                this.co = co;
            }

            public float getNo2() {
                return no2;
            }

            public void setNo2(float no2) {
                this.no2 = no2;
            }

            public float getO3() {
                return o3;
            }

            public void setO3(float o3) {
                this.o3 = o3;
            }

            public float getSo2() {
                return so2;
            }

            public void setSo2(float so2) {
                this.so2 = so2;
            }

            public float getPm2_5() {
                return pm2_5;
            }

            public void setPm2_5(float pm2_5) {
                this.pm2_5 = pm2_5;
            }

            public float getPm10() {
                return pm10;
            }

            public void setPm10(float pm10) {
                this.pm10 = pm10;
            }

            public int getUs_epa_index() {
                return us_epa_index;
            }

            public void setUs_epa_index(int us_epa_index) {
                this.us_epa_index = us_epa_index;
            }

            public int getGb_defra_index() {
                return gb_defra_index;
            }

            public void setGb_defra_index(int gb_defra_index) {
                this.gb_defra_index = gb_defra_index;
            }
        }


        public String getLast_updated() {
            return last_updated;
        }

        public void setLast_updated(String last_updated) {
            this.last_updated = last_updated;
        }

        public float getTemp_c() {
            return temp_c;
        }

        public void setTemp_c(float temp_c) {
            this.temp_c = temp_c;
        }

        public Condition getCondition() {
            return condition;
        }

        public void setCondition(Condition condition) {
            this.condition = condition;
        }

        public float getWind_kph() {
            return wind_kph;
        }

        public void setWind_kph(float wind_kph) {
            this.wind_kph = wind_kph;
        }

        public float getPressure_mb() {
            return pressure_mb;
        }

        public void setPressure_mb(float pressure_mb) {
            this.pressure_mb = pressure_mb;
        }

        public float getPrecip_mm() {
            return precip_mm;
        }

        public void setPrecip_mm(float precip_mm) {
            this.precip_mm = precip_mm;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public int getCloud() {
            return cloud;
        }

        public void setCloud(int cloud) {
            this.cloud = cloud;
        }

        public float getGust_kph() {
            return gust_kph;
        }

        public void setGust_kph(float gust_kph) {
            this.gust_kph = gust_kph;
        }

    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }


    public class Forecast{
        private List<Forecastday> forecastday;

        public List<Forecastday> getForecastday() {
            return forecastday;
        }

        public void setForecastday(List<Forecastday> forecastday) {
            this.forecastday = forecastday;
        }

        public class Forecastday{
            private String date;
            private Day day;
            private List<Hour> hour;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public class Day{
                private float maxtemp_c;
                private float mintemp_c;
                private float avgtemp_c;
                private float maxwind_kph;
                private float totalprecip_mm;
                private Condition condition;

                public float getMaxtemp_c() {
                    return maxtemp_c;
                }

                public void setMaxtemp_c(float maxtemp_c) {
                    this.maxtemp_c = maxtemp_c;
                }

                public float getMintemp_c() {
                    return mintemp_c;
                }

                public void setMintemp_c(float mintemp_c) {
                    this.mintemp_c = mintemp_c;
                }

                public float getAvgtemp_c() {
                    return avgtemp_c;
                }

                public void setAvgtemp_c(float avgtemp_c) {
                    this.avgtemp_c = avgtemp_c;
                }

                public float getMaxwind_kph() {
                    return maxwind_kph;
                }

                public void setMaxwind_kph(float maxwind_kph) {
                    this.maxwind_kph = maxwind_kph;
                }

                public float getTotalprecip_mm() {
                    return totalprecip_mm;
                }

                public void setTotalprecip_mm(float totalprecip_mm) {
                    this.totalprecip_mm = totalprecip_mm;
                }

                public Condition getCondition() {
                    return condition;
                }

                public void setCondition(Condition condition) {
                    this.condition = condition;
                }

                public class Condition{
                    private String text;
                    private String icon;

                    public String getText() {
                        return text;
                    }

                    public void setText(String text) {
                        this.text = text;
                    }

                    public String getIcon() {
                        return icon;
                    }

                    public void setIcon(String icon) {
                        this.icon = icon;
                    }
                }
            }

            public Day getDay() {
                return day;
            }

            public void setDay(Day day) {
                this.day = day;
            }

            public class Hour{
                private String time;
                private float temp_c;
                private Condition condition;
                private float wind_kph;
                private float precip_mm;
                private int humidity;
                private int cloud;

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public float getTemp_c() {
                    return temp_c;
                }

                public void setTemp_c(float temp_c) {
                    this.temp_c = temp_c;
                }

                public class Condition{
                    private String text;
                    private String icon;

                    public String getText() {
                        return text;
                    }

                    public void setText(String text) {
                        this.text = text;
                    }

                    public String getIcon() {
                        return icon;
                    }

                    public void setIcon(String icon) {
                        this.icon = icon;
                    }
                }

                public Condition getCondition() {
                    return condition;
                }

                public void setCondition(Condition condition) {
                    this.condition = condition;
                }

                public float getWind_kph() {
                    return wind_kph;
                }

                public void setWind_kph(float wind_kph) {
                    this.wind_kph = wind_kph;
                }

                public float getPrecip_mm() {
                    return precip_mm;
                }

                public void setPrecip_mm(float precip_mm) {
                    this.precip_mm = precip_mm;
                }

                public int getHumidity() {
                    return humidity;
                }

                public void setHumidity(int humidity) {
                    this.humidity = humidity;
                }

                public int getCloud() {
                    return cloud;
                }

                public void setCloud(int cloud) {
                    this.cloud = cloud;
                }
            }

            public List<Hour> getHour() {
                return hour;
            }

            public void setHour(List<Hour> hour) {
                this.hour = hour;
            }
        }
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }
}
