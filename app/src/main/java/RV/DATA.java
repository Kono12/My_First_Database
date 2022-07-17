package RV;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DATA {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int image;
    private String number ;
    private String name ;

    public DATA(int image, String number, String name) {
        this.image = image;
        this.number = number;
        this.name = name;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DATA(){};

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
