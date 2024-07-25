package com.lbg.hackathon.exception;

import lombok.Setter;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomErrorMessage {

	private String message;
	private HttpStatus status;
}
