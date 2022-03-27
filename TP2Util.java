import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Classe utilitaire contenant une methode qui retourne la date courante sous
 * forme d'une chaine de caracteres.
 * A utiliser pour le -
 *
 * @author -
 * @version Hiver 2022
 */
public class TP2Util {

   /**
    * Retourne une representation sous forme de chaine de caracteres de la date
    * courante (date au moment de l'appel de cette methode), sous le format :
    * jj/mm/aaaa hh:mm:ss
    *
    * @return une representation sous forme de chaine de caracteres de la date
    *         courante.
    */
   public static String dateCourante() {
      LocalDate date = LocalDate.now();
      LocalTime temps = LocalTime.now();
      int jour = date.getDayOfMonth();
      int mois = date.getMonthValue();
      int heure = temps.getHour();
      int minutes = temps.getMinute();
      int secondes = temps.getSecond();
      String sJour = jour < 10 ? "0" + jour : "" + jour;
      String sMois = mois < 10 ? "0" + mois : "" + mois;
      String sAnnee = date.getYear() + "";
      String sHeure = heure < 10 ? "0" + heure : "" + heure;
      String sMinutes = minutes < 10 ? "0" + minutes : "" + minutes;
      String sSecondes = secondes < 10 ? "0" + secondes : "" + secondes;

      return sJour + "/" + sMois + "/" + sAnnee + " " + sHeure + ":" + sMinutes
            + ":" + sSecondes;
   }
}
