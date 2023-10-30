package com.task.player.validation;

import com.task.player.helper.CSVHelper;
import com.task.player.model.Player;
import com.task.player.utils.PlayerUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
public class PlayerValidationUnitTest {

    @Autowired
    private EntityManager entityManager;

    private Player player;

    @BeforeEach
    public void init() {
        // Create a sample player
        player = PlayerUtil.createPlayerForTestByID("Aa1");
    }

    @Test
    public void testInvalidBats() {
        player.setBats("WW");
        Assertions.assertThrows(IllegalArgumentException.class, () -> entityManager.persist(player));

    }
    @Test
    public void testInvalidBirthYear() {
        player.setBirthYear(2033);
        Assertions.assertThrows(IllegalArgumentException.class, () -> entityManager.persist(player));

    }

    @Test
    public void testInvalidBirthMonth() {
        player.setBirthMonth(14);
        Assertions.assertThrows(IllegalArgumentException.class, () -> entityManager.persist(player));

    }

    @Test
    public void testInvalidThrows_() {
        player.setThrows_("A");
        Assertions.assertThrows(IllegalArgumentException.class, () -> entityManager.persist(player));

    }

}
