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
    private double displayDamagePositionMaxY = 2;   // ダメージ上昇頂点_Y
    private int criticalHit = 20;   // クリティカルヒット率(%表記)
    private int statusTime = 200;   // ステータスの更新タイミング(tick)
    private String statusActionBar = "&f体力:&d%h&f/&d@h&e+@ha &f魔力:&5%m&f/&5@m&e+@ma";    // アクションバーに表示するステータス
    private int statusActionBarTimer = 5;   // アクションバーに表示するステータスの更新頻度
    private String logLevel = "INFO";

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

    public double getDisplayDamagePositionMaxY() {
        return displayDamagePositionMaxY;
    }

    public void setDisplayDamagePositionMaxY(double displayDamagePositionMaxY) {
        this.displayDamagePositionMaxY = displayDamagePositionMaxY;
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

    public String getStatusActionBar() {
        return statusActionBar;
    }

    public void setStatusActionBar(String statusActionBar) {
        this.statusActionBar = statusActionBar;
    }

    public int getStatusActionBarTimer() {
        return statusActionBarTimer;
    }

    public void setStatusActionBarTimer(int statusActionBarTimer) {
        this.statusActionBarTimer = statusActionBarTimer;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }
}
