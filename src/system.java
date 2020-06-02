import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Pattern;

public class system {

	public static Scanner scanner = new Scanner(System.in);;
	public static ArrayList<Account> accounts = new ArrayList<Account>();
	public static ArrayList<Playground> playgrounds = new ArrayList<Playground>();

	public static int currentIndex = -1;

	public static String rand() {
		String charsNums = "0123456789" + "abcdefghijklmnopqrstuvxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		StringBuilder code = new StringBuilder(6);
		for (int i = 0; i < 6; i++) {
			int index = (int) (charsNums.length() * Math.random());

			code.append(charsNums.charAt(index));
		}

		return code.toString();
	}

	public static void main(String[] args) throws Exception {

		System.out.println("Welcome to GoFo playground reservation");
		registerMenu();
		int x;
	}

	public static void addPlayground() {

		System.out.println("Enter playground name:");
		String n = scanner.nextLine();
		System.out.println("Enter playground address following this format (Street-region-city)");
		String a = scanner.nextLine();
		System.out.println("Enter Pay Per Hour (PPH)");
		double pph = scanner.nextDouble();
		while (true) {

			try {
				Playground temp = new Playground(n, a, pph);
				((PlaygroundOwner) accounts.get(currentIndex)).addPlayground(temp);
				playgrounds.add(temp);
				break;
			} catch (Exception e) {
				System.err.println(e + " \n to cancel type c or any other input to retry");
				if (scanner.nextLine() == "c")
					break;
			}
		}
		accounts.get(currentIndex);
	}

	public static void registerMenu() throws Exception {
		String UN, EM, PW;

		System.out.println("Username");
		UN = scanner.nextLine();
		String regexName = "^[a-zA-Z0-9_@-]{4,}$";
		if (!Pattern.matches(regexName, UN)) {
			throw new Exception(
					"Username should be at least 4 chars in length, alphanumerical and can contain these special characters (_@-)");
		}

		System.out.println("Email");
		EM = scanner.nextLine();
		String regexEmail = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
		if (!Pattern.matches(regexEmail, EM)) {
			throw new Exception(
					"Email should follow this fromat( alphanumerical_.- + @ + alphanumerical + . + alphanumerical");
		}

		System.out.println("Password");
		PW = scanner.nextLine();
		String regexPassword = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{8,}$";
		if (!Pattern.matches(regexPassword, PW)) {
			throw new Exception(
					"Password should be at least 8 chars in length and has atleast 1 Uppercase, 1 Lowercase, 1 Number");
		}
		while (true) {
			String verifCode = rand();
			System.out.println("Enter Verifaction Code:" + verifCode);
			String x = scanner.nextLine();
			System.out.println(x);
			System.out.println(verifCode);
			if (x.equals(verifCode)) {
				break;
			}
			System.out.println("Invalid Verification code, Please Retry!");
		}
		Account temp = new Account(UN, EM, PW);
		boolean loop = true;
		int choice = 0;
		while (loop) {
			try {
				System.out.println("Choose Account type:");
				System.out.println("1-Player");
				System.out.println("2-Playground Owner");
				System.out.println("3-Administrator");
				System.out.println("4-MainMenu");
				choice = scanner.nextInt();
				switch (choice) {
				case 1:
					System.out.println("Enter your Street,Neighbourhood and City separated by a '-':");
					String L = scanner.nextLine();
					if (L.split("-").length != 3) {
						throw new Exception("Wrong location fromat");
					}
					temp = new Player(UN, EM, PW, L);
					loop = false;
					break;
				case 2:
					System.out.println("Enter Address:");
					String A = scanner.nextLine();
					System.out.println("Enter Phonenumber:");
					long PN = scanner.nextLong();
					temp = new PlaygroundOwner(UN, EM, PW, A, PN);
					loop = false;
					break;
				case 3:
					temp = new Administrator(UN, EM, PW);
					loop = false;
					break;
				case 4:
					loop = false;
					break;
				default:
					System.out.println("wrong choice, retry");
					break;
				}
			} catch (Exception e) {
				System.out.println(e);
			}

		}
		if (choice != 4)
			accounts.add(temp);

	}

	public static void loginMenu() {
		String U, P;

		System.out.println("Enter Username/Email");
		U = scanner.nextLine();

		System.out.println("Enter Password");
		P = scanner.nextLine();
		int i;
		for (i = 0; i < accounts.size(); i++) {
			if (accounts.get(i).login(U, P)) {
				currentIndex = i;
				break;
			}
		}
		if (i == accounts.size()) {
			System.out.println("Invalid login info, please retry!");
			System.out.println("e to Main menu");
			if (scanner.nextLine() != "e") {
				loginMenu();
			}
		}
	}

	public static void logout() {
		currentIndex = -1;
	}

	public static void bookPlayground(Playground toBook, String date) {
		String from, to;
		int choice = 0;
		ArrayList<Player> toInvite = new ArrayList<Player>();

		boolean loop = true;
		while (loop) {
			System.out.println("Choose Who to invite");
			System.out.println("1-Enter individual name and email");
			System.out.println("2-Your Team");
			System.out.println("3-Proceed");
			System.out.println("4-Remove a player from the List");
			System.out.println("4-Back");
			switch (choice = scanner.nextInt()) {
			case 1:
				System.out.println("Enter Username");
				String name = scanner.nextLine();
				System.out.println("Enter email");
				String email = scanner.nextLine();
				boolean found = false;
				int i;
				for (i = 0; i < accounts.size(); i++) {
					if (accounts.get(i).getUserName() == name && accounts.get(i).getEmail() == email
							&& accounts.get(i) instanceof Player && !(toInvite.contains(accounts.get(i)))) {
						found = true;
						toInvite.add((Player) accounts.get(i));
					}
				}
				if (toInvite.contains(accounts.get(i))) {
				} else if (found == false) {
					System.out.println("Player not found in the system");
				}
				break;
			case 2:
				for (int j = 0; j < ((Player) accounts.get(currentIndex)).getTeam().size(); j++) {
					if (!(toInvite.contains(((Player) accounts.get(currentIndex)).getTeam().get(j)))) {
						toInvite.add(((Player) accounts.get(currentIndex)).getTeam().get(j));
					}
				}

				break;
			case 3:
				if (!(toInvite.contains(accounts.get(currentIndex)))) {
					toInvite.add((Player) accounts.get(currentIndex));
				}
				loop = false;
				break;
			case 4:
				if (toInvite.size() == 0) {
					System.out.println("There are no players in the List");
				} else {
					for (int k = 0; k < toInvite.size(); k++) {
						System.out.println((k + 1) + "-" + toInvite.get(k));
					}
					int toRemove = scanner.nextInt();
					if (toRemove >= toInvite.size() && toRemove <= 0) {
						System.out.println("wrong choice");
					} else {
						toInvite.remove(toRemove - 1);
					}
				}
				break;
			case 5:
				break;
			default:
				System.out.println("Wrong choice!");
			}
		}
		if (choice != 5) {

			try {
				System.out.println("Please enter starting hour (24-hour format)");
				from = scanner.nextLine();
				System.out.println("Please enter ending hour (24-hour format)");
				to = scanner.nextLine();
				double total = (double) (Integer.parseInt(to) - Integer.parseInt(from)) * toBook.getPph();
				if ((Integer.parseInt(from) < toBook.getAvaliableTime().from
						|| Integer.parseInt(from) > toBook.getAvaliableTime().to)
						|| (Integer.parseInt(to) < toBook.getAvaliableTime().from
								|| Integer.parseInt(to) > toBook.getAvaliableTime().to)) {
					throw new Exception("Hours not avaliable");
				}
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM HH");
				from = date + " " + from;
				to = date + " " + to;
				Booking temp = new Booking(formatter.parse(from), formatter.parse(to), toBook, toInvite);

				for (int i = 0; i < toBook.getBookings().size(); i++) {
					if (toBook.getBookings().get(i).collides(temp)) {
						throw new Exception("The hours entred collide with other bookings in the playground");
					}
				}
				System.out.println("total :" + total * toBook.getPph() + " EGP");
				System.out.println("Proceed?(Y/N will take you back to main menu)");
				int choice2 = scanner.nextInt();
				if ((choice2 == 'Y' || choice2 == 'y')
						&& ((Player) accounts.get(currentIndex)).getWallet().getBalance() >= total) {
					((Player) accounts.get(currentIndex)).getWallet()
							.setBalance(((Player) accounts.get(currentIndex)).getWallet().getBalance() - total);
					toBook.getOwner().getWallet().setBalance(toBook.getOwner().getWallet().getBalance() + total);
					temp.invitePlayers();
					toBook.getBookings().add(temp);
					((Player) accounts.get(currentIndex)).getBookings().add(temp);
				} else if (((Player) accounts.get(currentIndex)).getWallet().getBalance() < total) {
					System.out.println("insufficient balance");
				}
			} catch (Exception e) {
				System.out.println("incorrect date entered" + e);
			}
		}
	}

	public static void approveMenu() {
		@SuppressWarnings("unchecked")
		ArrayList<Playground> waiting = (ArrayList<Playground>) playgrounds.clone();
		for (Playground temp : waiting) {
			if (temp.getPlaygroundState() != State.WAITING) {
				waiting.remove(temp);
			}
		}
		System.out.println("Here is a list of waiting playgrounds");
		int i = 1;
		for (Playground temp : waiting) {
			System.out.println(i + "-" + temp);
			i++;
		}
		i = scanner.nextInt();
		((Administrator) accounts.get(currentIndex)).approve(waiting.get(i));

	}

	public static void viewFilter() {
		System.out.println("How do you want to display the playgrounds");
		System.out.println("1-Sort by Location");
		System.out.println("2-Sort by Price per hour");
		System.out.println("3-Filter by Avaliable time");
		System.out.println("Press anything else to go back");
		int choice = 0;
		String date;
		@SuppressWarnings("unchecked")
		ArrayList<Playground> avaliable = (ArrayList<Playground>) playgrounds.clone();
		switch (scanner.nextInt()) {
		case 1:
			Collections.sort(avaliable, new locationComparator());
			for (int i = avaliable.size() - 1; i >= 0; i--) {
				if (avaliable.get(i).getPlaygroundState() != State.AVALIABLE) {
					avaliable.remove(i);
				}
			}

			while (true) {
				try {
					for (int i = 0; i < avaliable.size(); i++) {
						System.out.println((i + 1) + "-" + avaliable.get(i));
					}
					System.out.println("Your choice:");
					choice = scanner.nextInt();
					System.out.println("Please enter date (dd-MM)");
					date = scanner.nextLine();
					avaliable.get(choice).displaySlots(new SimpleDateFormat("dd-MM").parse(date));
					System.out.println("Do you want to book a slot (Y/N)");
					if (scanner.nextLine() == "Y" || scanner.nextLine() == "y") {
						bookPlayground(avaliable.get(choice), date);
					}
					break;
				} catch (ParseException e) {
					System.out.println("Invalid date entered");
				}
			}
			break;
		case 2:

			Collections.sort(avaliable, new pphComparator());
			for (int i = avaliable.size() - 1; i >= 0; i--) {
				if (avaliable.get(i).getPlaygroundState() != State.AVALIABLE) {
					avaliable.remove(i);
				}
			}

			while (true) {
				try {
					for (int i = 0; i < avaliable.size(); i++) {
						System.out.println((i + 1) + "-" + avaliable.get(i));
					}
					System.out.println("Your choice:");
					choice = scanner.nextInt();
					System.out.println("Please enter date (dd-MM)");
					date = scanner.nextLine();
					avaliable.get(choice).displaySlots(new SimpleDateFormat("dd-MM").parse(date));
					System.out.println("Do you want to book a slot (Y/N)");
					if (scanner.nextLine() == "Y" || scanner.nextLine() == "y") {
						bookPlayground(avaliable.get(choice), date);
					}
					break;
				} catch (ParseException e) {
					System.out.println("Invalid date entered");
				}
			}
			break;
		case 3:
			try {
				ArrayList<Playground> matched = timeFilter();
				for (int i = matched.size() - 1; i >= 0; i--) {
					if (matched.get(i).getPlaygroundState() != State.AVALIABLE) {
						matched.remove(i);
					}
				}

				while (true) {
					try {
						for (int i = 0; i < matched.size(); i++) {
							System.out.println((i + 1) + "-" + matched.get(i));
						}
						System.out.println("Your choice:");
						choice = scanner.nextInt();
						System.out.println("Please enter date (dd-MM)");
						date = scanner.nextLine();
						matched.get(choice).displaySlots(new SimpleDateFormat("dd-MM").parse(date));
						System.out.println("Do you want to book a slot (Y/N)");
						if (scanner.nextLine() == "Y" || scanner.nextLine() == "y") {
							bookPlayground(matched.get(choice), date);
						}
						break;
					} catch (ParseException e) {
						System.out.println("Invalid date entered");
					}
				}
			} catch (ParseException e) {

				System.out.println("Incorrect date entered");
			}
			break;
		default:

		}
	}

	public static ArrayList<Playground> timeFilter() throws ParseException {
		@SuppressWarnings("unchecked")
		ArrayList<Playground> matched = (ArrayList<Playground>) playgrounds.clone();
		String date, from, to;

		System.out.println("Please enter date (dd-MM)");
		date = scanner.nextLine();
		System.out.println("Please enter starting hour (24-hour format)");
		from = scanner.nextLine();
		System.out.println("Please enter ending hour (24-hour format)");
		to = scanner.nextLine();
		for (int i = matched.size() - 1; i >= 0; i--) {
			if ((Integer.parseInt(from) < matched.get(i).getAvaliableTime().from
					|| Integer.parseInt(from) > matched.get(i).getAvaliableTime().to)
					|| (Integer.parseInt(to) < matched.get(i).getAvaliableTime().from
							|| Integer.parseInt(to) > matched.get(i).getAvaliableTime().to)) {
				matched.remove(i);
				break;
			}
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM HH");
			from = date + " " + from;
			to = date + " " + to;
			Booking temp = new Booking(formatter.parse(from), formatter.parse(to));
			for (int j = 0; j < matched.get(i).getBookings().size(); j++) {
				if (matched.get(i).getBookings().get(j).collides(temp)) {
					matched.remove(i);
					break;
				}
			}
		}
		return matched;
	}

}
