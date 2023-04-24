package com.epam.mjc.io;

import java.io.File;
import java.io.IOException;



public class FileReader {

    public Profile getDataFromFile(File file) {

        String str = "";
        try (java.io.FileReader fin = new java.io.FileReader(file.getPath())){
            int i;

            do{
                i = fin.read();
                if(i != -1) {
                    str += (char)i;
                }

            }while ( i != -1);
        }catch (IOException e) {
            e.printStackTrace();
        }
        String[] values = parseString(str, ':');

        return new Profile(values[0], Integer.parseInt(values[1]), values[2], Long.parseLong(values[3]));
    }

    private int countSeparator(String str, char sep){
        int count=0;
        for (int i = 0; i < str.length(); i++){
            if (str.charAt(i) == sep)
                count++;
       }
        return count;
    }

    private String[] parseString(String str, char sep){
        String[] values = new String[countSeparator(str, sep)];
        int arrCounter = 0;
        String currentStr = str;
        while (currentStr.indexOf(sep) != -1){
            values[arrCounter++] = currentStr.substring(currentStr.indexOf(':')+1, currentStr.indexOf('\n')).trim();
            currentStr= currentStr.substring(currentStr.indexOf('\n')+1);
        };
        return values;
    }

}
