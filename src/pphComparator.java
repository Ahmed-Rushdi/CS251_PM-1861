
import java.util.Comparator;

public class pphComparator implements  Comparator<Playground> {
	    public int compare(Playground lhs, Playground rhs) {
	        return lhs.getPph() > rhs.getPph() ? 1 : (lhs.getPph() < rhs.getPph()) ? -1 : 0;
	    }
	}