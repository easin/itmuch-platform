package com.itmuch.platform.admin.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.itmuch.core.log.Log;
import com.itmuch.core.log.Pagination;
import com.itmuch.core.page.PageVo;
import com.itmuch.platform.admin.vo.LogQueryVo;

@Service
public class LogService {
    @Resource
    private MongoTemplate mongo;

    public Pagination selectPaged(LogQueryVo queryVo, PageVo pageVo) {

        Query query = new Query();
        Criteria cirteria = new Criteria();

        List<Criteria> clist = Lists.newArrayList();

        if (queryVo.getStartTime() != null) {
            Criteria gte = Criteria.where("timestamp").gte(queryVo.getStartTime());
            clist.add(gte);
        }
        if (queryVo.getEndTime() != null) {
            Criteria lte = Criteria.where("timestamp").lte(queryVo.getEndTime());
            clist.add(lte);
        }
        if (!StringUtils.isEmpty(queryVo.getIp())) {
            Criteria ip = Criteria.where("ip").is(queryVo.getIp());
            clist.add(ip);
        }
        if (!StringUtils.isEmpty(queryVo.getLevel())) {
            Criteria level = Criteria.where("level").is(queryVo.getLevel());
            clist.add(level);
        }
        //        if (queryVo.getMessage() != null) {
        //            Pattern pattern = Pattern.compile(queryVo.getMessage(), Pattern.CASE_INSENSITIVE);
        //            query.
        //        }
        Criteria[] array = clist.toArray(new Criteria[clist.size()]);
        if (array.length > 0) {
            cirteria.andOperator(array);

        }
        query.addCriteria(cirteria);

        long totalCount = this.mongo.count(query, Log.class);
        Pagination page = new Pagination(pageVo.getPage(), pageVo.getRows(), totalCount);

        // skip相当于从那条记录开始
        query.skip(page.getFirstResult());
        // 从skip开始,取多少条记录
        query.limit(pageVo.getRows());
        // 按照时间排序
        query.with(new Sort(Direction.DESC, "timestamp"));
        List<Log> find = this.mongo.find(query, Log.class);
        page.setRows(find);

        return page;
    }
}
