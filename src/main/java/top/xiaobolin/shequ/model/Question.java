package top.xiaobolin.shequ.model;

import lombok.Data;

/**
 * @author：xiaobolin
 * @date 2020/7/19/0019 - 17:31
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator ;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likenCount;
    private String accountId;
    private String userName;
}
