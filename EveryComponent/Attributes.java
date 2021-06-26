package EveryComponent;

class Attributes {
    public String name;
    public String currentlyStudying;
    public int age;
    public String country;
    public String city;

    public Attributes() {
        this.name = "";
        this.currentlyStudying = "";
        this.age = 0;
        this.country = "";
        this.city = "";
    }

    public void userName(String name) {
        this.name = name;
    }

    public void userSubject(String newCurrentlyStudying) {
        this.currentlyStudying = newCurrentlyStudying;
    }

    public void userAge(int newAge) {
        this.age = newAge;
    }

    public void userCountry(String newCountry) {
        this.country = newCountry;
    }

    public void userCity(String newCity) {
        this.city = newCity;
    }
}