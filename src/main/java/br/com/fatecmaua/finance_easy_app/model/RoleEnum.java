package br.com.fatecmaua.finance_easy_app.model;

public enum RoleEnum {

	USER("User"),
	ADMIN("Admin");
	
	private final String role;
	
	RoleEnum(String role){
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
}
