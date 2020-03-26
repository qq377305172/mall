package com.example.demo;

import com.example.demo.dao.PmsBaseAttrInfoDao;
import com.example.demo.dao.PmsProductInfoDao;
import com.example.demo.dao.PmsSkuSaleAttrValueDao;
import com.example.demo.entity.PmsSkuInfo;
import com.example.demo.entity.PmsSkuSaleAttrValue;
import com.example.demo.entity.UmsMember;
import com.example.demo.entity.search.PmsSearchSkuInfo;
import com.example.demo.service.AttrService;
import com.example.demo.util.HttpClientUtil;
import com.example.demo.util.JwtUtil;
import com.example.demo.util.RedisUtil;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import io.searchbox.client.JestClient;
import io.searchbox.core.*;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.lucene.queryparser.xml.builders.FilteredQueryBuilder;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.elasticsearch.index.query.*;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptService;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Resource
    PmsProductInfoDao pmsProductInfoDao;
    @Resource
    PmsBaseAttrInfoDao pmsBaseAttrInfoDao;
    @Resource
    RedisUtil redisUtil;
    @Resource
    JestClient jestClient;
    @Resource
    AttrService attrService;

    @Test
    public void contextLoads2() throws IOException {
//        String url = "https://api.weibo.com/oauth2/authorize?client_id=4246669400&response_type=code&redirect_uri=http://127.0.0.1:9000/vlogin";
//        http://127.0.0.1:9000/vlogin?code=6e36ac3d1e4f228a758fa1dd20c85c26
//        String s = HttpClientUtil.doGet(url);
//        String s1 = HttpClientUtil.doGet("http://127.0.0.1:9000/success?code=f00d6de6e4bc3f04fa5353b9dc5da744");
//        Map<String, String> paramMap = new HashMap<>();
//        paramMap.put("client_id", "4246669400");
//        paramMap.put("client_secret", "f00d6de6e4bc3f04fa5353b9dc5da744");
//        paramMap.put("grant_type", "authorization_code");
//        paramMap.put("redirect_uri", "http://127.0.0.1:9000/vlogin");
//        paramMap.put("code", "6e36ac3d1e4f228a758fa1dd20c85c26");
////        String s2 = HttpClientUtil.doPost("https://api.weibo.com/oauth2/access_token", paramMap);
//        String s = HttpClientUtil.doGet("https://api.weibo.com/2/users/show.json?access_token=2.00xDLLIHkha5dE8cd28c0d80yvhbWC&uid=1");
//        System.err.println(s);

//        Map<String, Object> map = new HashMap<>();
//        map.put("nick_name", "阿斯达斯");
//        map.put("id", 1L);
//        String gmall = JwtUtil.encode("gmall", map, "127.0.0.1" + DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
//        System.out.println(gmall);

//        Prop prop = PropKit.use("redis_constants.properties");
//        String sku_attr_key_prefix = prop.get("sku_info_prefix");
//        String sku_info_suffix = prop.get("sku_info_suffix");
//        System.err.println(sku_attr_key_prefix);
//        System.err.println(sku_info_suffix);
//        PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
//        pmsBaseAttrInfo.setAttrName("测试");
//        Long insert = pmsBaseAttrInfoDao.insert(pmsBaseAttrInfo);
//        System.out.println(insert);

//        PmsProductInfo pmsProductInfo = new PmsProductInfo();
//        pmsProductInfo.setProductName("测试3");
//        pmsProductInfo.setDescription("测试3");
//        pmsProductInfoDao.insert(pmsProductInfo);
//        System.out.println(pmsProductInfo.getId());
//        Jedis jedis = redisUtil.getJedis();
//        jedis.set("test11", "1");
//        String test11 = jedis.get("test11");
//        System.out.println(test11);
    }

    private void search() {
        SearchSourceBuilder builder = new SearchSourceBuilder();
        //bool
        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        //filter
//        TermQueryBuilder termQueryBuilder = new TermQueryBuilder();
//        boolQueryBuilder.filter(termQueryBuilder);
        //match
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("skuName", "小米");
        boolQueryBuilder.must(matchQueryBuilder);
        builder.query(boolQueryBuilder);
        //query
//        builder.query();
        //highlight
//        HighlightBuilder highlightBuilder = new HighlightBuilder();
//        builder.highlight();
        builder.from(0);
        builder.size(5);
        System.err.println(builder.toString());
        Search build = new Search.Builder(builder.toString()).addIndex("gmall").addType("PmsSkuInfo").build();
        try {
            SearchResult searchResult = jestClient.execute(build);
            List<SearchResult.Hit<PmsSearchSkuInfo, Void>> hits = searchResult.getHits(PmsSearchSkuInfo.class);
            for (SearchResult.Hit<PmsSearchSkuInfo, Void> hit : hits) {
                PmsSearchSkuInfo source = hit.source;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void insertDataToESFromMysql() throws IOException {
        List<PmsSkuInfo> pmsSkuInfoList = attrService.getAllSkuInfo();
        List<PmsSearchSkuInfo> pmsSearchSkuInfoList = new ArrayList<>();
        for (PmsSkuInfo pmsSkuInfo : pmsSkuInfoList) {
            PmsSearchSkuInfo pmsSearchSkuInfo = new PmsSearchSkuInfo(pmsSkuInfo.getId(), pmsSkuInfo.getSkuName(), pmsSkuInfo.getSkuDesc(),
                    pmsSkuInfo.getCatalog3Id(), pmsSkuInfo.getPrice(), pmsSkuInfo.getSkuDefaultImg(), 0, pmsSkuInfo.getProductId(), pmsSkuInfo.getSkuAttrValueList());
            pmsSearchSkuInfoList.add(pmsSearchSkuInfo);
        }
        for (PmsSearchSkuInfo pmsSearchSkuInfo : pmsSearchSkuInfoList) {
            Index build = new Index.Builder(pmsSearchSkuInfo).index("gmall").type("PmsSkuInfo").id(pmsSearchSkuInfo.getId().toString()).build();
            DocumentResult execute = jestClient.execute(build);
        }
    }

    @Test
    public void contextLoads() throws IOException, MyException {
        String file = this.getClass().getResource("/tracker.conf").getFile();
        ClientGlobal.init(file);
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        StorageClient storageClient = new StorageClient(trackerServer, null);
        String orginalFilename = "C:\\Users\\Admin\\Desktop\\1.jpeg";
        String[] upload_file = storageClient.upload_file(orginalFilename, "jpeg", null);
        for (String s : upload_file) {
            System.out.println("s = " + s);
        }
    }

}
