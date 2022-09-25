package DB;

public class Person {
    private String surname;
    private String name;
    private String middleName;
    private String count;

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getName() {
        return this.name;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public String getCount() {
        return this.count;
    }
}
