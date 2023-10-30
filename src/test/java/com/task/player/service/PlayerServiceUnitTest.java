package com.task.player.service;

import com.task.player.exception.PlayerNotFoundException;
import com.task.player.model.Player;
import com.task.player.repository.PlayerRepository;
import com.task.player.utils.PlayerUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class PlayerServiceUnitTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    private Player player;

    @BeforeEach
    public void init() {
        // Create a sample player
        player = PlayerUtil.createPlayerForTestByID("Aa1");
    }

    @Test
    public void testGetPlayerById() {
        Mockito.when(playerRepository.findById("Aa1")).thenReturn(Optional.of(player));

        Optional<Player> result = Optional.ofNullable(playerService.getPlayerById("Aa1"));

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Aa1", result.get().getPlayerID());
    }

    @Test
    public void testGetPlayerById_NotFound() {
        // Mock the behavior of the repository when a player is not found
        Mockito.when(playerRepository.findById("NotFound")).thenReturn(Optional.empty());
        Assertions.assertThrows(PlayerNotFoundException.class, () -> Optional.ofNullable(playerService.getPlayerById("NotFound")));
    }

    @Test
    public void testGetPlayers() {

        List<Player> players = new ArrayList<>();
        players.add(PlayerUtil.createPlayerForTestByID("Aa1"));
        players.add(PlayerUtil.createPlayerForTestByID("Aa2"));

        // Mock the behavior of the repository
        Mockito.when(playerRepository.findAll()).thenReturn(players);

        List<Player> result = playerService.getAllPlayers();

        Assertions.assertEquals(2, result.size());
    }


    @Test
    public void testSaveAll() throws IOException {
        // Create a sample CSV file content as an InputStream
        String csvContent = "playerID,birthYear,birthMonth,birthDay,birthCountry,birthState,birthCity,deathYear,deathMonth,deathDay,deathCountry,deathState,deathCity,nameFirst,nameLast,nameGiven,weight,height,bats,throws,debut,finalGame,retroID,bbrefID\n";
        csvContent += "aardsda01,1981,12,27,USA,CO,Denver,,,,,,,David,Aardsma,David Allan,215,75,R,R,2004-04-06,2015-08-23,aardd001,aardsda01\n";
        csvContent += "aardsda02,1981,12,27,USA,CO,Denver,,,,,,,David,Aardsma,David Allan,215,75,R,R,2004-04-06,2015-08-23,aardd001,aardsda01\\n\n";

        InputStream inputStream = new ByteArrayInputStream(csvContent.getBytes());

        when(playerRepository.save(any(Player.class))).thenReturn(null);
        playerService.saveAll(inputStream);

        // Verify that the save method was called twice
        verify(playerRepository, times(2)).save(any(Player.class));
    }

}
