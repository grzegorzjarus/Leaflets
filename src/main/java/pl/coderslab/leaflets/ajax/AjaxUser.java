package pl.coderslab.leaflets.ajax;

public class AjaxUser {


    private String name;
    private String surname;
    private int age;
    private int points;
    //private List<String> contacts;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public int getPoints() {
        return points;
    }

    public AjaxUser() {
    }


    @Override
    public String toString() {
        return "AjaxUser{" +
                "name='" + this.name + '\'' +
                ", surname='" + this.surname + '\'' +
                ", age=" + this.age +
                ", points=" + this.points +
                '}';
    }
}
