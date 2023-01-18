import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *	Population - :	We have	a list of over 30,000 USA cities and their populations. It would be	helpful	if the data could be sorted	in different
 * and	helpful	ways to	answer important questions.
 *
 *	Requires FileUtils and Prompt classes.
 *
 *	@author	Harshil Dalal
 *	@since  1/12/23
 */
public class Population {
	
	// List of cities
	private List<City> cities;
	
	// US data file
	private final String DATA_FILE = "usPopData2017.txt";

	/**
	 *  main method
	 */
	public static void main(String[] args)
	{
		Population poppa = new Population();
		poppa.inputCities();
		poppa.printIntroduction();
		poppa.run();
	}

	/**	run method
	 * Prompts for selection (1-6) of which sort to print out using printf
	 */
	public void run()
	{
		int choice = 0;
		while(choice != 9)
		{
			if(choice != 7 && choice != 8)printMenu();
			choice = Prompt.getInt("Enter selection");
			System.out.println();
			long startMillisec = 0;
			long endMillisec = 0;
			int length = 0;
			if(choice == 1)
			{
				startMillisec = System.currentTimeMillis();
				ascendingPop(cities);
				endMillisec = System.currentTimeMillis();
				System.out.println("Fifty least populous cities");
				length = 50;
			} else if(choice == 2)
			{
				startMillisec = System.currentTimeMillis();
				descendingPop(cities);
				endMillisec = System.currentTimeMillis();
				System.out.println("Fifty most populous cities");
				 length = 50;
			} else if(choice == 3)
			{
				startMillisec = System.currentTimeMillis();
				ascendingName(cities);
				endMillisec = System.currentTimeMillis();
				System.out.println("Fifty cities sorted by name");
				length = 50;
			} else if(choice == 4)
			{
				startMillisec = System.currentTimeMillis();
				descendingName(cities);
				endMillisec = System.currentTimeMillis();
				System.out.println("Fifty cities sorted by name descending");
				length = 50;
			} else if(choice == 5)
			{
				boolean isState = false;
				String stateName = "";
				while(!isState)
				{
					stateName = Prompt.getString("Enter state name (ie. Alabama)");
					for(int i=0; i<cities.size(); i++)
					{
						if(cities.get(i).getState().equalsIgnoreCase(stateName))
						{
							isState = true;
						}
					}
					if(!isState)
					{
						System.out.println("ERROR: " + stateName + " is not valid");
					}
				}
				startMillisec = System.currentTimeMillis();
				descendingCitiesState(cities, stateName);
				endMillisec = System.currentTimeMillis();
				System.out.println("\nFifty most populous cities in named state");
				length = 50;
			} else if(choice == 6)
			{
				boolean isCity = false;
				String cityName = "";
				while(!isCity)
				{
					cityName = Prompt.getString("Enter city name");
					for(int i=0; i<cities.size(); i++)
					{
						//System.out.println(cities.get(i).getName());
						if(cities.get(i).getName().trim().equalsIgnoreCase(cityName))
						{
							isCity = true;
						}
					}
					if(!isCity)
					{
						System.out.println("ERROR: " + cityName + " is not valid");
					}
				}
				startMillisec = System.currentTimeMillis();
				citiesOfName(cities, cityName);
				endMillisec = System.currentTimeMillis();
				System.out.println("\nCity " + cityName + " by population");
				length = cities.size();
			}
			if(choice != 7 && choice != 8 && choice != 9) System.out.printf("%2s  %-22s %-24s %-12s %12s\n", "", "State", "Name", "Designation", "Population");
			for(int i=0; i<length; i++)
			{
				System.out.printf("%2d: " + cities.get(i).toString() + "\n", (i+1));
			}
			if(choice != 7 && choice != 8 && choice != 9)
			{
				System.out.println("\nElapsed Time " + (endMillisec-startMillisec) + " milliseconds");
				System.out.println();
				inputCities();
			}
		}
		System.out.println("Thank you for using Population!");
	}

	/**	Uses selection sort to sort arraylist by ascending population
	 * @param List <City> arr of all Cities
	 */
	public void ascendingPop(List <City> arr)
	{
		for(int i=arr.size()-1; i>0; i--)
		{
			City max = arr.get(i);
			int greatestIndex = i;
			for(int j=0; j<=i; j++)
			{
				if(arr.get(j).compareTo(max) >= 0)
				{
					greatestIndex = j;
					max = arr.get(j);
				}
			}
			swap(arr, i, greatestIndex);
		}
	}

	/**	Uses insertion sort to sort arraylist by ascending name (compareTo)
	 * @param List <City> arr of all Cities
	 */
	public void ascendingName(List <City> arr)
	{
		for(int i=1; i<arr.size(); i++)
		{
			City cur = arr.get(i);
			int previous = i-1;
			while((previous) >= 0 && (arr.get(previous).getName().compareTo(cur.getName()) > 0))
			{
				arr.set(previous+1, arr.get(previous));
				previous--;
			}
			arr.set(previous+1, cur);
		}
	}

	/**	Uses merge sort calling mergeArr() to sort arraylist by descending population
	 * @param List <City> arr of all Cities
	 */
	public void descendingPop(List <City> arr)
	{
		if (arr.size() == 1)
		{
			return;
		}
		int middle = arr.size()/2;
		List <City> left = new java.util.ArrayList<City>();
		List <City> right = new java.util.ArrayList<City>();
		for (int i = 0; i < middle; i++)
		{
			left.add(arr.get(i));
		}
		for (int i = middle; i < arr.size(); i++)
		{
			right.add(arr.get(i));
		}
		descendingPop(left);
		descendingPop(right);
		mergeArr1(arr, left, right);
	}

	/**	Uses merge sort calling mergeArr() to sort arraylist by descending name (compareTo)
	 * @param List <City> arr of all Cities
	 */
	public void descendingName(List <City> arr)
	{
		if (arr.size() == 1)
		{
			return;
		}
		int middle = arr.size()/2;
		List <City> left = new java.util.ArrayList<City>();
		List <City> right = new java.util.ArrayList<City>();
		for (int i = 0; i < middle; i++)
		{
			left.add(arr.get(i));
		}
		for (int i = middle; i < arr.size(); i++)
		{
			right.add(arr.get(i));
		}
		descendingName(left);
		descendingName(right);
		mergeArr2(arr, left, right);
	}

	/**	Takes in left and right half of array of Cities and performs merge sort
	 *  Sorts by descending population
	 * @param List <City> arr of all Cities, List <City> left, List <City> right
	 */
	public int mergeArr1(List <City> arr, List <City> left, List <City> right)
	{
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < left.size() || j < right.size())
		{
			if (i == left.size()) {
				arr.set(k, right.get(j));
				j++;
			} else if (j == right.size()) {
				arr.set(k, left.get(i));
				i++;
			} else if (left.get(i).compareTo(right.get(j)) > 0) {
				arr.set(k, left.get(i));
				i++;
			} else {
				arr.set(k, right.get(j));
				j++;
			}
			k++;
		}
		return 0;
	}

	/**	Takes in left and right half of array of Cities and performs merge sort
	 * Sorts by ascending name
	 * @param List <City> arr of all Cities, List <City> left, List <City> right
	 */
	public int mergeArr2(List <City> arr, List <City> left, List <City> right)
	{
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < left.size() || j < right.size())
		{
			if (i == left.size()) {
				arr.set(k, right.get(j));
				j++;
			} else if (j == right.size()) {
				arr.set(k, left.get(i));
				i++;
			} else if (left.get(i).getName().compareTo(right.get(j).getName()) > 0) {
				arr.set(k, left.get(i));
				i++;
			} else {
				arr.set(k, right.get(j));
				j++;
			}
			k++;
		}
		return 0;
	}

	/**	Uses bubble sort to sort all the cities of a state by descending population
	 * @param List <City> arr of all Cities, String state
	 */
	public void descendingCitiesState(List <City> arr, String state)
	{
		for(int i=0; i<arr.size(); i++)
		{
			if(!arr.get(i).getState().equalsIgnoreCase(state))
			{
				arr.remove(i);
				i--;
			}
		}
		for(int i=0; i<arr.size()-1; i++)
		{
			for(int j=i+1; j<arr.size(); j++)
			{
				if(arr.get(i).compareTo(arr.get(j)) < 0)
				{
					swap(arr, i, j);
				}
			}
		}
	}

	/**	Uses selection sort to sort arraylist by ascending population
	 * @param List <City> arr of all Cities
	 */
	public void citiesOfName(List <City> arr, String city)
	{
		for(int i=0; i<arr.size(); i++)
		{
			if(!arr.get(i).getName().trim().equalsIgnoreCase(city))
			{
				arr.remove(i);
				i--;
			}
		}
		for(int i=0; i<arr.size()-1; i++)
		{
			for(int j=i+1; j<arr.size(); j++)
			{
				if(arr.get(i).compareTo(arr.get(j)) < 0)
				{
					swap(arr, i, j);
				}
			}
		}
	}

	/**	Swaps the values at index x and y of Cities
	 * @param List <City> arr of all Cities, int x, int y
	 */
	private void swap(List <City> arr, int x, int y)
	{
		City temp = arr.get(x);
		arr.set(x, arr.get(y));
		arr.set(y, temp);
	}

	/** Reads file usPopData2017.txt and inputs each City
	 * With attributes of state, name, designation, and population with the use of delimeters
	 */
	public void inputCities()
	{
		cities = new ArrayList<City>(10);
		Scanner input = FileUtils.openToRead(DATA_FILE);
		input.useDelimiter("[\t\n]");
		while(input.hasNext())
		{
			String state = input.next();
			String city = "";
			String name = input.next();
			while((name.charAt(0) >= 65 && name.charAt(0) <= 90) || name.charAt(0) == '(')
			{
				city += name + " ";
				name = input.next();
			}
			city.substring(0, city.length());
			String designation = name;
			int pop = input.nextInt();
			cities.add(new City(state, city, designation, pop));
			input.nextLine();
		}
	}

	/**	Prints the introduction to Population */
	public void printIntroduction() {
		System.out.println("   ___                  _       _   _");
		System.out.println("  / _ \\___  _ __  _   _| | __ _| |_(_) ___  _ __ ");
		System.out.println(" / /_)/ _ \\| '_ \\| | | | |/ _` | __| |/ _ \\| '_ \\ ");
		System.out.println("/ ___/ (_) | |_) | |_| | | (_| | |_| | (_) | | | |");
		System.out.println("\\/    \\___/| .__/ \\__,_|_|\\__,_|\\__|_|\\___/|_| |_|");
		System.out.println("           |_|");
		System.out.println();
	}
	
	/**	Print out the choices for population sorting */
	public void printMenu() {
		System.out.println("1. Fifty least populous cities in USA (Selection Sort)");
		System.out.println("2. Fifty most populous cities in USA (Merge Sort)");
		System.out.println("3. First fifty cities sorted by name (Insertion Sort)");
		System.out.println("4. Last fifty cities sorted by name descending (Merge Sort)");
		System.out.println("5. Fifty most populous cities in named state");
		System.out.println("6. All cities matching a name sorted by population");
		System.out.println("9. Quit");
	}
	
}