package com.giriraj.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ErrorDTO {

	private String message;
	private HttpStatus error;
	private Integer status;
	private LocalDateTime timestamp;

	
     @Override
	public String toString() {
		return "ErrorDTO [message=" + message + ", error=" + error + ", status=" + status + ", timestamp=" + timestamp
				+ "]";
	}

}
