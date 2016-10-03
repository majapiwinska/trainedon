package org.example.ws.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maja on 07.09.16.
 */
@Entity
@Table(name="block")
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "block_id")
    private Long id;

    @NotNull
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;


    @NotNull
    @Column(name = "minutes")
    private int minutes;


     @ManyToMany(cascade = CascadeType.ALL)
     @JoinTable(
             name="training_block",
             joinColumns = {@JoinColumn(name = "block_id", referencedColumnName = "block_id")},
             inverseJoinColumns = {@JoinColumn(name = "training_id", referencedColumnName = "id")})
     private List<Training> trainings = new ArrayList<Training>();


    public Block(){};

    public Block(String title, String description, int minutes){
        this.title = title;
        this.description = description;
        this.minutes = minutes;
    }

    public String getTitle() {
        return title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }
}
