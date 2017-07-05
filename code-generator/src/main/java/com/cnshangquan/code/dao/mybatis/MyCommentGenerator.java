/*
 *  Copyright 2008 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.cnshangquan.code.dao.mybatis;

import static org.mybatis.generator.internal.util.StringUtility.isTrue;

import java.util.Properties;

import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.internal.DefaultCommentGenerator;

/**
 * @author Jeff Butler
 * 
 */
public class MyCommentGenerator extends DefaultCommentGenerator {

    private final Properties properties;
    private boolean suppressDate;
    private boolean suppressAllComments;

    public MyCommentGenerator() {
        super();
        this.properties = new Properties();
        this.suppressDate = false;
        this.suppressAllComments = false;
    }

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        // add no file level comments by default
        return;
    }

    /**
     * 为xml写注释
     */
    @Override
    public void addComment(XmlElement xmlElement) {

    }

    @Override
    public void addRootComment(XmlElement rootElement) {
        return;
    }

    @Override
    public void addConfigurationProperties(Properties properties) {
        this.properties.putAll(properties);

        this.suppressDate = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_DATE));

        this.suppressAllComments = isTrue(properties.getProperty(PropertyRegistry.COMMENT_GENERATOR_SUPPRESS_ALL_COMMENTS));
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {

        StringBuilder sb = new StringBuilder();

        innerClass.addJavaDocLine("/**"); //$NON-NLS-1$

        sb.append(" * 表： "); //$NON-NLS-1$
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append(" 的映射类").append(".");

        innerClass.addJavaDocLine(sb.toString());

        innerClass.addJavaDocLine(" * @author wanghaijun");
        innerClass.addJavaDocLine(" */"); //$NON-NLS-1$
    }

    /**
     * 字段注释.
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (this.suppressAllComments) {
            return;
        }
        StringBuffer sb = new StringBuffer();
        field.addJavaDocLine("/**");
        sb.append(" * ").append(introspectedColumn.getRemarks()).append(".");
        field.addJavaDocLine(sb.toString());
        field.addJavaDocLine(" */");
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {

    }

    /**
     * 映射类mapper方法的注释
     */
    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
        if (this.suppressAllComments) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        method.addJavaDocLine("/**"); //$NON-NLS-1$

        sb.append(" * ");
        String methodName = method.getName();
        FullyQualifiedTable tableName = introspectedTable.getFullyQualifiedTable();
        if ("insert".equals(methodName)) {
            sb.append("向表").append(tableName).append("中插入数据");
        } else if ("deleteById".equals(methodName)) {
            sb.append("通过id逻辑删除").append(tableName).append("的数据");
        } else if ("updateById".equals(methodName)) {
            sb.append("通过id修改表").append(tableName);
        } else if ("selectById".equals(methodName)) {
            sb.append("通过id查询表").append(tableName);
        } else if ("deleteByIdReal".equals(methodName)) {
            sb.append("通过id物理删除").append(tableName).append("的数据");
        }
        sb.append(".");
        method.addJavaDocLine(sb.toString());

        method.addJavaDocLine(" */"); //$NON-NLS-1$

    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
        if (this.suppressAllComments) {
            return;
        }

        StringBuilder sb = new StringBuilder();

        innerClass.addJavaDocLine("/**"); //$NON-NLS-1$

        sb.append(" * 表： "); //$NON-NLS-1$
        sb.append(introspectedTable.getFullyQualifiedTable());
        sb.append(" 的映射类").append(".");

        innerClass.addJavaDocLine(sb.toString());

        innerClass.addJavaDocLine(" * @author wanghaijun");
        innerClass.addJavaDocLine(" */"); //$NON-NLS-1$
    }
}
