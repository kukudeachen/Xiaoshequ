package top.xiaobolin.shequ.service;

/**
 * @author：xiaobolin
 * @date 2020/8/25/0025 - 19:52
 */
public class ResultUtil {
    public static Result success(String[] images){
        Result result = new Result();
        result.setErrno(0);
        result.setData(images);
        return result;
    }
    public static Result success(){
        return  success(null);
    }
}
