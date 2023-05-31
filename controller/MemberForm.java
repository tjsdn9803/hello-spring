package hello.hellospring.controller;

import hello.hellospring.domain.Member;

public class MemberForm {
    private String email;
    private String name;
    private String password;
    private String nickname;
    private String jwt;

    public static MemberForm toMemberForm(Member member) {
        MemberForm memberForm = new MemberForm();
        memberForm.setEmail(member.getEmail());
        memberForm.setPassword(member.getPassword());
        return memberForm;
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

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
