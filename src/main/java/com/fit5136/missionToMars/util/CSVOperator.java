package com.fit5136.missionToMars.util;

import com.opencsv.*;


import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.List;




public class CSVOperator {
    public static List<String[]> readAll(String fileName, int escape){
        List<String[]> list = new ArrayList<>();

        try {
            Reader reader = Files.newBufferedReader(Paths.get(
                    ClassLoader.getSystemResource(fileName).toURI()));
            CSVParser parser = new CSVParserBuilder()
                    .withSeparator(',')
                    //.withIgnoreQuotations(true)
                    .build();

            CSVReader csvReader = new CSVReaderBuilder(reader)
                    .withSkipLines(escape)
                    .withCSVParser(parser)
                    .build();

            list = csvReader.readAll();
            reader.close();
            csvReader.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }



    public static void write(String filename, List<String[]> list){
        try {
            Path path = Paths.get(
                    ClassLoader.getSystemResource(filename).toURI());
            CSVWriter writer = new CSVWriter(new FileWriter(path.toString()));
            writer.writeAll(list);
            writer.flush();

            writer.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
