package com.neu.pojo;

import com.neu.util.StringUtil;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Movie {
	private Long id;//电影id
	private String name;//电影名称
	private String year;//电影上映年份
	private List<String> type;//电影类型（List<String>形式）
	private String typeInfo;//电影类型（String形式）
	private double rate;//电影得分

	public Movie() {
	}



	public String getTypeInfo() {
		return typeInfo;
	}

	public void setTypeInfo(String typeInfo) {
		this.typeInfo = typeInfo;
		String[] types = typeInfo.split(",");
		this.type = Arrays.asList(types);

	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<String> getType() {
		return this.type;
	}

	public void setType(List<String> type) {
		this.type = type;
	}

	public double relevance(Movie m) {
		String patternString = StringUtil.connectString(this.type, "|");
		Pattern pattern = Pattern.compile(patternString);
		int count = 0;
		for (String mType : m.getType()) {
			Matcher matcher = pattern.matcher(mType);
			if (matcher.matches()) {
				count++;
			}
		}
		return Math.log10(count + 1);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: " + this.name + "  ");
		sb.append("Publish Year: " + this.year + "  ");
		sb.append("Type: " + StringUtil.connectString(this.type, ", "));
		return sb.toString();
	}

}