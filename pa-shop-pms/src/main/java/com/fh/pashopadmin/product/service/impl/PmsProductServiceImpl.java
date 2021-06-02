package com.fh.pashopadmin.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fh.pashopadmin.attributevalue.entity.PmsProductAttributeValue;
import com.fh.pashopadmin.attributevalue.service.IPmsProductAttributeValueService;
import com.fh.pashopadmin.fullreduction.entity.PmsProductFullReduction;
import com.fh.pashopadmin.fullreduction.service.IPmsProductFullReductionService;
import com.fh.pashopadmin.ladder.entity.PmsProductLadder;
import com.fh.pashopadmin.ladder.service.IPmsProductLadderService;
import com.fh.pashopadmin.memberprice.entity.PmsMemberPrice;
import com.fh.pashopadmin.memberprice.service.IPmsMemberPriceService;
import com.fh.pashopadmin.product.entity.PmsProduct;
import com.fh.pashopadmin.product.entity.ProductBo;
import com.fh.pashopadmin.product.mapper.PmsProductMapper;
import com.fh.pashopadmin.product.service.IPmsProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fh.pashopadmin.skustock.service.IPmsSkuStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mulonglong
 * @since 2021-05-23
 */
@Service
public class PmsProductServiceImpl extends ServiceImpl<PmsProductMapper, PmsProduct> implements IPmsProductService {

     @Autowired
     private IPmsMemberPriceService memberPriceService;
    @Autowired/* 满减*/
    private IPmsProductFullReductionService  fullReductionService;

    @Autowired/* 梯减*/
    private IPmsProductLadderService ladderService;

    @Autowired
    private IPmsProductAttributeValueService valueService;

    @Autowired
    private IPmsSkuStockService skuStockService;

    @Override
    @Transactional
    public void saveOrUpdatesaveOrUpdateProduct(ProductBo pmsProduct) {
        /* 商品*/
        boolean b = saveOrUpdate(pmsProduct);
        /* 梯减*/
        QueryWrapper<PmsProductLadder> productLadderQueryWrapper = new QueryWrapper<>();
        productLadderQueryWrapper.eq("product_id",pmsProduct.getId());
        ladderService.remove(productLadderQueryWrapper);
        pmsProduct.getProductLadderList().forEach(ladder->{
            ladder.setProductId(pmsProduct.getId());
        });
        ladderService.saveBatch(pmsProduct.getProductLadderList());
 /* 会员*/
        QueryWrapper<PmsMemberPrice> memberPriceQueryWrapper = new QueryWrapper<>();
        memberPriceQueryWrapper.eq("product_id",pmsProduct.getId());
        memberPriceService.remove(memberPriceQueryWrapper);
        pmsProduct.getMemberList().forEach(member->{
            member.setProductId(pmsProduct.getId());
        });
        memberPriceService.saveBatch(pmsProduct.getMemberList());

/* 满减*/
        QueryWrapper<PmsProductFullReduction> fullReductionQueryWrapper = new QueryWrapper<>();
        fullReductionQueryWrapper.eq("product_id",pmsProduct.getId());
        fullReductionService.remove(fullReductionQueryWrapper);
        pmsProduct.getFullReductionList().forEach(full->{
            full.setProductId(pmsProduct.getId());
        });
        fullReductionService.saveBatch(pmsProduct.getFullReductionList());
/*  value */
QueryWrapper<PmsProductAttributeValue> valueQueryWrapper = new QueryWrapper<>();
        pmsProduct.getProductAttributeValueList().forEach(value->{
            value.setProductId(pmsProduct.getId());
        });
        valueService.saveBatch(pmsProduct.getProductAttributeValueList());
/* sku*/
        pmsProduct.getSkuStockList().forEach(sku->{

            sku.setSkuCode(pmsProduct.getId()+""+new Date().getTime());
            sku.setProductId(pmsProduct.getId());
        });


        boolean b1 = skuStockService.saveBatch(pmsProduct.getSkuStockList());

    }
}
