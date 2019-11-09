package org.huha.ruleengine.demo.faworking.dao;

import org.apache.ibatis.annotations.Mapper;
import org.huha.ruleengine.demo.faworking.dto.FaWorkingDTO;

import java.util.List;

/**
 * 工作信息-申请库 持久层
 *
 * @author WangKun
 * @date 2019-02-12 11:16
 **/
@Mapper
public interface FaWorkingMapper {

    /**
     * 根据进件编号查询工作信息
     *
     * @param applicationNumber 进件编号
     * @return
     * @author lichengqiang
     * @date 2019-03-01
     **/
    List<FaWorkingDTO> findFaWorkingByAppNum(String applicationNumber);

    List<FaWorkingDTO> searchByPaging();

}
