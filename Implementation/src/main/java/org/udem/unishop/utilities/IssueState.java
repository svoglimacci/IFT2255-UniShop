package org.udem.unishop.utilities;

public enum IssueState {
    OPEN("Ouvert"),
    AWAITING("En attente de réponse"),

    PROCESSING("En traitement"),

    DELIVERING("En cours de livraison"),

    CLOSED("Fermé"),
    RESOLVED("Résolu"), AWAITING_RETURN("En attente de retour");

    private final String displayName;

    IssueState(String displayName) {
        this.displayName = displayName;
    }

}