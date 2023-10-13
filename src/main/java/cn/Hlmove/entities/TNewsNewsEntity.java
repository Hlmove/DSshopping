package cn.Hlmove.entities;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 新闻信息表实体类
 * t_news_news
 * */
@Data
public class TNewsNewsEntity {
    private Integer newsId;//新闻编号
    private Integer classId;//分类编号
    private String title;
    private String content;
    private Timestamp datetime;
    private String publisher;
}