package com.vlad.securities.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.ValidationException;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor()
@Entity
@Table(name = "securities")
public class SecuritiesEntity implements Serializable {

    private static final String NAME_REGEXP = "^[А-Яа-я0-9\\s]+$";

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "security")
    private List<HistoryEntity> histories;

    @NotBlank
    @Column(length = 36)
    private String secId;

    @NotBlank
    @Column(length = 189)
    private String shortName;

    @NotBlank
    @Column(length = 765)
    @Setter(AccessLevel.NONE)
    private String name;

    private Integer isTraded;

    @Column(length = 189)
    private String regNumber;

    @Column(length = 765)
    private String isin;

    private Integer emitentId;

    @Column(length = 765)
    private String emitentTitle;

    @Column(length = 30)
    private String emitentInn;

    @Column(length = 24)
    private String emitentOkpo;

    @Column(length = 189)
    private String gosReg;

    @Column(length = 93)
    private String type;

    @Column(length = 93)
    private String secGroup;

    @Column(length = 12)
    private String primaryBoardId;

    @Column(length = 12)
    private String marketPriceBoardId;

    public void setName(String name) {
        if (!java.util.regex.Pattern.matches(NAME_REGEXP, name)) {
            throw new ValidationException("Parameter 'name' is incorrect for pattern " + NAME_REGEXP);
        }
        this.name = name;
    }

    public void setNameWithoutPattern(String name) {
        this.name = name;
    }
}
