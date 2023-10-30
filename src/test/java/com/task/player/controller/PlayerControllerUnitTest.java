package com.task.player.controller;


import com.task.player.model.Player;
import com.task.player.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@ContextConfiguration()
@AutoConfigureWebClient
@WebMvcTest(value = PlayerController.class)
public class PlayerControllerUnitTest {

    @MockBean
    PlayerService playerService;
    @Autowired
    private MockMvc mockMvc;


    @Test
    void whenGetPlayerByIdReturn200OK() throws Exception {
        when(playerService.getPlayerById("Aa1")).thenReturn(new Player());
        mockMvc.perform(get("/api/players/Aa1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void whenGetPlayersReturn200OK() throws Exception {
        when(playerService.getAllPlayers()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/api/players").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
