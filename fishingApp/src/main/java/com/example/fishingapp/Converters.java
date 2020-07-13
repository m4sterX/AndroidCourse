package com.example.fishingapp;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.fishingapp.entity.Location;

import java.util.Locale;

public class Converters {

    private static final String LOCATION_FORMAT = "%f;%f";
    private static final String LOCATION_FORMAT_SEPARATOR = ";";
    private static Locale DEF_LOCALE = Locale.getDefault();

    @TypeConverter
    public static String locationToString(Location location) {
        return String.format(DEF_LOCALE, LOCATION_FORMAT, location.getLatitude(), location.getLongitude());
    }

    @TypeConverter
    public static Location stringToLocation(String location) {
        String[] locArgs = location.split(LOCATION_FORMAT_SEPARATOR);
        return new Location(Double.parseDouble(locArgs[0]), Double.parseDouble(locArgs[1]));
    }

}