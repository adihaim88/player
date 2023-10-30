package com.task.player.validation;

import com.task.player.model.Player;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


public class PlayerValidator {

    @PrePersist
    @PreUpdate
    public void validatePlayer(Player player) {
        validateBirthYear(player);
        validateBirthMonth(player);
        validateBats(player);
        validateThrows_(player);

    }

    private void validateThrows_(Player player) {
        String thr = player.getThrows_();
        if (thr.equals("S") || thr.equals("R") || thr.equals("L")|| thr.isBlank()) {
            return;
        } else {
            throw new IllegalArgumentException("invalid bats values: B,L,R, . The value is "+thr);
        }
    }

    private void validateBats(Player player) {
        String bats = player.getBats();
        if (bats.equals("R") || bats.equals("B") || bats.equals("L")|| bats.isBlank()) {
            return;
        } else {
            throw new IllegalArgumentException("invalid bats values: B,L,R, . The value is "+bats);
        }
    }

    private void validateBirthYear(Player player) {
        int birthYear = player.getBirthYear();

        if (birthYear > 2023) {
            throw new IllegalArgumentException("birthYear should not be greater than 2023");
        }

    }

    private void validateBirthMonth(Player player) {
        int birthMonth = player.getBirthMonth();

        if (birthMonth < 0 || (birthMonth >13)) {
            throw new IllegalArgumentException("The birthMonth is invalid: " + birthMonth);
        }

    }


}