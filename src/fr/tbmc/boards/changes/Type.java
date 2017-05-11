package fr.tbmc.boards.changes;

/**
 * Created by tbmc on 04/05/2017.
 */

public enum Type {

    LINE,
    RECTANGLE,
    CIRCLE,

    CLEAR,
    UNDO,
    RED0
    ;

    public static Type getType(String type) {
        return Type.valueOf(type);
    }

}