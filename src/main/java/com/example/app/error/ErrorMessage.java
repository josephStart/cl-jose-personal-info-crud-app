package com.example.app.error;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ErrorMessage {
	
	private String errorDate;
	private String errorMessage;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	public ErrorMessage(Date errorDate, Throwable errorMessage) {
		this.errorDate = sdf.format(errorDate);
		this.errorMessage = errorMessage.getLocalizedMessage();
	}

	public String getErrorDate() {
		return errorDate;
	}

	public void setErrorDate(String errorDate) {
		this.errorDate = errorDate;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
