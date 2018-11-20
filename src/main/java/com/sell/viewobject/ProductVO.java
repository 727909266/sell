package com.sell.viewobject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * Created by huhaoran on 2018/11/21 0021.
 */
@Data
public class ProductVO {
    @JsonProperty("name")
    private String categoryName;
    @JsonProperty("type")
    private int categoryType;
    @JsonProperty("food")
    private List<ProductInfoVO> productInfoVOList;
}
