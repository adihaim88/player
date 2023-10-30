package com.task.player.exception;

public class PlayerNotFoundException extends RuntimeException {
    public PlayerNotFoundException(String id) {
        super("Player not found with ID: " + id);
    }
}