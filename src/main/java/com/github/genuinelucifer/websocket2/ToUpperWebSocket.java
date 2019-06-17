package com.github.genuinelucifer.websocket2;

import java.io.IOException;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

@WebSocket
public class ToUpperWebSocket {
	Gson gson;

	@OnWebSocketMessage
	public void onText(Session session, String message) throws IOException {
		System.out.println("Message received:" + message);
		try {
			SimpleRequestObject obj = gson.fromJson(message, SimpleRequestObject.class);
			System.out.println("Json object successfully received. Obj: " + obj);
		} catch (JsonSyntaxException e) {
			System.out.println("Error while converting string to JSON. Not a json object.");
		}
		if (session.isOpen()) {
			String response = message.toUpperCase();
			session.getRemote().sendString(response);
		}
	}

	@OnWebSocketConnect
	public void onConnect(Session session) throws IOException {
		gson = new GsonBuilder().create();
		System.out.println(session.getRemoteAddress().getHostString() + " connected!");
	}

	@OnWebSocketClose
	public void onClose(Session session, int status, String reason) {
		System.out.println(session.getRemoteAddress().getHostString() + " closed!");
	}

}