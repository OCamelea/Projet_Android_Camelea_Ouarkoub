package com.example.projetandroidcamelea.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pokemon implements Serializable {
    @SerializedName("_id")
    private int id ;
    private String num ;
    @SerializedName("name")
    private String name ;
    @SerializedName("img")
    private String img ;
    @SerializedName("type")
    private List<String> type ;
    @SerializedName("height")
    private String height ;
    @SerializedName("weight")
    private String weight ;
    private String candy ;
    private int candy_count ;
    private String egg ;
    private double spawn_chance ;
    private double avg_spawns ;
    private String spawn_time ;
    private List<Double> multipliers;
    @SerializedName("weaknesses")
    private List<String> weaknesses ;
    @SerializedName("next_evolution")
    private List<NextEvolution> next_evolution ;
    @SerializedName("prev_evolution")
    private List<PrevEvolution> prev_evolution ;

    public Pokemon() {
    }

    /*
    Constructor
     */
    public Pokemon(int id, String num, String name, String img, List<String> type, String height, String weight, String candy, int candy_count, String egg, double spawn_chance, double avg_spawns, String spawn_time, List<String> weaknesses, List<NextEvolution> next_evolution, List<PrevEvolution> prev_evolution) {
        this.id = id;
        this.num = num;
        this.name = name;
        this.img = img;
        this.type = type;
        this.height = height;
        this.weight = weight;
        this.candy = candy;
        this.candy_count = candy_count;
        this.egg = egg;
        this.spawn_chance = spawn_chance;
        this.avg_spawns = avg_spawns;
        this.spawn_time = spawn_time;
        this.weaknesses = weaknesses;
        this.next_evolution = next_evolution;
        this.prev_evolution = prev_evolution;
    }

    /**
     * Constructor.
     * @param id the id.
     * @param name the name.
     * @param image the image.
     */
    public Pokemon(int id, String name, String image,String height,String weight,String types,String weakness,String nextEvolution,String prevEvolution) {
        this.id = id;
        this.name = name;
        this.img = image;
        this.height=height;
        this.weight=weight;
        List<String> typesList = new ArrayList<String>(Arrays.asList(types.split(" ")));
        this.type = typesList;
        List<String> weaknessList = new ArrayList<String>(Arrays.asList(weakness.split(" ")));
        this.weaknesses = weaknessList;
        List<NextEvolution> nextEvolList = new ArrayList<NextEvolution>();
        List<String> nextevol = new ArrayList<String>(Arrays.asList(nextEvolution.split(" ")));
        if( nextevol != null) {
            for (String ne : nextevol) {
                NextEvolution nxtev = new NextEvolution();
                nxtev.setName(ne);
                nextEvolList.add(nxtev);
            }
        }

        List<PrevEvolution> prevEvolList = new ArrayList<PrevEvolution>();
        List<String> prevevol = new ArrayList<String>(Arrays.asList(prevEvolution.split(" ")));
        if( nextevol != null) {
            for (String pe : prevevol) {
                PrevEvolution prvevl = new PrevEvolution();
                prvevl.setName(pe);
                prevEvolList.add(prvevl);
            }
        }


        this.next_evolution = nextEvolList;
        this.prev_evolution = prevEvolList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCandy() {
        return candy;
    }

    public void setCandy(String candy) {
        this.candy = candy;
    }

    public int getCandy_count() {
        return candy_count;
    }

    public void setCandy_count(int candy_count) {
        this.candy_count = candy_count;
    }

    public String getEgg() {
        return egg;
    }

    public void setEgg(String egg) {
        this.egg = egg;
    }

    public double getSpawn_chance() {
        return spawn_chance;
    }

    public void setSpawn_chance(double spawn_chance) {
        this.spawn_chance = spawn_chance;
    }

    public double getAvg_spawns() {
        return avg_spawns;
    }

    public void setAvg_spawns(double avg_spawns) {
        this.avg_spawns = avg_spawns;
    }

    public String getSpawn_time() {
        return spawn_time;
    }

    public void setSpawn_time(String spawn_time) {
        this.spawn_time = spawn_time;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public void setWeaknesses(List<String> weaknesses) {
        this.weaknesses = weaknesses;
    }

    public List<NextEvolution> getNext_evolution() {
        return next_evolution;
    }

    public void setNext_evolution(List<NextEvolution> next_evolution) {
        this.next_evolution = next_evolution;
    }

    public List<PrevEvolution> getPrev_evolution() {
        return prev_evolution;
    }

    public void setPrev_evolution(List<PrevEvolution> prev_evolution) {
        this.prev_evolution = prev_evolution;
    }
}
