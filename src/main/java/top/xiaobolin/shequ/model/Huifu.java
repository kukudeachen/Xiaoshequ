package top.xiaobolin.shequ.model;

import lombok.Data;

/**
 * @author：xiaobolin
 * @date 2020/8/21/0021 - 14:40
 */
@Data
public class Huifu {
    private  Integer id;
    private  String userName;
    private  String userTou;
    private  String huiFuBody;
    private  Integer dianZan;
    private  Integer pingLunShu;
    private  Integer chengJieShang;
    private  Integer chengJieXia;
    private  Long gmtCreate;
    private  Long gmtModified;
}
