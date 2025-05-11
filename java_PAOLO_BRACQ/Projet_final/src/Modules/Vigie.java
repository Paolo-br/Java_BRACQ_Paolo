package Modules;

import java.util.*;

public class Vigie {

    //------------------------Déclaration des variables-----------------------------

    /**
     * Liste des alertes reçues par la vigie.
     */
    private final List<String> alertes = new ArrayList<>();

    //------------------------Déclaration des méthodes-----------------------------

    /**
     * Méthode pour recevoir une alerte et l'ajouter à la liste des alertes.
     * @param alerte le message d'alerte à ajouter
     */
    public void recevoirAlerte(String alerte) {
        alertes.add(alerte);
        System.out.println("[VIGIE] " + alerte);
    }

    /**
     * Méthode pour obtenir la liste des alertes.
     * @return la liste des alertes
     */
    public List<String> getAlertes() {
        return alertes;
    }
}
