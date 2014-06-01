package connexion;


public  class selectionner {

	public static String[] displayString(Object object) {
		String[] tab = ((String) object).split(" ");
		//	System.out.println("Nom : " + tab[0] + " Prenom : " + tab[1]);
		return tab;
    }
}