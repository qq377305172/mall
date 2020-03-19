package com.example.demo.entity.search;

import com.example.demo.entity.PmsSkuAttrValue;
import lombok.*;
import org.apache.ibatis.annotations.ConstructorArgs;

import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Admin
 * @title: PmsSearchSkuInfo
 * @projectName demo
 * @description: TODO
 * @date 2020/2/29 19:18
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PmsSearchSkuInfo implements Serializable {
    private static final long serialVersionUID = -8881147525238259892L;
    @Id
    private Long id;
    private String skuName;
    private String skuDesc;
    private Long catalog3Id;
    private BigDecimal price;
    private String skuDefaultImg;
    private double hotScore;
    private Long productId;
    private List<PmsSkuAttrValue> skuAttrValueList;


}
