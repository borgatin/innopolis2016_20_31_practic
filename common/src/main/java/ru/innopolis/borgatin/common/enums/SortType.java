package ru.innopolis.borgatin.common.enums;

import java.io.Serializable;

/**
 * Enum хранит варианты сортировки сущностей:
 *  - по возрастанию (ASC)
 *  - по убыванию (DESC)
 */
public enum SortType implements Serializable {

    ASC,
    DESC;
    SortType(){

    }

}
