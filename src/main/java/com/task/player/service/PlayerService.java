package com.task.player.service;

import com.task.player.exception.PlayerNotFoundException;
import com.task.player.exception.PlayersNotExistException;
import com.task.player.helper.CSVHelper;
import com.task.player.model.Player;
import com.task.player.repository.PlayerRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }


    public List<Player> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        if (players.isEmpty()) {
            throw new PlayersNotExistException();
        }
        return players;
    }


    public Player getPlayerById(String id) {

        return playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException(id));
    }

    //@PostConstruct
    public void saveAll(InputStream file) throws IOException {
        BufferedReader fileReader = null;
        try {
            fileReader = new BufferedReader(new InputStreamReader(file, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        CSVParser csvParser = new CSVParser(fileReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
        {
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Player player = CSVHelper.csvToPlayer(csvRecord);
                if (player != null) {
                    playerRepository.save(player);
                    System.out.println("insert line " + csvRecord + " to the H2, record number: " + csvRecord.getRecordNumber());

                }
            }

            // List<Player> players = CSVHelper.csvToPlayers(file);

            //File initialFile = new File("src/main/resources/smallplayer.csv");

            // Save the parsed data to the H2 database
            // playerRepository.saveAll(players);

        }


    }
}
