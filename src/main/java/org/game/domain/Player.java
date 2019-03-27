package org.game.domain;

import lombok.Data;

import java.util.List;

@Data
public class Player {
    private String name;
    private List<Card> cards;

    public List<Card> removeCard(List<Card> cards) {
        this.cards.removeAll(cards);
        return cards;
    }
}
