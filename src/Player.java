import java.util.ArrayList;

public class Player extends Account {
	private eWallet wallet;
	private String location;
	private ArrayList<Booking> bookings = new ArrayList<Booking>();
	private ArrayList<Player> Team = new ArrayList<Player>();

	@Override
	public String toString() {
		return "Player [Username:" + userName + ", email " + email + "]";
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

	Player(String U, String e, String P, String L) {
		super(U, e, P);
		location = L;
		wallet = new eWallet();
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
		if (Team.size() == 0) {
			System.out.println("No teammembers yet");
		}
		while (true) {
			try {
				System.out.println("Team members");
				for (int i = 0; i < Team.size(); i++) {
					System.out.println(Team.get(i));
				}
				if (Team.size() == 0) {
					System.out.println("No teammembers yet");
				}
				System.out.println("1-Add a teammate");
				System.out.println("2-Remove a teammate");
				switch (system.scanner.nextLine()) {
				case "1":
					System.out.println("Enter their email:");
					String sEmail = system.scanner.nextLine();
					if (!system.accounts.contains(new Account("", sEmail, ""))) {
						throw new Exception("Player doesn't exist");
					} else if (Team.contains(new Account("", sEmail, ""))) {
						throw new Exception("teammate already exists");
					} else {
						int index = system.accounts.indexOf(new Account("", sEmail, ""));
						if (system.accounts.get(index) instanceof Player) {
							Team.add((Player) system.accounts.get(index));
							System.out.println("Added Successfully");
						} else {
							throw new Exception("Player doesn't exist");
						}
					}
					break;
				case "2":
					System.out.println("Enter their email:");
					String rEmail = system.scanner.nextLine();
					if (!system.accounts.contains(new Account("", rEmail, ""))) {
						throw new Exception("Player doesn't exist");
					} else if (Team.contains(new Account("", rEmail, ""))) {
						throw new Exception("teammate already exists");
					} else {
						int index = system.accounts.indexOf(new Account("", rEmail, ""));
						if (system.accounts.get(index) instanceof Player) {
							Team.remove(index);
							System.out.println("Removed Successfully");
						} else {
							throw new Exception("Player doesn't exist");
						}
					}
					break;
				default:
					throw new Exception("Invalid choice");
				}
				break;
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

}
