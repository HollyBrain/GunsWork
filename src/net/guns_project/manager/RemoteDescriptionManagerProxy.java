package net.guns_project.manager;

import java.util.List;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import net.guns_project.domain.Description;
import net.guns_project.server.RemoteRequestMessage;
import net.guns_project.server.RemoteResponseMessage;

public class RemoteDescriptionManagerProxy implements IDescriptionManager{
private static final String REMOTE_SERVER_ADDR = "localhost";
	
	private static final int REMOTE_SERVER_PORT = 9999;

	public int createDescription(Description desc) throws Exception {
		RemoteResponseMessage msg = invokeRemoteCall(new RemoteRequestMessage(
				IDescriptionManager.class.getSimpleName(), "createDescription",
				new Object[] { desc }));
		if (msg.getException() != null) {
			throw msg.getException();
		}

		return (Integer) msg.getResult();
	}

	public void updateDescription(Description desc) throws Exception {
		RemoteResponseMessage msg = invokeRemoteCall(new RemoteRequestMessage(
				IDescriptionManager.class.getSimpleName(), "updateDescription",
				new Object[] { desc }));

		if (msg.getException() != null) {
			throw msg.getException();
		}
		
	}


	public void removeDescription(int idDescription) throws Exception {
		RemoteResponseMessage msg = invokeRemoteCall(new RemoteRequestMessage(
				IWeaponManager.class.getSimpleName(), "removeDescription",
				new Object[] { idDescription }));

		if (msg.getException() != null) {
			throw msg.getException();
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<Description> getAllDescription() throws Exception {
		RemoteResponseMessage msg = invokeRemoteCall(new RemoteRequestMessage(
				IWeaponManager.class.getSimpleName(), "getAllDescription", null));

		if (msg == null) {
			return null;
		}

		if (msg.getException() != null) {
			throw msg.getException();
		}

		return (List<Description>) msg.getResult();
	}

	
	public Description getDescriptionById(int idDescription) throws Exception {
		RemoteResponseMessage msg = invokeRemoteCall(new RemoteRequestMessage(
				IWeaponManager.class.getSimpleName(), "getDescriptionById",
				new Object[] { idDescription }));

		if (msg == null) {
			return null;
		}

		if (msg.getException() != null) {
			throw msg.getException();
		}

		return (Description) msg.getResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Description> getWeaponDescription(int idDescription)
			throws Exception {
		RemoteResponseMessage msg = invokeRemoteCall(new RemoteRequestMessage(
				IWeaponManager.class.getSimpleName(), "getMakerWeapons",
				new Object[] { idDescription }));

		if (msg == null) {
			return null;
		}

		if (msg.getException() != null) {
			throw msg.getException();
		}

		return (List<Description>) msg.getResult();
	}
	private RemoteResponseMessage invokeRemoteCall(RemoteRequestMessage call)
			throws Exception {

		// ������� ������ �� ��������
		Socket clientSocket = new Socket(REMOTE_SERVER_ADDR, REMOTE_SERVER_PORT);
		ObjectOutputStream outToServer = new ObjectOutputStream(
				clientSocket.getOutputStream());

		// ������ �� ������ ������������ ���� �� ������� �� ���������
		outToServer.writeObject(call);

		// ������ �� ������� ������� �� ��������� ���� ����
		// RemoteResponseMessage
		ObjectInputStream inFromServer = new ObjectInputStream(
				clientSocket.getInputStream());
		Object response = inFromServer.readObject();

		// ������� ������ �� ��������
		clientSocket.close();

		// ������� ������� �������
		return (RemoteResponseMessage) response;
	}



}
