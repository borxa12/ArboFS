package com.santirodriguezlorenzo.arbofs.model;

/**
 * Created by Santi on 09/09/2015.
 */
public class Player {
    private Long _id;
    private String name;
    private String fullName;
    private String idName;
    private String birthdate;
    private String height;
    private String dorsal;
    private String nationality;
    private String nickname;
    private String position;
    private String descriptionEs;
    private String descriptionGl;

    public Player(Long _id, String name, String fullName, String idName, String birthdate, String height, String dorsal, String nationality, String nickname, String position, String descriptionEs, String descriptionGl) {
        this._id = _id;
        this.name = name;
        this.fullName = fullName;
        this.idName = idName;
        this.birthdate = birthdate;
        this.height = height;
        this.dorsal = dorsal;
        this.nationality = nationality;
        this.nickname = nickname;
        this.position = position;
        this.descriptionEs = descriptionEs;
        this.descriptionGl = descriptionGl;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIdName() {
        return idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getDorsal() {
        return dorsal;
    }

    public void setDorsal(String dorsal) {
        this.dorsal = dorsal;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescriptionEs() {
        return descriptionEs;
    }

    public void setDescriptionEs(String descriptionEs) {
        this.descriptionEs = descriptionEs;
    }

    public String getDescriptionGl() {
        return descriptionGl;
    }

    public void setDescriptionGl(String descriptionGl) {
        this.descriptionGl = descriptionGl;
    }
}
