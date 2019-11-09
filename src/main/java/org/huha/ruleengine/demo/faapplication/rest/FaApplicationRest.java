package org.huha.ruleengine.demo.faapplication.rest;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.huha.ruleengine.demo.faapplication.dto.FaApplicationDTO;
import org.huha.ruleengine.demo.faapplication.service.FaApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 进件申请表
 *
 * @author WangKun
 * @date 2019-01-28 02:33
 **/
@Api(tags = {"fa-申请信息"}, description = "申请信息-申请库")
@RestController
@Scope("prototype")
@RequestMapping(value = "/api/faApplication")
public class FaApplicationRest {

    private static Logger logger = LoggerFactory.getLogger(FaApplicationRest.class);

    @Autowired
    private FaApplicationService service;


    /**
     * 进件申请列表查询
     *
     * @author WangKun
     * @date 2019-01-28 03:12
     **/
    @ApiOperation(value = "按条件查询并分页查询进件申请", notes = "按条件查询并分页查询进件申请")
    @RequestMapping(value = "/searchByPage/v1", method = RequestMethod.GET)
    public ResponseEntity searchByPage(int pageNum, int pageSize) throws Exception {
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<FaApplicationDTO> list = service.search();

        PageInfo pageInfo = new PageInfo(list);
        // 分页带条件查询
        return new ResponseEntity<>(pageInfo, HttpStatus.OK);
    }


}
