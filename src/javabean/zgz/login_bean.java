package javabean.zgz;

import java.sql.*;
import java.util.*;

public class login_bean {
    private String useravatar;
    private String nickname;
    private String telephone;
    private String useremail;
    private String password;
    private String creditscore;
    private int ready;
    public static Connection_DB db;

    public login_bean() {
        db = new Connection_DB();
        db.getCoon();
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setUseravatar(String useravatar) {
        this.useravatar = useravatar;

    }

    public void setCreditscore(String creditscore) {
        this.creditscore = creditscore;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public int getReady() {
        String sql = "insert into mytest.test values(?,?,3,?,?,?)";
        String params[] = {useravatar, useremail,telephone, password,creditscore};
        //String params[]={"13","1","4","4","5","5"};
        System.out.println(params.toString());
        System.out.println("sssss");
        ready = db.update(sql, params);
        return ready;
    }


}
