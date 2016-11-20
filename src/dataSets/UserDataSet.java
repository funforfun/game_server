package dataSets;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserDataSet {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    public UserDataSet(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserDataSet(String name) {
        this.id = -1;
        this.name = name;
    }

    public UserDataSet() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}