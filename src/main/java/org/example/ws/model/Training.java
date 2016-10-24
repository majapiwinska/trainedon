package org.example.ws.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maja on 30.08.16.
 */
@Entity
@Table(name = "session")
public class Training {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "session_name")
    @NotNull
    private String title;

    @Column(name = "trainer")
    @NotNull
    private String trainer;

    @Column(name = "length")
    @NotNull
    private int length;


    @Column(name = "userId")
    @NotNull
    private int userId;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name="training_block",
            joinColumns = {@JoinColumn(name = "training_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "block_id", referencedColumnName = "block_id")})
   private List<Block> blocks = new ArrayList<Block>() {
    };


    public Training(){}

    public Training(String title, String trainer, List<Block> blocks, Block block, int userId){

        this.title = title;

        this.trainer = trainer;

        this.blocks = blocks;

        this.userId = userId;


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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
