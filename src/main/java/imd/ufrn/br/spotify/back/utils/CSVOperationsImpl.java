package imd.ufrn.br.spotify.back.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class CSVOperationsImpl implements ICSVOperations {
    private  final String CSV_FILE_NAME;
    final private String COMMA_DELIMITER = ",";

    public CSVOperationsImpl(String CSV_FILE_NAME) {
        this.CSV_FILE_NAME = CSV_FILE_NAME;
    }

    public List<String[]> readFile() {
        List<String[]> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] values = line.split(COMMA_DELIMITER);

//                List<String> list = Arrays.asList(values);

                records.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
//            throw new RuntimeException(e);
        }

        return records;
    }

    public void writeFile(List<String[]> dataLines) {
        File csvOutputFile = new File(CSV_FILE_NAME);

        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(COMMA_DELIMITER));
    }

     private String escapeSpecialCharacters(String data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data cannot be null");
        }
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

    public static void main(String[] args) throws IOException {
        CSVOperationsImpl csvApi = new CSVOperationsImpl("/home/luizgustavoou/Documentos/study_java/spotify/db/users.txt");

        List<List<String>> teste = csvApi.readFile().stream().map(Arrays::asList).toList();

        System.out.println(teste);

        List<String[]> write = new ArrayList<>();

        write.add(new String[]{UUID.randomUUID().toString(), "luiz", "123", "1"});

        csvApi.writeFile(write);

    }

}