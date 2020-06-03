import java.util.ArrayList;

public class Account {
	protected String userName;
	protected String email;
	protected String passWord;
	protected ArrayList<String> inbox;

	Account(String U, String e, String P) {
		userName = U;
		email = e;
		passWord = P;
		inbox = new ArrayList<String>();
	}

	public String getUserName() {
		return userName;
	}

	public String getEmail() {
		return email;
	}

	public ArrayList<String> getInbox() {
		return inbox;
	}

	public void setInbox(ArrayList<String> inbox) {
		this.inbox = inbox;
	}

	public boolean login(String U, String P) {
		return (U.equalsIgnoreCase(email) && P.equals(passWord));
	}

	public void inboxToString() {
		for (String e : inbox) {
			System.out.println(e);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Account other = (Account) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
}
