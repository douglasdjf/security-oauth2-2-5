package com.sprincsecurity.exceptionHandler;

import com.sprincsecurity.domain.exception.EmailNaoValidoException;
import com.sprincsecurity.domain.exception.UsuarioNaoEncontratoException;
import com.sprincsecurity.resource.exception.ResourceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintDeclarationException;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * Captura exceções de respostas de entidades do response
 * @author douglas
 *
 */

@ControllerAdvice
public class SecurityExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        StandardError standardError = StandardError.builder()
                                        .status(HttpStatus.BAD_REQUEST.value())
                                        .path(((ServletWebRequest)request).getRequest().getRequestURI())
                                        .timestamp(System.currentTimeMillis())
                                        .error(ex.getMessage())
                                        .message("Dados inválidos")
                                        .erros(criarListaFieldErros(ex.getBindingResult()))
                                        .build();

       return handleExceptionInternal(ex,standardError,headers,HttpStatus.BAD_REQUEST,request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        StandardError standardError = StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .path(((ServletWebRequest)request).getRequest().getRequestURL().toString())
                .timestamp(System.currentTimeMillis())
                .error(ex.getCause().getMessage().toString())
                .message(ex.getMessage())
                .build();

        return super.handleExceptionInternal(ex,standardError, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(UsuarioNaoEncontratoException.class)
    public ResponseEntity<StandardError> objectNotFound(UsuarioNaoEncontratoException ex, HttpServletRequest request){
        StandardError standardError = StandardError.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .path(((ServletWebRequest)request).getRequest().getRequestURL().toString())
                .timestamp(System.currentTimeMillis())
                .error(ex.getCause().getMessage().toString())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> contraintValidation(ConstraintViolationException ex, HttpServletRequest request){
        StandardError standardError = StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .path(((ServletWebRequest)request).getRequest().getRequestURL().toString())
                .timestamp(System.currentTimeMillis())
                .error(ex.getCause().getMessage().toString())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);

    }

    @ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
    public ResponseEntity<StandardError> contrainViolation(ConstraintViolationException ex, HttpServletRequest request){
        StandardError standardError = StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .path(((ServletWebRequest)request).getRequest().getRequestURL().toString())
                .timestamp(System.currentTimeMillis())
                .error(ex.getCause().getMessage().toString())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);

    }

    @ExceptionHandler(ConstraintDeclarationException.class)
    public ResponseEntity<StandardError> contrainViolationTypeException(ConstraintViolationException ex, HttpServletRequest request){
        StandardError standardError = StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .path(((ServletWebRequest)request).getRequest().getRequestURL().toString())
                .timestamp(System.currentTimeMillis())
                .error(ex.getCause().getMessage().toString())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);

    }


    @ExceptionHandler(EmailNaoValidoException.class)
    public ResponseEntity<StandardError> emailInvalido(EmailNaoValidoException ex, HttpServletRequest request){

        StandardError standardError = StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .path(((ServletWebRequest)request).getRequest().getRequestURL().toString())
                .timestamp(System.currentTimeMillis())
                .error(ex.getCause().getMessage().toString())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);

    }

    @ExceptionHandler(ResourceException.class)
    public ResponseEntity<StandardError> resourceInvalido(ResourceException ex, HttpServletRequest request){

        StandardError standardError = StandardError.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .path(((ServletWebRequest)request).getRequest().getRequestURL().toString())
                .timestamp(System.currentTimeMillis())
                .error(ex.getCause().getMessage().toString())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);

    }











    private List<FieldMessage> criarListaFieldErros(BindingResult bindingResult){
        List<FieldMessage> erros = new ArrayList<>();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String field = fieldError.getField();
            String mensage =   fieldError.getField() + " "+ fieldError.getDefaultMessage()  ;
            erros.add(new FieldMessage(field, mensage));
        }
        return erros;
    }
}
