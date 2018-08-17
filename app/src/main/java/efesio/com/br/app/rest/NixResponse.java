package efesio.com.br.app.rest;

import java.util.HashMap;
import java.util.Map;


public class NixResponse<T> {

	private int status;
	private String message;
	private T entity;
	private Map<String, String> headers = new HashMap<>();
	
	public NixResponse(){
		
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T object) {
		this.entity = object;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	@Override
	public String toString() {
		return "Response{" +
				"status=" + status +
				", message='" + message + '\'' +
				", headers=" + headers +
				", entity=" + entity +
				'}';
	}
}
