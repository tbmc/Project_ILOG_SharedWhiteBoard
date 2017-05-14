package fr.tbmc.boards.changes.types;

/**
 * Created by tbmc on 04/05/2017.
 */

public enum Type {

    LINE,
    RECTANGLE,
    CIRCLE,

    PENCIL,

    CLEAR,
    UNDO,
    REDO
    ;

    public static Type getTypeFromString(String type) {
        String upperCaseType = type.toUpperCase();
        return Type.valueOf(upperCaseType);
    }

}