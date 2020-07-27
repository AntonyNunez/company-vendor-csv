package com.exercise.companyvendor.util;

import com.exercise.companyvendor.dto.CompanyData;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class CsvUtil {

    final static char COMMA = ',';
    final static Integer BUFFERED_OUTPUT_STREAM_SIZE = 1024;

    /**
     * Generic method to read files.
     *
     * @param classType desired class to read.
     * @param file      file to read.
     * @param <T>       generic class.
     * @return the object list with data.
     * @throws IOException exception checker to throw errors while operating with files.
     */
    public static <T> List<T> genericReader(Class<T> classType, File file) throws IOException {
        // We create a schema based on the first row of the file
        CsvSchema schema = CsvSchema.emptySchema().withHeader();
        CsvMapper mapper = new CsvMapper();
        MappingIterator<T> values = mapper.readerFor(classType).with(schema).readValues(file);
        return values.readAll();
    }

    /**
     * Method to generate the desired output file.
     *
     * @param companyList the objects list to convert to a CSV file.
     * @throws IOException exception checker to throw errors while operating with files.
     */
    public static void companyWriter(List<CompanyData> companyList, String filePath) throws IOException {
        // We create the mapper and Company schema
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(CompanyData.class).withHeader().withColumnSeparator(COMMA);

        // We create the output writer
        ObjectWriter objectWriter = mapper.writer(schema);
        File file = new File(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream, BUFFERED_OUTPUT_STREAM_SIZE);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(bufferedOutputStream, StandardCharsets.UTF_8);
        objectWriter.writeValue(outputStreamWriter, companyList);
    }
}
