package cn.luojh.reagion.ctrl;

import cn.luojh.reagion.render.AjaxRender;
import cn.luojh.reagion.repo.entity.Region;
import cn.luojh.reagion.repo.repository.RegionRepository;
import jodd.io.FileUtil;
import jodd.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
@RequestMapping("/")
public class IndexCtrl {

    @Autowired
    private RegionRepository regionDao;

    @RequestMapping("/saveRegion")
    public String saveRegion(String region){

        if(StringUtil.isBlank(region)){
            return AjaxRender.build()
                             .fail();
        }

        String[] arr = StringUtil.split(region,",");

        Region province = null;
        Region city = null;
        Region area = null;

        if(arr.length > 0){
            String name = arr[0];
            int level = 1;
            if(StringUtil.isNotBlank(name)){
                province = regionDao.findByNameAndLevel(name, level);
                if(province == null){
                    province = new Region();
                    province.setName(name);
                    province.setLevel(level);
                    province.setPid(0l);
                    province.setCode("");
                    province.setNamePath(name);
                    province.setCodePath("");
                    province.setIdPath("");
                    province = regionDao.save(province);

                    Long provinceId = province.getId();
                    province.setIdPath(String.valueOf(provinceId));
                    regionDao.save(province);
                }
            }
        }

        if(arr.length > 1){
            String name = arr[1];
            int level = 2;
            if(StringUtil.isNotBlank(name)){
                city = regionDao.findByNameAndLevel(name, level);
                String provinceName = province.getName();
                String provinceId = String.valueOf(province.getId());
                if(city == null){
                    city = new Region();
                    city.setName(name);
                    city.setLevel(level);
                    city.setPid(province.getId());
                    city.setCode("");
                    city.setNamePath(provinceName + "," + name);
                    city.setCodePath("");
                    city.setIdPath("");
                    city = regionDao.save(city);

                    Long cityId = city.getId();
                    city.setIdPath(provinceId+ "," + String.valueOf(cityId));
                    regionDao.save(city);
                }
            }
        }

        if(arr.length > 2){
            String name = arr[2];
            int level = 3;
            if(StringUtil.isNotBlank(name)){
                area = regionDao.findByNameAndLevel(name, level);
                String provinceName = province.getName();
                String provinceId = String.valueOf(province.getId());
                String cityName = city.getName();
                String cityId = String.valueOf(city.getId());
                if(area == null){
                    area = new Region();
                    area.setName(name);
                    area.setLevel(level);
                    area.setPid(city.getId());
                    area.setCode("");
                    area.setNamePath(provinceName + "," + cityName + "," + name);
                    area.setCodePath("");
                    area.setIdPath("");
                    area = regionDao.save(area);

                    Long areaId = area.getId();
                    area.setIdPath(provinceId+ "," + cityId + ","+ String.valueOf(areaId));
                    regionDao.save(area);
                }
            }
        }

        return AjaxRender.build()
                         .ok();
    }

    public static void main(String[] args) {
        String md5 = "";
        try{
            md5 = FileUtil.md5(new File("/Users/luojh/git/wxregion/region.sql"));
        }catch(Exception e){

        }
        System.out.println(md5);
    }
}
