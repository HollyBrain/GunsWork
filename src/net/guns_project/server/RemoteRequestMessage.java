package net.guns_project.server;

import java.io.Serializable;

public class RemoteRequestMessage implements Serializable {

	private static final long serialVersionUID = 6476159144372602136L;

	// імя менеджера який треба викликати
	private String service;

	// імя метода в менеджері який треба
	// виклилати
	private String methodName;

	// параметри для цього метода
	private Object[] arguments;

	public RemoteRequestMessage() {

	}

	public RemoteRequestMessage(String service, String methodName,
			Object[] arguments) {
		super();
		this.service = service;
		this.methodName = methodName;
		this.arguments = arguments;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Object[] getArguments() {
		return arguments;
	}

	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}

}