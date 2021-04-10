package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String pathToCsv = "/Users/arronbrady/Desktop/Fedex Revised.tsv";
        String pathToCsvToWrite = "/Users/arronbrady/Desktop/Fedex-New.csv";

        CSVReader csvReader = new CSVReader(pathToCsv, pathToCsvToWrite);



    }
}
