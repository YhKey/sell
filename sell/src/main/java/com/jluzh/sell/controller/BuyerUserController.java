package com.jluzh.sell.controller;

import com.jluzh.sell.dataobject.BuyerDetail;
import com.jluzh.sell.dataobject.BuyerInfo;
import com.jluzh.sell.dto.OrderDTO;
import com.jluzh.sell.enums.ResultEnum;
import com.jluzh.sell.exception.SellException;
import com.jluzh.sell.form.AddressForm;
import com.jluzh.sell.service.BuyerService;
import com.jluzh.sell.utils.ResultVOUtil;
import com.jluzh.sell.vo.BuyerDetailVO;
import com.jluzh.sell.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author: yanghongkun
 * @description:
 * @date: 2020/02/12
 */
@RestController
@RequestMapping("/buyer")
@Slf4j
public class BuyerUserController {

    @Autowired
    private BuyerService service;

    @GetMapping("/login")
    public ResultVO login(@RequestParam(value = "openid") String openid) {
        BuyerInfo buyer = service.findBuyerOne(openid);
        if (buyer==null){
            buyer = service.create(openid);
        }
        //todo
        Map<String,Object> map=new HashMap<>();
        map.put("buyerId",buyer.getBuyerId());
        return ResultVOUtil.success();
    }

    /**
     * 保存地址信息
     * @param addressForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/save/detail")
    public ResultVO saveDetail(@RequestBody @Valid AddressForm addressForm,
                           BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error("【保存用户详情信息】参数不正确，addressForm={}", addressForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        service.saveBuyerDetailInfo(addressForm);
        return ResultVOUtil.success();
    }

    @GetMapping("/detail/list")
    public ResultVO<Map<String, Object>> detailList(@RequestParam("openid") String openid){
        List<BuyerDetailVO> buyerDetailVOList = service.findAllByOpenid(openid);
        Map<String,Object> map=new HashMap<>();
        map.put("addressList",buyerDetailVOList);
        return ResultVOUtil.success(map);
    }

    @PostMapping("/detail/remove")
    public Map<String, Object> remove(Integer detailId){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", service.deleteDetail(detailId));
        return map;
    }
}
