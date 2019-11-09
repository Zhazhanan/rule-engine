package org.huha.ruleengine.demo.faapplication.service;

import org.huha.ruleengine.demo.faapplication.dao.FaApplicationMapper;
import org.huha.ruleengine.demo.faapplication.dto.FaApplicationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 进件申请业务层
 *
 * @author WangKun
 * @date 2019-01-29 11:31
 **/
@Service
public class FaApplicationService {

    private static Logger logger = LoggerFactory.getLogger(FaApplicationService.class);

    @Autowired
    private FaApplicationMapper mapper;

    public static final String INTOLOCK = "S0-0101:FAAPPLICATION:lock:";
    private static final String MESSAGE = "待判定，进件编号%s";


    /***FC_FLAG_0 = 0 不存在欺诈库*/
    private static final int FC_FLAG_0 = 0;
    /***FC_FLAG_1 = 1 存在欺诈库*/
    private static final int FC_FLAG_1 = 1;

    public List<FaApplicationDTO> search() {
        return mapper.searchByPaging();
    }

}

