package com.evartem.jobajob.data

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.stream.Collectors
import org.springframework.transaction.TransactionSystemException
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.persistence.RollbackException
import javax.validation.ConstraintViolationException

/*@Suppress("unused")
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(TransactionSystemException::class)
    @Throws(Throwable::class)
    protected fun handleTransactionException(ex: TransactionSystemException): ResponseEntity<List<String>> {
        val cause = ex.cause
        if (cause !is RollbackException) throw cause!!
        if (cause.cause !is ConstraintViolationException) throw cause.cause!!
        val validationException = cause.cause as ConstraintViolationException
        val messages = validationException.constraintViolations.stream()
                .map { it.message }.collect(Collectors.toList())
        return ResponseEntity(messages, HttpStatus.BAD_REQUEST)
    }
}*/
