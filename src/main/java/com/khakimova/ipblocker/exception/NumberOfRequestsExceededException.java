package com.khakimova.ipblocker.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_GATEWAY;

@ResponseStatus(value = BAD_GATEWAY)
public class NumberOfRequestsExceededException extends RuntimeException {}
