package com.task.player.helper;

import com.task.player.model.Player;
import com.task.player.validation.DateValidatorUsingLocalDate;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CSVHelper {
    public static String TYPE = "text/csv";
    public static int EMPTY_INT = 0;
    public static LocalDate EMPTY_DATE = null;


    public static boolean hasCSVFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

//    public static List<Player> csvToPlayers(InputStream is) {
//
//        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//             CSVParser csvParser = new CSVParser(fileReader,
//                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
//
//            List<Player> players = new ArrayList<Player>();
//
//            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
//
//            for (CSVRecord csvRecord : csvRecords) {
//                Player player = new Player(
//                        csvRecord.get(0),
//                        StringUtils.isNotBlank(csvRecord.get(1)) ? Integer.parseInt(csvRecord.get(1)) : EMPTY_INT,
//                        StringUtils.isNotBlank(csvRecord.get(2)) ? Integer.parseInt(csvRecord.get(2)) : EMPTY_INT,
//                        StringUtils.isNotBlank(csvRecord.get(3)) ? Integer.parseInt(csvRecord.get(3)) : EMPTY_INT,
//                        csvRecord.get(4),
//                        csvRecord.get(5),
//                        csvRecord.get(6),
//                        StringUtils.isNotBlank(csvRecord.get(7)) ? Integer.parseInt(csvRecord.get(7)) : EMPTY_INT,
//                        StringUtils.isNotBlank(csvRecord.get(8)) ? Integer.parseInt(csvRecord.get(8)) : EMPTY_INT,
//                        StringUtils.isNotBlank(csvRecord.get(9)) ? Integer.parseInt(csvRecord.get(9)) : EMPTY_INT,
//                        csvRecord.get(10),
//                        csvRecord.get(11),
//                        csvRecord.get(12),
//                        csvRecord.get(13),
//                        csvRecord.get(14),
//                        csvRecord.get(15),
//                        StringUtils.isNotBlank(csvRecord.get(16)) ? Integer.parseInt(csvRecord.get(16)) : EMPTY_INT,
//                        StringUtils.isNotBlank(csvRecord.get(17)) ? Integer.parseInt(csvRecord.get(17)) : EMPTY_INT,
//                        csvRecord.get(18),
//                        csvRecord.get(19),
//                        isValidDate(csvRecord.get(20)),
//                        isValidDate(csvRecord.get(21)),
//                        csvRecord.get(22),
//                        csvRecord.get(23)
//                );
//                players.add(player);
//
//                // System.out.println("insert line "+csvRecord+ " to the players list, record number: "+ csvRecord.getRecordNumber());
//            }
//            return players;
//
//        } catch (IOException e) {
//            throw new RuntimeException("fail to parse CSV file : " + e.getMessage());
//        } catch (NumberFormatException e) {
//            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
//        }
//
//
//    }

    public static Player csvToPlayer(CSVRecord csvRecord) {
        try {
            Player player = new Player(
                    csvRecord.get(0),
                    StringUtils.isNotBlank(csvRecord.get(1)) ? Integer.parseInt(csvRecord.get(1)) : EMPTY_INT,
                    StringUtils.isNotBlank(csvRecord.get(2)) ? Integer.parseInt(csvRecord.get(2)) : EMPTY_INT,
                    StringUtils.isNotBlank(csvRecord.get(3)) ? Integer.parseInt(csvRecord.get(3)) : EMPTY_INT,
                    csvRecord.get(4),
                    csvRecord.get(5),
                    csvRecord.get(6),
                    StringUtils.isNotBlank(csvRecord.get(7)) ? Integer.parseInt(csvRecord.get(7)) : EMPTY_INT,
                    StringUtils.isNotBlank(csvRecord.get(8)) ? Integer.parseInt(csvRecord.get(8)) : EMPTY_INT,
                    StringUtils.isNotBlank(csvRecord.get(9)) ? Integer.parseInt(csvRecord.get(9)) : EMPTY_INT,
                    csvRecord.get(10),
                    csvRecord.get(11),
                    csvRecord.get(12),
                    csvRecord.get(13),
                    csvRecord.get(14),
                    csvRecord.get(15),
                    StringUtils.isNotBlank(csvRecord.get(16)) ? Integer.parseInt(csvRecord.get(16)) : EMPTY_INT,
                    StringUtils.isNotBlank(csvRecord.get(17)) ? Integer.parseInt(csvRecord.get(17)) : EMPTY_INT,
                    csvRecord.get(18),
                    csvRecord.get(19),
                    isValidDate(csvRecord.get(20)),
                    isValidDate(csvRecord.get(21)),
                    csvRecord.get(22),
                    csvRecord.get(23)
            );
            return player;

        } catch (NumberFormatException e) {
            System.out.println("failed to parse CSV record: " + csvRecord);
            return null;
            //throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }


    }

    private static LocalDate isValidDate(String date) {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateValidatorUsingLocalDate validator = new DateValidatorUsingLocalDate(dateFormatter);
        if (date.isBlank()) {
            return EMPTY_DATE;
        }
        if (validator.isValid(date)) {
            return LocalDate.parse(date, dateFormatter);
        } else {
            throw new RuntimeException("can't convert the value of:  " + date + " to date");
        }

    }

}