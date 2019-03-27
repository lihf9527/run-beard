package org.game.domain;

import lombok.Data;

import java.util.List;

@Data
public class Room {
    private String name;
    private List<Player> players;

    public void addPlayer(Player player) {
        players.add(player);
    }
}
