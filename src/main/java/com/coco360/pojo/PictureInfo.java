package com.coco360.pojo;

import java.util.Date;

public class PictureInfo {
    private String beginTime;   //开始时间
    private String endTime;
    private String phoneNumber;
    private String reason;  //原因
    private String position;  //位置
    private String destination;//目的地
    private String duration;
    private String ownName;  //自己名字
    private String counselorSName;  // 辅导员名字
    private String receiveApplication;  //辅导员通过申请时间
    private String initiateAnApplication;  //发起申请

    @Override
    public String toString() {
        return "PictureInfo{" +
                "beginTime='" + beginTime + '\'' +"\n"+
                ", endTime='" + endTime + '\'' +"\n"+
                ", phoneNumber='" + phoneNumber + '\'' +"\n"+
                ", reason='" + reason + '\'' +"\n"+
                ", position='" + position + '\'' +"\n"+
                ", destination='" + destination + '\'' +"\n"+
                ", duration='" + duration + '\'' +"\n"+
                ", ownName='" + ownName + '\'' +"\n"+
                ", counselorSName='" + counselorSName + '\'' +"\n"+
                ", receiveApplication='" + receiveApplication + '\'' +"\n"+
                ", initiateAnApplication='" + initiateAnApplication + '\'' +"\n"+
                '}';
    }

    public PictureInfo() {
    }

    public PictureInfo(String beginTime, String endTime, String phoneNumber, String reason, String position, String destination, String duration, String ownName, String counselorSName, String receiveApplication, String initiateAnApplication) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.phoneNumber = phoneNumber;
        this.reason = reason;
        this.position = position;
        this.destination = destination;
        this.duration = duration;
        this.ownName = ownName;
        this.counselorSName = counselorSName;
        this.receiveApplication = receiveApplication;
        this.initiateAnApplication = initiateAnApplication;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getOwnName() {
        return ownName;
    }

    public void setOwnName(String ownName) {
        this.ownName = ownName;
    }

    public String getCounselorSName() {
        return counselorSName;
    }

    public void setCounselorSName(String counselorSName) {
        this.counselorSName = counselorSName;
    }

    public String getReceiveApplication() {
        return receiveApplication;
    }

    public void setReceiveApplication(String receiveApplication) {
        this.receiveApplication = receiveApplication;
    }

    public String getInitiateAnApplication() {
        return initiateAnApplication;
    }

    public void setInitiateAnApplication(String initiateAnApplication) {
        this.initiateAnApplication = initiateAnApplication;
    }
}
