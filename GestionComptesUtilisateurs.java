/**
* Cette classe represente le programme de comptes d'utilisateurs pour le TP2
*
* @author -
* Code permanent : -
* Courriel : -
* Cours : -
* @version 2022-16-03
*/

public class GestionComptesUtilisateurs {
	public static final String VERSION = "APPLICATION D'AUTHENTIFICATION - PROTOTYPE VERSION 1.0";
	public static final String MENU_1 = "\n====\nMENU\n====\n"
			+ "1. Creer un compte\n"
			+ "2. S'authentifier\n"
			+ "3. Quitter\n";
	public static final String MENU_2 = "\n====\nMENU\n====\n"
			+ "1. Modifier le mot de passe\n"
			+ "2. Supprimer le compte\n"
			+ "3. Se deconnecter\n";
	public static final String CHOIX_MENU = "Entrez votre choix au menu : ";
	public static final String ERR_MENU = "\nErreur, choix de menu invalide! Recommencez...";
	public static final String MSG_CREE_NOM = "Entrez le nom d'utilisateur (<ENTREE> pour annuler) : ";
	public static final String ANNULEE = "\n*** OPERATION ANNULEE ! ***";
	public static final String ENTREE = "\nTapez sur <ENTREE> pour revenir au menu...";
	public static final String ERR_LONGUEUR_NOM = "\nErreur, le nom doit contenir entre 3 et 25 caracteres! Recommencez...";
	public static final String ERR_CONTENU_NOM = "\nErreur, le nom ne doit contenir que des lettres, des chiffres \n"
			+ "ou les caracteres POINT (.) et TRAIT BAS (_). Recommencez...";
	public static final String ERR_LONGUEUR_MOT = "\nErreur, le mot de passe doit contenir entre 8 et 16 caracteres! Recommencez...";
	public static final String MSG_CREE_MOT = "Entrez le mot de passe (<ENTREE> pour annuler) : ";
	public static final String MSG_MODIFIER_MOT = "Entrez le nouveau mot de passe (<ENTREE> pour annuler) : ";
	public static final String ERR_CONTENU_MOT = "\nErreur, le mot de passe ne doit contenir que des lettres, des chiffres \n"
			+ "ou les caracteres speciaux *?#@!.+&. Recommencez...";
	public static final String ERR_CONTENU_MOT_2 = "\nErreur, le mot de passe doit contenir : \n"
			+ "  1) au moins 2 lettres majuscules\n"
			+ "  2) au moins 1 caractere special parmi *?#@!.+&\n"
			+ "  3) au moins 1 chiffre\n\nRecommencez... ";
	public static final String ERR_NOM_EXISTE = "\n==> Un compte avec ce nom d'utilisateur existe deja ! <==";
	public static final String SUCCES = "\n==> Compte cree avec succes ! <==";
	public static final String FIN = "\n\nFin normale du programme.";
	public static final String UTILISATEUR = "Nom d'utilisateur : ";
	public static final String MOT_DE_PASSE = "Mot de passe : ";
	public static final String COMPTE_NEXISTE_PAS = "\n==> Ce compte n'existe pas ! <==";
	public static final String DEBUT_BIENVENUE = "\n\n******************\n* BIENVENUE ";
	public static final String FIN_BIENVENUE = " *\n******************\n";
	public static final String SUPPRIMER = "\n==> Compte supprime avec succes ! <==";
	public static final String MOT_MODIFIER = "\n==> Mot de passe modifie avec succes ! <==";
			
    public static void main(String[] args) {
    	char choix1;
    	String nomUtilisateur;
    	String motDePasse;
    	String infosComptes = "\n";
    	
    	System.out.println(VERSION);
    	do {
    		System.out.println(MENU_1);
    		
    		choix1 = choixMenu();
    		
    		if (choix1 == '1') {
    			nomUtilisateur = nomUtilisateur();
    			if (nomUtilisateur == "") {
    				System.out.println(ANNULEE);
    			} else {
					if (infosComptes.contains(nomUtilisateur)) {
						System.out.println(ERR_NOM_EXISTE);
	    				System.out.println(ANNULEE);
					} else {
						motDePasse = motDePasse(MSG_CREE_MOT);
						if (motDePasse == "") {
							System.out.println(ANNULEE);
	    				} else {
	    					System.out.println(SUCCES);
	    					infosComptes = infosComptes + nomUtilisateur + "|" + motDePasse + "|\n";
	    				}
					}
    			}
    			System.out.print(ENTREE);
				Clavier.lireFinLigne();
    		} else if (choix1 == '2') {
    			System.out.print(UTILISATEUR);
    			nomUtilisateur = Clavier.lireString();
    			System.out.print(MOT_DE_PASSE);
    			motDePasse = Clavier.lireString();
    			infosComptes = connection(infosComptes, nomUtilisateur, motDePasse);
    			System.out.print(ENTREE);
				Clavier.lireFinLigne();
    		}
    	} while(choix1 != '3');
    	System.out.print(FIN);
    }
    
    /**
     * M�thode qui g�re la connexion d'un utilisateur et des options qui s'offrent � lui.
     *
     * @param  comptes est la liste infosComptes des informations de tout les utilisateurs.
     * @param  nom est le nom d'utilisateur de celui qui tente de se connecter.
     * @param  mot est le mot de passe rentr� par l'utilisateur.
     * @return  la liste des informations des utilisateur
     */
    public static String connection(String comptes, String nom, String mot) {
    	String date;
    	String user = (nom + "|" + mot + "|");
    	int debut;
    	int fin;
    	char choix;
    	String motDePasse;

    	if (!comptes.contains(user)) {
    		System.out.println(COMPTE_NEXISTE_PAS);
    	} else {
    		date = comptes.substring(comptes.indexOf(user) + user.length(),
    				comptes.indexOf("\n", comptes.indexOf(user)));
    		if (date.equals("")) {
    			date = "-";
    		}
    		comptes = modifConnexion(comptes, user);
    		do {
    			System.out.println(DEBUT_BIENVENUE + nom.toUpperCase() + FIN_BIENVENUE);
    			System.out.println("  Dernier acces : " + date + "\n\n");
    			System.out.println(MENU_2);
	    		choix = choixMenu();
	    		switch (choix) {
	    		case '1':
	    			motDePasse = motDePasse(MSG_MODIFIER_MOT);
	    			if (motDePasse == "") {
						System.out.println(ANNULEE);
    				} else {
    					System.out.println(MOT_MODIFIER);
    					debut = comptes.indexOf(mot);
    					fin = comptes.indexOf("|", debut);
    					comptes = comptes.substring(0, debut) + motDePasse + comptes.substring(fin);
    				}
	    			break;
	    			
	    		case '2':
	    			debut = comptes.indexOf(user);
	    			if ((comptes.indexOf("\n", debut) + 2) > comptes.length()) {
	    				comptes = comptes.substring(0, debut);
	    			} else {
	    				fin = comptes.indexOf("\n", debut) + 2;
		    			comptes = comptes.substring(0, debut) + comptes.substring(fin);
	    			}
	    			System.out.println(SUPPRIMER);
	    			break;
	    			
	    		case '3':
	    			System.out.println("\nAU REVOIR !");
	    			break;
	    		}
    		} while (choix != '2' && choix != '3');
    	}
    	return comptes;
    }
    
    /**
     * M�thode qui modifie la derni�re date de connexion d'un utilisateur donn�.
     *
     * @param  comptes est liste infosComptes
     * @param  user est une chaine contenant un nom d'utilisateur suivi 
     * 			de "|"suivi du mot de passe suivi de "|".
     * @return  la liste des comptes modifi� avec la nouvelle date de connexion.
     */
    public static String modifConnexion(String comptes, String user) {
    	int debut = comptes.indexOf(user) + user.length();
    	int fin = comptes.indexOf("\n", debut);
    	String dateCourante = TP2Util.dateCourante();
    	comptes = comptes.substring(0, debut) + dateCourante + comptes.substring(fin);
    	return comptes;
    }
    
    /**
     * M�thode qui demande � l'utilisateur d'entres un choix d'option dans un menu, 
     *	jusqu'� ce qu'un choix valide est fait.
     *
     * @return  caract�re num�rique du choix
     */
    public static char choixMenu() {
    	System.out.print(CHOIX_MENU);
    	String choix = Clavier.lireString();
    	while (!choix.equals("1") && !choix.equals("2") && !choix.equals("3")) {
    		System.out.println(ERR_MENU);
    		System.out.print(CHOIX_MENU);
    		choix = Clavier.lireString();
    	}
    	return choix.charAt(0);
    }

    /**
     * M�thode qui demande � l'utilisateur un mot de passe jusqu'� ce qu'il soit valide ou une chaine vide.
     *
     * @param  msg est le message affich� dans la console pour solicit� l'utilisateur � entrer un mot de passe
     * @return  le mot de passe
     */
    public static String motDePasse(String msg) {
    	String mot;
    	do {
	    	System.out.print(msg);
	    	mot = Clavier.lireString();
    	} while (validePasse(mot) == false);
    	
    	return mot;
    }
	/**
     * M�thode qui v�rifie si le string re�u en param�tre est un mot de passe valide valide.
     *
     * @param  mot est la cha�ne de caract�re � analyser.
     * @return  true si la cha�ne est un mot de passe valide, false sinon.
     */
    public static boolean validePasse(String mot) {
    	int maj = 0;
    	int chiffre = 0;
    	int special = 0;
    	
    	if (mot == "") {
    		return true;
    	}
    	if (mot.length() < 8 || mot.length() > 16) {
    		System.out.println(ERR_LONGUEUR_MOT);
    		return false;
    	}
    	 
    	for (int i = 0; i < mot.length(); i++) {
    		char character = mot.charAt(i);
    		int ascii = (int) character;
    		if (!((ascii >= 48 && ascii <= 57) || (ascii >= 65 && ascii <= 90)
    				|| (ascii >= 97 && ascii <= 122) 
    				|| "*?#@!.+&".contains(String.valueOf(character)))) {
    			System.out.println(ERR_CONTENU_MOT);
    			return false;
    		}
    	}
    	
    	for (int i = 0; i < mot.length(); i++) {
    		char ch = mot.charAt(i);
    		if (ch >= 'A' && ch <= 'Z')
                maj++;
    		else if (ch >= '0' && ch <= '9')
                chiffre++;
    		else if ("*?#@!.+&".indexOf(ch) != -1) {
    			special++;
    		}
    	}
    	
    	if (maj >= 2 && chiffre >= 1 && special >= 1) {
    		return true;
    	} else {
    		System.out.println(ERR_CONTENU_MOT_2);
    		return false;
    	}
    }

    /**
     * M�thode qui demande � l'utilisateur un nom d'utilisateur jusqu'� ce qu'il 
     * soit valide ou une chaine vide.
     *
     *
     * @return  le nom d'utilisateur obtenu
     */
    public static String nomUtilisateur() {
    	String nom;
    	do {
	    	System.out.print(MSG_CREE_NOM);
	    	nom = Clavier.lireString();
    	} while (valideNom(nom) == false);
    	
    	return nom;
    }
    
	/**
     * M�thode qui v�rifie si le string re�u en param�tre est un nom d'utilisateur valide.
     *
     * @param  nom est la cha�ne de caract�re � analyser.
     * @return  true si la cha�ne est un nom d'utilisateur valide, false sinon.
     */
    public static boolean valideNom(String nom) {
    	boolean valide = false;
    	int erreurs = 0;
    	
    	if (nom == "") {
    		valide = true;
    	}
    	if (nom.length() < 3 || nom.length() > 25) {
    		if (valide == false) {
    			System.out.println(ERR_LONGUEUR_NOM);
    		}
    	} else {
	    	for (int i = 0; i < nom.length(); i++) {
	    		char character = nom.charAt(i);
	    		int ascii = (int) character;
	    		if (!((ascii >= 48 && ascii <= 57) || (ascii >= 65 && ascii <= 90)
	    				|| (ascii >= 97 && ascii <= 122) || ascii == 95 || ascii == 46)) {
	    			erreurs++;
	    		}
	    	}
	    	
	    	if (erreurs > 0) {
	    		System.out.println(ERR_CONTENU_NOM);
	    	} else {
	    		valide = true;
	    	}
    	}
    	return valide;
    }
}