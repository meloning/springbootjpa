package com.example.springbootjpa;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {
    @Id
    @GeneratedValue // 자동으로 생성되는 값을 사용하겠다고 명시
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    // @Column
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created = new Date();

    private String yes;

    @Transient
    private String no;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "home_street"))
    })
    private Address address;

//    @Embedded
//    @AttributeOverrides({
//            @AttributeOverride(name = "street", column = @Column(name = "home_street"))
//    })
//    private Address homeAddress;
//
//    @Embedded
//    private Address officeAddress;

    @OneToMany(mappedBy = "owner")
    private Set<Study> studies = new HashSet<>();

    public Set<Study> getStudies() {
        return studies;
    }

    public void setStudies(Set<Study> studies) {
        this.studies = studies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // combinant Method???
    public void addStudy(Study study) {
        this.getStudies().add(study); // 객체지향적으로 본다면 양방향으로 참조를 해야하기 때문에 해줘야하는게 맞음
        study.setOwner(this);
    }

    public void removeStudy(Study study) {
        this.getStudies().remove(study);
    }
}
