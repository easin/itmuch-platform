/*
 *  Copyright 2009 The Apache Software Foundation
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

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.mybatis3.MyBatis3FormattingUtilities;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

/**
 * 
 * @author Jeff Butler
 * 
 */
public class DeleteByPrimaryKeyLogicElementGenerator extends AbstractXmlElementGenerator {

	public DeleteByPrimaryKeyLogicElementGenerator() {
		super();
	}

	@Override
	public void addElements(XmlElement parentElement) {
		XmlElement answer = new XmlElement("delete"); //$NON-NLS-1$

		answer.addAttribute(new Attribute("id", this.introspectedTable.getDeleteByPrimaryKeyLogicStatementId())); //$NON-NLS-1$
		String parameterClass;
		if (this.introspectedTable.getRules().generatePrimaryKeyClass()) {
			parameterClass = this.introspectedTable.getPrimaryKeyType();
		} else {
			// PK fields are in the base class. If more than on PK
			// field, then they are coming in a map.
			if (this.introspectedTable.getPrimaryKeyColumns().size() > 1) {
				parameterClass = "map"; //$NON-NLS-1$
			} else {
				parameterClass = this.introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType().toString();
			}
		}
		answer.addAttribute(new Attribute("parameterType", //$NON-NLS-1$
				parameterClass));

		this.context.getCommentGenerator().addComment(answer);

		StringBuilder sb = new StringBuilder();
		sb.append("update  "); //$NON-NLS-1$
		sb.append(this.introspectedTable.getFullyQualifiedTableNameAtRuntime()).append(" ");
		sb.append("set del_flag = 1 ");
		answer.addElement(new TextElement(sb.toString()));

		boolean and = false;
		for (IntrospectedColumn introspectedColumn : this.introspectedTable.getPrimaryKeyColumns()) {
			sb.setLength(0);
			if (and) {
				sb.append("  and "); //$NON-NLS-1$
			} else {
				sb.append("where "); //$NON-NLS-1$
				and = true;
			}

			sb.append(MyBatis3FormattingUtilities.getEscapedColumnName(introspectedColumn));
			sb.append(" = "); //$NON-NLS-1$
			sb.append(MyBatis3FormattingUtilities.getParameterClause(introspectedColumn));
			answer.addElement(new TextElement(sb.toString()));
		}

		if (this.context.getPlugins().sqlMapDeleteByPrimaryKeyElementGenerated(answer, this.introspectedTable)) {
			parentElement.addElement(answer);
		}
	}
}
