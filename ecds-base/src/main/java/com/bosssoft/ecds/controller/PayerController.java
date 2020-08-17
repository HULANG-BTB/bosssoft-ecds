package com.bosssoft.ecds.controller;


import com.bosssoft.ecds.response.CommonCode;
import com.bosssoft.ecds.response.QueryResponseResult;
import com.bosssoft.ecds.entity.dto.PageDTO;
import com.bosssoft.ecds.entity.dto.PayerDTO;
import com.bosssoft.ecds.entity.vo.PageVO;
import com.bosssoft.ecds.entity.vo.PayerVO;
import com.bosssoft.ecds.service.impl.PayerServiceImpl;
import com.bosssoft.ecds.utils.MyBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author AloneH
 * @since 2020-08-10
 */
@RestController
@RequestMapping("/payer")
public class PayerController {

    @Autowired
    PayerServiceImpl payerService;

    /**
     * 新增数据
     *
     * @param payerVO
     * @return
     */
    @PostMapping("/save")
    public QueryResponseResult<PayerVO> save(@RequestBody PayerVO payerVO) {
        PayerDTO payerDTO = new PayerDTO();
        MyBeanUtil.copyProperties(payerVO, payerDTO);
        payerDTO = payerService.save(payerDTO);
        MyBeanUtil.copyProperties(payerDTO, payerVO);
        if (payerDTO.getId() != null) {
            return new QueryResponseResult<>(CommonCode.SUCCESS, payerVO);
        } else {
            return new QueryResponseResult<>(CommonCode.FAIL, payerVO);
        }
    }

    /**
     * 删除数据
     *
     * @param id
     * @return
     */
    @DeleteMapping("/remove/{id}")
    public QueryResponseResult<Boolean> remove(@PathVariable("id") Long id) {
        PayerVO payerVO = new PayerVO();
        payerVO.setId(id);
        PayerDTO payerDTO = new PayerDTO();
        MyBeanUtil.copyProperties(payerVO, payerDTO);
        Boolean remove = payerService.remove(payerDTO);
        if (remove.equals(true)) {
            return new QueryResponseResult<>(CommonCode.SUCCESS, remove);
        } else {
            return new QueryResponseResult<>(CommonCode.FAIL, remove);
        }
    }

    /**
     * 更新数据
     *
     * @param payerVO
     * @return
     */
    @PutMapping("/update")
    public QueryResponseResult<Boolean> update(@RequestBody PayerVO payerVO) {
        PayerDTO payerDTO = new PayerDTO();
        MyBeanUtil.copyProperties(payerVO, payerDTO);
        Boolean updateResult = payerService.update(payerDTO);
        if (updateResult.equals(true)) {
            return new QueryResponseResult<>(CommonCode.SUCCESS, updateResult);
        } else {
            return new QueryResponseResult<>(CommonCode.FAIL, updateResult);
        }
    }

    /**
     * 通过ID查询数据
     *
     * @param id
     * @return
     */
    @GetMapping("/getById/{id}")
    public QueryResponseResult<PayerDTO> getById(@PathVariable("id") Long id) {
        PayerVO payerVO = new PayerVO();
        payerVO.setId(id);
        PayerDTO payerDTO = new PayerDTO();
        MyBeanUtil.copyProperties(payerVO, payerDTO);
        payerDTO = payerService.getById(payerDTO);
        if (payerDTO.getId() != null) {
            return new QueryResponseResult<>(CommonCode.SUCCESS, payerDTO);
        } else {
            return new QueryResponseResult<>(CommonCode.FAIL, payerDTO);
        }
    }

    /**
     * 分页查询数据
     *
     * @param page
     * @param limit
     * @param keyword
     * @return
     */
    @GetMapping("listByPage")
    public QueryResponseResult<PageVO> listByPage(@RequestParam("page") Long page, @RequestParam("limit") Long limit, @RequestParam("keyword") String keyword) {
        PageVO pageVO = new PageVO();
        pageVO.setLimit(limit);
        pageVO.setPage(page);
        pageVO.setKeyword(keyword);
        PageDTO<PayerDTO> pageDTO = MyBeanUtil.copyProperties(pageVO, PageDTO.class);
        pageDTO = payerService.listByPage(pageDTO);
        pageVO = MyBeanUtil.copyProperties(pageDTO, PageVO.class);
        return new QueryResponseResult<>(CommonCode.SUCCESS, pageVO);
    }

    /**
     * 批量删除数据
     *
     * @param payerVOList
     * @return
     */
    @DeleteMapping("removeBatch")
    public QueryResponseResult<Boolean> removeBatch(@RequestBody List<PayerVO> payerVOList) {
        List<PayerDTO> payerDTOList = MyBeanUtil.copyListProperties(payerVOList, PayerDTO::new);
        Boolean result = payerService.removeBatch(payerDTOList);
        return new QueryResponseResult<>(CommonCode.SUCCESS, result);
    }

}

