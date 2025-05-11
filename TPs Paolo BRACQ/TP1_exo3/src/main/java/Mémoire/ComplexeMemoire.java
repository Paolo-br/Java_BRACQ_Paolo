package Mémoire;
import Complx_number.Complexe;


public class ComplexeMemoire extends Complexe {
    private String history;
    private static String memoireCollective;

    /**
     * Constructeur d'une instance de ComplexeMemoire
     *
     * @param partieReelle
     * @param partieImaginaire
     */
    public ComplexeMemoire(double partieReelle, double partieImaginaire) {
        super(partieReelle, partieImaginaire);
        this.history = "";
    }

    /**
     * Constructeur par copie
     *
     * @param autreComplxe
     */
    ComplexeMemoire(ComplexeMemoire autreComplxe) {
        super(autreComplxe.getReal(), autreComplxe.getImaginary());
        history = autreComplxe.history;
    }

    /**
     * Ajouter un message à l'historique des opérations
     *
     * @param message texte à ajouter
     */
    public void addMessage(String message) {
        history += message + "\n";
        memoireCollective += message; // Ajout unique à la mémoire collective
    }

    /**
     * Consulter la mémoire individuelle des opérations
     *
     * @return l'historique des opérations
     */
    public String consultMemoire() {
        return history;
    }

    /**
     * Consulter la mémoire collective des opérations
     *
     * @return l'historique global des opérations effectuées sur toutes les instances
     */
    public static String consultMemoireCollective() {
        return String.join("\n", memoireCollective);
    }

    /**
     * Redéfinition de la méthode d'addition
     *
     * @param autre instance de la classe Complexe
     * @return un nouvel objet ComplexeMemoire représentant le résultat
     */
    @Override
    public ComplexeMemoire addition(Complexe autre) {
        Complexe resultat = super.addition(autre);

        String message = "Addition avec " + autre + " résultat: " + resultat;
        addMessage(message);

        if (autre instanceof ComplexeMemoire) {
            ((ComplexeMemoire) autre).addMessage(message);
        }

        ComplexeMemoire nouveauResultat = new ComplexeMemoire(resultat.getReal(), resultat.getImaginary());
        nouveauResultat.history = this.history;

        return nouveauResultat;
    }

    /**
     * Redéfinition de la méthode de multiplication
     *
     * @param autre instance de la classe Complexe
     * @return un nouvel objet ComplexeMemoire représentant le résultat
     */
    @Override
    public ComplexeMemoire multiplication(Complexe autre) {
        Complexe resultat = super.multiplication(autre);

        String message = "Multiplication avec " + autre + " résultat: " + resultat;
        addMessage(message);

        if (autre instanceof ComplexeMemoire) {
            ((ComplexeMemoire) autre).addMessage(message);
        }

        ComplexeMemoire nouveauResultat = new ComplexeMemoire(resultat.getReal(), resultat.getImaginary());
        nouveauResultat.history = this.history;

        return nouveauResultat;
    }
}


