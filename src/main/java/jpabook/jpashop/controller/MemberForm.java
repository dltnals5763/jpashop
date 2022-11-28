package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor
public class MemberForm {

    private String name;
    private String street;

    private String city;
    private String zipcode;
}
