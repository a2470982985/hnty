package com.coco360.pojo;

public class Task {
    private int id;
    private int courseId;
    private int num;
    private String title;
    private String teacherName;
    private String courseName;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", num=" + num +
                ", title='" + title + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", courseName='" + courseName + '\'' +
                '}';
    }

    public Task() {
    }

    public Task(int id, int courseId, int num, String title, String teacherName, String courseName) {
        this.id = id;
        this.courseId = courseId;
        this.num = num;
        this.title = title;
        this.teacherName = teacherName;
        this.courseName = courseName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
