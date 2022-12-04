/*
 * MIT License
 *
 * Copyright (c) 2022 JUAN CALVOPINA M
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Juan Calvopina <juan.calvopina@gmail.com>
 */
@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponseDTO> handleAllExceptions(final Exception exception) {
        final ExceptionResponseDTO exceptionResponseDTO =
                this.getExceptionResponseDTO(exception.getMessage(),
                                             Exception.class.getSimpleName(),
                                             HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * {@inheritDoc}
     */
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ExceptionResponseDTO> handleNotFoundException(final Exception exception) {
        final ExceptionResponseDTO exceptionResponseDTO =
                this.getExceptionResponseDTO(exception.getMessage(),
                                             NotFoundException.class.getSimpleName(),
                                             HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);
    }

    /**
     * {@inheritDoc}
     */
    @ExceptionHandler(AlreadyExistsException.class)
    public final ResponseEntity<ExceptionResponseDTO> handleAlreadyExistsException(final Exception exception) {
        final ExceptionResponseDTO exceptionResponseDTO =
                this.getExceptionResponseDTO(exception.getMessage(),
                                             AlreadyExistsException.class.getSimpleName(),
                                             HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.CONFLICT);
    }

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

}
