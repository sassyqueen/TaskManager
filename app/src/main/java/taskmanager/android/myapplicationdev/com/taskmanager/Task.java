package taskmanager.android.myapplicationdev.com.taskmanager;

import java.io.Serializable;

/**
 * Created by 15004557 on 25/5/2017.
 */

public class Task implements Serializable {

    private int id;
    private String description;
    private String name;

    public Task(int id, String description, String name) {
        this.id = id;
        this.description = description;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
