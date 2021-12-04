package com.sergeev.visitcard.web.model.pg2;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PeopleInfoReq {
    private String name;
    private String lastName;
    private String age;
    private String town;
    private String country;

    @Override
    public String toString() {
        return "PeopleInfoReq{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age='" + age + '\'' +
                ", town='" + town + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
