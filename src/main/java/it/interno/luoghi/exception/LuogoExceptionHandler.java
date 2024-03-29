package it.interno.luoghi.exception;

import it.interno.luoghi.dto.ResponseDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class LuogoExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;
    
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String message = messageSource.getMessage("errore.generico", null, LocaleContextHolder.getLocale());
        ResponseDto responseDto = ResponseDto.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value()).error(message).build();

        return ResponseEntity.internalServerError().body(responseDto);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        String message = messageSource.getMessage("errore.parametro.nonPresenteInRequest", new Object[]{ex.getParameterName()}, LocaleContextHolder.getLocale());
        ResponseDto responseDto = ResponseDto.builder().code(HttpStatus.BAD_REQUEST.value()).error(message).build();

        return ResponseEntity.badRequest().body(responseDto);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> collect = ex.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return ResponseEntity.badRequest().body(ResponseDto.builder().code(HttpStatus.BAD_REQUEST.value()).error(collect.get(0)).build());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final ResponseEntity<ResponseDto> handleValidationsFields(ConstraintViolationException cve) {

        ResponseDto<?> responseDto = null;
        for (ConstraintViolation<?> violation : cve.getConstraintViolations())
            responseDto = ResponseDto.builder().code(HttpStatus.BAD_REQUEST.value()).error(violation.getMessage()).build();

        return ResponseEntity.badRequest().body(responseDto);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<ResponseDto> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException mame){

        String message = messageSource.getMessage("errore.valore.campo",
                new Object[]{mame.getName(), "".equals(mame.getValue()) ? "''" : "'" + mame.getValue() + "'"}, LocaleContextHolder.getLocale());
        ResponseDto responseDto = ResponseDto.builder().code(HttpStatus.BAD_REQUEST.value()).error(message).build();

        return ResponseEntity.badRequest().body(responseDto);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ResponseDto<Object> responseDto = ResponseDto.builder().code(HttpStatus.BAD_REQUEST.value()).body(ex.getCause().getMessage()).build();
        return ResponseEntity.badRequest().body(responseDto);
    }

    @ExceptionHandler(ValidationFieldException.class)
    public final ResponseEntity<ResponseDto<Object>> handleValidationFieldException(ValidationFieldException ex) {

        String message = messageSource.getMessage(ex.getMessage(),
                new Object[]{ex.getParam() != null ? ex.getParam() : ""},
                LocaleContextHolder.getLocale()) ;
        ResponseDto<Object> responseDto = ResponseDto.builder().code(601).error(message).build();

        return ResponseEntity.badRequest().body(responseDto);
    }


    @ExceptionHandler(NoDataException.class)
    public final ResponseEntity handleNoData(NoDataException ex) {

        String message = messageSource.getMessage(ex.getMessage(), null, LocaleContextHolder.getLocale());
        ResponseDto<String> responseDto = ResponseDto.<String>builder().code(HttpStatus.NOT_FOUND.value()).error(message).build();

        return new ResponseEntity(responseDto, HttpStatus.NOT_FOUND);
    }
    
	@ExceptionHandler(IllegalStateException.class)
	public final ResponseEntity<ResponseDto<Object>> handleIllegalState(IllegalStateException ex) {

		String message = messageSource.getMessage(ex.getMessage(), new Object[] { "" },
				LocaleContextHolder.getLocale());
		ResponseDto<Object> responseDto = ResponseDto.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.error(message).build();

		return ResponseEntity.badRequest().body(responseDto);
	}
	
	@ExceptionHandler(SQLException.class)
	public final ResponseEntity<ResponseDto<Object>> handleSQLException(SQLException ex) {

		String message = messageSource.getMessage(ex.getMessage(), new Object[] { "" },
				LocaleContextHolder.getLocale());
		ResponseDto<Object> responseDto = ResponseDto.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.error(message).build();

		return ResponseEntity.badRequest().body(responseDto);
	}
	@ExceptionHandler(InvalidDataAccessResourceUsageException.class)
	public final ResponseEntity<ResponseDto<Object>> handleInvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException ex) {

		String message = messageSource.getMessage(ex.getMessage(), new Object[] { "" },
				LocaleContextHolder.getLocale());
		ResponseDto<Object> responseDto = ResponseDto.builder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.error(message).build();

		return ResponseEntity.badRequest().body(responseDto);
	}

    @ExceptionHandler(BlobConversionException.class)
    public final ResponseDto<Object> blobConversionException(BlobConversionException ex){
        return ResponseDto.<Object>builder().code(HttpStatus.UNPROCESSABLE_ENTITY.value()).error(ex.getMessage()).build();
    }
}
