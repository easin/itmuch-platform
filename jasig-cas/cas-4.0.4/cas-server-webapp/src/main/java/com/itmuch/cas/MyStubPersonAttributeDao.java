package com.itmuch.cas;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

import org.jasig.services.persondir.IPersonAttributes;
import org.jasig.services.persondir.support.AttributeNamedPersonImpl;
import org.jasig.services.persondir.support.StubPersonAttributeDao;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 登陆成功返回更多信息, 目前返回的是用户id+用户名
 * 参考文档: http://www.cnblogs.com/vhua/p/cas_4.html
 * 缺点: sql语句在java类里, 不是很灵活, 改使用在deployerConfigContext.xml
 * 配置 org.jasig.services.persondir.support.jdbc.SingleRowJdbcPersonAttributeDao 的方式
 * @author zhouli
 */
@Deprecated
public class MyStubPersonAttributeDao extends StubPersonAttributeDao {

    @NotNull
    private JdbcTemplate jdbcTemplate;

    @NotNull
    private DataSource dataSource;

    public final void setDataSource(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.dataSource = dataSource;
    }

    @Override
    public IPersonAttributes getPerson(String uid) {

        String sql = "select id,login_name as username from sys_user where login_name = ? and del_flag = 0";
        Map<String, Object> map = this.jdbcTemplate.queryForMap(sql, uid);

        Map<String, List<Object>> attributes = new HashMap<String, List<Object>>();

        attributes.put("username", Collections.singletonList((Object) uid));
        attributes.put("id", Collections.singletonList(map.get("id")));

        return new AttributeNamedPersonImpl(attributes);
    }
}
