package e1001;

import e0000.OutDto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 18-3-7
 * Time: 下午3:26
 * 注册用户信息.
 */
public class E100101OutDto extends OutDto {
    private List<E100101Dto> list; //用户真实姓名

    public List<E100101Dto> getList() {
        return list;
    }

    public void setList(List<E100101Dto> list) {
        this.list = list;
    }
}
