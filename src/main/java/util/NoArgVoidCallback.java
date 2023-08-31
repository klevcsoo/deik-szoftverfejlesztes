package util;

/**
 * The NoArgVoidCallback interface represents a callback function with no arguments and no return
 * value. It is intended to be used as a functional interface for defining callback behavior.
 */
@FunctionalInterface
public interface NoArgVoidCallback {

    /**
     * Executes the callback function.
     */
    void call();
}
