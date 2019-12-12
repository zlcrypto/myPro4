package demo.pkg.helloapp.dao;

import demo.pkg.helloapp.pojo.TvCharacterDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
@Mapper
public interface TvCharacterDao {
    
    @Select("select * from tv_character where id=#{id}")
    public TvCharacterDto getOneById(int id);
    
    @Select("select * from tv_character where tv_series_id=#{tvSeriesId}")
    public List<TvCharacterDto > getAllByTvSeriesId(int tvSeriesId);
    
    public int update(TvCharacterDto  tvCharacter);
    public int insert(TvCharacterDto  tvCharacter);
    
    @Delete("delete from tv_character where id=#{id}")
    public int delete(int id);
}
