package com.task.player.utils;

import com.task.player.model.Player;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;

public class PlayerUtil {

    public static Player createPlayerForTestByID(String id){
        Player player=new Player();
        player.setPlayerID(id);
        player.setBats("R");
        player.setBbrefID("beardda01");
        player.setBirthCity("Atlanta");
        player.setBirthCountry("USA");
        player.setBirthDay(1);
        player.setBirthMonth(1);
        player.setBirthState("GA");
        player.setBirthYear(1980);
        player.setDeathCity("");
        player.setDeathCountry("");
        player.setDeathYear(1999);
        player.setDeathMonth(12);
        player.setDeathState("GA");
        player.setDeathYear(1999);
        player.setDebut(LocalDate.parse("2016-08-16"));
        player.setFinalGame(LocalDate.parse("2016-08-18"));
        player.setHeight(77);
        player.setNameFirst("Adi");
        player.setNameGiven("adia");
        player.setNameLast("Azlan");
        player.setRetroID("beard001");
        player.setThrows_("R");
        player.setWeight(190);

        return  player;

    }

    public static CSVRecord getCSVRecord(String csvData) throws IOException {
        CSVFormat csvFormat = CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreHeaderCase()
                .withTrim();
//        String csvData = "playerID,birthYear,birthMonth,birthDay,birthCountry,birthState,birthCity,deathYear,deathMonth,deathDay,deathCountry,deathState,deathCity,nameFirst,nameLast,nameGiven,weight,height,bats,throws,debut,finalGame,retroID,bbrefID\n" +
//                "aardsda01,1981,12,27,USA,CO,Denver,,,,,,,David,Aardsma,David Allan,215,75,R,R,2004-04-06,2015-08-23,aardd001,aardsda01\n";
        StringReader stringReader = new StringReader(csvData);
        Iterable<CSVRecord> csvRecords = csvFormat.parse(stringReader);
        return csvRecords.iterator().next();

    }

}
