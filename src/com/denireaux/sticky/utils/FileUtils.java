package com.denireaux.sticky.utils;

import java.time.LocalDateTime;

public class FileUtils {

    public static String buildFileName(String outDirectory, String filePrefix) {
        LocalDateTime localDateTime = LocalDateTime.now();
        String localDateTimeAsString = localDateTime.toString();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(outDirectory);
        stringBuilder.append(filePrefix);
        stringBuilder.append("_");
        stringBuilder.append(localDateTimeAsString);
        stringBuilder.append(".png");

        return stringBuilder.toString();
    }
    
}
