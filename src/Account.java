import java.util.ArrayList;

public class Account {
	protected String userName;
	protected String email;
	protected String passWord;
	protected ArrayList<String> inbox;
	
	Account(String U,String e,String P)
	{
		userName=U;
		email=e;
		passWord=P;
		inbox=new ArrayList<String>();
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

	public boolean login(String U,String P)
	{
		return((U==userName || U==email) && P==passWord);
	}
	
	public void inboxToString()
	{
		for(String e:inbox)
		{
			System.out.println(e);
		}
	}
}
