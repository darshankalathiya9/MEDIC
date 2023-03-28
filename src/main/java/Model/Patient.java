package Model;

public class Patient {
	private int ID;
	private String FisrtName, LastName, Email, Address, Gender, Password;
	private Long Mobile;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getFisrtName() {
		return FisrtName;
	}

	public void setFisrtName(String fisrtName) {
		FisrtName = fisrtName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public Long getMobile() {
		return Mobile;
	}

	public void setMobile(Long mobile) {
		Mobile = mobile;
	}

	@Override
	public String toString() {
		return "Patient [ID=" + ID + ", FisrtName=" + FisrtName + ", LastName=" + LastName + ", Email=" + Email
				+ ", Address=" + Address + ", Gender=" + Gender + ", Password=" + Password + ", Mobile=" + Mobile + "]";
	}
}
