package models;

    public enum BookGenre {
            ACADEMIC("Académique"),
            NOVEL("Roman"),
            DOCUMENTARY("Documentaire"),
            COMIC("Bande dessinée");


        private final String displayName;

        BookGenre(String displayName) {
                this.displayName = displayName;
            }

            public String getDisplayName() {
                return displayName;
            }
        }