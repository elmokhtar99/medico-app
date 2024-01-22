package com.medico.app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static com.medico.app.Constant.API_DATE_TIME_FORMAT;

@Getter
@Setter
public class Response<D> {

	private String uid;

	@JsonFormat(pattern = API_DATE_TIME_FORMAT)
	private LocalDateTime date;

	private D data;

	public Response() {
		super();
	}

	public Response(String uid) {
		super();
		date = LocalDateTime.now();
		this.uid = uid;
	}
}
