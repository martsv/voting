package ru.martsv.voting.util.exception;

/**
 * mart
 * 20.08.2016
 */
public class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static void checkNotFoundWithId(boolean found, int id) {
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFoundWithId(T object, int id) {
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFound(T object, String msg) {
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) {
        if (!found) {
            throw new NotFoundException("Not found entity with " + msg);
        }
    }

    public static <T> T checkNotFoundParentWithId(T object, int id) {
        if (object == null) {
            throw new NotFoundException("Not found parent entity with id=" + id);
        }
        return object;
    }
}
