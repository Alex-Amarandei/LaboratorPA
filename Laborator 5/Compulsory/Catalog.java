/**
 * @author Alex Amarandei
 */

package compulsory;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Catalog implements Serializable {
    private String name;
    private String path;
    private List<Item> items = new ArrayList<>();

    public Catalog() {
        this.name = "";
        this.path = "";
    }

    public Catalog(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public void add(Item item) {
        this.items.add(item);
    }

    public void list() {
        this.items.forEach(System.out::println);
    }

    public void play(Item item) {
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(new File(item.getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Item findByName(String name) {
        return this.items.stream()
                .filter(i -> i.getName().equals(name)).findFirst().orElse(null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
