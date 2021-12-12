import com.netty.adminServe.AdminServeApplication;
import com.netty.adminServe.dao.AuthMapper;
import com.netty.adminServe.dao.FriendMapper;
import com.netty.adminServe.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/7
 * @描述
 */
@SpringBootTest(classes = AdminServeApplication.class)
public class Test {
    @Autowired
    FriendMapper authMapper;

    @org.junit.jupiter.api.Test
    public void name(){

        System.out.println(authMapper.getUserFriends(1));
    }


}
