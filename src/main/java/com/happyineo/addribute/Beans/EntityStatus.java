package com.happyineo.addribute.Beans;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class EntityStatus {
    // プレイヤー以外のステータスを格納する
    private ConcurrentHashMap<UUID,Status> entityStatus = new ConcurrentHashMap<>();


    public Map<UUID, Status> getEntityStatus() {
        return entityStatus;
    }

    public void setEntityStatus(Map<UUID, Status> entityStatus) {
        this.entityStatus = (ConcurrentHashMap<UUID, Status>) entityStatus;
    }
}
