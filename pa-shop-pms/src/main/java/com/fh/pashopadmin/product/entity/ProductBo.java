package com.fh.pashopadmin.product.entity;

import com.fh.pashopadmin.attributevalue.entity.PmsProductAttributeValue;
import com.fh.pashopadmin.fullreduction.entity.PmsProductFullReduction;
import com.fh.pashopadmin.ladder.entity.PmsProductLadder;
import com.fh.pashopadmin.memberprice.entity.PmsMemberPrice;
import com.fh.pashopadmin.skustock.entity.PmsSkuStock;
import lombok.Data;

import java.util.List;

@Data
public class ProductBo  extends  PmsProduct{

private  List<PmsMemberPrice> memberList;
private List<PmsProductFullReduction> fullReductionList;
private List<PmsProductLadder> productLadderList;
private  List<PmsProductAttributeValue>  productAttributeValueList;
private  List<PmsSkuStock> skuStockList;

}
