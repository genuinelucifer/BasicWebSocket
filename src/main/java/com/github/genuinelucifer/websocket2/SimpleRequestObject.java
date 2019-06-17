package com.github.genuinelucifer.websocket2;

import java.util.ArrayList;
import java.util.List;

public class SimpleRequestObject {
	public String command;
	public String tag; // Optional parameter
	public List<String> params;

	public SimpleRequestObject() {
		params = new ArrayList<>();
		tag = null;
		command = "";
	}

	@Override
	public String toString() {
		return "Command: " + command + ", " +
				tag == null ? "" : ("Tag: " + tag + ", ") +
				"Params: " + params.toString();
	}
}
