import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class ClosedAddressing {
	
	private final static int TABLE_SIZE = 101;
	static Name[] table;
	static Scanner read;
	static int i = 0;
	static int n;
	static int NoOfRecords=0;
	static ArrayList<String> temp;
	static int modStartTime = 0, modEndTime = 0, modDuration = 0 ;
	static int mulStartTime = 0, mulEndTime = 0, mulDuration = 0 ;
	static int searchStartTime = 0, searchEndTime = 0, searchAvgTime = 0;
	
	public static void Display() {
		
		for(int i=0;i<TABLE_SIZE;i++) {
			Name output = table[i];
			
			System.out.print("Index " + i + ": ");
			while(output!=null) {
				System.out.print(output.getValue()+"("+output.getMatric()+")"+"-->");
				output = output.getNext();
			}
			System.out.println("null");
			
		}
	}
	
	public static void put(int key, String value, String matric) {
		
		int hashcode = (key % TABLE_SIZE);
		
		if(table[hashcode] == null) {
			table[hashcode] = new Name(key, value, matric);
			
		}else {
			
			Name entry = table[hashcode];
			while(entry.getNext()!=null && entry.getKey()!=key ) {
				entry = entry.getNext();
			}
			if(entry.getKey() == key ) {
				entry.setStudentname(value);
				NoOfRecords-=1;
			}else {
				entry.setNext(new Name(key,value,matric));
			}
		}
		
	}
	
	public static void putCongruentialMethod(int key, String value, String matric)
	{
		//constant a
		double a = 8*Math.floor(TABLE_SIZE/23) + 5;
		double index = (a*key)%TABLE_SIZE;
		int hashcode = (int) index;
		
		if(table[hashcode] == null)
		{
			table[hashcode] = new Name(key, value, matric);
		}
		else
		{
			Name entry = table[hashcode];
			while(entry.getNext()!=null && entry.getKey()!=key ) {
				entry = entry.getNext();
			}
			if(entry.getKey() == key ) {
				entry.setStudentname(value);
			}else {
				entry.setNext(new Name(key,value, matric));
			}
		}
		
	}
	
	public static void HashObjects(int mode) {
		Scanner split, lf;
		String name, matric, file;
		int choice;
		temp = new ArrayList<String>();
		
		lf = new Scanner(System.in);
		do {
			System.out.println("\nLoad factor");
			System.out.println("1) 0.25");
			System.out.println("2) 0.5");
			System.out.println("3) 0.75");
			System.out.print("Enter your load factor: ");
			choice = lf.nextInt();
		}while(choice < 1 || choice >3);
		
		if(choice == 1) {
			file = "namelist1.txt";
		}else if(choice == 2) {
			file = "namelist2.txt";
		}else {
			file = "namelist3.txt";
		}
		System.out.print("\n");
		
		try {
			read = new Scanner(new File(file));
			if(mode == 1) {
				while(read.hasNextLine()) {
					String str = read.nextLine();
					split = new Scanner(str);
					split.useDelimiter(",");
					name = split.next();
					matric = split.next();
					temp.add(matric);
					put(Math.abs(matric.hashCode()), name, matric);
					NoOfRecords+=1;
				}
			}
			else {
				while(read.hasNextLine()) {
					String str = read.nextLine();
					split = new Scanner(str);
					split.useDelimiter(",");
					name = split.next();
					matric = split.next();
					temp.add(matric);
					putCongruentialMethod(Math.abs(matric.hashCode()), name, matric);
					NoOfRecords+=1;
				}
			}
			
		}catch(Exception e) {
			System.out.println("Cannot find file");
		}
	}
	
	public static String propersearch(String matric) {
		
		int count =0;
		int nodePosition =0;
		int key = Math.abs(matric.hashCode());
		int hashcode = key%TABLE_SIZE;
		Name searchEntry = table[hashcode];
		
		while(searchEntry !=null) {
			count++;
			if(searchEntry.getMatric().equals(matric)) {
				return ("Name: "+ searchEntry.getValue() +", " + "Index: " + hashcode 
						+ ", " + "Node: "+ nodePosition +", Number of Key Comparisons: " + count);
			}
			searchEntry = searchEntry.getNext();
			nodePosition += 1;
		}
		
		return "\nName not found.\nNumber of Key comparisons: " + count;
	}
	
	public static String propersearch_multplicative(String matric) {
		
		int count =0;
		int nodePosition =0;
		int key = Math.abs(matric.hashCode());
		double a = 8*Math.floor(TABLE_SIZE/23) + 5;
		double index = (a*key)%TABLE_SIZE;
		int hashcode = (int) index;
		Name searchEntry = table[hashcode];
		
		while(searchEntry !=null) {
			count++;
			if(searchEntry.getMatric().equals(matric)) {
				return ("Name: "+ searchEntry.getValue() +", " + "Index: " + hashcode 
						+ ", " + "Node: "+ nodePosition +", Number of Key Comparisons: " + count);
			}
			searchEntry = searchEntry.getNext();
			nodePosition += 1;
		}
		
		
		return "\nName not found.\nNumber of Key comparisons: " + count;
	}
	
	public static int modCpuSearchTime()
	{
		modStartTime = (int) System.nanoTime();
	    
		for(int i=0;i<temp.size();i++) {
	    	propersearch(temp.get(i));
	    }
	    
		modEndTime = (int) System.nanoTime();
	    modDuration = (modEndTime - modStartTime)/temp.size();
	    return modDuration;
	}
	
	public static int mulCpuSearchTime()
	{
		mulStartTime = (int) System.nanoTime();
	    
		for(int i=0;i<temp.size();i++) {
			propersearch_multplicative(temp.get(i));
	    }
	    
		mulEndTime = (int) System.nanoTime();
	    mulDuration = (mulEndTime - mulStartTime)/temp.size();
	    return mulDuration;
	}
	
	public static void main(String[] args) {
		
		String name, matric;
		String searchOutput= "";
		int choice,functionchoice, modTotalTime, mulTotalTime, averageTime;
		Scanner in = new Scanner(System.in);
		table = new Name[TABLE_SIZE];
		
		do {
			for(int i=0;i<TABLE_SIZE;i++) {
				table[i] = null;
			}
			
			System.out.println("Choose function: ");
			System.out.println("1) Mod Function");
			System.out.println("2) Multiplicative function");
			System.out.print("\nSelect option: ");
			functionchoice = in.nextInt();
			
			if(functionchoice == 1) {
				HashObjects(1);
			}
			else {
				HashObjects(2);
			}
	
			System.out.println("1) Display Table Output Function");
			System.out.println("2) Add new user");
			System.out.println("3) Search user");
			System.out.println("4) Check number of node");
			System.out.println("5) Display average CPU time for searching per user");
			System.out.println("6) Exit Menu");
			
			System.out.print("\nSelect option: ");
			choice = in.nextInt();
			
				switch(choice) {
					case 1:  System.out.print("\n");
							 Display();
							 System.out.print("\n");
							 break;
					case 2:  System.out.print("Enter user name: ");
							 in.nextLine();	
							 name = in.nextLine();
							 System.out.print("Enter Matriculation Number: ");
							 matric = in.nextLine();
							 if(functionchoice == 1) {
							 	 put(Math.abs(matric.hashCode()), name, matric);
							 }
							 else {
								 putCongruentialMethod(Math.abs(matric.hashCode()), name, matric);
							 }
							 NoOfRecords+=1;
							 System.out.println();
							 break;
					case 3:  System.out.print("Enter matriculation number: ");	
							 in.nextLine();
							 matric = in.nextLine();
							 searchStartTime = (int)System.nanoTime();
							 
							 if(functionchoice==1) {
								 searchOutput = propersearch(matric);
							 }
							 else {
								 searchOutput = propersearch_multplicative(matric);
							 }
							 searchEndTime = (int)System.nanoTime();
							 searchAvgTime = searchEndTime - searchStartTime;
							 System.out.println(searchOutput);
							 System.out.println("Average time spent in searching: " + searchAvgTime + " ns");
							 System.out.println();							
							 break;
					case 4:	 System.out.println("Node = " + NoOfRecords + "\n");
							 break;
					case 5: averageTime = 0;
							modTotalTime = 0;
							mulTotalTime = 0;
							
							if(functionchoice == 1) {
								 for(int i =0; i<30; i++) {
								 	modTotalTime = modTotalTime + modCpuSearchTime();
								 	System.out.print("The total CPU time taken for searching(Attempt " + i + "): " + modDuration + " ns\n");
								 }
								 averageTime = modTotalTime / 30;
							 }
							 else {
								 for(int i =0; i<30; i++) {
									 mulTotalTime = mulTotalTime + mulCpuSearchTime();
									 System.out.print("The total CPU time taken for searching(Attempt " + i + "): " + mulDuration + " ns\n");
								 }
								 averageTime = mulTotalTime / 30; 
							 }
							 System.out.println("\nThe average CPU time taken for searching is: " + averageTime + " ns\n");
							 break;
					case 6:  System.out.println("Exiting...");
							 break;
					default: System.out.println("Wrong selection!");
				}
		}while(choice > 0 && choice < 6);
	}
}
