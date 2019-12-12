package demo.pkg.helloapp.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import demo.pkg.helloapp.pojo.TvCharacterDto;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

public class TvSeriesDto{
    @Null private Integer id;
    @NotNull private String name;
    @DecimalMin("1") private int seasonCount;
    @Past Date originRelease;



    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @Valid @NotNull @Size(min = 2) private List<TvCharacterDto> tvCharacters;
    public TvSeriesDto(){
    }

    public TvSeriesDto(int id, String name, int seasonCount, Date originRelease){
        this.id = id;
        this.name = name;
        this.seasonCount = seasonCount;
        this.originRelease = originRelease;
//        this.tvCharacters = tvCharacters;
    }
//    public TvSeriesDto(String name, int seasonCount, Date originRelease,List<TvCharacterDto> tvCharacters){
//        this.id = id;
//        this.name = name;
//        this.seasonCount = seasonCount;
//        this.originRelease = originRelease;
//        this.tvCharacters = tvCharacters;
//    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeasonCount() {
        return seasonCount;
    }

    public void setSeasonCount(int seasonCount) {
        this.seasonCount = seasonCount;
    }

    public Date getOriginRelease() {
        return originRelease;
    }

    public void setOriginRelease(Date originRelease) {
        this.originRelease = originRelease;
    }

    public List<TvCharacterDto> getTvCharacters() {
        return tvCharacters;
    }

    public void setTvCharacters(List<TvCharacterDto> tvCharacters) {
        this.tvCharacters = tvCharacters;
    }
    @Override
    public String toString(){
        return this.getClass().getName()+"{id=" + id +"name = " + name + " }";
    }

}
