package cn.edu.cust.util.login;

import java.security.Principal;

public class RolePrincipal implements Principal {
	private String name;
	
	public RolePrincipal(String name){
		this.name = name;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

}
