public class Minion {

    private String name;
    private int age;
    private String villain;

//  -----------------------CONSTRUCTOR------------------------------

    public Minion(String name, int age,String  villain){
        this.setVillain(villain);
        this.setName(name);
        this.setAge(age);
    }
//  ------------------------SETTERS---------------------------------

    private void setVillain(String villain) {
        this.villain = villain;
    }
    private void setName(String name) {
        this.name = name;
    }
    private void setAge(int age) {
        this.age = age;
    }
//  ------------------------GETTERS---------------------------------


    public String getVillain() {
        return villain;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
