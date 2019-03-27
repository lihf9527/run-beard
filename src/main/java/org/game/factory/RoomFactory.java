package org.game.factory;

import org.game.domain.Player;
import org.game.domain.Room;

public class RoomFactory {

    public static Room createRoom(Player player) {
        Room room = new Room();
        return room;
    }
}
