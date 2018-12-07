
public class Name {

	private int key;
	private String StudentName;
	private String Matriculation;
	private Name next;
	
	
	Name(int key, String value, String matric){
		this.key = key;
		this.StudentName = value;
		this.Matriculation = matric;
		this.next = null;
	}
	
	public String getValue() {
		return StudentName;
	}
	
	public int getKey() {
		return key;
	}
	
	public Name getNext() {
		return next;
	}
	
	public String getMatric() {
		return Matriculation;
	}
	
	public void setStudentname(String value) {
		this.StudentName = value;
	}
	
	public void setNext(Name next) {
		this.next = next;
	}
	
	public void setMatriculation(String matric) {
		this.Matriculation = matric;
	}
	
}
