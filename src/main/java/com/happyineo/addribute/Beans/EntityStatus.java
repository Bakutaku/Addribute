package com.happyineo.addribute.Beans;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EntityStatus {
    // プレイヤー以外のステータスを格納する
    private Map<UUID,Status> entityStatus = new HashMap<>();


    public Map<UUID, Status> getEntityStatus() {
        return entityStatus;
    }

    public void setEntityStatus(Map<UUID, Status> entityStatus) {
        this.entityStatus = entityStatus;
    }
}
