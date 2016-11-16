package ru.innopolis.borgatin.server.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * Абстрактный класс нужен для работы с полем версия
 */
@MappedSuperclass
public abstract class AbstractEntityVersion {

    @Version
    @Column
    private int version;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
