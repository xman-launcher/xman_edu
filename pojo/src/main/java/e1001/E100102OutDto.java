package e1001;

import e0000.OutDto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 18-3-7
 * Time: 下午3:26
 * 查询用户信息.
 */
public class E100102OutDto extends OutDto {
    private List<E100102Dto> list; //实体类

    public List<E100102Dto> getList() {
        return list;
    }

    public void setList(List<E100102Dto> list) {
        this.list = list;
    }
}
