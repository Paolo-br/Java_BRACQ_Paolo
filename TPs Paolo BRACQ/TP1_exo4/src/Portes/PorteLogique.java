package Portes;

abstract class PorteLogique {
    protected boolean A;
    protected boolean B;
    protected boolean Q;
    protected int maxCycles;
    protected int cycles;

    /**
     * Constructeur de porte logique
     *
     * @param A         boolean
     * @param B         boolean
     * @param maxCycles int
     */
    public PorteLogique(boolean A, boolean B, int maxCycles) {
        this.A = A;
        this.B = B;
        this.maxCycles = maxCycles;
        this.cycles = 0;
        updateQ();
    }

    // Méthode abstraite pour calculer la sortie
    protected abstract void updateQ();

    /**
     * Affichage de A
     *
     * @return A boolean
     */
    public boolean getA() throws ExceptionPorteLogique {
        incrementCycles();
        return A;

    }

    /**
     * Affichage de B
     *
     * @return B boolean
     */
    public boolean getB() throws ExceptionPorteLogique {
        incrementCycles();
        return B;
    }

    /**
     * Modification de A
     *
     * @return
     */
    public void setA(boolean a) throws ExceptionPorteLogique {
        A = a;
        incrementCycles();
        updateQ();
    }

    /**
     * Modification de B
     *
     * @return
     */
    public void setB(boolean b) throws ExceptionPorteLogique {
        B = b;
        incrementCycles();
        updateQ();
    }

    /**
     * Affichage de Q
     *
     * @return Q boolean
     */
    public boolean getQ() throws ExceptionPorteLogique {
        incrementCycles();
        return Q;
    }

    protected void incrementCycles() throws ExceptionPorteLogique {
        cycles++;
        if (cycles > maxCycles) {
            throw new ExceptionPorteAChanger("La porte logique doit être changée.");
        }
    }
}

