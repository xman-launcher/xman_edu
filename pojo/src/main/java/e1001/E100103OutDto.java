package e1001;

import e0000.OutDto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 18-3-7
 * Time: 下午3:26
 * 登录用户信息.
 */
public class E100103OutDto extends OutDto {
    private List<E100103Dto> list; //实体类

    public List<E100103Dto> getList() {
        return list;
    }

    public void setList(List<E100103Dto> list) {
        this.list = list;
    }
}
