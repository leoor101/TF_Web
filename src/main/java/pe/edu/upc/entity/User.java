package pe.edu.upc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int UserID;

	@NotEmpty(message = "Ingrese el nombre del usuario")
	@Column(name = "Name", nullable = false, length = 50)
	private String Name;

	@NotEmpty(message = "Ingrese la contraseña del usuario")
	@Column(name = "Password", nullable = false, length = 20)
	private String Password;

	@NotEmpty(message = "Ingrese el nombre del usuario")
	@Column(name = "Username", nullable = false, length = 50)
	private String Username;

	@NotEmpty(message = "Ingrese el correo del usuario")
	@Column(name = "EmailAddress", nullable = false, length = 30)
	private String EmailAddress;

	@NotEmpty(message = "Ingrese el teléfono del usuario")
	@Column(name = "PhoneNumber", nullable = false, length = 7)
	private String PhoneNumber;

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getEmailAddress() {
		return EmailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		EmailAddress = emailAddress;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int userID, @NotEmpty(message = "Ingrese el nombre del usuario") String name,
			@NotEmpty(message = "Ingrese la contraseña del usuario") String password,
			@NotEmpty(message = "Ingrese el nombre del usuario") String username,
			@NotEmpty(message = "Ingrese el correo del usuario") String emailAddress,
			@NotEmpty(message = "Ingrese el teléfono del usuario") String phoneNumber) {
		super();
		UserID = userID;
		Name = name;
		Password = password;
		Username = username;
		EmailAddress = emailAddress;
		PhoneNumber = phoneNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((EmailAddress == null) ? 0 : EmailAddress.hashCode());
		result = prime * result + ((Name == null) ? 0 : Name.hashCode());
		result = prime * result + ((Password == null) ? 0 : Password.hashCode());
		result = prime * result + ((PhoneNumber == null) ? 0 : PhoneNumber.hashCode());
		result = prime * result + UserID;
		result = prime * result + ((Username == null) ? 0 : Username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (EmailAddress == null) {
			if (other.EmailAddress != null)
				return false;
		} else if (!EmailAddress.equals(other.EmailAddress))
			return false;
		if (Name == null) {
			if (other.Name != null)
				return false;
		} else if (!Name.equals(other.Name))
			return false;
		if (Password == null) {
			if (other.Password != null)
				return false;
		} else if (!Password.equals(other.Password))
			return false;
		if (PhoneNumber == null) {
			if (other.PhoneNumber != null)
				return false;
		} else if (!PhoneNumber.equals(other.PhoneNumber))
			return false;
		if (UserID != other.UserID)
			return false;
		if (Username == null) {
			if (other.Username != null)
				return false;
		} else if (!Username.equals(other.Username))
			return false;
		return true;
	}

}
