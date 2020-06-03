
public class Administrator extends Account {
	Administrator(String U, String e, String P) {
		super(U, e, P);
	}

	@Override
	public String toString() {
		return "Administrator [userName=" + userName + ", email=" + email + "]";
	}

	public void approve(Playground toApprove) {
		toApprove.setPlaygroundState(State.AVALIABLE);
	}

}
