package it.interno.luoghi.exception;

import lombok.Getter;

@Getter
public class ValidationFieldException extends  Exception {

	private static final long serialVersionUID = 7979387409065128758L;
	private final String message ;
    private String param ;

    public ValidationFieldException(String message, String param) {
        super(message);
        this.param = param ;
        this.message = message;
    }
}
