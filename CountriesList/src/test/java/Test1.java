import com.github.pagehelper.PageHelper;
import com.ssm.bean.Country;
import com.ssm.dao.CountryMapper;
import com.ssm.service.impl.CountryService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.mapperhelper.MapperHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations="classpath:applicationContext.xml")
public class Test1 {

    @Autowired
    CountryService countryService;
    @Autowired
    CountryMapper countryMapper;

    @Test
    public void test() throws IOException {
        String resource = "mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MapperHelper mapperHelper = new MapperHelper();
        mapperHelper.processConfiguration(sqlSession.getConfiguration());
        CountryMapper mapper = sqlSession.getMapper(CountryMapper.class);
        List<Country> countries= mapper.selectAll();
//        List<Country> countries = mapper.selectByExample(null);
        for (Country country : countries) {
            System.out.println(country);
        }
    }

    @Test
    public void test1(){
        List<Country> countries = countryService.selectByExample(null);
        for (Country country : countries) {
            System.out.println(country);
        }
    }


    @Test
    public void test2(){
        PageHelper.startPage(2,10);
        List<Country> countries = countryMapper.selectAll();
        for (Country country : countries) {
            System.out.println(country);
        }
//        PageInfo pageInfo = new PageInfo(countries);
//        List<Country> list = pageInfo.getList();
//        for (Country o : list) {
//            System.out.println(o);
//        }
//        System.out.println(pageInfo);
//        System.out.println(countries);
    }
}
