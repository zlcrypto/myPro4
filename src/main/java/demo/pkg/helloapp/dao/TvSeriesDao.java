package demo.pkg.helloapp.dao;

import demo.pkg.helloapp.pojo.TvSeriesDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: helloapp
 * @Package: demo.pkg.helloapp
 * @ClassName: TvSeriesDao
 * @Author: liang.zhang.ms
 * @Description: DAO
 * @Date: 2019/10/17 6:51
 * @Version: 1.0
 */
//@Repository
@Mapper
public interface TvSeriesDao {
//    @Select("select * from tv_series")
//    public List<TvSeriesDto> getAll();

    @Select("select * from tv_series where id=#{id}")
    public TvSeriesDto getOneById(int id);

    @Select("select * from tv_series where status=0")
    public List<TvSeriesDto > getAll();

    public int update(TvSeriesDto  tvSeries);
    public int insert(TvSeriesDto  tvSeries);

    @Delete("delete from tv_series where id=#{id}")
    public int delete(int id);

    @Update("update tv_series set status=-1, reason=#{reason} where id=#{id}")
    public int logicDelete(int id, String reason);
}
