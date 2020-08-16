package top.xiaobolin.shequ.dto;

import lombok.Data;
import top.xiaobolin.shequ.model.User;

/**
 * @author：xiaobolin
 * @date 2020/7/21/0021 - 15:29
 */
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likenCount;
    private User user;
}
