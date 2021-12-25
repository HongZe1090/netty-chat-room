import cn.hutool.core.date.DateTime;
import com.netty.adminServe.AdminServeApplication;
import com.netty.adminServe.dao.LogMapper;
import com.netty.adminServe.dao.MessageMapper;
import com.netty.common.entity.LogInfo;
import com.netty.common.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @创建人 HongZe
 * @创建时间 2021/12/7
 * @描述
 */
@SpringBootTest(classes = AdminServeApplication.class)
public class Test {
    @Autowired
    MessageMapper logMapper;

    @org.junit.jupiter.api.Test
    public void name(){
        logMapper.getMessage(2,1);
        System.out.println(logMapper.getAllInfo().get(1).getFromId());
        System.out.println(logMapper.getAllInfo().get(1).getToId());
        System.out.println(logMapper.getAllInfo().get(1).getInfoContent());
        System.out.println(logMapper.getAllInfo().get(1).getTime());
    }
}
