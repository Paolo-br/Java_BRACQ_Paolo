package Model;

import java.time.LocalDate;
import java.util.*;

public class Publication {

    //------------------------Déclaration des variables-----------------------------

    private String type; // Type de la publication (ex : article, interview, etc.)
    private Media media; // Média dans lequel la publication est parue
    private List<Entite> mentions; // Liste des entités mentionnées dans la publication
    private LocalDate date; // Date de la publication

    //------------------------Déclaration du constructeur-----------------------------

    public Publication(String type, Media media, List<Entite> mentions) {
        this.type = type;
        this.media = media;
        this.mentions = mentions;
        this.date = LocalDate.now(); // date automatique à la création
    }

    //-------------------------Déclaration des méthodes--------------------------------

    /**
     * Méthode d'accès au type de publication
     * @return chaîne correspondant au type
     */
    public String getType() {
        return type;
    }

    /**
     * Méthode d'accès au média de la publication
     * @return objet Media
     */
    public Media getMedia() {
        return media;
    }

    /**
     * Méthode d'accès à la liste des entités mentionnées
     * @return liste d'objets Entite
     */
    public List<Entite> getMentions() {
        return mentions;
    }

    /**
     * Méthode d'accès à la date de publication
     * @return date (LocalDate)
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Méthode toString redéfinie pour afficher les informations d'une publication
     * @return chaîne formatée contenant les détails de la publication
     */
    @Override
    public String toString() {
        StringBuilder mentionsStr = new StringBuilder();
        for (Entite e : mentions) {
            mentionsStr.append(e.getNom()).append(", ");
        }
        if (!mentions.isEmpty()) {
            mentionsStr.setLength(mentionsStr.length() - 2); // retirer la dernière virgule
        }
        return "[ " + date + " | Type: " + type + " | Média: " + media.getNom() + " | Mentions: [" + mentionsStr + "] ]";
    }

}
