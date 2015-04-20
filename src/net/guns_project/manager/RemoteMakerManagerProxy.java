package net.guns_project.manager;

import java.util.List;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import net.guns_project.domain.Maker;
import net.guns_project.server.RemoteRequestMessage;
import net.guns_project.server.RemoteResponseMessage;

public class RemoteMakerManagerProxy implements IMakerManager{
	private static final String REMOTE_SERVER_ADDR = "localhost";
	
	private static final int REMOTE_SERVER_PORT = 9999;

	
	public int createMaker(Maker maker) throws Exception {
		RemoteResponseMessage msg = invokeRemoteCall(new RemoteRequestMessage(
				IMakerManager.class.getSimpleName(), "createMaker",
				new Object[] { maker }));
		if (msg.getException() != null) {
			throw msg.getException();
		}

		return (Integer) msg.getResult();
	}

	
	public void updateMaker(Maker maker) throws Exception {
		RemoteResponseMessage msg = invokeRemoteCall(new RemoteRequestMessage(
				IMakerManager.class.getSimpleName(), "updateMaker",
				new Object[] { maker }));

		if (msg.getException() != null) {
			throw msg.getException();
		}
		
	}

	
	public void removeMaker(int MakerId) throws Exception {
		RemoteResponseMessage msg = invokeRemoteCall(new RemoteRequestMessage(
				IMakerManager.class.getSimpleName(), "removeMaker",
				new Object[] { MakerId }));

		if (msg.getException() != null) {
			throw msg.getException();
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<Maker> getAllMakers() throws Exception {
		RemoteResponseMessage msg = invokeRemoteCall(new RemoteRequestMessage(
				IMakerManager.class.getSimpleName(), "getAllMakers", null));

		if (msg == null) {
			return null;
		}

		if (msg.getException() != null) {
			throw msg.getException();
		}

		return (List<Maker>) msg.getResult();
	}

	
	public Maker getMakerById(int MakerId) throws Exception {
		RemoteResponseMessage msg = invokeRemoteCall(new RemoteRequestMessage(
				IMakerManager.class.getSimpleName(), "getMakerById",
				new Object[] { MakerId }));

		if (msg == null) {
			return null;
		}

		if (msg.getException() != null) {
			throw msg.getException();
		}

		return (Maker) msg.getResult();
	}
	private RemoteResponseMessage invokeRemoteCall(RemoteRequestMessage call)
			throws Exception {

		// відкриває звязок із сервером
		Socket clientSocket = new Socket(REMOTE_SERVER_ADDR, REMOTE_SERVER_PORT);
		ObjectOutputStream outToServer = new ObjectOutputStream(
				clientSocket.getOutputStream());

		// відсилає на сервер серіалізований обєкт із запитом на виконання
		outToServer.writeObject(call);

		// отримує із сервера відповідь та десеріалізує обєкт типу
		// RemoteResponseMessage
		ObjectInputStream inFromServer = new ObjectInputStream(
				clientSocket.getInputStream());
		Object response = inFromServer.readObject();

		// закриває звязок із сервером
		clientSocket.close();

		// повератє відповідь сервера
		return (RemoteResponseMessage) response;
	}

}
