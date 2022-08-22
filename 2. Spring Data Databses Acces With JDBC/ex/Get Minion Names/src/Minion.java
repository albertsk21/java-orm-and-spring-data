public class Minion {

    private String name;
    private int age;

//  -----------------------CONSTRUCTOR------------------------------

    public Minion(String name, int age) {
        this.setName(name);
        this.setAge(age);
    }
//  ------------------------SETTERS---------------------------------

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
//  ------------------------GETTERS---------------------------------

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
