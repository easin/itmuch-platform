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

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.CountByExampleMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.DeleteByExampleMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.DeleteByPrimaryKeyMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.InsertSelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByExampleWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByExampleWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.SelectByPrimaryKeyMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByExampleSelectiveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByExampleWithBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByExampleWithoutBLOBsMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.UpdateByPrimaryKeySelectiveMethodGenerator;
import org.mybatis.generator.config.PropertyRegistry;

/**
 * @author Jeff Butler
 * 
 */
public class JavaMapperGenerator extends AbstractJavaClientGenerator {

	public JavaMapperGenerator() {
		super(true);
	}

	public JavaMapperGenerator(boolean requiresMatchedXMLGenerator) {
		super(requiresMatchedXMLGenerator);
	}

	@Override
	public List<CompilationUnit> getCompilationUnits() {
		this.progressCallback.startTask(getString("Progress.17", //$NON-NLS-1$
				this.introspectedTable.getFullyQualifiedTable().toString()));
		CommentGenerator commentGenerator = this.context.getCommentGenerator();

		FullyQualifiedJavaType type = new FullyQualifiedJavaType(this.introspectedTable.getMyBatis3JavaMapperType());
		Interface interfaze = new Interface(type);
		interfaze.setVisibility(JavaVisibility.PUBLIC);
		commentGenerator.addJavaFileComment(interfaze);

		String rootInterface = this.introspectedTable.getTableConfigurationProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
		if (!stringHasValue(rootInterface)) {
			rootInterface = this.context.getJavaClientGeneratorConfiguration().getProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
		}

		if (stringHasValue(rootInterface)) {
			FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(rootInterface);
			interfaze.addSuperInterface(fqjt);
			interfaze.addImportedType(fqjt);
		}

		this.addCountByExampleMethod(interfaze);
		this.addDeleteByExampleMethod(interfaze);
		this.addDeleteByPrimaryKeyMethod(interfaze);
		this.addDeleteByPrimaryKeyLogicMethod(interfaze);
		this.addInsertMethod(interfaze);
		this.addInsertSelectiveMethod(interfaze);
		this.addSelectByExampleWithBLOBsMethod(interfaze);
		this.addSelectByExampleWithoutBLOBsMethod(interfaze);
		this.addSelectByPrimaryKeyMethod(interfaze);
		this.addUpdateByExampleSelectiveMethod(interfaze);
		this.addUpdateByExampleWithBLOBsMethod(interfaze);
		this.addUpdateByExampleWithoutBLOBsMethod(interfaze);
		this.addUpdateByPrimaryKeySelectiveMethod(interfaze);
		this.addUpdateByPrimaryKeyWithBLOBsMethod(interfaze);
		this.addUpdateByPrimaryKeyWithoutBLOBsMethod(interfaze);

		List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
		if (this.context.getPlugins().clientGenerated(interfaze, null, this.introspectedTable)) {
			answer.add(interfaze);
		}

		List<CompilationUnit> extraCompilationUnits = this.getExtraCompilationUnits();
		if (extraCompilationUnits != null) {
			answer.addAll(extraCompilationUnits);
		}

		return answer;
	}

	protected void addCountByExampleMethod(Interface interfaze) {
		if (this.introspectedTable.getRules().generateCountByExample()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new CountByExampleMethodGenerator();
			this.initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addDeleteByExampleMethod(Interface interfaze) {
		if (this.introspectedTable.getRules().generateDeleteByExample()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new DeleteByExampleMethodGenerator();
			this.initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addDeleteByPrimaryKeyMethod(Interface interfaze) {
		if (this.introspectedTable.getRules().generateDeleteByPrimaryKey()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new DeleteByPrimaryKeyMethodGenerator();
			this.initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	/**
	 * 添加逻辑删除
	 * @param interfaze
	 */
	protected void addDeleteByPrimaryKeyLogicMethod(Interface interfaze) {
		if (this.introspectedTable.getRules().generateDeleteByPrimaryKey()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new DeleteByPrimaryKeyLogicMethodGenerator();
			this.initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addInsertMethod(Interface interfaze) {
		//		if (this.introspectedTable.getRules().generateInsert()) {
		//			AbstractJavaMapperMethodGenerator methodGenerator = new InsertMethodGenerator();
		//			this.initializeAndExecuteGenerator(methodGenerator, interfaze);
		//		}
	}

	protected void addInsertSelectiveMethod(Interface interfaze) {
		if (this.introspectedTable.getRules().generateInsertSelective()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new InsertSelectiveMethodGenerator();
			this.initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addSelectByExampleWithBLOBsMethod(Interface interfaze) {
		if (this.introspectedTable.getRules().generateSelectByExampleWithBLOBs()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new SelectByExampleWithBLOBsMethodGenerator();
			this.initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addSelectByExampleWithoutBLOBsMethod(Interface interfaze) {
		if (this.introspectedTable.getRules().generateSelectByExampleWithoutBLOBs()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new SelectByExampleWithoutBLOBsMethodGenerator();
			this.initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addSelectByPrimaryKeyMethod(Interface interfaze) {
		if (this.introspectedTable.getRules().generateSelectByPrimaryKey()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new SelectByPrimaryKeyMethodGenerator();
			this.initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addUpdateByExampleSelectiveMethod(Interface interfaze) {
		if (this.introspectedTable.getRules().generateUpdateByExampleSelective()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByExampleSelectiveMethodGenerator();
			this.initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addUpdateByExampleWithBLOBsMethod(Interface interfaze) {
		if (this.introspectedTable.getRules().generateUpdateByExampleWithBLOBs()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByExampleWithBLOBsMethodGenerator();
			this.initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addUpdateByExampleWithoutBLOBsMethod(Interface interfaze) {
		if (this.introspectedTable.getRules().generateUpdateByExampleWithoutBLOBs()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByExampleWithoutBLOBsMethodGenerator();
			this.initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addUpdateByPrimaryKeySelectiveMethod(Interface interfaze) {
		if (this.introspectedTable.getRules().generateUpdateByPrimaryKeySelective()) {
			AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByPrimaryKeySelectiveMethodGenerator();
			this.initializeAndExecuteGenerator(methodGenerator, interfaze);
		}
	}

	protected void addUpdateByPrimaryKeyWithBLOBsMethod(Interface interfaze) {
		//		if (this.introspectedTable.getRules().generateUpdateByPrimaryKeyWithBLOBs()) {
		//			AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByPrimaryKeyWithBLOBsMethodGenerator();
		//			this.initializeAndExecuteGenerator(methodGenerator, interfaze);
		//		}
	}

	protected void addUpdateByPrimaryKeyWithoutBLOBsMethod(Interface interfaze) {
		//		if (this.introspectedTable.getRules().generateUpdateByPrimaryKeyWithoutBLOBs()) {
		//			AbstractJavaMapperMethodGenerator methodGenerator = new UpdateByPrimaryKeyWithoutBLOBsMethodGenerator();
		//			this.initializeAndExecuteGenerator(methodGenerator, interfaze);
		//		}
	}

	protected void initializeAndExecuteGenerator(AbstractJavaMapperMethodGenerator methodGenerator, Interface interfaze) {
		methodGenerator.setContext(this.context);
		methodGenerator.setIntrospectedTable(this.introspectedTable);
		methodGenerator.setProgressCallback(this.progressCallback);
		methodGenerator.setWarnings(this.warnings);
		methodGenerator.addInterfaceElements(interfaze);
	}

	public List<CompilationUnit> getExtraCompilationUnits() {
		return null;
	}

	@Override
	public AbstractXmlGenerator getMatchedXMLGenerator() {
		return new XMLMapperGenerator();
	}
}
