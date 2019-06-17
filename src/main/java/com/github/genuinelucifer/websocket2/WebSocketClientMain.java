package com.github.genuinelucifer.websocket2;

import java.net.URI;
import java.util.Arrays;

import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WebSocketClientMain {

	public static void main(String[] args) {
		String dest = "ws://localhost:8080/toUpper";
		WebSocketClient client = new WebSocketClient();
		try {

			ToUpperClientSocket socket = new ToUpperClientSocket();
			client.start();
			URI echoUri = new URI(dest);
			ClientUpgradeRequest request = new ClientUpgradeRequest();
			client.connect(socket, echoUri, request);
			socket.getLatch().await();
			socket.sendMessage("test");

			// The GSON object
			Gson gson = new GsonBuilder().create();

			// Send an object
			SimpleRequestObject obj1 = new SimpleRequestObject();
			obj1.command = "command1";
			// obj1.tag = "mynametag";
			obj1.params = Arrays.asList("param1", "param2");
			String msg1 = gson.toJson(obj1, SimpleRequestObject.class);
			socket.sendMessage(msg1);

			Thread.sleep(1000l);

		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			try {
				client.stop();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
