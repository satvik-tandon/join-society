package edu.dbms.joinsociety.convertors;

public interface DataConvertor {
    Object toDTO(Object databaseObject);

    Object toDatabaseObject(Object dto);
}
