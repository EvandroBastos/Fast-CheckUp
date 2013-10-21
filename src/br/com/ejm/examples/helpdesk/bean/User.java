package br.com.ejm.examples.helpdesk.bean;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String email = "";

	public User() {
	}

	public User(String name, String email) {
		this.name = name;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return getEmail().hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		return (obj instanceof User)
				&& ((User) obj).getEmail().equals(getEmail());

	}

	@Override
	public String toString() {
		return "[" + getEmail() + "]";
	}

}
