package com.task.player.exception;

public class PlayersNotExistException extends RuntimeException {
    public PlayersNotExistException() {
        super("There are no players in the system");
    }
}
