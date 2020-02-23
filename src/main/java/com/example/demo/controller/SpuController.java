package com.example.demo.controller;

import com.example.demo.entity.PmsProductInfo;
import com.example.demo.service.ProductService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Whyn
 * @date 2020/2/22 16:24
 */
@CrossOrigin
@RestController
public class SpuController {
    /**
     * 服务对象
     */
    @Resource
    private ProductService productService;


    /**
     * 根据三级分类获取商品列表
     *
     * @return JsonResult
     */
    @GetMapping("/spuList")
    public List<PmsProductInfo> queryAllByCatalog(Integer catalog3Id) {
        return this.productService.queryAllByCatalog(catalog3Id);
    }

    /**
     * 新增商品spu属性
     *
     * @return
     */
    @PostMapping("/saveSpuInfo")
    public int saveSpuInfo(@RequestBody PmsProductInfo pmsProductInfo) {
        return productService.saveSpuInfo(pmsProductInfo);
    }

    /**
     * 图片上传
     */
    @PostMapping("/fileUpload")
    public String fileUpload(@RequestParam("file") MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        return "";
    }
}
