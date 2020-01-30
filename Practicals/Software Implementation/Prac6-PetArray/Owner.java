class Owner {
    
	/**
	 * @directed true
	 */
	
    private Pet myPet;
	private String name;
	
	public Owner(String nm) {
	 	name = nm;
	 	myPet = null;
	} // constructor
	
	public void mkPet(String name, int age) {
	 	myPet = new Pet(name, age);
	} // mkPet

	public String getPetName() {
	 	if (myPet != null)
			return myPet.getName();
		else
			return "No pet";
	} // getPetName
	
	public boolean hasName(String nm) {
		return name.equals(nm);
	} // hasName
	
	public boolean equals(Object o) {
		if (o == null || !(o instanceof Owner))
			return false;
		return name.equals(((Owner) o).name);
	} // equals
	
} // Owner

