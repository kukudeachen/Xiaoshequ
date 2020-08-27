package top.xiaobolin.shequ.service;

import java.io.Serializable;

/**
 * @author：xiaobolin
 * @date 2020/8/25/0025 - 19:49
 */
public class Result implements Serializable {
    private  Integer errno;
    private  String[] data;
    Result(){}

    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] images) {
        this.data = images;
    }
}
