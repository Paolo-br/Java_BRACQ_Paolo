package Modules;

import Model.*;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public interface ModuleSpecialise {

    //-------------------------Déclaration des méthodes--------------------------------

    /**
     * Méthode à implémenter pour traiter un événement de type Publication
     * @param evenement objet Publication à analyser
     * @param toutesRelations liste de toutes les relations existantes
     */
    void traiterEvenement(Publication evenement, List<Proprietaire<? extends Entite, ? extends Entite>> toutesRelations);

    /**
     * Méthode à implémenter pour traiter un nouveau rachat
     * @param nouveauRachat objet représentant la nouvelle relation de type rachat
     */
    void traiterRachat(Proprietaire nouveauRachat);


}
