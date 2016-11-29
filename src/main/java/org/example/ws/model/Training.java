package org.example.ws.model;


import com.sun.istack.internal.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maja on 30.08.16.
 */
@Entity
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Nullable
    private String title;

    @NotNull
    private String trainer;

    @NotNull
    private int length;

    @Nullable
    @ElementCollection(targetClass = String.class)
    private List<String> tags = new ArrayList<String>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name="training_block",
            joinColumns = {@JoinColumn(name = "training_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "block_id", referencedColumnName = "id")})
   private List<Block> blocks = new ArrayList<Block>() {
    };


    public Training(){}

    public Training(String title, String trainer, List<Block> blocks, Block block, Long userId, int length, User user, List<String> tags){

        this.title = title;

        this.trainer = trainer;




        this.blocks = blocks;

        this.length = length;

        this.user = user;

        this.tags = tags;
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

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
