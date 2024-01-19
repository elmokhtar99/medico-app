package com.medico.app.exeption;

import com.medico.app.dto.ApiErrorDetails;
import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static com.medico.app.Constant.API_DEFAULT_ERROR_MESSAGE;
import static com.medico.app.Constant.API_DEFAULT_REQUEST_FAILED_MESSAGE;
import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    // Process @Valid
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            @NonNull final MethodArgumentNotValidException ex,
            @NonNull final HttpHeaders headers,
            @NonNull final HttpStatusCode status,
            @NonNull final WebRequest request) {
        log.info(ex.getMessage(), ex);

        final List<ApiErrorDetails> errors = new ArrayList<>();

        for (final ObjectError err : ex.getBindingResult().getAllErrors()) {
            errors.add(
                    ApiErrorDetails.builder()
                            .pointer(((FieldError) err).getField())
                            .reason(err.getDefaultMessage())
                            .build());
        }

        return ResponseEntity.status(BAD_REQUEST)
                .body(this.buildProblemDetail(BAD_REQUEST, "Validation failed.", errors));
    }

    // Process controller method parameter validations e.g. @RequestParam, @PathVariable etc.
    @Override
    protected ResponseEntity<Object> handleHandlerMethodValidationException(
            final @NotNull HandlerMethodValidationException ex,
            final @NotNull HttpHeaders headers,
            final @NotNull HttpStatusCode status,
            final @NotNull WebRequest request) {
        log.info(ex.getMessage(), ex);

        final List<ApiErrorDetails> errors = new ArrayList<>();
        for (final var validation : ex.getAllValidationResults()) {
            final String parameterName = validation.getMethodParameter().getParameterName();
            validation
                    .getResolvableErrors()
                    .forEach(
                            error -> {
                                errors.add(
                                        ApiErrorDetails.builder()
                                                .pointer(parameterName)
                                                .reason(error.getDefaultMessage())
                                                .build());
                            });
        }

        return ResponseEntity.status(BAD_REQUEST)
                .body(this.buildProblemDetail(BAD_REQUEST, "Validation failed.", errors));
    }

    @ExceptionHandler(RootException.class)
    public ResponseEntity<ProblemDetail> rootException(final RootException ex) {
        log.info(ex.getMessage(), ex);

        // Uses default message, can be adapted to use ex.getMessage().
        final ProblemDetail problemDetail =
                this.buildProblemDetail(
                        ex.getHttpStatus(), API_DEFAULT_REQUEST_FAILED_MESSAGE, ex.getErrors());

        return ResponseEntity.status(ex.getHttpStatus()).body(problemDetail);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ProblemDetail handleAllExceptions(final Throwable ex, final WebRequest request) {
        log.warn(ex.getMessage(), ex);

//        this.slack.notify(format("[API] InternalServerError: %s", ex.getMessage()));

        return this.buildProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, API_DEFAULT_ERROR_MESSAGE,null);
    }


    private ProblemDetail buildProblemDetail(
            final HttpStatus status, final String detail, final List<ApiErrorDetails> errors) {

        final ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(status, StringUtils.normalizeSpace(detail));

        // Adds errors fields on validation errors, following RFC 9457 best practices.
        if (CollectionUtils.isNotEmpty(errors)) {
            problemDetail.setProperty("errors", errors);
        }


        return problemDetail;
    }
}
