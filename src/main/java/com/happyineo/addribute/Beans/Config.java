package com.happyineo.addribute.Beans;

public class Config {

    private boolean disableInvincibleTime = true;   // 無敵時間無効(エンティティから受けるダメージのみ)
    private boolean displayDamage = true;   // ダメージ表示の有無
    private boolean natureStatusHasEntity = true;   // 自然湧きのエンティティもステータスを持たせるかどうか
    private String[] statusNotEntity = {"ARMOR_STAND"}; // 対象外のエンティティ
    private String displayDamageFormat = "?????0.00";  // ダメージ表記形式
    private int displayDamageTime = 10; // ダメージの表示時間
    private int displayDamageMax = -1;  // ダメージ表示の最大数
    private double displayDamagePositionX = 0.3;    // ダメージ拡散範囲_X
    private double displayDamagePositionY = 1.5;    // ダメージ拡散範囲_Y
    private double displayDamagePositionZ = 0.3;    // ダメージ拡散範囲_Z
    private int criticalHit = 20;   // クリティカルヒット率(%表記)
    private int statusTime = 200;   // ステータスの更新タイミング(tick)

    public boolean isDisableInvincibleTime() {
        return disableInvincibleTime;
    }

    public void setDisableInvincibleTime(boolean disableInvincibleTime) {
        this.disableInvincibleTime = disableInvincibleTime;
    }

    public boolean isDisplayDamage() {
        return displayDamage;
    }

    public void setDisplayDamage(boolean displayDamage) {
        this.displayDamage = displayDamage;
    }

    public boolean isNatureStatusHasEntity() {
        return natureStatusHasEntity;
    }

    public void setNatureStatusHasEntity(boolean natureStatusHasEntity) {
        this.natureStatusHasEntity = natureStatusHasEntity;
    }

    public String[] getStatusNotEntity() {
        return statusNotEntity;
    }

    public void setStatusNotEntity(String[] statusNotEntity) {
        this.statusNotEntity = statusNotEntity;
    }

    public String getDisplayDamageFormat() {
        return displayDamageFormat;
    }

    public void setDisplayDamageFormat(String displayDamageFormat) {
        this.displayDamageFormat = displayDamageFormat;
    }

    public int getDisplayDamageTime() {
        return displayDamageTime;
    }

    public void setDisplayDamageTime(int displayDamageTime) {
        this.displayDamageTime = displayDamageTime;
    }

    public int getDisplayDamageMax() {
        return displayDamageMax;
    }

    public void setDisplayDamageMax(int displayDamageMax) {
        this.displayDamageMax = displayDamageMax;
    }

    public double getDisplayDamagePositionX() {
        return displayDamagePositionX;
    }

    public void setDisplayDamagePositionX(double displayDamagePositionX) {
        this.displayDamagePositionX = displayDamagePositionX;
    }

    public double getDisplayDamagePositionY() {
        return displayDamagePositionY;
    }

    public void setDisplayDamagePositionY(double displayDamagePositionY) {
        this.displayDamagePositionY = displayDamagePositionY;
    }

    public double getDisplayDamagePositionZ() {
        return displayDamagePositionZ;
    }

    public void setDisplayDamagePositionZ(double displayDamagePositionZ) {
        this.displayDamagePositionZ = displayDamagePositionZ;
    }

    public int getCriticalHit() {
        return criticalHit;
    }

    public void setCriticalHit(int criticalHit) {
        this.criticalHit = criticalHit;
    }

    public int getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(int statusTime) {
        this.statusTime = statusTime;
    }
}
