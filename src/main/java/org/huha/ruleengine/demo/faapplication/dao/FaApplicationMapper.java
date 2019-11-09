package org.huha.ruleengine.demo.faapplication.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.huha.ruleengine.demo.faapplication.dto.FaApplicationDTO;

import java.util.List;
import java.util.Map;

/**
 * 进件申请持久层
 *
 * @author WangKun
 * @date 2019-01-29 11:41
 **/
@Mapper
public interface FaApplicationMapper {

    List<FaApplicationDTO> searchByPaging();
}
