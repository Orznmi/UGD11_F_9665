package com.pbp.gd11_f_9665;

public class Download {

    private String name, path,url,tipe;
    private double size;

    public Download(String name, String path, String url, double size, String tipe) {
        this.name = name;
        this.path = path;
        this.url = url;
        this.size = size;
        this.tipe=tipe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getTipe() {
        return tipe;
    }
}
