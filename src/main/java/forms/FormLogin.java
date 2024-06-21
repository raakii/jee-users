package forms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import models.User;

public class FormLogin {
	private String LOGIN_FIELD = "login";
	private String PASSWORD_FIELD = "password";

	private boolean status;
	private String statusMessage;
	private User user;
	private HttpServletRequest request;
	private String login;
	private final String WRONG_PASSWORD = "The passwords are not the same";

	public FormLogin(HttpServletRequest request) {
		this.request = request;
		this.status = false;
		this.statusMessage = "error";
	}

	public boolean login() {
		String login = getParameter(LOGIN_FIELD);
		String password = getParameter(PASSWORD_FIELD);

		User user = UserDao.getLogin(login);

		if (user != null && user.getPassword().equals(password)) {
			status = true;
			HttpSession session = request.getSession();
			session.setAttribute("isConnected", true);

		}
		return status;
	}

	public String getParameter(String parametre) {
		String valeur = request.getParameter(parametre);
		return (valeur == null || valeur.trim().isEmpty()) ? null : valeur.trim();
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public User getuser() {
		return user;
	}

	public Object getLogin() {
		return login;
	}
}
