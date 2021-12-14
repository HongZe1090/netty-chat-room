import com.netty.adminServe.AdminServeApplication;
import com.netty.adminServe.dao.LogMapper;
import com.netty.common.entity.LogInfo;
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
    LogMapper logMapper;

    @org.junit.jupiter.api.Test
    public void name(){
        LogInfo logInfo = new LogInfo();
        logInfo.setOperName("test");
        logInfo.setOperStatus(0);
        System.out.println(logMapper.getAllLog());
    }


}
