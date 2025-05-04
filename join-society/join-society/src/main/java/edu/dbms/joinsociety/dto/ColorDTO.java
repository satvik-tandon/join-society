package edu.dbms.joinsociety.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
public class ColorDTO {
    @EqualsAndHashCode.Include
    private Integer id;
    private String name;
    private String hexCode;
}
