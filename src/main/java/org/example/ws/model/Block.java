package org.example.ws.model;

import com.sun.istack.internal.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maja on 07.09.16.
 */
@Entity
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String title;

    private String description;

    @NotNull
    private int minutes;

    @Nullable
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
             name="training_block",
             joinColumns = {@JoinColumn(name = "block_id", referencedColumnName = "id")},
             inverseJoinColumns = {@JoinColumn(name = "training_id", referencedColumnName = "id")})
    private List<Training> trainings = new ArrayList<Training>();

    /*@OneToMany(mappedBy = "block")
    private List<Training> trainingList;*/

    public Block(){};

    public Block(String title, String description, int minutes, List<Training> trainings, Category category){
        this.title = title;
        this.description = description;
        this.minutes = minutes;
        this.trainings = trainings;
        this.category = category;
    }

    public enum Category{
        THEORY, EXERCISE, DISCUSSION, DEBRIEFING, ENERGIZER, BREAK
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    /*public List<Training> getTrainingList() {
        return trainingList;
    }

    public void setTrainingList(List<Training> trainingList) {
        this.trainingList = trainingList;
    }
*/}
