package com.btjf.service.sys;

import com.btjf.mapper.sys.ShortUrlMappingMapper;
import com.btjf.model.sys.ShortUrlMapping;
import com.btjf.util.ShortUrlUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liuyq
 * @date 2019/11/2 0002 上午 9:50
 */
@Service
public class ShortUrlService {

    @Resource
    private ShortUrlMappingMapper shortUrlMappingMapper;


    public String saveAndReturnShortUrl(String url){
        String shortUrl = ShortUrlUtil.shortUrl(url);
        ShortUrlMapping shortUrlMapping = new ShortUrlMapping();
        shortUrlMapping.setLongUrl(url);
        shortUrlMapping.setShortUrl(shortUrl);
        shortUrlMappingMapper.insertSelective(shortUrlMapping);
        return "/wx/work/getUrl?shortUrl="+shortUrl;
    }

   public String getByShort(String shortUrl){
      return shortUrlMappingMapper.getByShort(shortUrl);
   }
}
