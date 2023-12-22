package org.udem.unishop.utilities;

    public enum OrderState{
        IN_PRODUCTION{
            public String toString(){
                return "En production";
            }
        },
        DELIVERING{
            public String toString(){
                return "En livraison";
            }
        },
      AWAITING_RETURN
        {
            public String toString(){
                return "En attente de retour";
            }
        }, RETURNED{
            public String toString(){
                return "Retourné";
            }
      }, DELIVERED{
            public String toString(){
                return "Livré";
            }
        }
    }