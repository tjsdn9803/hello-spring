package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {

    @Id
    private Long id;

    @Column
    private String appendDate;

    @Column(unique = true)
    private String email;

    @Column
    private Long index;

    @Column
    private String name;

    @Column(unique = true)
    private String nickname;

    @Column
    private String password;

    @Column
    private String updateDate;

    @Column
    private String userAuth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(String userAuth) {
        this.userAuth = userAuth;
    }

    public String getAppendDate() {
        return appendDate;
    }

    public void setAppendDate(String appendDate) {
        this.appendDate = appendDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
