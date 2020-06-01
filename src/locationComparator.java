import java.util.Comparator;

public class locationComparator implements  Comparator<Playground> {
    public int compare(Playground lhs, Playground rhs) {
        String pStreet =((Player)system.accounts.get(system.currentIndex)).getLocation().split("-")[0];
        String pNeighbourhood=((Player)system.accounts.get(system.currentIndex)).getLocation().split("-")[1];
        String pCity=((Player)system.accounts.get(system.currentIndex)).getLocation().split("-")[2];
        
        if(pCity==lhs.getAddress().split("0")[1].split("-")[2] 
        		&& rhs.getAddress().split("0")[1].split("-")[2]==pCity)
        {
        	
        	if(pNeighbourhood==lhs.getAddress().split("0")[1].split("-")[1] 
            		&& rhs.getAddress().split("0")[1].split("-")[1]==pNeighbourhood)
            {
            	
        		if(pStreet==lhs.getAddress().split("0")[1].split("-")[0] 
                		&& rhs.getAddress().split("0")[1].split("-")[0]==pStreet)
                {
                	
        			return 0;
                	
                	
                }
        		else if(pStreet==lhs.getAddress().split("0")[1].split("-")[0] 
                		&& rhs.getAddress().split("0")[1].split("-")[0]!=pStreet)
        		{
        			return -1;
        		}
        		else if(pStreet!=lhs.getAddress().split("0")[1].split("-")[0] 
                		&& rhs.getAddress().split("0")[1].split("-")[0]==pStreet)
        		{
        			return 1;
        		}
            	
            } 
        	else if(pNeighbourhood==lhs.getAddress().split("0")[1].split("-")[1] 
            		&& rhs.getAddress().split("0")[1].split("-")[1]!=pNeighbourhood)
        	{
        		return -1;
        	}
        	else if(pNeighbourhood!=lhs.getAddress().split("0")[1].split("-")[1] 
            		&& rhs.getAddress().split("0")[1].split("-")[1]==pNeighbourhood)
        	{
        		return 1;
        	}
        	
        	
        }
        else if(pCity==lhs.getAddress().split("0")[1].split("-")[2] 
        		&& rhs.getAddress().split("0")[1].split("-")[2]!=pCity)
        {
        	return -1;
        }
        else if(pCity!=lhs.getAddress().split("0")[1].split("-")[2] 
        		&& rhs.getAddress().split("0")[1].split("-")[2]==pCity)
        {
        	return 1;
        }
        return 0;
    }
}