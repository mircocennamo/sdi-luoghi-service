package it.interno.luoghi.exception;


public class NoDataException extends Exception {

    private static final long serialVersionUID = -2704298221329055416L;
    private String message ;


    public NoDataException(String message) {
        super(message);
        this.message = message ;
    }

    @Override
    public String getMessage(){
        return this.message ;
    }
}
