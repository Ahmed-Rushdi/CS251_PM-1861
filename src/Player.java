import java.util.ArrayList;


public class Player extends Account{
	private eWallet wallet;
	private String location;
	private ArrayList<Booking> bookings;
	private ArrayList<Player> Team;
	@Override
	public String toString() {
		return "Player [UserName:" + userName + ", email " + email + "]";
	}

	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}

	public ArrayList<Player> getTeam() {
		return Team;
	}

	public void setTeam(ArrayList<Player> team) {
		Team = team;
	}

	Player(String U,String e,String P,String L)
	{
		super(U,e,P);
		location=L;
		wallet=new eWallet();
		wallet.setBalance(0);
	}
	
	public eWallet getWallet() {
		return wallet;
	}
	public void setWallet(eWallet wallet) {
		this.wallet = wallet;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void modifyTeam() {
		
	}
	
}
