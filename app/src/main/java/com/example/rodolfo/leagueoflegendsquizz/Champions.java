package com.example.rodolfo.leagueoflegendsquizz;

import java.util.ArrayList;

/**
 * Created by Rodolfo on 11/8/2015.
 */
public class Champions {
    private String name;
    private String title;
    private ArrayList<String> skills;

    public Champions(String name, String title){
        this.name = name;
        this.title = title;
        this.skills = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addSkill(String skill){
        this.skills.add(skill);
    }

    public ArrayList<String> getSkills() {
        return skills;
    }
}
