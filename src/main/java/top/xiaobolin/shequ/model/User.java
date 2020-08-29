package top.xiaobolin.shequ.model;

import lombok.Data;

/**
 * @author：xiaobolin
 * @date 2020/7/14/0014 - 20:54
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gemCreate;
    private Long gemModified;
    private String bio;
    private String figureurlQq;
    private int cishu;
}
