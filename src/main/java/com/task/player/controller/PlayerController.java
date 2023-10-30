package com.task.player.controller;


import com.task.player.helper.CSVHelper;
import com.task.player.model.Player;
import com.task.player.service.PlayerService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PlayerController {

    private static final Logger log = LoggerFactory.getLogger(PlayerController.class);

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @PostMapping("upload")
    @ApiOperation(value = " access all players", response = Player.class, code = 200)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            try (InputStream inputStream = file.getInputStream()) {
                playerService.saveAll(inputStream);
                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body("\" message \": \" " + message + " \"");
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("\" message \": \" " + message + " \"");
            }
        } else {
            message = "Please upload a CSV file!";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("\" message \": \" " + message + " \"");
        }
    }


    @GetMapping("/players")
    @ApiOperation(value = "Get all players", response = Player.class, code = 200)
    ResponseEntity<List<Player>> all() {
        return new ResponseEntity<>(playerService.getAllPlayers(), HttpStatus.OK);
    }

    @GetMapping("/players/{id}")
    @ApiOperation(value = "Get player by ID", response = Player.class, code = 200)
    public ResponseEntity<Player> getPlayerById(@PathVariable String id) {
        return new ResponseEntity<>(playerService.getPlayerById(id), HttpStatus.OK);

    }


}
