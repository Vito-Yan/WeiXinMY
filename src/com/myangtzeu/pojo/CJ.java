package com.myangtzeu.pojo;

public class CJ {
	private String course;
	private String score;
	private CJ[] cj;
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public CJ[] getCj() {
		return cj;
	}
	public void setCj(CJ[] cj) {
		this.cj = cj;
	}
	public CJ(String course, String score, CJ[] cj) {
		super();
		this.course = course;
		this.score = score;
		this.cj = cj;
	}
	public CJ() {
		super();
	}
 }
