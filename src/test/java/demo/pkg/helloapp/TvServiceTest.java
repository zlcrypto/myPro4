package demo.pkg.helloapp;/**
 * @ProjectName: helloapp
 * @Package: demo.pkg.helloapp
 * @ClassName: TvServiceTest
 * @Author: liang.zhang.ms
 * @Description: 服务测试
 * @Date: 2019/10/17 21:31
 * @Version: 1.0
 */

import demo.pkg.helloapp.dao.TvSeriesDao;
import demo.pkg.helloapp.pojo.TvCharacterDto;
import demo.pkg.helloapp.pojo.TvSeriesDto;
import demo.pkg.helloapp.service.TvSeriesService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

/**
 *@类名 TvServiceTest
 *@描述 TODO
 *@作者 liang.zhang.ms
 *@创建日期 2019/10/17 21:31
 *@版本号 1.0
 *@参考地址
 **/
public class TvServiceTest {
    @MockBean
    TvSeriesDao tvSeriesDao;
    @MockBean
    TvCharacterDto tvCharacterDto;
    @Autowired
    TvSeriesService tvSeriesService;
    @Test
    public void testGetAllWithoutMockit(){
        List<TvSeriesDto> list =  tvSeriesService.getAllTvSeries();
        Assert.assertTrue(list.size() > 0);
    }
    @Test
    public void testGetAll(){
        List<TvSeriesDto> list = new ArrayList<>();
        TvSeriesDto tvSeriesDto =  new TvSeriesDto();
        tvSeriesDto.setName("POI");
        list.add(tvSeriesDto);

        Mockito.when(tvSeriesDao.getAll()).thenReturn(list);
        List<TvSeriesDto> result = tvSeriesService.getAllTvSeries();
        Assert.assertTrue(result.size()==list.size());
        Assert.assertTrue("POI".equals(result.get(0).getName()));
    }


}
    