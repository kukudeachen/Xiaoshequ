package top.xiaobolin.shequ.dto;

import lombok.Data;

/**
 * @author：xiaobolin
 * @date 2020/7/9/0009 - 22:47
 */
@Data
public class AccessTokenDTO {
    private String grant_type;
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;

}
