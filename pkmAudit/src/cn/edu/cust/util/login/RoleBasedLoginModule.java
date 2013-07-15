package cn.edu.cust.util.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import javax.sql.DataSource;

import org.apache.naming.ContextBindings;

import cn.edu.cust.util.login.RolePrincipal;
import cn.edu.cust.util.login.UserPrincipal;

public class RoleBasedLoginModule implements LoginModule {

	static final String SQL = "select password, role from admin where name = ? ";

	private String username;
	private String userrole;

	private Subject subject;

	private CallbackHandler callbackHandler;

	public boolean abort() throws LoginException {
		return false;
	}

	public boolean commit() throws LoginException {
		UserPrincipal user = new UserPrincipal(username);
		RolePrincipal role = new RolePrincipal(userrole);
		subject.getPrincipals().add(user);
		subject.getPrincipals().add(role);
		return true;
	}

	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
		this.subject = subject;
		this.callbackHandler = callbackHandler;
	}

	public boolean login() throws LoginException {
		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("login");
		callbacks[1] = new PasswordCallback("password", true);

		try {

			callbackHandler.handle(callbacks);

			String username = ((NameCallback) callbacks[0]).getName();
			String password = String.valueOf(((PasswordCallback) callbacks[1])
					.getPassword());
			Connection dbConnection = openDbConnection();
			try {
				String role = null;
				PreparedStatement stmt = dbConnection.prepareStatement(SQL);
				try {
					stmt.setString(1, username);
					ResultSet rs = stmt.executeQuery();
					try {
						if (rs.next()) {
							String dbpass = rs.getString(1);
							role = rs.getString(2);
							if (!dbpass.equals(password)) {
								throw new LoginException(
										"Authentication failed: pass error");
							}
						} else {
							throw new LoginException(
									"Authentication failed: user error");
						}
					} finally {
						rs.close();
					}
				} finally {
					stmt.close();
				}
				
				this.username = username;
				this.userrole = role;
				return true;
			} finally {
				dbConnection.close();
			}

		} catch (IOException e) {

			throw new LoginException(e.getMessage());

		} catch (UnsupportedCallbackException e) {

			throw new LoginException(e.getMessage());
		} catch (NamingException e) {

			throw new LoginException(e.getMessage());
		} catch (SQLException e) {

			throw new LoginException(e.getMessage());
		}
	}

	public boolean logout() throws LoginException {
		UserPrincipal user = new UserPrincipal(username);
		RolePrincipal role = new RolePrincipal(userrole);

		subject.getPrincipals().remove(user);
		subject.getPrincipals().remove(role);

		return true;
	}

	/**
	 * Open the specified database connection.
	 * 
	 * @return Connection to the database
	 */
	protected Connection openDbConnection() throws NamingException,
			SQLException {

		Context context = null;
		context = ContextBindings.getClassLoader();
		context = (Context) context.lookup("comp/env");
		DataSource dataSource = (DataSource) context.lookup("jdbc/pkm");
		return dataSource.getConnection();
	}

}
