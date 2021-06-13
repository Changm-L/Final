package com.example.afinal;
//사용자 계정 정보 모델 클래스
public class UserAccount {
    private String Id;
    private String Pw;
    private String name;
    private String add;
    private String idToken;

    public UserAccount() { } //파이어 베이스 DB 사용할때는 빈 생성자가 하나 있어야만 한다! 안그럼 조회할때 오류남

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPw() {
        return Pw;
    }

    public void setPw(String pw) {
        Pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
}
