package com.revature.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * Let's say that we've created own business exception. We can directly annotate
 * our custom exceptions using the @ResponseStatus annotation. This annotation
 * directly correlates your exception with a specific status code and message.
 */

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Sorry. This is a demo and I needed to show this.")
public class BusinessException extends RuntimeException{

}
