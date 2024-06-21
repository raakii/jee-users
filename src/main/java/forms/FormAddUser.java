package forms;

import dao.UserDao;
import models.User;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class FormAddUser {
	// Constantes
	private static final String LASTNAME_FIELD = "lastname";
	private static final String FIRSTNAME_FIELD = "firstname";
	private static final String LOGIN_FIELD = "login";
	private static final String PASSWORD_FIELD = "password";
	private static final String PASSWORD_FIELD_BIS = "passwordBis";
	private static final String MESSAGE_FIELD = "Veuillez renseigner ce champ";
	private static final String WRONG_PASSWORD = "Les mots de passe ne correspondent pas";

	private HttpServletRequest request;
	private Map<String, String> erreurs;
	private boolean status;
	private String messageStatus;
	private User user;

	public FormAddUser(HttpServletRequest request) {

		this.status = false;
		this.request = request;
		this.erreurs = new HashMap<String, String>();
	}

	public boolean ajouter() {
		String lastname = getParameter(LASTNAME_FIELD);
		String firstname = getParameter(FIRSTNAME_FIELD);
		String login = getParameter(LOGIN_FIELD);
		String password = getParameter(PASSWORD_FIELD);

		this.user = new User(lastname, firstname, login, password);
		validateField(LASTNAME_FIELD, FIRSTNAME_FIELD, LOGIN_FIELD, PASSWORD_FIELD, PASSWORD_FIELD_BIS);
		validatePassword();

		if (erreurs.isEmpty()) {
			status = true;
			messageStatus = "L'ajout a �t� effectu� avec succ�s";
			UserDao.add(user);

		}

		else {
			status = false;
			messageStatus = "L'ajout a �chou� ! R�essayer";

		}

		return status;
	}

	private String getParameter(String parametre) {
		// TODO Auto-generated method stub
		String valeur = request.getParameter(parametre);

		return (valeur == null || valeur.trim().isEmpty()) ? null : valeur.trim();

	}

	private void validateField(String... parametres) {
		for (String parametre : parametres) {
			if (this.getParameter(parametre) == null) {
				erreurs.put(parametre, MESSAGE_FIELD);
			}
		}

	}

	private void validatePassword() {
		String password = this.getParameter(PASSWORD_FIELD);
		String passwordBis = this.getParameter(PASSWORD_FIELD_BIS);

		if (password != null && !password.equals(passwordBis)) {
			erreurs.put(PASSWORD_FIELD, WRONG_PASSWORD);
			erreurs.put(PASSWORD_FIELD_BIS, WRONG_PASSWORD);

		}

	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public boolean getStatus() {
		return status;
	}

	public String getMessageStatus() {
		return messageStatus;
	}

	public User getuser() {
		return user;
	}

}
