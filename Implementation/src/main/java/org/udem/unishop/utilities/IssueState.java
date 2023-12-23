package org.udem.unishop.utilities;

/**
 * Enumeration representing different states of an issue.
 * Each issue state has an associated display name.
 */
public enum IssueState {

    /**
     * Represents an open issue state.
     */
    OPEN("Ouvert"),

    /**
     * Represents an issue state awaiting a response.
     */
    AWAITING("En attente de réponse"),

    /**
     * Represents an issue state in progress.
     */
    PROCESSING("En traitement"),

    /**
     * Represents an issue in the process of delivering.
     */
    DELIVERING("En cours de livraison"),

    /**
     * Represents a closed issue state.
     */
    CLOSED("Fermé"),

    /**
     * Represents an resolved issue state.
     */
    RESOLVED("Résolu"),

    /**
     * Represents an issue state awaiting for return.
     */
    AWAITING_RETURN("En attente de retour");

    private final String displayName;

    /**
     * Constructor for IssueState enum.
     *
     * @param displayName The display name of associated issue state.
     */
    IssueState(String displayName) {
        this.displayName = displayName;
    }

}