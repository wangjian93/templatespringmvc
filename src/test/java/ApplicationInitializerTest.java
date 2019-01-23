import com.ivo.org.model.SignedDepartment;
import com.ivo.org.service.AdjustService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author wangjian
 * @date 2018/11/7
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/applicationContext-*.xml" })
public class ApplicationInitializerTest {

    @Autowired
    private AdjustService adjustService;

    @Test
    public void contextLoad() {}

    @Test
    public void testLoad() {
        List<SignedDepartment> list = adjustService.getSignedDept();
        for(SignedDepartment signedDept : list) {
            System.out.println(signedDept.getDeptId() + "  ： " + signedDept.getParentId() + "    " + signedDept.getDeptName());
        }
    }

    @Test
    public void testAdd() {

    }

    @Test
    public void testRemove() {
        long[] ids = {15};
        adjustService.removeAdjust(ids);
    }
}
