package com.task.player.component;


import com.task.player.helper.CSVHelper;
import com.task.player.model.Player;
import com.task.player.utils.PlayerUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@SpringBootTest
public class CSVHelpeUnitTest {

    @Test
    public void testHasCSVFormatWithCSVFile() {
        MultipartFile csvFile = createCSVFile();
        Assertions.assertTrue(CSVHelper.hasCSVFormat(csvFile));
    }

    @Test
    public void testCsvToPlayer_Valid() throws IOException {

        String csvData = "playerID,birthYear,birthMonth,birthDay,birthCountry,birthState,birthCity,deathYear,deathMonth,deathDay,deathCountry,deathState,deathCity,nameFirst,nameLast,nameGiven,weight,height,bats,throws,debut,finalGame,retroID,bbrefID\n" +
                "aardsda01,1981,12,27,USA,CO,Denver,,,,,,,David,Aardsma,David Allan,215,75,R,R,2004-04-06,2015-08-23,aardd001,aardsda01\n";


        Player player = CSVHelper.csvToPlayer(PlayerUtil.getCSVRecord(csvData));

        Assertions.assertEquals("aardsda01", player.getPlayerID());
        Assertions.assertEquals("R", player.getBats());
        Assertions.assertEquals(LocalDate.parse("2004-04-06"), player.getDebut());
        Assertions.assertEquals(215, player.getWeight());


    }


    @Test
    public void invalidDateInCSV() throws IOException {

        String customCSVData = "playerID,birthYear,birthMonth,birthDay,birthCountry,birthState,birthCity,deathYear,deathMonth,deathDay,deathCountry,deathState,deathCity,nameFirst,nameLast,nameGiven,weight,height,bats,throws,debut,finalGame,retroID,bbrefID\n" +
                "aardsda01,1981,12,27,USA,CO,Denver,,,,,,,David,Aardsma,David Allan,215,75,R,R,2004-44-06,2015-08-23,aardd001,aardsda01\n";

        Assertions.assertThrows(RuntimeException.class, () -> CSVHelper.csvToPlayer(PlayerUtil.getCSVRecord(customCSVData)));

    }


    private MultipartFile createCSVFile() {
        String customCSVData = "playerID,birthYear,birthMonth,birthDay,birthCountry,birthState,birthCity,deathYear,deathMonth,deathDay,deathCountry,deathState,deathCity,nameFirst,nameLast,nameGiven,weight,height,bats,throws,debut,finalGame,retroID,bbrefID\n" +
                "aardsda01,1981,12,27,USA,CO,Denver,,,,,,,David,Aardsma,David Allan,215,75,R,R,2004-04-06,2015-08-23,aardd001,aardsda01\n";
        return new MockMultipartFile("file", "test.csv", "text/csv", customCSVData.getBytes());
    }


}
