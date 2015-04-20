package net.guns_project.manager;

import java.util.List;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import net.guns_project.domain.Relations;
import net.guns_project.server.RemoteRequestMessage;
import net.guns_project.server.RemoteResponseMessage;

public class RemoteRelationManagerProxy implements IRelationManager{
	private static final String REMOTE_SERVER_ADDR = "localhost";
	
	private static final int REMOTE_SERVER_PORT = 9999;

	@SuppressWarnings("unchecked")
	public List<Relations> getAllRelations() throws Exception {
			RemoteResponseMessage msg = invokeRemoteCall(new RemoteRequestMessage(
					IRelationManager.class.getSimpleName(), "getAllRelations", null));

			if (msg == null) {
				return null;
			}

			if (msg.getException() != null) {
				throw msg.getException();
			}

			return (List<Relations>) msg.getResult();
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
