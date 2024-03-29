package it.interno.luoghi.exception;

public class BlobConversionException extends RuntimeException{

    public BlobConversionException(){
        super("Si è verificato un errore nella conversione del BLOB");
    }

}
