package pl.coderslab.leaflets.model;

public class User {
    private String name;
    private int age;
    //private List<String> contacts;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public void setContacts(List<String> contacts) {
//        this.contacts = contacts;
//    }

//    public User(String name, int age, List<String> contacts) {
//        this.name = name;
//        this.age = age;
//        this.contacts = contacts;
//    }


    public User() {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
