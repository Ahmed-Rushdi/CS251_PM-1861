
public class Administrator extends Account{
	Administrator(String U,String e,String P)
	{
		super(U,e,P);
	}
	public void approve(Playground toApprove)
	{
		toApprove.setPlaygroundState(State.AVALIABLE);
	}
	
}
