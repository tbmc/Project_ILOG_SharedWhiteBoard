package fr.tbmc.boards.changes.types;

/**
 * Created by tbmc on 04/05/2017.
 * Enum which represents the Type of Change
 */
public enum Type {

    LINE,
    RECTANGLE,
    CIRCLE,

    PENCIL,
    TEXT,

    CLEAR,
    UNDO,
    RED0
    ;

    /**
     * Get the Type object corresponding to String in parameter
     * It is the same as {@link Type#valueOf(String)} but type String
     * doesn't have to be in uppercase
     * @param type name of a type, but not case sensitive
     * @return Type corresponding to the input String
     */
    public static Type getTypeFromString(String type) {
        String upperCaseType = type.toUpperCase();
        return Type.valueOf(upperCaseType);
    }

}