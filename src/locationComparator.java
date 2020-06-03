import java.util.Comparator;

public class locationComparator implements Comparator<Playground> {
	public int compare(Playground lhs, Playground rhs) {
		String pStreet = ((Player) system.accounts.get(system.currentIndex)).getLocation().split("-")[0];
		String pNeighbourhood = ((Player) system.accounts.get(system.currentIndex)).getLocation().split("-")[1];
		String pCity = ((Player) system.accounts.get(system.currentIndex)).getLocation().split("-")[2];

		if (pCity.equalsIgnoreCase(lhs.getAddress().split("0")[1].split("-")[2])
				&& rhs.getAddress().split("0")[1].split("-")[2].equalsIgnoreCase(pCity)) {

			if (pNeighbourhood.equalsIgnoreCase(lhs.getAddress().split("0")[1].split("-")[1])
					&& rhs.getAddress().split("0")[1].split("-")[1].equalsIgnoreCase(pNeighbourhood)) {

				if (pStreet.equalsIgnoreCase(lhs.getAddress().split("0")[1].split("-")[0])
						&& rhs.getAddress().split("0")[1].split("-")[0].equalsIgnoreCase(pStreet)) {

					return 0;

				} else if (pStreet.equalsIgnoreCase(lhs.getAddress().split("0")[1].split("-")[0])
						&& !rhs.getAddress().split("0")[1].split("-")[0].equalsIgnoreCase(pStreet)) {
					return -1;
				} else if (!pStreet.equalsIgnoreCase(lhs.getAddress().split("0")[1].split("-")[0])
						&& rhs.getAddress().split("0")[1].split("-")[0].equalsIgnoreCase(pStreet)) {
					return 1;
				}

			} else if (pNeighbourhood.equalsIgnoreCase(lhs.getAddress().split("0")[1].split("-")[1])
					&& !rhs.getAddress().split("0")[1].split("-")[1].equalsIgnoreCase(pNeighbourhood)) {
				return -1;
			} else if (!pNeighbourhood.equalsIgnoreCase(lhs.getAddress().split("0")[1].split("-")[1])
					&& rhs.getAddress().split("0")[1].split("-")[1].equalsIgnoreCase(pNeighbourhood)) {
				return 1;
			}

		} else if (pCity.equalsIgnoreCase(lhs.getAddress().split("0")[1].split("-")[2])
				&& !rhs.getAddress().split("0")[1].split("-")[2].equalsIgnoreCase(pCity)) {
			return -1;
		} else if (!pCity.equalsIgnoreCase(lhs.getAddress().split("0")[1].split("-")[2])
				&& rhs.getAddress().split("0")[1].split("-")[2].equalsIgnoreCase(pCity)) {
			return 1;
		}
		return 0;
	}
}