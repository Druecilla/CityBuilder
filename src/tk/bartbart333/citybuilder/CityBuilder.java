package tk.bartbart333.citybuilder;

public class CityBuilder {
	
	private static CityBuilder instance;
	
	public CityBuilder(){
		instance = this;
	}
	
	/**
	 * @author Barthold
	 * Returns the CityBuilder object.
	 * @return CityBuilder object.
	 */
	public static CityBuilder getInstance(){
		return instance;
	}
	
	public static void main(String[] args){
		new CityBuilder();
	}
}