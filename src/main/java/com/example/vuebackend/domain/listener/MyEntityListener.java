package com.example.vuebackend.domain.listener;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class MyEntityListener {
    
    @PrePersist
    public void prePersist(Object o){
        if (o instanceof Auditable){
            ((Auditable)o).setCreatedAt(LocalDateTime.now());
            ((Auditable)o).setUpdatedAt(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Object o){
        if(o instanceof Auditable){
            ((Auditable)o).setUpdatedAt(LocalDateTime.now());
        }
    }

}
