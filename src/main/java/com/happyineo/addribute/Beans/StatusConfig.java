package com.happyineo.addribute.Beans;

public class StatusConfig {
    //名前===============================================================================================================
    private String healthName = "HP";
    private String recoveryHealthName = "RHP";
    private String magicName = "MP";
    private String recoveryMagicName = "RMP";
    private String strengthName = "STR";
    private String intelligenceName = "INT";
    private String vitalityName = "VIT";
    private String agilityName = "AGI";
    private String expName = "EXP";
    private String levelName = "LV";
    //増加値=============================================================================================================
    private double healthValue = 1;
    private double recoveryHealthValue = 0.1;
    private double magicValue = 1;
    private double recoveryMagicValue = 0.1;
    private double strengthValue = 1;
    private double intelligenceValue = 1;
    private double vitalityValue = 1;
    private double agilityValue = 1;
    //デフォルト値========================================================================================================
    private String nameDefault = "%n";
    private int maxHealthDefault = 20;
    private int addMaxHealthDefault = 0;
    private double healthDefault = 20;
    private int recoveryHealthDefault = 1;
    private int maxMagicDefault = 20;
    private int addMaxMagicDefault = 0;
    private double magicDefault = 20;
    private int recoveryMagicDefault = 1;
    private int strengthDefault = 0;
    private int addStrengthDefault = 0;
    private int intelligenceDefault = 0;
    private int addIntelligenceDefault = 0;
    private int vitalityDefault = 0;
    private int addVitalityDefault = 0;
    private int agilityDefault = 0;
    private int addAgilityDefault = 0;
    private int expDefault = 0;
    private int dropExpDefault = 5;
    private int levelDefault = 0;
    private int statusPointDefault = 5;
    //ステータスポイント===================================================================================================
    private int levelUpAddStatusPoint = 1;
    //経験値=============================================================================================================
    private String levelUpNeedExpPoint = "lv * 15";
    //ダメージ計算========================================================================================================
    private String calcDamage = "damage * mags - vit";
    //==================================================================================================================


    public String getHealthName() {
        return healthName;
    }

    public void setHealthName(String healthName) {
        this.healthName = healthName;
    }

    public String getRecoveryHealthName() {
        return recoveryHealthName;
    }

    public void setRecoveryHealthName(String recoveryHealthName) {
        this.recoveryHealthName = recoveryHealthName;
    }

    public String getMagicName() {
        return magicName;
    }

    public void setMagicName(String magicName) {
        this.magicName = magicName;
    }

    public String getRecoveryMagicName() {
        return recoveryMagicName;
    }

    public void setRecoveryMagicName(String recoveryMagicName) {
        this.recoveryMagicName = recoveryMagicName;
    }

    public String getStrengthName() {
        return strengthName;
    }

    public void setStrengthName(String strengthName) {
        this.strengthName = strengthName;
    }

    public String getIntelligenceName() {
        return intelligenceName;
    }

    public void setIntelligenceName(String intelligenceName) {
        this.intelligenceName = intelligenceName;
    }

    public String getVitalityName() {
        return vitalityName;
    }

    public void setVitalityName(String vitalityName) {
        this.vitalityName = vitalityName;
    }

    public String getAgilityName() {
        return agilityName;
    }

    public void setAgilityName(String agilityName) {
        this.agilityName = agilityName;
    }

    public String getExpName() {
        return expName;
    }

    public void setExpName(String expName) {
        this.expName = expName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public double getHealthValue() {
        return healthValue;
    }

    public void setHealthValue(double healthValue) {
        this.healthValue = healthValue;
    }

    public double getRecoveryHealthValue() {
        return recoveryHealthValue;
    }

    public void setRecoveryHealthValue(double recoveryHealthValue) {
        this.recoveryHealthValue = recoveryHealthValue;
    }

    public double getMagicValue() {
        return magicValue;
    }

    public void setMagicValue(double magicValue) {
        this.magicValue = magicValue;
    }

    public double getRecoveryMagicValue() {
        return recoveryMagicValue;
    }

    public void setRecoveryMagicValue(double recoveryMagicValue) {
        this.recoveryMagicValue = recoveryMagicValue;
    }

    public double getStrengthValue() {
        return strengthValue;
    }

    public void setStrengthValue(double strengthValue) {
        this.strengthValue = strengthValue;
    }

    public double getIntelligenceValue() {
        return intelligenceValue;
    }

    public void setIntelligenceValue(double intelligenceValue) {
        this.intelligenceValue = intelligenceValue;
    }

    public double getVitalityValue() {
        return vitalityValue;
    }

    public void setVitalityValue(double vitalityValue) {
        this.vitalityValue = vitalityValue;
    }

    public double getAgilityValue() {
        return agilityValue;
    }

    public void setAgilityValue(double agilityValue) {
        this.agilityValue = agilityValue;
    }

    public String getNameDefault() {
        return nameDefault;
    }

    public void setNameDefault(String nameDefault) {
        this.nameDefault = nameDefault;
    }

    public int getMaxHealthDefault() {
        return maxHealthDefault;
    }

    public void setMaxHealthDefault(int maxHealthDefault) {
        this.maxHealthDefault = maxHealthDefault;
    }

    public int getAddMaxHealthDefault() {
        return addMaxHealthDefault;
    }

    public void setAddMaxHealthDefault(int addMaxHealthDefault) {
        this.addMaxHealthDefault = addMaxHealthDefault;
    }

    public double getHealthDefault() {
        return healthDefault;
    }

    public void setHealthDefault(double healthDefault) {
        this.healthDefault = healthDefault;
    }

    public int getRecoveryHealthDefault() {
        return recoveryHealthDefault;
    }

    public void setRecoveryHealthDefault(int recoveryHealthDefault) {
        this.recoveryHealthDefault = recoveryHealthDefault;
    }

    public int getMaxMagicDefault() {
        return maxMagicDefault;
    }

    public void setMaxMagicDefault(int maxMagicDefault) {
        this.maxMagicDefault = maxMagicDefault;
    }

    public int getAddMaxMagicDefault() {
        return addMaxMagicDefault;
    }

    public void setAddMaxMagicDefault(int addMaxMagicDefault) {
        this.addMaxMagicDefault = addMaxMagicDefault;
    }

    public double getMagicDefault() {
        return magicDefault;
    }

    public void setMagicDefault(double magicDefault) {
        this.magicDefault = magicDefault;
    }

    public int getRecoveryMagicDefault() {
        return recoveryMagicDefault;
    }

    public void setRecoveryMagicDefault(int recoveryMagicDefault) {
        this.recoveryMagicDefault = recoveryMagicDefault;
    }

    public int getStrengthDefault() {
        return strengthDefault;
    }

    public void setStrengthDefault(int strengthDefault) {
        this.strengthDefault = strengthDefault;
    }

    public int getAddStrengthDefault() {
        return addStrengthDefault;
    }

    public void setAddStrengthDefault(int addStrengthDefault) {
        this.addStrengthDefault = addStrengthDefault;
    }

    public int getIntelligenceDefault() {
        return intelligenceDefault;
    }

    public void setIntelligenceDefault(int intelligenceDefault) {
        this.intelligenceDefault = intelligenceDefault;
    }

    public int getAddIntelligenceDefault() {
        return addIntelligenceDefault;
    }

    public void setAddIntelligenceDefault(int addIntelligenceDefault) {
        this.addIntelligenceDefault = addIntelligenceDefault;
    }

    public int getVitalityDefault() {
        return vitalityDefault;
    }

    public void setVitalityDefault(int vitalityDefault) {
        this.vitalityDefault = vitalityDefault;
    }

    public int getAddVitalityDefault() {
        return addVitalityDefault;
    }

    public void setAddVitalityDefault(int addVitalityDefault) {
        this.addVitalityDefault = addVitalityDefault;
    }

    public int getAgilityDefault() {
        return agilityDefault;
    }

    public void setAgilityDefault(int agilityDefault) {
        this.agilityDefault = agilityDefault;
    }

    public int getAddAgilityDefault() {
        return addAgilityDefault;
    }

    public void setAddAgilityDefault(int addAgilityDefault) {
        this.addAgilityDefault = addAgilityDefault;
    }

    public int getExpDefault() {
        return expDefault;
    }

    public void setExpDefault(int expDefault) {
        this.expDefault = expDefault;
    }

    public int getDropExpDefault() {
        return dropExpDefault;
    }

    public void setDropExpDefault(int dropExpDefault) {
        this.dropExpDefault = dropExpDefault;
    }

    public int getLevelDefault() {
        return levelDefault;
    }

    public void setLevelDefault(int levelDefault) {
        this.levelDefault = levelDefault;
    }

    public int getStatusPointDefault() {
        return statusPointDefault;
    }

    public void setStatusPointDefault(int statusPointDefault) {
        this.statusPointDefault = statusPointDefault;
    }

    public int getLevelUpAddStatusPoint() {
        return levelUpAddStatusPoint;
    }

    public void setLevelUpAddStatusPoint(int levelUpAddStatusPoint) {
        this.levelUpAddStatusPoint = levelUpAddStatusPoint;
    }

    public String getLevelUpNeedExpPoint() {
        return levelUpNeedExpPoint;
    }

    public void setLevelUpNeedExpPoint(String levelUpNeedExpPoint) {
        this.levelUpNeedExpPoint = levelUpNeedExpPoint;
    }

    public String getCalcDamage() {
        return calcDamage;
    }

    public void setCalcDamage(String calcDamage) {
        this.calcDamage = calcDamage;
    }
}
