package net.guns_project.server;

import java.io.Serializable;


public class RemoteResponseMessage implements Serializable {

	private static final long serialVersionUID = -1798443047352798529L;

	// результат виклику методу - те що поверне
	// метод менеджера
	private Object result;

	// помилка якщо є
	private Exception exception;

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

}