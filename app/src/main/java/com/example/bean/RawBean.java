package com.example.bean;

import java.util.List;

public class RawBean {
    private String createTime;
    private int day;
    private String downloadLastTime;
    private long download_last_time;
    private int download_num;
    private String fileType;
    private int file_num;
    private String highLightName;
    private String id;
    private String ip;
    private long length;
    private List<MetaFileBean> meta_files;
    private int meta_length;
    private String name;
    private String port;
    private int score;
    private String sourceSize;
    private long time;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getDownloadLastTime() {
        return downloadLastTime;
    }

    public void setDownloadLastTime(String downloadLastTime) {
        this.downloadLastTime = downloadLastTime;
    }

    public long getDownload_last_time() {
        return download_last_time;
    }

    public void setDownload_last_time(long download_last_time) {
        this.download_last_time = download_last_time;
    }

    public int getDownload_num() {
        return download_num;
    }

    public void setDownload_num(int download_num) {
        this.download_num = download_num;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public int getFile_num() {
        return file_num;
    }

    public void setFile_num(int file_num) {
        this.file_num = file_num;
    }

    public String getHighLightName() {
        return highLightName;
    }

    public void setHighLightName(String highLightName) {
        this.highLightName = highLightName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public List<MetaFileBean> getMeta_files() {
        return meta_files;
    }

    public void setMeta_files(List<MetaFileBean> meta_files) {
        this.meta_files = meta_files;
    }

    public int getMeta_length() {
        return meta_length;
    }

    public void setMeta_length(int meta_length) {
        this.meta_length = meta_length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getSourceSize() {
        return sourceSize;
    }

    public void setSourceSize(String sourceSize) {
        this.sourceSize = sourceSize;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
