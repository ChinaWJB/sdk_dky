package org.caeit.cs.client.xd.impl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import org.caeit.cs.client.xd.ConnectClient;
import org.caeit.cs.client.xd.Message;
import org.caeit.cs.client.xd.MsgType;

public class DeployClient extends ConnectClient {
	public ArrayList<String> UnifyAddressOfWebapp(ArrayList<String> al)
			throws IOException, ClassNotFoundException {

		Socket socket = new Socket(serverIP, serverPort);
		ObjectOutputStream outputStream = new ObjectOutputStream(
				socket.getOutputStream());
		Message outMes = new Message(MsgType.AddServiceComp, al);
		outputStream.writeObject(outMes);
		outputStream.flush();

		ArrayList<String> reAl = new ArrayList<String>();
		ObjectInputStream inputStream = new ObjectInputStream(
				socket.getInputStream());
		Message inMes = (Message) inputStream.readObject();
		if (inMes.getType() == MsgType.USEREXIST) {
			reAl = (ArrayList<String>) inMes.getBody();
		}
		socket.close();
		return reAl;

	}
}
