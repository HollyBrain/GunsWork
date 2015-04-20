package net.guns_project.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import net.guns_project.domain.*;
import net.guns_project.manager.*;

public class MainServer {
	private static final IMakerManager makerManager = new MakerManager();
	private static final IWeaponManager weaponManager = new WeaponManager();
	private static final IDescriptionManager descManager = new DescriptionManager();
	public static void main(String[] args) throws Exception {
		// вікриває сокет на порті 9999
		ServerSocket serverSocket = new ServerSocket(9999);

		while (true) {
			// Слухай порт 9999, чекає на повідомлення від клієнтів
			Socket socket = serverSocket.accept();

			// зчитує нове повідомлення та десеріалізує його до обєкта
			// RemoteRequestMessage
			ObjectInputStream inFromClient = new ObjectInputStream(
					socket.getInputStream());
			RemoteRequestMessage msg = (RemoteRequestMessage) inFromClient
					.readObject();

			// виводить на екран повідомлення про те, який метод був викликаний
			System.out.println("** Received call for " + msg.getService() + "."
					+ msg.getMethodName() + "()");

			// викликає метод, що обробляє запит та повертає результат.
			RemoteResponseMessage response = handleRequest(msg);

			// серіалізує обєкт response, що містить результат
			ObjectOutputStream outToClient = new ObjectOutputStream(
					socket.getOutputStream());

			// відсилає серіалізований обєкт response клієнтові
			outToClient.writeObject(response);
		}
	}

	private static RemoteResponseMessage handleRequest(RemoteRequestMessage msg) {

		if (IMakerManager.class.getSimpleName().equals(msg.getService())) {
			return handleMakerRequest(msg);
		}
		if (IWeaponManager.class.getSimpleName().equals(msg.getService())) {
			return handleWeaponRequest(msg);
		}
		if (IDescriptionManager.class.getSimpleName().equals(msg.getService())) {
			return handleDescRequest(msg);
		}

		return null;

	}
	private static RemoteResponseMessage handleMakerRequest(
			RemoteRequestMessage msg) {
		RemoteResponseMessage result = new RemoteResponseMessage();

		if (msg.getMethodName().equals("getAllMakers")) {
			try {
				result.setResult(makerManager.getAllMakers());
			} catch (Exception e) {
				result.setException(e);
			}
		} else if (msg.getMethodName().equals("getMakerById")) {
			try {

				result.setResult(makerManager.getMakerById((Integer) msg
						.getArguments()[0]));
			} catch (Exception e) {
				result.setException(e);
			}
		} else if (msg.getMethodName().equals("createMaker")) {
			try {
				result.setResult(makerManager.createMaker((Maker) msg
						.getArguments()[0]));
			} catch (Exception e) {
				result.setException(e);
			}
		} else if (msg.getMethodName().equalsIgnoreCase("removeMaker")) {
			try {
				makerManager.removeMaker((Integer) msg.getArguments()[0]);
			} catch (Exception e) {
				result.setException(e);
			}
		} else if (msg.getMethodName().equals("updateMaker")) {
			try {
				makerManager.updateMaker((Maker) msg.getArguments()[0]);
			} catch (Exception e) {
				result.setException(e);
			}
		}
		return result;
	}
	
	private static RemoteResponseMessage handleWeaponRequest(
			RemoteRequestMessage msg) {
		RemoteResponseMessage result = new RemoteResponseMessage();

		if (msg.getMethodName().equals("getAllWeapons")) {
			try {
				result.setResult(weaponManager.getAllWeapons());
			} catch (Exception e) {
				result.setException(e);
			}
		} else if (msg.getMethodName().equals("getMakerWeapons")) {
			try {

				result.setResult(weaponManager.getMakerWeapons((Integer) msg
						.getArguments()[0]));
			} catch (Exception e) {
				result.setException(e);
			}
		} else if (msg.getMethodName().equals("createWeapon")) {
			try {
				result.setResult(weaponManager.createWeapon((Weapon) msg
						.getArguments()[0]));
			} catch (Exception e) {
				result.setException(e);
			}
		} else if (msg.getMethodName().equalsIgnoreCase("removeWeapon")) {
			try {
				weaponManager.removeWeapon((Integer) msg.getArguments()[0]);
			} catch (Exception e) {
				result.setException(e);
			}
		} else if (msg.getMethodName().equals("updateWeapon")) {
			try {
				weaponManager.updateWeapon((Weapon) msg.getArguments()[0]);
			} catch (Exception e) {
				result.setException(e);
			}
		}
		return result;
	}
	
	private static RemoteResponseMessage handleDescRequest(
			RemoteRequestMessage msg) {
		RemoteResponseMessage result = new RemoteResponseMessage();

		if (msg.getMethodName().equals("getAllDescription")) {
			try {
				result.setResult(descManager.getAllDescription());
			} catch (Exception e) {
				result.setException(e);
			}
		} else if (msg.getMethodName().equals("getMakerDescription")) {
			try {

				result.setResult(descManager.getWeaponDescription((Integer) msg
						.getArguments()[1]));
			} catch (Exception e) {
				result.setException(e);
			}
		} else if (msg.getMethodName().equals("createDescription")) {
			try {
				result.setResult(descManager.createDescription((Description) msg
						.getArguments()[0]));
			} catch (Exception e) {
				result.setException(e);
			}
		} else if (msg.getMethodName().equalsIgnoreCase("removeDdescription")) {
			try {
				descManager.removeDescription((Integer) msg.getArguments()[0]);
			} catch (Exception e) {
				result.setException(e);
			}
		} else if (msg.getMethodName().equals("updateDescription")) {
			try {
				descManager.updateDescription((Description)msg.getArguments()[0]);
			} catch (Exception e) {
				result.setException(e);
			}
		}
		return result;
	}

}
