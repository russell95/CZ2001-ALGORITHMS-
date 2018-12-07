import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class GenerateGraph {
	public static void main(String args[]) {
		
		//8 inputs
		String[] cities = {"Beijing", "HongKong", "LosAngeles", "Seoul", "Shanghai", "Singapore", "Sydney", "Tokyo"};
		
		//30 inputs
//		String[] cities = {"Beijing", "HongKong", "LosAngeles", "Seoul", "Shanghai", "Singapore", "Sydney", "Tokyo",
//							"Paris", "Bangkok", "NewYork", "KualaLumpur", "Dubai", "Istanbul", "Bei'an ", "Beihai", "Beipiao",
//							"Beirut", "Béjaïa ", "Rome", "LasVegas", "Miami", "Toronto", "Barcelona", "Dublin", "Amsterdam",
//							"Moscow", "CairoPrague", "Vienna", "Madrid"};
		
		//50 inputs
//		String[] cities = {"Beijing", "HongKong", "LosAngeles", "Seoul", "Shanghai", "Singapore", "Sydney", "Tokyo",
//							"Paris", "Bangkok", "NewYork", "KualaLumpur", "Dubai", "Istanbul", "Bei'an ", "Beihai", "Beipiao",
//							"Beirut", "Béjaïa ", "Rome", "LasVegas", "Miami", "Toronto", "Barcelona", "Dublin", "Amsterdam",
//							"Moscow", "CairoPrague", "Vienna", "Madrid", "SanFrancisco", "VancouverBudapest", "RioDeJaneiro",
//							"Berlin", "MexicoCity", "BuenosAires", "St.Petersburg", "Athens", "JerusalemSeattle", "Delhi",
//							"Beersheba", "Mumbai", "Munich", "Venice", "Florence", "CapeTown", "WashingtonD.C.", "Montreal",
//							"Atlanta", "Boston"};
		
		//80 inputs
//		String[] cities = {"Beijing", "HongKong", "LosAngeles", "Seoul", "Shanghai", "Singapore", "Sydney", "Tokyo",
//							"Paris", "Bangkok", "NewYork", "KualaLumpur", "Dubai", "Istanbul", "Bei'an ", "Beihai", "Beipiao",
//							"Beirut", "Béjaïa ", "Rome", "LasVegas", "Miami", "Toronto", "Barcelona", "Dublin", "Amsterdam",
//							"Moscow", "CairoPrague", "Vienna", "Madrid", "SanFrancisco", "VancouverBudapest", "RioDeJaneiro",
//							"Berlin", "MexicoCity", "BuenosAires", "St.Petersburg", "Athens", "JerusalemSeattle", "Delhi",
//							"Beersheba", "Mumbai", "Munich", "Venice", "Florence", "CapeTown", "WashingtonD.C.", "Montreal",
//							"Atlanta", "Boston", "Orlando", "Brussels", "Chennai", "MarrakeshPhuket", "Edirne", "Bali",
//							"Copenhagen", "SãoPaulo", "Bayannur", "Bayawan", "Baybay", "Bazhou", "BeauBassin", "Beaumont",
//							"Beawar", "Béchar", "Agra", "Varna", "Riyadh", "Jakarta", "Auckland", "Honolulu", "Edinburgh",
//							"Wellington", "NewOrleans", "Petra", "Melbourne", "Luxor", "Taipei", "Hànoi"};
		
		//100 inputs
//		String[] cities = {"Beijing", "HongKong", "LosAngeles", "Seoul", "Shanghai", "Singapore", "Sydney", "Tokyo",
//							"Paris", "Bangkok", "NewYork", "KualaLumpur", "Dubai", "Istanbul", "Bei'an ", "Beihai", "Beipiao",
//							"Beirut", "Béjaïa ", "Rome", "LasVegas", "Miami", "Toronto", "Barcelona", "Dublin", "Amsterdam",
//							"Moscow", "CairoPrague", "Vienna", "Madrid", "SanFrancisco", "VancouverBudapest", "RioDeJaneiro",
//							"Berlin", "MexicoCity", "BuenosAires", "St.Petersburg", "Athens", "JerusalemSeattle", "Delhi",
//							"Beersheba", "Mumbai", "Munich", "Venice", "Florence", "CapeTown", "WashingtonD.C.", "Montreal",
//							"Atlanta", "Boston", "Orlando", "Brussels", "Chennai", "MarrakeshPhuket", "Edirne", "Bali",
//							"Copenhagen", "SãoPaulo", "Bayannur", "Bayawan", "Baybay", "Bazhou", "BeauBassin", "Beaumont",
//							"Beawar", "Béchar", "Agra", "Varna", "Riyadh", "Jakarta", "Auckland", "Honolulu", "Edinburgh",
//							"Wellington", "NewOrleans", "Petra", "Melbourne", "Luxor", "Taipei", "Hànoi", "Philadelphia",
//							"Chicago", "SanDiego", "Stockholm", "Cancún", "Warsaw", "SharmEl-SheikhDallas", "HoChíMinh",
//							"Milan", "Oslo", "LibsonPuntaCana", "Johannesburg", "Antalya", "Mecca", "Macau", "Pattaya",
//							"Guangzhou", "Kiev", "Shenzhen", "Bucharest"};
		
		int noOfCities = cities.length;
		int adjacent[][] = new int[noOfCities][noOfCities];
//		String cities[] = new String[noOfCities];
//		
//		cities[0] = "Beijing";
//		cities[1] = "HongKong";
//		cities[2] = "LosAngeles";
//		cities[3] = "Seoul";
//		cities[4] = "Shanghai";
//		cities[5] = "Singapore";
//		cities[6] = "Sydney";
//		cities[7] = "Tokyo";
		
		
		for(int i=0;i<noOfCities;i++) {
			for(int j=i;j<noOfCities;j++) {
					Random rand = new Random();
					int value = rand.nextInt(2);
					
					adjacent[i][j] = value;
					adjacent[j][i] = value;
			}
			adjacent[i][i] = 0;
		}
		
		adjacent[0][0] = 0;
		adjacent[0][1] = 1;
		adjacent[0][2] = 0;
		adjacent[0][3] = 0;
		adjacent[0][4] = 1;
		adjacent[0][5] = 1;
		adjacent[0][6] = 0;
		adjacent[0][7] = 1;
		
		adjacent[1][0] = 1;
		adjacent[1][1] = 0;
		adjacent[1][2] = 0;
		adjacent[1][3] = 0;
		adjacent[1][4] = 1;
		adjacent[1][5] = 1;
		adjacent[1][6] = 0;
		adjacent[1][7] = 0;
		
		adjacent[2][0] = 0;
		adjacent[2][1] = 0;
		adjacent[2][2] = 0;
		adjacent[2][3] = 1;
		adjacent[2][4] = 0;
		adjacent[2][5] = 0;
		adjacent[2][6] = 0;
		adjacent[2][7] = 1;
		
		adjacent[3][0] = 0;
		adjacent[3][1] = 0;
		adjacent[3][2] = 1;
		adjacent[3][3] = 0;
		adjacent[3][4] = 1;
		adjacent[3][5] = 1;
		adjacent[3][6] = 0;
		adjacent[3][7] = 1;
		
		adjacent[4][0] = 1;
		adjacent[4][1] = 1;
		adjacent[4][2] = 0;
		adjacent[4][3] = 1;
		adjacent[4][4] = 0;
		adjacent[4][5] = 1;
		adjacent[4][6] = 0;
		adjacent[4][7] = 1;
		
		adjacent[5][0] = 1;
		adjacent[5][1] = 1;
		adjacent[5][2] = 0;
		adjacent[5][3] = 1;
		adjacent[5][4] = 1;
		adjacent[5][5] = 0;
		adjacent[5][6] = 1;
		adjacent[5][7] = 1;
		
		adjacent[6][0] = 0;
		adjacent[6][1] = 0;
		adjacent[6][2] = 0;
		adjacent[6][3] = 0;
		adjacent[6][4] = 0;
		adjacent[6][5] = 1;
		adjacent[6][6] = 0;
		adjacent[6][7] = 0;
		
		adjacent[7][0] = 1;
		adjacent[7][1] = 0;
		adjacent[7][2] = 1;
		adjacent[7][3] = 1;
		adjacent[7][4] = 1;
		adjacent[7][5] = 1;
		adjacent[7][6] = 0;
		adjacent[7][7] = 0;
		
		
		//print matrix
		for(int i=0;i<noOfCities;i++) {
			for(int j=0;j<noOfCities;j++) {
				System.out.print(adjacent[i][j]);
				System.out.print(" ");
			}
			System.out.println();
		}
		
		

		
		try {
			PrintWriter writer = new PrintWriter("src/Cities.txt");
			
			writer.println(noOfCities);
			for(int i=0;i<noOfCities;i++) {
				writer.println(cities[i]);
			}
			for(int i=0;i<noOfCities;i++) {
				for(int j=0;j<noOfCities;j++) {
					if(adjacent[i][j]==1) {
						writer.println(cities[i] +" "+ cities[j]);
					}
				}
			}
			
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
