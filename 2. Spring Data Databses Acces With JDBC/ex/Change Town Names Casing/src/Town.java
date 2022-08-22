public class Town {

        private String name;
        private int id;

//        -------------------------CONSTRUCTOR-------------------------
        public Town(String name, int id) {
                this.setName(name);
                this.setId(id);
        }


//        -------------------------SETTERS------------------------------

        private void setName(String name) {
                this.name = name;
        }

        private void setId(int id) {
                this.id = id;
        }

//        -------------------------GETTERS------------------------------

        public String getName() {
                return name;
        }

        public int getId() {
                return id;
        }
}

