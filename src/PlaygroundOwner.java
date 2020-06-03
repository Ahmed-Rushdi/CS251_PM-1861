import java.util.ArrayList;

public class PlaygroundOwner extends Account {
	private eWallet wallet;
	private String Address;
	private String phoneNum;
	private ArrayList<Playground> playgrounds = new ArrayList<Playground>();

	@Override
	public String toString() {
		return "PlaygroundOwner [Address=" + Address + ", phoneNum=" + phoneNum + ", userName=" + userName + ", email="
				+ email + "]";
	}

	public ArrayList<Playground> getPlaygrounds() {
		return playgrounds;
	}

	PlaygroundOwner(String U, String e, String P, String L, String PN) {
		super(U, e, P);
		Address = L;
		phoneNum = PN;
		wallet = new eWallet();
		wallet.setBalance(0);
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public eWallet getWallet() {
		return wallet;
	}

	public void setWallet(eWallet wallet) {
		this.wallet = wallet;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public void displayProfile() {
		System.out.println("Name:" + userName);
		System.out.println("Email" + email);
		System.out.println("Phone number" + phoneNum);
		System.out.println("Address:" + Address);
		for (int i = 0; i < playgrounds.size(); i++) {
			System.out.println(playgrounds.get(i).toString());
		}
	}

	public void addPlayground(Playground p) {
		p.setOwner(this);
		playgrounds.add(p);
	}
}
