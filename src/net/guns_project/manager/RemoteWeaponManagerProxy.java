package net.guns_project.manager;

import java.util.List;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import net.guns_project.domain.Weapon;
import net.guns_project.server.RemoteRequestMessage;
import net.guns_project.server.RemoteResponseMessage;

public class RemoteWeaponManagerProxy implements IWeaponManager{
	private static final String REMOTE_SERVER_ADDR = "localhost";
	
	private static final int REMOTE_SERVER_PORT = 9999;

	public int createWeapon(Weapon weapon) throws Exception {
		RemoteResponseMessage msg = invokeRemoteCall(new RemoteRequestMessage(
				IWeaponManager.class.getSimpleName(), "createWeapon",
				new Object[] { weapon }));
		if (msg.getException() != null) {
			throw msg.getException();
		}

		return (Integer) msg.getResult();
	}

	
	public void updateWeapon(Weapon weapon) throws Exception {
		// TODO Auto-generated method stub
		RemoteResponseMessage msg = invokeRemoteCall(new RemoteRequestMessage(
				IWeaponManager.class.getSimpleName(), "updateWeapon",
				new Object[] { weapon }));

		if (msg.getException() != null) {
			throw msg.getException();
		}
	}

	
	public void removeWeapon(int WeaponId) throws Exception {
		// TODO Auto-generated method stub
		RemoteResponseMessage msg = invokeRemoteCall(new RemoteRequestMessage(
				IWeaponManager.class.getSimpleName(), "removeWeapon",
				new Object[] { WeaponId }));

		if (msg.getException() != null) {
			throw msg.getException();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Weapon> getAllWeapons() throws Exception {
		// TODO Auto-generated method stub
		RemoteResponseMessage msg = invokeRemoteCall(new RemoteRequestMessage(
				IWeaponManager.class.getSimpleName(), "getAllWeapons", null));

		if (msg == null) {
			return null;
		}

		if (msg.getException() != null) {
			throw msg.getException();
		}

		return (List<Weapon>) msg.getResult();
	}
	@SuppressWarnings("unchecked")
	public List<Weapon> getMakerWeapons(int WeaponId) throws Exception {
		RemoteResponseMessage msg = invokeRemoteCall(new RemoteRequestMessage(
				IWeaponManager.class.getSimpleName(), "getMakerWeapons",
				new Object[] { WeaponId }));

		if (msg == null) {
			return null;
		}

		if (msg.getException() != null) {
			throw msg.getException();
		}

		return (List<Weapon>) msg.getResult();
	}

	
	public Weapon getWeaponById(int WeaponId) throws Exception {
		// TODO Auto-generated method stub
		RemoteResponseMessage msg = invokeRemoteCall(new RemoteRequestMessage(
				IWeaponManager.class.getSimpleName(), "getWeaponById",
				new Object[] { WeaponId }));

		if (msg == null) {
			return null;
		}

		if (msg.getException() != null) {
			throw msg.getException();
		}

		return (Weapon) msg.getResult();
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
