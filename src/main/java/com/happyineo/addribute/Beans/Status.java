package com.happyineo.addribute.Beans;

import java.util.ArrayList;
import java.util.List;

public class Status {
    private String name = "%n";
    private int maxHealth = 20; // 最大体力
    private int addMaxHealth = 0;   // 最大体力の増加分(バフなど)
    private double health = 20; // 現在のHP(内部ステータス)
    private int recoveryHealth = 1;    // 自然回復速度(HP)
    private int maxMagic = 20;  // 最大MP
    private int addMaxMagic = 0;    // 最大MPの増加分(バフなど)
    private double magic = 20; // 現在のMP(内部ステータス)
    private int recoveryMagic = 1; // 自然回復速度(MP)
    private int strength = 0;   // 物理攻撃力
    private int addStrength = 0;    // 物理攻撃力の増加分(バフなど)
    private int intelligence = 0;   // 魔法攻撃力
    private int addIntelligence = 0;    // 魔法攻撃力の増加分(バフなど)
    private int vitality = 0;   // 防御力
    private int addVitality = 0;    // 防御力の増加分(バフなど)
    private int agility = 0;    // 素早さ
    private int addAgility = 0; // 素早さの増加分(バフなど)
    private int exp = 0;    // 経験値
    private int dropExp = 5;   // 落とす経験値
    private int level = 0;  // レベル
    private int statusPoint = 5;    // ステータスポイント
    private List<String> attribute = new ArrayList<>(); // 属性

    public Status() {
        // 初期値設定(属性)
        attribute.add("default");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getAddMaxHealth() {
        return addMaxHealth;
    }

    public void setAddMaxHealth(int addMaxHealth) {
        this.addMaxHealth = addMaxHealth;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public int getRecoveryHealth() {
        return recoveryHealth;
    }

    public void setRecoveryHealth(int recoveryHealth) {
        this.recoveryHealth = recoveryHealth;
    }

    public int getMaxMagic() {
        return maxMagic;
    }

    public void setMaxMagic(int maxMagic) {
        this.maxMagic = maxMagic;
    }

    public int getAddMaxMagic() {
        return addMaxMagic;
    }

    public void setAddMaxMagic(int addMaxMagic) {
        this.addMaxMagic = addMaxMagic;
    }

    public double getMagic() {
        return magic;
    }

    public void setMagic(double magic) {
        this.magic = magic;
    }

    public int getRecoveryMagic() {
        return recoveryMagic;
    }

    public void setRecoveryMagic(int recoveryMagic) {
        this.recoveryMagic = recoveryMagic;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAddStrength() {
        return addStrength;
    }

    public void setAddStrength(int addStrength) {
        this.addStrength = addStrength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getAddIntelligence() {
        return addIntelligence;
    }

    public void setAddIntelligence(int addIntelligence) {
        this.addIntelligence = addIntelligence;
    }

    public int getVitality() {
        return vitality;
    }

    public void setVitality(int vitality) {
        this.vitality = vitality;
    }

    public int getAddVitality() {
        return addVitality;
    }

    public void setAddVitality(int addVitality) {
        this.addVitality = addVitality;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getAddAgility() {
        return addAgility;
    }

    public void setAddAgility(int addAgility) {
        this.addAgility = addAgility;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getDropExp() {
        return dropExp;
    }

    public void setDropExp(int dropExp) {
        this.dropExp = dropExp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStatusPoint() {
        return statusPoint;
    }

    public void setStatusPoint(int statusPoint) {
        this.statusPoint = statusPoint;
    }

    public List<String> getAttribute() {
        return attribute;
    }

    public void setAttribute(List<String> attribute) {
        this.attribute = attribute;
    }
}
