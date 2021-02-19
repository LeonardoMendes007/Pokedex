package com.pokedex.resources.exception;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.FieldError;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ValidationError  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")  
	private Instant timestamp;
	private Integer status;
	private String error;
	private List<FieldMessage> erros = new ArrayList<>();
	
	public ValidationError(Instant timestamp, Integer status, String error) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
	}

	public void addErrors(String field, String defaultMessage) {
		
		erros.add(new FieldMessage(field, defaultMessage));
		
	}



	
	
}
