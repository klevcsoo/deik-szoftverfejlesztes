package util;

public class InvalidMoveDirectionException extends RuntimeException {

    public InvalidMoveDirectionException() {
        super("The ball tried to move in an invalid direction.");
    }
}
