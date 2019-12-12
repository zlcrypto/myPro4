package demo.pkg.helloapp.service;/**
 * @ProjectName: helloapp
 * @Package: demo.pkg.helloapp
 * @ClassName: TvSeriesService
 * @Author: liang.zhang.ms
 * @Description: service
 * @Date: 2019/10/17 6:57
 * @Version: 1.0
 */

import demo.pkg.helloapp.dao.TvCharacterDao;
import demo.pkg.helloapp.pojo.TvCharacterDto;
import demo.pkg.helloapp.pojo.TvSeriesDto;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import demo.pkg.helloapp.dao.TvSeriesDao;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.logging.Log;

import java.util.List;

/**
 *@类名 TvSeriesService
 *@描述 TODO
 *@作者 liang.zhang.ms
 *@创建日期 2019/10/17 6:57
 *@版本号 1.0
 *@参考地址
 **/
@Service
public class TvSeriesService {
    private final Log log = (Log) LogFactory.getLog(TvSeriesService.class);

    @Autowired TvSeriesDao tvSeriesDao;
    @Autowired TvCharacterDao tvCharacterDao;

    @Transactional(readOnly = true)
    public List<TvSeriesDto> getAllTvSeries(){
        try {
            Thread.sleep(10);
        }catch (Exception e){

        }
        if (log.isTraceEnabled()){
            log.trace("getAllTvSeries started  ");
        }
        List<TvSeriesDto> list = tvSeriesDao.getAll();
        return list;
    }

    @Transactional(readOnly = true)
    public TvSeriesDto getTvSeriesById(int tvSeriesId){
        if (log.isTraceEnabled()){
            log.trace("getTvSeriesById started for "+tvSeriesId);
        }
        TvSeriesDto seriesDto = tvSeriesDao.getOneById(tvSeriesId);
        if (seriesDto != null){
            seriesDto.setTvCharacters(tvCharacterDao.getAllByTvSeriesId(tvSeriesId));
        }
        return seriesDto;
    }

    public TvSeriesDto updateTvSeries(TvSeriesDto tvSeriesDto){
        if (log.isTraceEnabled()){
            log.trace("updateTvSeries started for " + tvSeriesDto);
        }
        tvSeriesDao.update(tvSeriesDto);
        return tvSeriesDto;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TvSeriesDto addTvSeries(TvSeriesDto tvSeriesDto){
        if (log.isTraceEnabled()){
            log.trace("addTvSeries started for " + tvSeriesDto);
        }
        tvSeriesDao.insert(tvSeriesDto);
        if (tvSeriesDto.getId() == null){
            throw new RuntimeException("cannot got primary key id");
        }
        if(tvSeriesDto.getTvCharacters() != null){
            for(TvCharacterDto tvCharacterDto : tvSeriesDto.getTvCharacters()){
                tvCharacterDto.setTvSeriesId(tvSeriesDto.getId().intValue());
                tvCharacterDao.insert(tvCharacterDto);
            }
        }
        return tvSeriesDto;
    }

    public TvCharacterDto updateTvCharacter(TvCharacterDto tvCharacterDto){
        tvCharacterDao.update(tvCharacterDto);
        return tvCharacterDto;
    }

    public TvCharacterDto addTvCharacter(TvCharacterDto tvCharacterDto){
        tvCharacterDao.insert(tvCharacterDto);
        return tvCharacterDto;
    }

    public boolean deleteTvSeries(int id,String reason){
        tvSeriesDao.logicDelete(id,reason);
        return true;
    }
}
    