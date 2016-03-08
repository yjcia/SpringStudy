package com.spring.model;

/**
 * Created by YanJun on 2016/2/29.
 */
public class Patch {
    private int id;
    private String patch_name;
    private String path;
    private String script;
    private String host;
    private String user;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatch_name() {
        return patch_name;
    }

    public void setPatch_name(String patch_name) {
        this.patch_name = patch_name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Patch{" +
                "id=" + id +
                ", patch_name='" + patch_name + '\'' +
                ", path='" + path + '\'' +
                ", script='" + script + '\'' +
                ", host='" + host + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
