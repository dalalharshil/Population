/**
 *	City data - the city name, state name, location designation,
 *				and population est. 2017
 *
 *	@author	Harshil Dalal
 *	@since	1/12/23
 */
public class City implements Comparable<City> {
	
	// fields
	private String state;
	private String name;
	private String designation;
	private int population;

	// constructor
	public City(String state2, String name2, String designation2, int population2)
	{
		state = state2;
		name = name2;
		designation = designation2;
		population = population2;
	}

	/**	Compare two cities populations
	 *	@param other		the other City to compare
	 *	@return				the following value:
	 *		If populations are different, then returns (this.population - other.population)
	 *		else if states are different, then returns (this.state - other.state)
	 *		else returns (this.name - other.name)
	 */
	public int compareTo(City other)
	{
		if(this.population != other.population)
		{
			return this.population - other.population;
		} else if(!this.state.equalsIgnoreCase(other.state))
		{
			return this.state.compareTo(other.state);
		}
		return this.name.compareTo(other.name);
	}

	/**	Equal city name and state name
	 *	@param other		the other City to compare
	 *	@return				true if city name and state name equal; false otherwise
	 */
	public boolean equals(City other)
	{
		if(name.equals(other.name) && state.equals(other.state))
		{
			return true;
		}
		return false;
	}

	/**	Access population of citypopulation variable
	 * @return population variable
	 */
	public int getPopulation()
	{
		return population;
	}

	/**	Access name of city
	 * @return name variable
	 */
	public String getName()
	{
		return name;
	}

	/**	Access state that city is in
	 * @return state variable
	 */
	public String getState()
	{
		return state;
	}
	
	/**	toString */
	@Override
	public String toString() {
		return String.format("%-22s %-24s %-12s %,12d", state, name, designation,
						population);
	}
}