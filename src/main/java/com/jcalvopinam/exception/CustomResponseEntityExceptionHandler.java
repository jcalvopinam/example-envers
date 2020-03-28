/*
 * MIT License
 *
 * Copyright (c) 2020 JUAN CALVOPINA M
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

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URISyntaxException;

/**
 * Custom error handling.
 */
@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * {@inheritDoc}
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(final Exception exception) {
        final ExceptionResponse exceptionResponse =
                ExceptionResponse.builder()
                                 .message(exception.getMessage())
                                 .type(Exception.class.getSimpleName())
                                 .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                 .errorUserTitle("Internal Server Error.")
                                 .errorUserMsg("An error has occurred in the system.")
                                 .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * {@inheritDoc}
     */
    @ExceptionHandler(OrderDetailNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handlePayerConnectorNotFoundException(final Exception exception) {
        final ExceptionResponse exceptionResponse =
                ExceptionResponse.builder()
                                 .message(exception.getMessage())
                                 .type(OrderDetailNotFoundException.class.getSimpleName())
                                 .code(HttpStatus.NOT_FOUND.value())
                                 .errorUserTitle("Order Detail Not Found.")
                                 .errorUserMsg("Order Detail has not been found.")
                                 .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * {@inheritDoc}
     */
    @ExceptionHandler(OrderNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleAttachmentNotFoundException(final Exception exception) {
        final ExceptionResponse exceptionResponse =
                ExceptionResponse.builder()
                                 .message(exception.getMessage())
                                 .type(OrderNotFoundException.class.getSimpleName())
                                 .code(HttpStatus.NOT_FOUND.value())
                                 .errorUserTitle("Order Not Found.")
                                 .errorUserMsg("Order has not been found.")
                                 .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * {@inheritDoc}
     */
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handlePersonNotFoundException(final Exception exception) {
        final ExceptionResponse exceptionResponse =
                ExceptionResponse.builder()
                                 .message(exception.getMessage())
                                 .type(NotFoundException.class.getSimpleName())
                                 .code(HttpStatus.NOT_FOUND.value())
                                 .errorUserTitle("Information Not Found.")
                                 .errorUserMsg("Data has not been found.")
                                 .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * {@inheritDoc}
     */
    @ExceptionHandler(ConflictException.class)
    public final ResponseEntity<ExceptionResponse> handleConflictException(final Exception exception) {
        final ExceptionResponse exceptionResponse =
                ExceptionResponse.builder()
                                 .message(exception.getMessage())
                                 .type(ConflictException.class.getSimpleName())
                                 .code(HttpStatus.CONFLICT.value())
                                 .errorUserTitle("Existing information!")
                                 .errorUserMsg("Data has been found in the database.")
                                 .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }

    /**
     * {@inheritDoc}
     */
    @ExceptionHandler(ValidationException.class)
    public final ResponseEntity<ExceptionResponse> handleAttachmentException(final Exception exception) {
        final ExceptionResponse exceptionResponse =
                ExceptionResponse.builder()
                                 .message(exception.getMessage())
                                 .type(ValidationException.class.getSimpleName())
                                 .code(HttpStatus.BAD_REQUEST.value())
                                 .errorUserTitle("Validation.")
                                 .errorUserMsg("Invalid request.")
                                 .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * {@inheritDoc}
     */
    @ExceptionHandler(URISyntaxException.class)
    public final ResponseEntity<ExceptionResponse> handleURISyntaxException(final Exception exception) {
        final ExceptionResponse exceptionResponse =
                ExceptionResponse.builder()
                                 .message(exception.getMessage())
                                 .type(URISyntaxException.class.getSimpleName())
                                 .code(HttpStatus.SERVICE_UNAVAILABLE.value())
                                 .errorUserTitle("URI Wrong Syntax.")
                                 .errorUserMsg("Error in the syntax URI.")
                                 .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }

}
