package demo.pkg.helloapp.controller;

import demo.pkg.helloapp.pojo.TvSeriesDto;
import demo.pkg.helloapp.service.TvSeriesService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.springframework.data;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
//import sun.nio.ch.IOUtil;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;
//import java.util.*;
import java.util.List;
//import org.apache.commons.lang.StringUtils.*;
//import org.apache.commons.io.*;
//import org.apache.hadoop.io.IOUtils;
//import org.apache.tomcat.util.http.fileupload.*;

//import org.apache.*;

import static org.apache.tomcat.util.http.fileupload.IOUtils.copy;

@RestController
@RequestMapping("/tvseries")
public class AppController {
    private static final Log log = LogFactory.getLog(AppController.class);

    @Autowired TvSeriesService tvSeriesService;
    @GetMapping
    public List<TvSeriesDto> getAll(){
        if(log.isTraceEnabled()){
            log.trace("getAll();被调用了");
        }
        List<TvSeriesDto> list = tvSeriesService.getAllTvSeries();
        if(log.isTraceEnabled()){
            log.trace("查询获取" + list.size() + "条记录");
        }
        return list;
    }
//    @GetMapping
//    public List<TvSeriesDto> getAll(){
//        if (log.isTraceEnabled()){
//            log.trace("getAll();被调用了");
//        }
//        List<TvSeriesDto> list = new ArrayList<>();
//        list.add(createWestWorld());
//        list.add(createPoi());
//        list.add(createWestWorld());
//        return list;
//    }

    @GetMapping("/{id}")
    public TvSeriesDto getOne(@PathVariable int id){
        if (log.isTraceEnabled()){
            log.trace("getOne " + id );
        }
        if (id == 101){
            return createWestWorld();
        }else if(id == 102){
            return createPoi();
        }else {
//            throw new ResourceNotFoundException();
            return null;
        }
    }
    @PostMapping
    public TvSeriesDto insertOne( @RequestBody TvSeriesDto tvSeriesDto ){
        if(log.isTraceEnabled()) {
            log.trace("这里应该写新增tvSeriesDto到数据库的代码，传到进来的参数是：" + tvSeriesDto);
        }
//        tvSeriesDto.setId(9999);
        tvSeriesService.addTvSeries(tvSeriesDto);
        return tvSeriesDto;
    }
    @PutMapping("/{id}")
    public TvSeriesDto updateOne(@PathVariable int id,@RequestBody TvSeriesDto tvSeriesDto){
        if(log.isTraceEnabled()){
            log.trace("updateOne" + id);
        }
        TvSeriesDto ts = tvSeriesService.getTvSeriesById(id);
        if (ts == null) {
//            throw new ResourceNotFoundException();
            return null;
        }
        ts.setSeasonCount(tvSeriesDto.getSeasonCount());
        ts.setName(tvSeriesDto.getName());
        ts.setOriginRelease(tvSeriesDto.getOriginRelease());
        tvSeriesService.updateTvSeries(ts);
        return ts;
    }
    @DeleteMapping("/{id}")
    public Map<String,String>deleteOne(@PathVariable int id, HttpServletRequest request,@RequestParam(value = "delete_reason",required = false) String deleteReason)throws Exception{
        if (log.isTraceEnabled()){
            log.trace("deleteOne"+id);
        }
        Map<String,String> result = new HashMap<>();
        if (id == 101){
            result.put("message","#101被"+request.getRemoteAddr()+"删除（原因:"+deleteReason +")");
        }else if(id == 102){
            throw new RuntimeException("#102不能删除");
        }else{
            return null;
        }
        return result;
    }
    @PostMapping(value = "/{id}/photos",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addPhoto(@PathVariable int id, @RequestParam("photo") MultipartFile imgFile) throws Exception {
        if (log.isTraceEnabled()){
            log.trace("接收到文件 " + id + "收到文件" + imgFile.getOriginalFilename());
        }
        File file;
        FileOutputStream fileOutputStream =  new FileOutputStream("target/"+imgFile.getOriginalFilename());
        copy(imgFile.getInputStream(),fileOutputStream);
        fileOutputStream.close();
    }
    @GetMapping(value = "/{id}/icon", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getIcon(@PathVariable int id) throws Exception{
        if (log.isTraceEnabled()){
            log.trace("getIcon(" + id + ")");
        }
        String iconFile = "icon.jpg";
        File file;
        InputStream inputStream = new FileInputStream(iconFile);

        byte [] buffer = new byte[27277];
        IOUtils.readFully(inputStream,buffer);
        return buffer;
//        IOUtils ioUtils = new IOUtils();
//        ioUtils.t
    }
    private TvSeriesDto modifyPoi(){
        return null;
    }
    private TvSeriesDto createPoi(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016,Calendar.OCTOBER,2,0,0,0);
        return new TvSeriesDto(102,"Person of Interest",5, calendar.getTime());
    }
    private TvSeriesDto createWestWorld(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016,Calendar.SEPTEMBER,2,0,0,0);
        return new TvSeriesDto(101,"West World",1,calendar.getTime());
    }
}
