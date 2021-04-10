package com.company;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    private String row;
    private List<String> csvFileFromReader = new ArrayList<>();
    private static final int SHORT_DESCRIPTION_LIMIT = 74;
    private static int LONG_DESCRIPTION_LIMIT = 450;

    public CSVReader(String pathToCsv, String pathToCsvToWrite) {
        readCVFile(pathToCsv, pathToCsvToWrite);
    }


    public void readCVFile(String pathToCsv, String pathToCsvToWrite) {

        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(pathToCsv));

            while ((row = csvReader.readLine()) != null) {

                String[] data = row.split("\t");

                if (data[23].equals("0")) {
                    data[23] = "1";

                    if (data[1].length() > SHORT_DESCRIPTION_LIMIT) {
                        data[1] = changeStringLength(data[1], SHORT_DESCRIPTION_LIMIT);
                    }

                    if (data[2].length() > LONG_DESCRIPTION_LIMIT) {
                        data[2] = changeStringLength(data[2], LONG_DESCRIPTION_LIMIT);
                    }

                    row = changeRow(data);
                    csvFileFromReader.add(row);
                }
            }


            writeCSVFile(csvFileFromReader, pathToCsvToWrite);
            csvReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }


    public String changeRow(String[] strArray) {

        StringBuffer sb = new StringBuffer();
        String returnedString;

        for(int i = 0; i < strArray.length; i++) {

            sb.append(strArray[i] + ",");
        }

        returnedString = sb.toString();
        return returnedString;
    }


    public void writeCSVFile(List<String> csvFile, String pathToCsvToWrite) {

        try {
            FileWriter fileWriter = new FileWriter(pathToCsvToWrite);
            for(String string : csvFile) {

                fileWriter.append(string);
                fileWriter.append("\n");

            }
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String changeStringLength(String stringToChange, int stringLimit) {
        return stringToChange.substring(0, Math.min(stringToChange.length(), stringLimit));
    }

}
