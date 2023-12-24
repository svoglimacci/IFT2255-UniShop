package org.udem.unishop.utilities;

/**
 * Enumeration representing different states of an order.
 */
public enum OrderState{

    /**
     * Represents an order in production
     */
    IN_PRODUCTION{
        public String toString(){
            return "En production";
        }
    },

    /**
     * Represents an order in delivery
     */
    DELIVERING{
        public String toString(){
            return "En livraison";
        }
    },

    /**
     * Represents an order awaiting for return.
     */
    AWAITING_RETURN
    {
        public String toString(){
            return "En attente de retour";
        }
    },
    /**
     * Represents a returned order
     */
    RETURNED{
        public String toString(){
            return "Retourné";
        }
    },

    /**
     * Represents an order awaiting for exchange.
     */
    AWAITING_EXCHANGE{
        public String toString(){
            return "En attente d'échange";
        }
    },
    /**
     * Represents an exchanged order.
     */
    EXCHANGED{
        public String toString(){
            return "Échangé";
        }
    },

    /**
     * Represents a delivered order.
     */
    DELIVERED{
        public String toString(){
            return "Livré";
        }
    }
}