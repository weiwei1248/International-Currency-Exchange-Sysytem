package ca.uwindsor.ices.exception;


public class SystemException extends RuntimeException {

    private static final long serialVersionUID = -1732859138523645390L;

    public SystemException(String message) {
        super(message);
    }
}
