package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.User;

public class UserDao {
	private static int lastId = 0;
	private static final String INSERT_USERS = "INSERT INTO Utilisateur VALUES (0, ?, ?, ?, ?)";
	private static final String UPDATE_USERS = "UPDATE Utilisateur SET nom = ?, prenom = ?, login =?, password = ? WHERE id = ? ";
	private static final String SELECT_USER_BY_ID = "SELECT * FROM Utilisateur WHERE id = ?";
	private static final String SELECT_USER_BY_LOGIN = "SELECT * FROM Utilisateur WHERE login = ?";
	private static final String DELETE_USER = "DELETE FROM Utilisateur WHERE id = ?";
	private static final ArrayList<User> utilisateurs = new ArrayList<User>();

	private static User user;
	static {
		utilisateurs.add(new User("fall", "assane", "admin", "passer"));
	}

	public static boolean ajouter(User utilisateur) {
		Connection connection = ConnexionManager.getInstance();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(INSERT_USERS);
			statement.setString(1, utilisateur.getNom());
			statement.setString(2, utilisateur.getPrenom());
			statement.setString(3, utilisateur.getLogin());
			statement.setString(4, utilisateur.getPassword());
			int status = statement.executeUpdate();
			return status == 1 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	public static ArrayList<User> lister() {
		ArrayList<User> utilisateurs = new ArrayList<User>();
		Connection connection = ConnexionManager.getInstance();
		Statement statement = null;
		ResultSet resultSet = null;
		if (connection != null) {
			try {
				statement = connection.createStatement();
				resultSet = statement.executeQuery("SELECT * FROM Utilisateur");
				int id;
				String nom, prenom, login, password;
				while (resultSet.next()) {
					id = resultSet.getInt("id");
					nom = resultSet.getString("nom");
					prenom = resultSet.getString("prenom");
					login = resultSet.getString("login");
					password = resultSet.getString("password");

					utilisateurs.add(new User(id, nom, prenom, login, password));

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return utilisateurs;
	}

	public static boolean modifier(User utilisateur) {
		Connection connection = ConnexionManager.getInstance();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(UPDATE_USERS);
			statement.setString(1, utilisateur.getNom());
			statement.setString(2, utilisateur.getPrenom());
			statement.setString(3, utilisateur.getLogin());
			statement.setString(4, utilisateur.getPassword());
			statement.setInt(5, utilisateur.getId());

			int affectedRows = statement.executeUpdate();
			return affectedRows == 1 ? true : false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return false;
	}

	public static boolean supprimer(int id) {
		Connection connection = ConnexionManager.getInstance();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(DELETE_USER);
			statement.setInt(1, id);

			int affectedRows = statement.executeUpdate();
			return affectedRows == 1 ? true : false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		for (User utilisateur : utilisateurs) {
			if (utilisateur.getId() == id) {
				utilisateurs.remove(utilisateur);
				return true;
			}
		}

		return false;
	}

	public static User get(int id) {

		Connection connection = ConnexionManager.getInstance();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.prepareStatement(SELECT_USER_BY_ID);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {

				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				String login = resultSet.getString("login");
				String password = resultSet.getString("password");
				return new User(id, nom, prenom, login, password);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	public static User getLogin(String login) {
		Connection connection = ConnexionManager.getInstance();
		PreparedStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.prepareStatement(SELECT_USER_BY_LOGIN);
			statement.setString(1, login);
			resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int id = resultSet.getInt("id");
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				String password = resultSet.getString("password");
				return new User(id, nom, prenom, login, password);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		finally {
			if (resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return null;

	}

}
