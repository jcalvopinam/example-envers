/*
 * MIT License
 *
 * Copyright (c) 2024 JUAN CALVOPINA M
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.jcalvopinam.exception;

import com.jcalvopinam.dto.ExceptionResponseDTO;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

/**
 * @author Juan Calvopina
 */
@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String REQUIRED_REQUEST_BODY_IS_MISSING = "Required request body is missing";

    /**
     * {@inheritDoc}
     */
    private static ExceptionResponseDTO getExceptionResponseDTO(final String exceptionMessage,
                                                                final String exceptionName,
                                                                final int httpStatusCode) {
        return ExceptionResponseDTO.builder()
                                   .message(exceptionMessage)
                                   .type(exceptionName)
                                   .code(httpStatusCode)
                                   .build();
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponseDTO> handleAllExceptions(final Exception exception) {
        final ExceptionResponseDTO response = getExceptionResponseDTO(exception.getMessage(),
                                                                      Exception.class.getSimpleName(),
                                                                      HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionResponseDTO> handleNotFoundException(final Exception exception) {
        final ExceptionResponseDTO response = getExceptionResponseDTO(exception.getMessage(),
                                                                      NotFoundException.class.getSimpleName(),
                                                                      HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public final ResponseEntity<ExceptionResponseDTO> handleAlreadyExistsException(final Exception exception) {
        final ExceptionResponseDTO response = getExceptionResponseDTO(exception.getMessage(),
                                                                      AlreadyExistsException.class.getSimpleName(),
                                                                      HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ExceptionResponseDTO> handleBadRequestException(final Exception exception) {
        final ExceptionResponseDTO response = getExceptionResponseDTO(exception.getMessage(),
                                                                      BadRequestException.class.getSimpleName(),
                                                                      HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<ExceptionResponseDTO> handleDataIntegrityViolationException(final Exception exception) {
        final ExceptionResponseDTO response = getExceptionResponseDTO(exception.getMessage(),
                                                                      DataIntegrityViolationException.class.getSimpleName(),
                                                                      HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException exception, HttpHeaders headers, HttpStatusCode status,
            WebRequest request) {
        final ExceptionResponseDTO response = getExceptionResponseDTO(exception.getMessage(),
                                                                      MethodNotAllowedException.class.getSimpleName(),
                                                                      HttpStatus.METHOD_NOT_ALLOWED.value());
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        String message;
        if (exception.getMessage()
                     .contains(REQUIRED_REQUEST_BODY_IS_MISSING)) {
            message = REQUIRED_REQUEST_BODY_IS_MISSING;
        } else {
            message = exception.getMessage();
        }
        final ExceptionResponseDTO response = getExceptionResponseDTO(message,
                                                                      BadRequestException.class.getSimpleName(),
                                                                      HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        final String errorMessage = exception.getBindingResult()
                                             .getFieldErrors()
                                             .stream()
                                             .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                             .toList()
                                             .stream()
                                             .map(error -> error + System.lineSeparator())
                                             .collect(Collectors.joining());

        final ExceptionResponseDTO response = getExceptionResponseDTO(errorMessage,
                                                                      BadRequestException.class.getSimpleName(),
                                                                      HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
