package com.bosssoft.ecds.service.serviceimp;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bosssoft.ecds.dao.UnePayBookMapper;
import com.bosssoft.ecds.entity.po.UnePayBook;
import com.bosssoft.ecds.service.UnePayBookService;
import org.springframework.stereotype.Service;

@Service
public class UnePayBookServiceImp implements UnePayBookService {

    private UnePayBookMapper unePayBookMapper;

    //查询
    @Override
    public void selectAll(int pageNumber,int size) {
        Page<UnePayBook> page = new Page<>(pageNumber,size);
        unePayBookMapper.selectPage(page,null);
        page.getRecords().forEach(System.out::println);
    }


}
