/* 
 * OSATE2-GTSE
 * 
 * Copyright 2017 Carnegie Mellon University. All Rights Reserved.
 * 
 * NO WARRANTY. THIS CARNEGIE MELLON UNIVERSITY AND SOFTWARE ENGINEERING INSTITUTE
 * MATERIAL IS FURNISHED ON AN "AS-IS" BASIS. CARNEGIE MELLON UNIVERSITY MAKES NO
 * WARRANTIES OF ANY KIND, EITHER EXPRESSED OR IMPLIED, AS TO ANY MATTER INCLUDING,
 * BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR PURPOSE OR MERCHANTABILITY,
 * EXCLUSIVITY, OR RESULTS OBTAINED FROM USE OF THE MATERIAL. CARNEGIE MELLON
 * UNIVERSITY DOES NOT MAKE ANY WARRANTY OF ANY KIND WITH RESPECT TO FREEDOM FROM
 * PATENT, TRADEMARK, OR COPYRIGHT INFRINGEMENT.
 * 
 * Released under an Eclipse Public License - v1.0-style license, please see
 * license.txt or contact permission@sei.cmu.edu for full terms. 
 * 
 * DM17-0002
 */
package org.osate.gtse.config.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.EnumRule;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.GrammarUtil;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.service.AbstractElementFinder.AbstractGrammarElementFinder;
import org.eclipse.xtext.service.GrammarProvider;
import org.osate.xtext.aadl2.properties.services.PropertiesGrammarAccess;
import org.osate.xtext.aadl2.services.Aadl2GrammarAccess;

@Singleton
public class ConfigGrammarAccess extends AbstractGrammarElementFinder {
	
	public class ConfigPkgElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.osate.gtse.config.Config.ConfigPkg");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cRootKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cRootAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final CrossReference cRootConfigurationCrossReference_1_0 = (CrossReference)cRootAssignment_1.eContents().get(0);
		private final RuleCall cRootConfigurationIDTerminalRuleCall_1_0_1 = (RuleCall)cRootConfigurationCrossReference_1_0.eContents().get(1);
		private final Assignment cConfigurationsAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cConfigurationsConfigurationParserRuleCall_2_0 = (RuleCall)cConfigurationsAssignment_2.eContents().get(0);
		
		//ConfigPkg: //	'package' name=PNAME
		//	'root' root=[Configuration] configurations+=Configuration* //	'end'
		//;
		@Override public ParserRule getRule() { return rule; }
		
		////	'package' name=PNAME
		//'root' root=[Configuration] configurations+=Configuration*
		public Group getGroup() { return cGroup; }
		
		////	'package' name=PNAME
		//'root'
		public Keyword getRootKeyword_0() { return cRootKeyword_0; }
		
		//root=[Configuration]
		public Assignment getRootAssignment_1() { return cRootAssignment_1; }
		
		//[Configuration]
		public CrossReference getRootConfigurationCrossReference_1_0() { return cRootConfigurationCrossReference_1_0; }
		
		//ID
		public RuleCall getRootConfigurationIDTerminalRuleCall_1_0_1() { return cRootConfigurationIDTerminalRuleCall_1_0_1; }
		
		//configurations+=Configuration*
		public Assignment getConfigurationsAssignment_2() { return cConfigurationsAssignment_2; }
		
		//Configuration
		public RuleCall getConfigurationsConfigurationParserRuleCall_2_0() { return cConfigurationsConfigurationParserRuleCall_2_0; }
	}
	public class ConfigurationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.osate.gtse.config.Config.Configuration");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cConfigurationKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameIDTerminalRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final RuleCall cParametersParserRuleCall_2 = (RuleCall)cGroup.eContents().get(2);
		private final Group cGroup_3 = (Group)cGroup.eContents().get(3);
		private final Keyword cExtendsKeyword_3_0 = (Keyword)cGroup_3.eContents().get(0);
		private final Assignment cExtendedAssignment_3_1 = (Assignment)cGroup_3.eContents().get(1);
		private final CrossReference cExtendedComponentClassifierCrossReference_3_1_0 = (CrossReference)cExtendedAssignment_3_1.eContents().get(0);
		private final RuleCall cExtendedComponentClassifierCNAMEParserRuleCall_3_1_0_1 = (RuleCall)cExtendedComponentClassifierCrossReference_3_1_0.eContents().get(1);
		private final Group cGroup_4 = (Group)cGroup.eContents().get(4);
		private final Keyword cWithKeyword_4_0 = (Keyword)cGroup_4.eContents().get(0);
		private final Assignment cCombinedAssignment_4_1 = (Assignment)cGroup_4.eContents().get(1);
		private final RuleCall cCombinedCombinationParserRuleCall_4_1_0 = (RuleCall)cCombinedAssignment_4_1.eContents().get(0);
		private final Group cGroup_4_2 = (Group)cGroup_4.eContents().get(2);
		private final Keyword cCommaKeyword_4_2_0 = (Keyword)cGroup_4_2.eContents().get(0);
		private final Assignment cCombinedAssignment_4_2_1 = (Assignment)cGroup_4_2.eContents().get(1);
		private final RuleCall cCombinedCombinationParserRuleCall_4_2_1_0 = (RuleCall)cCombinedAssignment_4_2_1.eContents().get(0);
		private final RuleCall cAssignmentsParserRuleCall_5 = (RuleCall)cGroup.eContents().get(5);
		
		//Configuration:
		//	'configuration' name=ID Parameters? ('extends' extended=[aadl2::ComponentClassifier|CNAME])? ('with'
		//	combined+=Combination (',' combined+=Combination)*)?
		//	Assignments?;
		@Override public ParserRule getRule() { return rule; }
		
		//'configuration' name=ID Parameters? ('extends' extended=[aadl2::ComponentClassifier|CNAME])? ('with'
		//combined+=Combination (',' combined+=Combination)*)? Assignments?
		public Group getGroup() { return cGroup; }
		
		//'configuration'
		public Keyword getConfigurationKeyword_0() { return cConfigurationKeyword_0; }
		
		//name=ID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_1_0() { return cNameIDTerminalRuleCall_1_0; }
		
		//Parameters?
		public RuleCall getParametersParserRuleCall_2() { return cParametersParserRuleCall_2; }
		
		//('extends' extended=[aadl2::ComponentClassifier|CNAME])?
		public Group getGroup_3() { return cGroup_3; }
		
		//'extends'
		public Keyword getExtendsKeyword_3_0() { return cExtendsKeyword_3_0; }
		
		//extended=[aadl2::ComponentClassifier|CNAME]
		public Assignment getExtendedAssignment_3_1() { return cExtendedAssignment_3_1; }
		
		//[aadl2::ComponentClassifier|CNAME]
		public CrossReference getExtendedComponentClassifierCrossReference_3_1_0() { return cExtendedComponentClassifierCrossReference_3_1_0; }
		
		//CNAME
		public RuleCall getExtendedComponentClassifierCNAMEParserRuleCall_3_1_0_1() { return cExtendedComponentClassifierCNAMEParserRuleCall_3_1_0_1; }
		
		//('with' combined+=Combination (',' combined+=Combination)*)?
		public Group getGroup_4() { return cGroup_4; }
		
		//'with'
		public Keyword getWithKeyword_4_0() { return cWithKeyword_4_0; }
		
		//combined+=Combination
		public Assignment getCombinedAssignment_4_1() { return cCombinedAssignment_4_1; }
		
		//Combination
		public RuleCall getCombinedCombinationParserRuleCall_4_1_0() { return cCombinedCombinationParserRuleCall_4_1_0; }
		
		//(',' combined+=Combination)*
		public Group getGroup_4_2() { return cGroup_4_2; }
		
		//','
		public Keyword getCommaKeyword_4_2_0() { return cCommaKeyword_4_2_0; }
		
		//combined+=Combination
		public Assignment getCombinedAssignment_4_2_1() { return cCombinedAssignment_4_2_1; }
		
		//Combination
		public RuleCall getCombinedCombinationParserRuleCall_4_2_1_0() { return cCombinedCombinationParserRuleCall_4_2_1_0; }
		
		//Assignments?
		public RuleCall getAssignmentsParserRuleCall_5() { return cAssignmentsParserRuleCall_5; }
	}
	public class CombinationElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.osate.gtse.config.Config.Combination");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cUnsafeAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final Keyword cUnsafeUnsafeKeyword_0_0 = (Keyword)cUnsafeAssignment_0.eContents().get(0);
		private final Assignment cConfigurationAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final CrossReference cConfigurationConfigurationCrossReference_1_0 = (CrossReference)cConfigurationAssignment_1.eContents().get(0);
		private final RuleCall cConfigurationConfigurationIDTerminalRuleCall_1_0_1 = (RuleCall)cConfigurationConfigurationCrossReference_1_0.eContents().get(1);
		private final RuleCall cArgumentsParserRuleCall_2 = (RuleCall)cGroup.eContents().get(2);
		
		//Combination:
		//	unsafe?='unsafe'?
		//	configuration=[Configuration] Arguments?;
		@Override public ParserRule getRule() { return rule; }
		
		//unsafe?='unsafe'? configuration=[Configuration] Arguments?
		public Group getGroup() { return cGroup; }
		
		//unsafe?='unsafe'?
		public Assignment getUnsafeAssignment_0() { return cUnsafeAssignment_0; }
		
		//'unsafe'
		public Keyword getUnsafeUnsafeKeyword_0_0() { return cUnsafeUnsafeKeyword_0_0; }
		
		//configuration=[Configuration]
		public Assignment getConfigurationAssignment_1() { return cConfigurationAssignment_1; }
		
		//[Configuration]
		public CrossReference getConfigurationConfigurationCrossReference_1_0() { return cConfigurationConfigurationCrossReference_1_0; }
		
		//ID
		public RuleCall getConfigurationConfigurationIDTerminalRuleCall_1_0_1() { return cConfigurationConfigurationIDTerminalRuleCall_1_0_1; }
		
		//Arguments?
		public RuleCall getArgumentsParserRuleCall_2() { return cArgumentsParserRuleCall_2; }
	}
	public class ParametersElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.osate.gtse.config.Config.Parameters");
		private final Group cGroup = (Group)rule.eContents().get(0);
		private final Keyword cLeftParenthesisKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Assignment cParametersAssignment_1_0 = (Assignment)cGroup_1.eContents().get(0);
		private final RuleCall cParametersConfigParameterParserRuleCall_1_0_0 = (RuleCall)cParametersAssignment_1_0.eContents().get(0);
		private final Group cGroup_1_1 = (Group)cGroup_1.eContents().get(1);
		private final Keyword cCommaKeyword_1_1_0 = (Keyword)cGroup_1_1.eContents().get(0);
		private final Assignment cParametersAssignment_1_1_1 = (Assignment)cGroup_1_1.eContents().get(1);
		private final RuleCall cParametersConfigParameterParserRuleCall_1_1_1_0 = (RuleCall)cParametersAssignment_1_1_1.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_2 = (Keyword)cGroup.eContents().get(2);
		
		//fragment Parameters *:
		//	'(' (parameters+=ConfigParameter (',' parameters+=ConfigParameter)*)? ')';
		@Override public ParserRule getRule() { return rule; }
		
		//'(' (parameters+=ConfigParameter (',' parameters+=ConfigParameter)*)? ')'
		public Group getGroup() { return cGroup; }
		
		//'('
		public Keyword getLeftParenthesisKeyword_0() { return cLeftParenthesisKeyword_0; }
		
		//(parameters+=ConfigParameter (',' parameters+=ConfigParameter)*)?
		public Group getGroup_1() { return cGroup_1; }
		
		//parameters+=ConfigParameter
		public Assignment getParametersAssignment_1_0() { return cParametersAssignment_1_0; }
		
		//ConfigParameter
		public RuleCall getParametersConfigParameterParserRuleCall_1_0_0() { return cParametersConfigParameterParserRuleCall_1_0_0; }
		
		//(',' parameters+=ConfigParameter)*
		public Group getGroup_1_1() { return cGroup_1_1; }
		
		//','
		public Keyword getCommaKeyword_1_1_0() { return cCommaKeyword_1_1_0; }
		
		//parameters+=ConfigParameter
		public Assignment getParametersAssignment_1_1_1() { return cParametersAssignment_1_1_1; }
		
		//ConfigParameter
		public RuleCall getParametersConfigParameterParserRuleCall_1_1_1_0() { return cParametersConfigParameterParserRuleCall_1_1_1_0; }
		
		//')'
		public Keyword getRightParenthesisKeyword_2() { return cRightParenthesisKeyword_2; }
	}
	public class ConfigParameterElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.osate.gtse.config.Config.ConfigParameter");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cNameAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cNameIDTerminalRuleCall_0_0 = (RuleCall)cNameAssignment_0.eContents().get(0);
		private final Keyword cColonKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Alternatives cAlternatives_2 = (Alternatives)cGroup.eContents().get(2);
		private final RuleCall cFClassifierTypeParserRuleCall_2_0 = (RuleCall)cAlternatives_2.eContents().get(0);
		private final RuleCall cFPropertyTypeParserRuleCall_2_1 = (RuleCall)cAlternatives_2.eContents().get(1);
		private final RuleCall cCandidatesParserRuleCall_3 = (RuleCall)cGroup.eContents().get(3);
		
		//ConfigParameter:
		//	name=ID ':' (FClassifierType | FPropertyType) Candidates?;
		@Override public ParserRule getRule() { return rule; }
		
		//name=ID ':' (FClassifierType | FPropertyType) Candidates?
		public Group getGroup() { return cGroup; }
		
		//name=ID
		public Assignment getNameAssignment_0() { return cNameAssignment_0; }
		
		//ID
		public RuleCall getNameIDTerminalRuleCall_0_0() { return cNameIDTerminalRuleCall_0_0; }
		
		//':'
		public Keyword getColonKeyword_1() { return cColonKeyword_1; }
		
		//FClassifierType | FPropertyType
		public Alternatives getAlternatives_2() { return cAlternatives_2; }
		
		//FClassifierType
		public RuleCall getFClassifierTypeParserRuleCall_2_0() { return cFClassifierTypeParserRuleCall_2_0; }
		
		//FPropertyType
		public RuleCall getFPropertyTypeParserRuleCall_2_1() { return cFPropertyTypeParserRuleCall_2_1; }
		
		//Candidates?
		public RuleCall getCandidatesParserRuleCall_3() { return cCandidatesParserRuleCall_3; }
	}
	public class FClassifierTypeElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.osate.gtse.config.Config.FClassifierType");
		private final Group cGroup = (Group)rule.eContents().get(0);
		private final Assignment cCategoryAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cCategoryComponentCategoryParserRuleCall_0_0 = (RuleCall)cCategoryAssignment_0.eContents().get(0);
		private final Assignment cClassifierAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final CrossReference cClassifierComponentClassifierCrossReference_1_0 = (CrossReference)cClassifierAssignment_1.eContents().get(0);
		private final RuleCall cClassifierComponentClassifierCNAMEParserRuleCall_1_0_1 = (RuleCall)cClassifierComponentClassifierCrossReference_1_0.eContents().get(1);
		
		//fragment FClassifierType *:
		//	category=ComponentCategory classifier=[aadl2::ComponentClassifier|CNAME];
		@Override public ParserRule getRule() { return rule; }
		
		//category=ComponentCategory classifier=[aadl2::ComponentClassifier|CNAME]
		public Group getGroup() { return cGroup; }
		
		//category=ComponentCategory
		public Assignment getCategoryAssignment_0() { return cCategoryAssignment_0; }
		
		//ComponentCategory
		public RuleCall getCategoryComponentCategoryParserRuleCall_0_0() { return cCategoryComponentCategoryParserRuleCall_0_0; }
		
		//classifier=[aadl2::ComponentClassifier|CNAME]
		public Assignment getClassifierAssignment_1() { return cClassifierAssignment_1; }
		
		//[aadl2::ComponentClassifier|CNAME]
		public CrossReference getClassifierComponentClassifierCrossReference_1_0() { return cClassifierComponentClassifierCrossReference_1_0; }
		
		//CNAME
		public RuleCall getClassifierComponentClassifierCNAMEParserRuleCall_1_0_1() { return cClassifierComponentClassifierCNAMEParserRuleCall_1_0_1; }
	}
	public class FPropertyTypeElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.osate.gtse.config.Config.FPropertyType");
		private final Assignment cPropertyTypeAssignment = (Assignment)rule.eContents().get(0);
		private final CrossReference cPropertyTypePropertyCrossReference_0 = (CrossReference)cPropertyTypeAssignment.eContents().get(0);
		private final RuleCall cPropertyTypePropertyPNAMEParserRuleCall_0_1 = (RuleCall)cPropertyTypePropertyCrossReference_0.eContents().get(1);
		
		//fragment FPropertyType *:
		//	propertyType=[aadl2::Property|PNAME];
		@Override public ParserRule getRule() { return rule; }
		
		//propertyType=[aadl2::Property|PNAME]
		public Assignment getPropertyTypeAssignment() { return cPropertyTypeAssignment; }
		
		//[aadl2::Property|PNAME]
		public CrossReference getPropertyTypePropertyCrossReference_0() { return cPropertyTypePropertyCrossReference_0; }
		
		//PNAME
		public RuleCall getPropertyTypePropertyPNAMEParserRuleCall_0_1() { return cPropertyTypePropertyPNAMEParserRuleCall_0_1; }
	}
	public class CandidatesElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.osate.gtse.config.Config.Candidates");
		private final Group cGroup = (Group)rule.eContents().get(0);
		private final Keyword cFromKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Keyword cLeftParenthesisKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Assignment cCandidatesAssignment_2_0 = (Assignment)cGroup_2.eContents().get(0);
		private final RuleCall cCandidatesConfigExpressionParserRuleCall_2_0_0 = (RuleCall)cCandidatesAssignment_2_0.eContents().get(0);
		private final Group cGroup_2_1 = (Group)cGroup_2.eContents().get(1);
		private final Keyword cCommaKeyword_2_1_0 = (Keyword)cGroup_2_1.eContents().get(0);
		private final Assignment cCandidatesAssignment_2_1_1 = (Assignment)cGroup_2_1.eContents().get(1);
		private final RuleCall cCandidatesConfigExpressionParserRuleCall_2_1_1_0 = (RuleCall)cCandidatesAssignment_2_1_1.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_3 = (Keyword)cGroup.eContents().get(3);
		
		//fragment Candidates *:
		//	'from' '(' (candidates+=ConfigExpression (',' candidates+=ConfigExpression)*)? ')';
		@Override public ParserRule getRule() { return rule; }
		
		//'from' '(' (candidates+=ConfigExpression (',' candidates+=ConfigExpression)*)? ')'
		public Group getGroup() { return cGroup; }
		
		//'from'
		public Keyword getFromKeyword_0() { return cFromKeyword_0; }
		
		//'('
		public Keyword getLeftParenthesisKeyword_1() { return cLeftParenthesisKeyword_1; }
		
		//(candidates+=ConfigExpression (',' candidates+=ConfigExpression)*)?
		public Group getGroup_2() { return cGroup_2; }
		
		//candidates+=ConfigExpression
		public Assignment getCandidatesAssignment_2_0() { return cCandidatesAssignment_2_0; }
		
		//ConfigExpression
		public RuleCall getCandidatesConfigExpressionParserRuleCall_2_0_0() { return cCandidatesConfigExpressionParserRuleCall_2_0_0; }
		
		//(',' candidates+=ConfigExpression)*
		public Group getGroup_2_1() { return cGroup_2_1; }
		
		//','
		public Keyword getCommaKeyword_2_1_0() { return cCommaKeyword_2_1_0; }
		
		//candidates+=ConfigExpression
		public Assignment getCandidatesAssignment_2_1_1() { return cCandidatesAssignment_2_1_1; }
		
		//ConfigExpression
		public RuleCall getCandidatesConfigExpressionParserRuleCall_2_1_1_0() { return cCandidatesConfigExpressionParserRuleCall_2_1_1_0; }
		
		//')'
		public Keyword getRightParenthesisKeyword_3() { return cRightParenthesisKeyword_3; }
	}
	public class AssignmentsElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.osate.gtse.config.Config.Assignments");
		private final Group cGroup = (Group)rule.eContents().get(0);
		private final Keyword cLeftCurlyBracketKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Assignment cAssignmentsAssignment_1_0 = (Assignment)cGroup_1.eContents().get(0);
		private final RuleCall cAssignmentsAssignmentParserRuleCall_1_0_0 = (RuleCall)cAssignmentsAssignment_1_0.eContents().get(0);
		private final Group cGroup_1_1 = (Group)cGroup_1.eContents().get(1);
		private final Keyword cCommaKeyword_1_1_0 = (Keyword)cGroup_1_1.eContents().get(0);
		private final Assignment cAssignmentsAssignment_1_1_1 = (Assignment)cGroup_1_1.eContents().get(1);
		private final RuleCall cAssignmentsAssignmentParserRuleCall_1_1_1_0 = (RuleCall)cAssignmentsAssignment_1_1_1.eContents().get(0);
		private final Keyword cRightCurlyBracketKeyword_2 = (Keyword)cGroup.eContents().get(2);
		
		//fragment Assignments *:
		//	'{' (assignments+=Assignment (',' assignments+=Assignment)*)? '}';
		@Override public ParserRule getRule() { return rule; }
		
		//'{' (assignments+=Assignment (',' assignments+=Assignment)*)? '}'
		public Group getGroup() { return cGroup; }
		
		//'{'
		public Keyword getLeftCurlyBracketKeyword_0() { return cLeftCurlyBracketKeyword_0; }
		
		//(assignments+=Assignment (',' assignments+=Assignment)*)?
		public Group getGroup_1() { return cGroup_1; }
		
		//assignments+=Assignment
		public Assignment getAssignmentsAssignment_1_0() { return cAssignmentsAssignment_1_0; }
		
		//Assignment
		public RuleCall getAssignmentsAssignmentParserRuleCall_1_0_0() { return cAssignmentsAssignmentParserRuleCall_1_0_0; }
		
		//(',' assignments+=Assignment)*
		public Group getGroup_1_1() { return cGroup_1_1; }
		
		//','
		public Keyword getCommaKeyword_1_1_0() { return cCommaKeyword_1_1_0; }
		
		//assignments+=Assignment
		public Assignment getAssignmentsAssignment_1_1_1() { return cAssignmentsAssignment_1_1_1; }
		
		//Assignment
		public RuleCall getAssignmentsAssignmentParserRuleCall_1_1_1_0() { return cAssignmentsAssignmentParserRuleCall_1_1_1_0; }
		
		//'}'
		public Keyword getRightCurlyBracketKeyword_2() { return cRightCurlyBracketKeyword_2; }
	}
	public class AssignmentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.osate.gtse.config.Config.Assignment");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Alternatives cAlternatives_0 = (Alternatives)cGroup.eContents().get(0);
		private final Group cGroup_0_0 = (Group)cAlternatives_0.eContents().get(0);
		private final Assignment cRefAssignment_0_0_0 = (Assignment)cGroup_0_0.eContents().get(0);
		private final RuleCall cRefElementRefParserRuleCall_0_0_0_0 = (RuleCall)cRefAssignment_0_0_0.eContents().get(0);
		private final Group cGroup_0_0_1 = (Group)cGroup_0_0.eContents().get(1);
		private final Keyword cNumberSignKeyword_0_0_1_0 = (Keyword)cGroup_0_0_1.eContents().get(0);
		private final Assignment cPropertyAssignment_0_0_1_1 = (Assignment)cGroup_0_0_1.eContents().get(1);
		private final CrossReference cPropertyPropertyCrossReference_0_0_1_1_0 = (CrossReference)cPropertyAssignment_0_0_1_1.eContents().get(0);
		private final RuleCall cPropertyPropertyPNAMEParserRuleCall_0_0_1_1_0_1 = (RuleCall)cPropertyPropertyCrossReference_0_0_1_1_0.eContents().get(1);
		private final Group cGroup_0_1 = (Group)cAlternatives_0.eContents().get(1);
		private final Keyword cNumberSignKeyword_0_1_0 = (Keyword)cGroup_0_1.eContents().get(0);
		private final Assignment cPropertyAssignment_0_1_1 = (Assignment)cGroup_0_1.eContents().get(1);
		private final CrossReference cPropertyPropertyCrossReference_0_1_1_0 = (CrossReference)cPropertyAssignment_0_1_1.eContents().get(0);
		private final RuleCall cPropertyPropertyPNAMEParserRuleCall_0_1_1_0_1 = (RuleCall)cPropertyPropertyCrossReference_0_1_1_0.eContents().get(1);
		private final Keyword cEqualsSignGreaterThanSignKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cValueAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cValueConfigExpressionParserRuleCall_2_0 = (RuleCall)cValueAssignment_2.eContents().get(0);
		
		//Assignment:
		//	(ref=ElementRef ('#' property=[aadl2::Property|PNAME])?
		//	| '#' property=[aadl2::Property|PNAME])
		//	'=>'
		//	value=ConfigExpression;
		@Override public ParserRule getRule() { return rule; }
		
		//(ref=ElementRef ('#' property=[aadl2::Property|PNAME])? | '#' property=[aadl2::Property|PNAME]) '=>'
		//value=ConfigExpression
		public Group getGroup() { return cGroup; }
		
		//// LVal
		//// using fragment LVal leads to an exception in LazyLinker for x.y#p => v
		//ref=ElementRef ('#' property=[aadl2::Property|PNAME])? | '#' property=[aadl2::Property|PNAME]
		public Alternatives getAlternatives_0() { return cAlternatives_0; }
		
		//ref=ElementRef ('#' property=[aadl2::Property|PNAME])?
		public Group getGroup_0_0() { return cGroup_0_0; }
		
		//ref=ElementRef
		public Assignment getRefAssignment_0_0_0() { return cRefAssignment_0_0_0; }
		
		//ElementRef
		public RuleCall getRefElementRefParserRuleCall_0_0_0_0() { return cRefElementRefParserRuleCall_0_0_0_0; }
		
		//('#' property=[aadl2::Property|PNAME])?
		public Group getGroup_0_0_1() { return cGroup_0_0_1; }
		
		//'#'
		public Keyword getNumberSignKeyword_0_0_1_0() { return cNumberSignKeyword_0_0_1_0; }
		
		//property=[aadl2::Property|PNAME]
		public Assignment getPropertyAssignment_0_0_1_1() { return cPropertyAssignment_0_0_1_1; }
		
		//[aadl2::Property|PNAME]
		public CrossReference getPropertyPropertyCrossReference_0_0_1_1_0() { return cPropertyPropertyCrossReference_0_0_1_1_0; }
		
		//PNAME
		public RuleCall getPropertyPropertyPNAMEParserRuleCall_0_0_1_1_0_1() { return cPropertyPropertyPNAMEParserRuleCall_0_0_1_1_0_1; }
		
		//'#' property=[aadl2::Property|PNAME]
		public Group getGroup_0_1() { return cGroup_0_1; }
		
		//'#'
		public Keyword getNumberSignKeyword_0_1_0() { return cNumberSignKeyword_0_1_0; }
		
		//property=[aadl2::Property|PNAME]
		public Assignment getPropertyAssignment_0_1_1() { return cPropertyAssignment_0_1_1; }
		
		//[aadl2::Property|PNAME]
		public CrossReference getPropertyPropertyCrossReference_0_1_1_0() { return cPropertyPropertyCrossReference_0_1_1_0; }
		
		//PNAME
		public RuleCall getPropertyPropertyPNAMEParserRuleCall_0_1_1_0_1() { return cPropertyPropertyPNAMEParserRuleCall_0_1_1_0_1; }
		
		//'=>'
		public Keyword getEqualsSignGreaterThanSignKeyword_1() { return cEqualsSignGreaterThanSignKeyword_1; }
		
		//value=ConfigExpression
		public Assignment getValueAssignment_2() { return cValueAssignment_2; }
		
		//ConfigExpression
		public RuleCall getValueConfigExpressionParserRuleCall_2_0() { return cValueConfigExpressionParserRuleCall_2_0; }
	}
	public class ConfigExpressionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.osate.gtse.config.Config.ConfigExpression");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final Group cGroup_0 = (Group)cAlternatives.eContents().get(0);
		private final Action cNamedElementRefAction_0_0 = (Action)cGroup_0.eContents().get(0);
		private final Assignment cRefAssignment_0_1 = (Assignment)cGroup_0.eContents().get(1);
		private final CrossReference cRefNamedElementCrossReference_0_1_0 = (CrossReference)cRefAssignment_0_1.eContents().get(0);
		private final RuleCall cRefNamedElementCNAMEParserRuleCall_0_1_0_1 = (RuleCall)cRefNamedElementCrossReference_0_1_0.eContents().get(1);
		private final RuleCall cArgumentsParserRuleCall_0_2 = (RuleCall)cGroup_0.eContents().get(2);
		private final RuleCall cAssignmentsParserRuleCall_0_3 = (RuleCall)cGroup_0.eContents().get(3);
		private final Group cGroup_1 = (Group)cAlternatives.eContents().get(1);
		private final Action cPropertyValueAction_1_0 = (Action)cGroup_1.eContents().get(0);
		private final Assignment cExpAssignment_1_1 = (Assignment)cGroup_1.eContents().get(1);
		private final RuleCall cExpCPropertyExpressionParserRuleCall_1_1_0 = (RuleCall)cExpAssignment_1_1.eContents().get(0);
		private final RuleCall cAssignmentsParserRuleCall_2 = (RuleCall)cAlternatives.eContents().get(2);
		
		////fragment LVal*:
		////	element=ElementRef ('#' property=[aadl2::Property|PNAME])? 
		////	| '#' property=[aadl2::Property|PNAME]
		////;
		//ConfigExpression ConfigValue:
		//	{NamedElementRef} ref=[aadl2::NamedElement|CNAME] Arguments? Assignments?
		//	| {PropertyValue} exp=CPropertyExpression
		//	| Assignments;
		@Override public ParserRule getRule() { return rule; }
		
		//{NamedElementRef} ref=[aadl2::NamedElement|CNAME] Arguments? Assignments? | {PropertyValue} exp=CPropertyExpression |
		//Assignments
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//{NamedElementRef} ref=[aadl2::NamedElement|CNAME] Arguments? Assignments?
		public Group getGroup_0() { return cGroup_0; }
		
		//{NamedElementRef}
		public Action getNamedElementRefAction_0_0() { return cNamedElementRefAction_0_0; }
		
		//ref=[aadl2::NamedElement|CNAME]
		public Assignment getRefAssignment_0_1() { return cRefAssignment_0_1; }
		
		//[aadl2::NamedElement|CNAME]
		public CrossReference getRefNamedElementCrossReference_0_1_0() { return cRefNamedElementCrossReference_0_1_0; }
		
		//CNAME
		public RuleCall getRefNamedElementCNAMEParserRuleCall_0_1_0_1() { return cRefNamedElementCNAMEParserRuleCall_0_1_0_1; }
		
		//Arguments?
		public RuleCall getArgumentsParserRuleCall_0_2() { return cArgumentsParserRuleCall_0_2; }
		
		//Assignments?
		public RuleCall getAssignmentsParserRuleCall_0_3() { return cAssignmentsParserRuleCall_0_3; }
		
		//{PropertyValue} exp=CPropertyExpression
		public Group getGroup_1() { return cGroup_1; }
		
		//{PropertyValue}
		public Action getPropertyValueAction_1_0() { return cPropertyValueAction_1_0; }
		
		//exp=CPropertyExpression
		public Assignment getExpAssignment_1_1() { return cExpAssignment_1_1; }
		
		//CPropertyExpression
		public RuleCall getExpCPropertyExpressionParserRuleCall_1_1_0() { return cExpCPropertyExpressionParserRuleCall_1_1_0; }
		
		//Assignments
		public RuleCall getAssignmentsParserRuleCall_2() { return cAssignmentsParserRuleCall_2; }
	}
	public class ArgumentsElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.osate.gtse.config.Config.Arguments");
		private final Group cGroup = (Group)rule.eContents().get(0);
		private final Keyword cLeftParenthesisKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Assignment cArgumentsAssignment_1_0 = (Assignment)cGroup_1.eContents().get(0);
		private final RuleCall cArgumentsArgumentParserRuleCall_1_0_0 = (RuleCall)cArgumentsAssignment_1_0.eContents().get(0);
		private final Group cGroup_1_1 = (Group)cGroup_1.eContents().get(1);
		private final Keyword cCommaKeyword_1_1_0 = (Keyword)cGroup_1_1.eContents().get(0);
		private final Assignment cArgumentsAssignment_1_1_1 = (Assignment)cGroup_1_1.eContents().get(1);
		private final RuleCall cArgumentsArgumentParserRuleCall_1_1_1_0 = (RuleCall)cArgumentsAssignment_1_1_1.eContents().get(0);
		private final Keyword cRightParenthesisKeyword_2 = (Keyword)cGroup.eContents().get(2);
		
		//fragment Arguments *:
		//	'(' (arguments+=Argument (',' arguments+=Argument)*)? ')';
		@Override public ParserRule getRule() { return rule; }
		
		//'(' (arguments+=Argument (',' arguments+=Argument)*)? ')'
		public Group getGroup() { return cGroup; }
		
		//'('
		public Keyword getLeftParenthesisKeyword_0() { return cLeftParenthesisKeyword_0; }
		
		//(arguments+=Argument (',' arguments+=Argument)*)?
		public Group getGroup_1() { return cGroup_1; }
		
		//arguments+=Argument
		public Assignment getArgumentsAssignment_1_0() { return cArgumentsAssignment_1_0; }
		
		//Argument
		public RuleCall getArgumentsArgumentParserRuleCall_1_0_0() { return cArgumentsArgumentParserRuleCall_1_0_0; }
		
		//(',' arguments+=Argument)*
		public Group getGroup_1_1() { return cGroup_1_1; }
		
		//','
		public Keyword getCommaKeyword_1_1_0() { return cCommaKeyword_1_1_0; }
		
		//arguments+=Argument
		public Assignment getArgumentsAssignment_1_1_1() { return cArgumentsAssignment_1_1_1; }
		
		//Argument
		public RuleCall getArgumentsArgumentParserRuleCall_1_1_1_0() { return cArgumentsArgumentParserRuleCall_1_1_1_0; }
		
		//')'
		public Keyword getRightParenthesisKeyword_2() { return cRightParenthesisKeyword_2; }
	}
	public class ArgumentElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.osate.gtse.config.Config.Argument");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cParameterAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final CrossReference cParameterConfigParameterCrossReference_0_0 = (CrossReference)cParameterAssignment_0.eContents().get(0);
		private final RuleCall cParameterConfigParameterIDTerminalRuleCall_0_0_1 = (RuleCall)cParameterConfigParameterCrossReference_0_0.eContents().get(1);
		private final Keyword cEqualsSignGreaterThanSignKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Assignment cValueAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cValueConfigExpressionParserRuleCall_2_0 = (RuleCall)cValueAssignment_2.eContents().get(0);
		
		//Argument:
		//	parameter=[ConfigParameter] '=>' value=ConfigExpression;
		@Override public ParserRule getRule() { return rule; }
		
		//parameter=[ConfigParameter] '=>' value=ConfigExpression
		public Group getGroup() { return cGroup; }
		
		//parameter=[ConfigParameter]
		public Assignment getParameterAssignment_0() { return cParameterAssignment_0; }
		
		//[ConfigParameter]
		public CrossReference getParameterConfigParameterCrossReference_0_0() { return cParameterConfigParameterCrossReference_0_0; }
		
		//ID
		public RuleCall getParameterConfigParameterIDTerminalRuleCall_0_0_1() { return cParameterConfigParameterIDTerminalRuleCall_0_0_1; }
		
		//'=>'
		public Keyword getEqualsSignGreaterThanSignKeyword_1() { return cEqualsSignGreaterThanSignKeyword_1; }
		
		//value=ConfigExpression
		public Assignment getValueAssignment_2() { return cValueAssignment_2; }
		
		//ConfigExpression
		public RuleCall getValueConfigExpressionParserRuleCall_2_0() { return cValueConfigExpressionParserRuleCall_2_0; }
	}
	public class ElementRefElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.osate.gtse.config.Config.ElementRef");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cElementAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final CrossReference cElementNamedElementCrossReference_0_0 = (CrossReference)cElementAssignment_0.eContents().get(0);
		private final RuleCall cElementNamedElementIDTerminalRuleCall_0_0_1 = (RuleCall)cElementNamedElementCrossReference_0_0.eContents().get(1);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Group cGroup_1_0 = (Group)cGroup_1.eContents().get(0);
		private final Action cElementRefPrevAction_1_0_0 = (Action)cGroup_1_0.eContents().get(0);
		private final Keyword cFullStopKeyword_1_0_1 = (Keyword)cGroup_1_0.eContents().get(1);
		private final Assignment cElementAssignment_1_0_2 = (Assignment)cGroup_1_0.eContents().get(2);
		private final CrossReference cElementNamedElementCrossReference_1_0_2_0 = (CrossReference)cElementAssignment_1_0_2.eContents().get(0);
		private final RuleCall cElementNamedElementIDTerminalRuleCall_1_0_2_0_1 = (RuleCall)cElementNamedElementCrossReference_1_0_2_0.eContents().get(1);
		
		//ElementRef:
		//	element=[aadl2::NamedElement] => ({ElementRef.prev=current} '.' element=[aadl2::NamedElement])*;
		@Override public ParserRule getRule() { return rule; }
		
		//element=[aadl2::NamedElement] => ({ElementRef.prev=current} '.' element=[aadl2::NamedElement])*
		public Group getGroup() { return cGroup; }
		
		//element=[aadl2::NamedElement]
		public Assignment getElementAssignment_0() { return cElementAssignment_0; }
		
		//[aadl2::NamedElement]
		public CrossReference getElementNamedElementCrossReference_0_0() { return cElementNamedElementCrossReference_0_0; }
		
		//ID
		public RuleCall getElementNamedElementIDTerminalRuleCall_0_0_1() { return cElementNamedElementIDTerminalRuleCall_0_0_1; }
		
		//=> ({ElementRef.prev=current} '.' element=[aadl2::NamedElement])*
		public Group getGroup_1() { return cGroup_1; }
		
		//{ElementRef.prev=current} '.' element=[aadl2::NamedElement]
		public Group getGroup_1_0() { return cGroup_1_0; }
		
		//{ElementRef.prev=current}
		public Action getElementRefPrevAction_1_0_0() { return cElementRefPrevAction_1_0_0; }
		
		//'.'
		public Keyword getFullStopKeyword_1_0_1() { return cFullStopKeyword_1_0_1; }
		
		//element=[aadl2::NamedElement]
		public Assignment getElementAssignment_1_0_2() { return cElementAssignment_1_0_2; }
		
		//[aadl2::NamedElement]
		public CrossReference getElementNamedElementCrossReference_1_0_2_0() { return cElementNamedElementCrossReference_1_0_2_0; }
		
		//ID
		public RuleCall getElementNamedElementIDTerminalRuleCall_1_0_2_0_1() { return cElementNamedElementIDTerminalRuleCall_1_0_2_0_1; }
	}
	public class CPropertyExpressionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.osate.gtse.config.Config.CPropertyExpression");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cBooleanLiteralParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cIntegerTermParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		private final RuleCall cRealTermParserRuleCall_2 = (RuleCall)cAlternatives.eContents().get(2);
		private final RuleCall cStringTermParserRuleCall_3 = (RuleCall)cAlternatives.eContents().get(3);
		private final RuleCall cNumericRangeTermParserRuleCall_4 = (RuleCall)cAlternatives.eContents().get(4);
		private final RuleCall cRecordTermParserRuleCall_5 = (RuleCall)cAlternatives.eContents().get(5);
		private final RuleCall cReferenceTermParserRuleCall_6 = (RuleCall)cAlternatives.eContents().get(6);
		private final RuleCall cComponentClassifierTermParserRuleCall_7 = (RuleCall)cAlternatives.eContents().get(7);
		private final RuleCall cListTermParserRuleCall_8 = (RuleCall)cAlternatives.eContents().get(8);
		
		//CPropertyExpression aadl2::PropertyExpression:
		//	BooleanLiteral
		//	| IntegerTerm
		//	| RealTerm
		//	| StringTerm
		//	| NumericRangeTerm
		//	| RecordTerm
		//	| ReferenceTerm
		//	| ComponentClassifierTerm
		//	| ListTerm
		//	//	| ComputedTerm
		//;
		@Override public ParserRule getRule() { return rule; }
		
		//BooleanLiteral | IntegerTerm | RealTerm | StringTerm | NumericRangeTerm | RecordTerm | ReferenceTerm |
		//ComponentClassifierTerm | ListTerm
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//BooleanLiteral
		public RuleCall getBooleanLiteralParserRuleCall_0() { return cBooleanLiteralParserRuleCall_0; }
		
		//IntegerTerm
		public RuleCall getIntegerTermParserRuleCall_1() { return cIntegerTermParserRuleCall_1; }
		
		//RealTerm
		public RuleCall getRealTermParserRuleCall_2() { return cRealTermParserRuleCall_2; }
		
		//StringTerm
		public RuleCall getStringTermParserRuleCall_3() { return cStringTermParserRuleCall_3; }
		
		//NumericRangeTerm
		public RuleCall getNumericRangeTermParserRuleCall_4() { return cNumericRangeTermParserRuleCall_4; }
		
		//RecordTerm
		public RuleCall getRecordTermParserRuleCall_5() { return cRecordTermParserRuleCall_5; }
		
		//ReferenceTerm
		public RuleCall getReferenceTermParserRuleCall_6() { return cReferenceTermParserRuleCall_6; }
		
		//ComponentClassifierTerm
		public RuleCall getComponentClassifierTermParserRuleCall_7() { return cComponentClassifierTermParserRuleCall_7; }
		
		//ListTerm
		public RuleCall getListTermParserRuleCall_8() { return cListTermParserRuleCall_8; }
	}
	public class NamedElementElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.osate.gtse.config.Config.NamedElement");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final RuleCall cConfigPkgParserRuleCall_0 = (RuleCall)cAlternatives.eContents().get(0);
		private final RuleCall cConfigurationParserRuleCall_1 = (RuleCall)cAlternatives.eContents().get(1);
		private final RuleCall cConfigParameterParserRuleCall_2 = (RuleCall)cAlternatives.eContents().get(2);
		
		//NamedElement aadl2::NamedElement:
		//	ConfigPkg | Configuration | ConfigParameter;
		@Override public ParserRule getRule() { return rule; }
		
		//ConfigPkg | Configuration | ConfigParameter
		public Alternatives getAlternatives() { return cAlternatives; }
		
		//ConfigPkg
		public RuleCall getConfigPkgParserRuleCall_0() { return cConfigPkgParserRuleCall_0; }
		
		//Configuration
		public RuleCall getConfigurationParserRuleCall_1() { return cConfigurationParserRuleCall_1; }
		
		//ConfigParameter
		public RuleCall getConfigParameterParserRuleCall_2() { return cConfigParameterParserRuleCall_2; }
	}
	public class CNAMEElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.osate.gtse.config.Config.CNAME");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cIDTerminalRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Keyword cColonColonKeyword_1_0 = (Keyword)cGroup_1.eContents().get(0);
		private final RuleCall cIDTerminalRuleCall_1_1 = (RuleCall)cGroup_1.eContents().get(1);
		private final Group cGroup_2 = (Group)cGroup.eContents().get(2);
		private final Keyword cFullStopKeyword_2_0 = (Keyword)cGroup_2.eContents().get(0);
		private final RuleCall cIDTerminalRuleCall_2_1 = (RuleCall)cGroup_2.eContents().get(1);
		
		//// classifier name
		//CNAME:
		//	ID ('::' ID)* ('.' ID)?;
		@Override public ParserRule getRule() { return rule; }
		
		//ID ('::' ID)* ('.' ID)?
		public Group getGroup() { return cGroup; }
		
		//ID
		public RuleCall getIDTerminalRuleCall_0() { return cIDTerminalRuleCall_0; }
		
		//('::' ID)*
		public Group getGroup_1() { return cGroup_1; }
		
		//'::'
		public Keyword getColonColonKeyword_1_0() { return cColonColonKeyword_1_0; }
		
		//ID
		public RuleCall getIDTerminalRuleCall_1_1() { return cIDTerminalRuleCall_1_1; }
		
		//('.' ID)?
		public Group getGroup_2() { return cGroup_2; }
		
		//'.'
		public Keyword getFullStopKeyword_2_0() { return cFullStopKeyword_2_0; }
		
		//ID
		public RuleCall getIDTerminalRuleCall_2_1() { return cIDTerminalRuleCall_2_1; }
	}
	public class QNAMEElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "org.osate.gtse.config.Config.QNAME");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cIDTerminalRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Group cGroup_1 = (Group)cGroup.eContents().get(1);
		private final Keyword cFullStopKeyword_1_0 = (Keyword)cGroup_1.eContents().get(0);
		private final RuleCall cIDTerminalRuleCall_1_1 = (RuleCall)cGroup_1.eContents().get(1);
		
		//// property name
		//// see Aadl2 PNAME: ID ('::' ID)?;
		//// qualified name
		//QNAME:
		//	ID ('.' ID)*;
		@Override public ParserRule getRule() { return rule; }
		
		//ID ('.' ID)*
		public Group getGroup() { return cGroup; }
		
		//ID
		public RuleCall getIDTerminalRuleCall_0() { return cIDTerminalRuleCall_0; }
		
		//('.' ID)*
		public Group getGroup_1() { return cGroup_1; }
		
		//'.'
		public Keyword getFullStopKeyword_1_0() { return cFullStopKeyword_1_0; }
		
		//ID
		public RuleCall getIDTerminalRuleCall_1_1() { return cIDTerminalRuleCall_1_1; }
	}
	
	
	private final ConfigPkgElements pConfigPkg;
	private final ConfigurationElements pConfiguration;
	private final CombinationElements pCombination;
	private final ParametersElements pParameters;
	private final ConfigParameterElements pConfigParameter;
	private final FClassifierTypeElements pFClassifierType;
	private final FPropertyTypeElements pFPropertyType;
	private final CandidatesElements pCandidates;
	private final AssignmentsElements pAssignments;
	private final AssignmentElements pAssignment;
	private final ConfigExpressionElements pConfigExpression;
	private final ArgumentsElements pArguments;
	private final ArgumentElements pArgument;
	private final ElementRefElements pElementRef;
	private final CPropertyExpressionElements pCPropertyExpression;
	private final NamedElementElements pNamedElement;
	private final CNAMEElements pCNAME;
	private final QNAMEElements pQNAME;
	
	private final Grammar grammar;
	
	private final Aadl2GrammarAccess gaAadl2;
	
	private final PropertiesGrammarAccess gaProperties;

	@Inject
	public ConfigGrammarAccess(GrammarProvider grammarProvider,
			Aadl2GrammarAccess gaAadl2,
			PropertiesGrammarAccess gaProperties) {
		this.grammar = internalFindGrammar(grammarProvider);
		this.gaAadl2 = gaAadl2;
		this.gaProperties = gaProperties;
		this.pConfigPkg = new ConfigPkgElements();
		this.pConfiguration = new ConfigurationElements();
		this.pCombination = new CombinationElements();
		this.pParameters = new ParametersElements();
		this.pConfigParameter = new ConfigParameterElements();
		this.pFClassifierType = new FClassifierTypeElements();
		this.pFPropertyType = new FPropertyTypeElements();
		this.pCandidates = new CandidatesElements();
		this.pAssignments = new AssignmentsElements();
		this.pAssignment = new AssignmentElements();
		this.pConfigExpression = new ConfigExpressionElements();
		this.pArguments = new ArgumentsElements();
		this.pArgument = new ArgumentElements();
		this.pElementRef = new ElementRefElements();
		this.pCPropertyExpression = new CPropertyExpressionElements();
		this.pNamedElement = new NamedElementElements();
		this.pCNAME = new CNAMEElements();
		this.pQNAME = new QNAMEElements();
	}
	
	protected Grammar internalFindGrammar(GrammarProvider grammarProvider) {
		Grammar grammar = grammarProvider.getGrammar(this);
		while (grammar != null) {
			if ("org.osate.gtse.config.Config".equals(grammar.getName())) {
				return grammar;
			}
			List<Grammar> grammars = grammar.getUsedGrammars();
			if (!grammars.isEmpty()) {
				grammar = grammars.iterator().next();
			} else {
				return null;
			}
		}
		return grammar;
	}
	
	@Override
	public Grammar getGrammar() {
		return grammar;
	}
	
	
	public Aadl2GrammarAccess getAadl2GrammarAccess() {
		return gaAadl2;
	}
	
	public PropertiesGrammarAccess getPropertiesGrammarAccess() {
		return gaProperties;
	}

	
	//ConfigPkg: //	'package' name=PNAME
	//	'root' root=[Configuration] configurations+=Configuration* //	'end'
	//;
	public ConfigPkgElements getConfigPkgAccess() {
		return pConfigPkg;
	}
	
	public ParserRule getConfigPkgRule() {
		return getConfigPkgAccess().getRule();
	}
	
	//Configuration:
	//	'configuration' name=ID Parameters? ('extends' extended=[aadl2::ComponentClassifier|CNAME])? ('with'
	//	combined+=Combination (',' combined+=Combination)*)?
	//	Assignments?;
	public ConfigurationElements getConfigurationAccess() {
		return pConfiguration;
	}
	
	public ParserRule getConfigurationRule() {
		return getConfigurationAccess().getRule();
	}
	
	//Combination:
	//	unsafe?='unsafe'?
	//	configuration=[Configuration] Arguments?;
	public CombinationElements getCombinationAccess() {
		return pCombination;
	}
	
	public ParserRule getCombinationRule() {
		return getCombinationAccess().getRule();
	}
	
	//fragment Parameters *:
	//	'(' (parameters+=ConfigParameter (',' parameters+=ConfigParameter)*)? ')';
	public ParametersElements getParametersAccess() {
		return pParameters;
	}
	
	public ParserRule getParametersRule() {
		return getParametersAccess().getRule();
	}
	
	//ConfigParameter:
	//	name=ID ':' (FClassifierType | FPropertyType) Candidates?;
	public ConfigParameterElements getConfigParameterAccess() {
		return pConfigParameter;
	}
	
	public ParserRule getConfigParameterRule() {
		return getConfigParameterAccess().getRule();
	}
	
	//fragment FClassifierType *:
	//	category=ComponentCategory classifier=[aadl2::ComponentClassifier|CNAME];
	public FClassifierTypeElements getFClassifierTypeAccess() {
		return pFClassifierType;
	}
	
	public ParserRule getFClassifierTypeRule() {
		return getFClassifierTypeAccess().getRule();
	}
	
	//fragment FPropertyType *:
	//	propertyType=[aadl2::Property|PNAME];
	public FPropertyTypeElements getFPropertyTypeAccess() {
		return pFPropertyType;
	}
	
	public ParserRule getFPropertyTypeRule() {
		return getFPropertyTypeAccess().getRule();
	}
	
	//fragment Candidates *:
	//	'from' '(' (candidates+=ConfigExpression (',' candidates+=ConfigExpression)*)? ')';
	public CandidatesElements getCandidatesAccess() {
		return pCandidates;
	}
	
	public ParserRule getCandidatesRule() {
		return getCandidatesAccess().getRule();
	}
	
	//fragment Assignments *:
	//	'{' (assignments+=Assignment (',' assignments+=Assignment)*)? '}';
	public AssignmentsElements getAssignmentsAccess() {
		return pAssignments;
	}
	
	public ParserRule getAssignmentsRule() {
		return getAssignmentsAccess().getRule();
	}
	
	//Assignment:
	//	(ref=ElementRef ('#' property=[aadl2::Property|PNAME])?
	//	| '#' property=[aadl2::Property|PNAME])
	//	'=>'
	//	value=ConfigExpression;
	public AssignmentElements getAssignmentAccess() {
		return pAssignment;
	}
	
	public ParserRule getAssignmentRule() {
		return getAssignmentAccess().getRule();
	}
	
	////fragment LVal*:
	////	element=ElementRef ('#' property=[aadl2::Property|PNAME])? 
	////	| '#' property=[aadl2::Property|PNAME]
	////;
	//ConfigExpression ConfigValue:
	//	{NamedElementRef} ref=[aadl2::NamedElement|CNAME] Arguments? Assignments?
	//	| {PropertyValue} exp=CPropertyExpression
	//	| Assignments;
	public ConfigExpressionElements getConfigExpressionAccess() {
		return pConfigExpression;
	}
	
	public ParserRule getConfigExpressionRule() {
		return getConfigExpressionAccess().getRule();
	}
	
	//fragment Arguments *:
	//	'(' (arguments+=Argument (',' arguments+=Argument)*)? ')';
	public ArgumentsElements getArgumentsAccess() {
		return pArguments;
	}
	
	public ParserRule getArgumentsRule() {
		return getArgumentsAccess().getRule();
	}
	
	//Argument:
	//	parameter=[ConfigParameter] '=>' value=ConfigExpression;
	public ArgumentElements getArgumentAccess() {
		return pArgument;
	}
	
	public ParserRule getArgumentRule() {
		return getArgumentAccess().getRule();
	}
	
	//ElementRef:
	//	element=[aadl2::NamedElement] => ({ElementRef.prev=current} '.' element=[aadl2::NamedElement])*;
	public ElementRefElements getElementRefAccess() {
		return pElementRef;
	}
	
	public ParserRule getElementRefRule() {
		return getElementRefAccess().getRule();
	}
	
	//CPropertyExpression aadl2::PropertyExpression:
	//	BooleanLiteral
	//	| IntegerTerm
	//	| RealTerm
	//	| StringTerm
	//	| NumericRangeTerm
	//	| RecordTerm
	//	| ReferenceTerm
	//	| ComponentClassifierTerm
	//	| ListTerm
	//	//	| ComputedTerm
	//;
	public CPropertyExpressionElements getCPropertyExpressionAccess() {
		return pCPropertyExpression;
	}
	
	public ParserRule getCPropertyExpressionRule() {
		return getCPropertyExpressionAccess().getRule();
	}
	
	//NamedElement aadl2::NamedElement:
	//	ConfigPkg | Configuration | ConfigParameter;
	public NamedElementElements getNamedElementAccess() {
		return pNamedElement;
	}
	
	public ParserRule getNamedElementRule() {
		return getNamedElementAccess().getRule();
	}
	
	//// classifier name
	//CNAME:
	//	ID ('::' ID)* ('.' ID)?;
	public CNAMEElements getCNAMEAccess() {
		return pCNAME;
	}
	
	public ParserRule getCNAMERule() {
		return getCNAMEAccess().getRule();
	}
	
	//// property name
	//// see Aadl2 PNAME: ID ('::' ID)?;
	//// qualified name
	//QNAME:
	//	ID ('.' ID)*;
	public QNAMEElements getQNAMEAccess() {
		return pQNAME;
	}
	
	public ParserRule getQNAMERule() {
		return getQNAMEAccess().getRule();
	}
	
	////import "http://www.eclipse.org/emf/2002/Ecore" as ecore
	//Model aadl2::ModelUnit:
	//	(AadlPackage | PropertySet)?;
	public Aadl2GrammarAccess.ModelElements getModelAccess() {
		return gaAadl2.getModelAccess();
	}
	
	public ParserRule getModelRule() {
		return getModelAccess().getRule();
	}
	
	//AadlPackage aadl2::AadlPackage:
	//	'package' name=PNAME (ownedPublicSection=PublicPackageSection
	//	ownedPrivateSection=PrivatePackageSection? | ownedPrivateSection=PrivatePackageSection) ('properties'
	//	(ownedPropertyAssociation+=BasicPropertyAssociation+ | 'none' ';'))?
	//	'end' PNAME ';';
	public Aadl2GrammarAccess.AadlPackageElements getAadlPackageAccess() {
		return gaAadl2.getAadlPackageAccess();
	}
	
	public ParserRule getAadlPackageRule() {
		return getAadlPackageAccess().getRule();
	}
	
	////ID ('::' ID)* ';';
	//PublicPackageSection aadl2::PublicPackageSection:
	//	{aadl2::PublicPackageSection}
	//	'public' ('with' importedUnit+=[aadl2::ModelUnit|PNAME] (',' importedUnit+=[aadl2::ModelUnit|PNAME])* ';' |
	//	ownedPackageRename+=(PackageRename | RenameAll) | ownedFeatureGroupTypeRename+=FGTRename |
	//	ownedComponentTypeRename+=CTRename)* (ownedClassifier+=Classifier
	//	| ownedAnnexLibrary+=AnnexLibrary)*;
	public Aadl2GrammarAccess.PublicPackageSectionElements getPublicPackageSectionAccess() {
		return gaAadl2.getPublicPackageSectionAccess();
	}
	
	public ParserRule getPublicPackageSectionRule() {
		return getPublicPackageSectionAccess().getRule();
	}
	
	//PrivatePackageSection aadl2::PrivatePackageSection:
	//	{aadl2::PrivatePackageSection}
	//	'private' ('with' importedUnit+=[aadl2::ModelUnit|PNAME] (',' importedUnit+=[aadl2::ModelUnit|PNAME])* ';' |
	//	ownedPackageRename+=(PackageRename | RenameAll) | ownedFeatureGroupTypeRename+=FGTRename |
	//	ownedComponentTypeRename+=CTRename)* (ownedClassifier+=Classifier
	//	| ownedAnnexLibrary+=AnnexLibrary)*;
	public Aadl2GrammarAccess.PrivatePackageSectionElements getPrivatePackageSectionAccess() {
		return gaAadl2.getPrivatePackageSectionAccess();
	}
	
	public ParserRule getPrivatePackageSectionRule() {
		return getPrivatePackageSectionAccess().getRule();
	}
	
	//PackageRename aadl2::PackageRename:
	//	name=ID 'renames' 'package' renamedPackage=[aadl2::AadlPackage|PNAME] ('::' renameAll?='all')?
	//	';';
	public Aadl2GrammarAccess.PackageRenameElements getPackageRenameAccess() {
		return gaAadl2.getPackageRenameAccess();
	}
	
	public ParserRule getPackageRenameRule() {
		return getPackageRenameAccess().getRule();
	}
	
	//RenameAll aadl2::PackageRename:
	//	'renames' renamedPackage=[aadl2::AadlPackage|PNAME] '::' renameAll?='all'
	//	';';
	public Aadl2GrammarAccess.RenameAllElements getRenameAllAccess() {
		return gaAadl2.getRenameAllAccess();
	}
	
	public ParserRule getRenameAllRule() {
		return getRenameAllAccess().getRule();
	}
	
	//FGTRename aadl2::FeatureGroupTypeRename:
	//	name=ID? 'renames' 'feature' 'group' renamedFeatureGroupType=[aadl2::FeatureGroupType|QCREF]
	//	';';
	public Aadl2GrammarAccess.FGTRenameElements getFGTRenameAccess() {
		return gaAadl2.getFGTRenameAccess();
	}
	
	public ParserRule getFGTRenameRule() {
		return getFGTRenameAccess().getRule();
	}
	
	//CTRename aadl2::ComponentTypeRename:
	//	name=ID? 'renames' category=ComponentCategory renamedComponentType=[aadl2::ComponentType|QCREF]
	//	';';
	public Aadl2GrammarAccess.CTRenameElements getCTRenameAccess() {
		return gaAadl2.getCTRenameAccess();
	}
	
	public ParserRule getCTRenameRule() {
		return getCTRenameAccess().getRule();
	}
	
	//TypeExtension aadl2::TypeExtension:
	//	'extends' extended=[aadl2::ComponentType|QCREF];
	public Aadl2GrammarAccess.TypeExtensionElements getTypeExtensionAccess() {
		return gaAadl2.getTypeExtensionAccess();
	}
	
	public ParserRule getTypeExtensionRule() {
		return getTypeExtensionAccess().getRule();
	}
	
	//ImplementationExtension aadl2::ImplementationExtension:
	//	'extends' extended=[aadl2::ComponentImplementation|QCREF];
	public Aadl2GrammarAccess.ImplementationExtensionElements getImplementationExtensionAccess() {
		return gaAadl2.getImplementationExtensionAccess();
	}
	
	public ParserRule getImplementationExtensionRule() {
		return getImplementationExtensionAccess().getRule();
	}
	
	//GroupExtension aadl2::GroupExtension:
	//	'extends' extended=[aadl2::FeatureGroupType|QCREF];
	public Aadl2GrammarAccess.GroupExtensionElements getGroupExtensionAccess() {
		return gaAadl2.getGroupExtensionAccess();
	}
	
	public ParserRule getGroupExtensionRule() {
		return getGroupExtensionAccess().getRule();
	}
	
	//ComponentCategory aadl2::ComponentCategory:
	//	'abstract' | 'bus' | 'data'
	//	| 'device' | 'memory' | 'process' | 'processor' | 'subprogram'
	//	| 'subprogram' 'group' | 'system' | 'thread' 'group'
	//	| 'thread' | 'virtual' 'bus' | 'virtual' 'processor';
	public Aadl2GrammarAccess.ComponentCategoryElements getComponentCategoryAccess() {
		return gaAadl2.getComponentCategoryAccess();
	}
	
	public ParserRule getComponentCategoryRule() {
		return getComponentCategoryAccess().getRule();
	}
	
	//Classifier aadl2::Classifier:
	//	ComponentType | ComponentImplementation | FeatureGroupType;
	public Aadl2GrammarAccess.ClassifierElements getClassifierAccess() {
		return gaAadl2.getClassifierAccess();
	}
	
	public ParserRule getClassifierRule() {
		return getClassifierAccess().getRule();
	}
	
	//ComponentType aadl2::ComponentType:
	//	AbstractType | SystemType | VirtualProcessorType
	//	| SubprogramGroupType | DataType
	//	| BusType | VirtualBusType | MemoryType
	//	| ProcessorType | ProcessType | ThreadGroupType | ThreadType | DeviceType | SubprogramType;
	public Aadl2GrammarAccess.ComponentTypeElements getComponentTypeAccess() {
		return gaAadl2.getComponentTypeAccess();
	}
	
	public ParserRule getComponentTypeRule() {
		return getComponentTypeAccess().getRule();
	}
	
	//AbstractType aadl2::AbstractType:
	//	'abstract' name=ID (ownedExtension=TypeExtension ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')?)? ('prototypes' (noPrototypes?='none' ';' |
	//	ownedPrototype+=Prototype+))? ('features' (noFeatures?='none' ';' | (ownedDataPort+=DataPort |
	//	ownedEventPort+=EventPort | ownedEventDataPort+=EventDataPort
	//	| ownedFeatureGroup+=FeatureGroup | ownedDataAccess+=DataAccess | ownedBusAccess+=BusAccess
	//	| ownedSubprogramAccess+=SubprogramAccess | ownedSubprogramGroupAccess+=SubprogramGroupAccess
	//	| ownedAbstractFeature+=AbstractFeature)+))? ('flows' (ownedFlowSpecification+=FlowSpecification+ | noFlows?='none'
	//	';'))? (derivedModes?='requires' 'modes'
	//	ownedMode+=Mode+ | 'modes' ((ownedMode+=Mode | ownedModeTransition+=ModeTransition)+ | noModes?='none' ';'))?
	//	('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' ID ';';
	public Aadl2GrammarAccess.AbstractTypeElements getAbstractTypeAccess() {
		return gaAadl2.getAbstractTypeAccess();
	}
	
	public ParserRule getAbstractTypeRule() {
		return getAbstractTypeAccess().getRule();
	}
	
	//SystemType aadl2::SystemType:
	//	'system' name=ID (ownedExtension=TypeExtension ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')?)? ('prototypes' (ownedPrototype+=Prototype+
	//	| noPrototypes?='none' ';'))? ('features' ((ownedDataPort+=DataPort | ownedEventPort+=EventPort |
	//	ownedEventDataPort+=EventDataPort
	//	| ownedFeatureGroup+=FeatureGroup | ownedDataAccess+=DataAccess | ownedBusAccess+=BusAccess
	//	| ownedSubprogramAccess+=SubprogramAccess | ownedSubprogramGroupAccess+=SubprogramGroupAccess
	//	| ownedAbstractFeature+=AbstractFeature)+
	//	| noFeatures?='none' ';'))? ('flows' (ownedFlowSpecification+=FlowSpecification+ | noFlows?='none' ';'))?
	//	(derivedModes?='requires' 'modes'
	//	ownedMode+=Mode+ | 'modes' ((ownedMode+=Mode | ownedModeTransition+=ModeTransition)+ | noModes?='none' ';'))?
	//	('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' ID ';';
	public Aadl2GrammarAccess.SystemTypeElements getSystemTypeAccess() {
		return gaAadl2.getSystemTypeAccess();
	}
	
	public ParserRule getSystemTypeRule() {
		return getSystemTypeAccess().getRule();
	}
	
	//ProcessType aadl2::ProcessType:
	//	'process' name=ID (ownedExtension=TypeExtension ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')?)? ('prototypes' (noPrototypes?='none' ';' |
	//	ownedPrototype+=Prototype+))? ('features' (noFeatures?='none' ';' | (ownedDataPort+=DataPort |
	//	ownedEventPort+=EventPort | ownedEventDataPort+=EventDataPort
	//	| ownedFeatureGroup+=FeatureGroup | ownedDataAccess+=DataAccess
	//	| ownedSubprogramAccess+=SubprogramAccess | ownedSubprogramGroupAccess+=SubprogramGroupAccess
	//	| ownedAbstractFeature+=AbstractFeature)+))? ('flows' (ownedFlowSpecification+=FlowSpecification+ | noFlows?='none'
	//	';'))? (derivedModes?='requires' 'modes'
	//	ownedMode+=Mode+ | 'modes' ((ownedMode+=Mode | ownedModeTransition+=ModeTransition)+ | noModes?='none' ';'))?
	//	('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' ID ';';
	public Aadl2GrammarAccess.ProcessTypeElements getProcessTypeAccess() {
		return gaAadl2.getProcessTypeAccess();
	}
	
	public ParserRule getProcessTypeRule() {
		return getProcessTypeAccess().getRule();
	}
	
	//ThreadGroupType aadl2::ThreadGroupType:
	//	'thread' 'group' name=ID (ownedExtension=TypeExtension ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')?)? ('prototypes' (noPrototypes?='none' ';' |
	//	ownedPrototype+=Prototype+))? ('features' (noFeatures?='none' ';' | (ownedDataPort+=DataPort |
	//	ownedEventPort+=EventPort | ownedEventDataPort+=EventDataPort
	//	| ownedFeatureGroup+=FeatureGroup | ownedDataAccess+=DataAccess
	//	| ownedSubprogramAccess+=SubprogramAccess | ownedSubprogramGroupAccess+=SubprogramGroupAccess
	//	| ownedAbstractFeature+=AbstractFeature)+))? ('flows' (ownedFlowSpecification+=FlowSpecification+ | noFlows?='none'
	//	';'))? (derivedModes?='requires' 'modes'
	//	ownedMode+=Mode+ | 'modes' ((ownedMode+=Mode | ownedModeTransition+=ModeTransition)+ | noModes?='none' ';'))?
	//	('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' ID ';';
	public Aadl2GrammarAccess.ThreadGroupTypeElements getThreadGroupTypeAccess() {
		return gaAadl2.getThreadGroupTypeAccess();
	}
	
	public ParserRule getThreadGroupTypeRule() {
		return getThreadGroupTypeAccess().getRule();
	}
	
	//ThreadType aadl2::ThreadType:
	//	'thread' name=ID (ownedExtension=TypeExtension ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')?)? ('prototypes' (noPrototypes?='none' ';' |
	//	ownedPrototype+=Prototype+))? ('features' (noFeatures?='none' ';' | (ownedDataPort+=DataPort |
	//	ownedEventPort+=EventPort | ownedEventDataPort+=EventDataPort
	//	| ownedFeatureGroup+=FeatureGroup | ownedDataAccess+=DataAccess
	//	| ownedSubprogramAccess+=SubprogramAccess | ownedSubprogramGroupAccess+=SubprogramGroupAccess
	//	| ownedAbstractFeature+=AbstractFeature)+))? ('flows' (ownedFlowSpecification+=FlowSpecification+ | noFlows?='none'
	//	';'))? (derivedModes?='requires' 'modes'
	//	ownedMode+=Mode+ | 'modes' ((ownedMode+=Mode | ownedModeTransition+=ModeTransition)+ | noModes?='none' ';'))?
	//	('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' ID ';';
	public Aadl2GrammarAccess.ThreadTypeElements getThreadTypeAccess() {
		return gaAadl2.getThreadTypeAccess();
	}
	
	public ParserRule getThreadTypeRule() {
		return getThreadTypeAccess().getRule();
	}
	
	//SubprogramType aadl2::SubprogramType:
	//	'subprogram' name=ID (ownedExtension=TypeExtension ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')?)? ('prototypes' (noPrototypes?='none' ';' |
	//	ownedPrototype+=Prototype+))? ('features' (noFeatures?='none' ';' | (ownedParameter+=Parameter |
	//	ownedEventPort+=EventPort | ownedEventDataPort+=EventDataPort
	//	| ownedFeatureGroup+=FeatureGroup | ownedDataAccess+=DataAccess
	//	| ownedSubprogramAccess+=SubprogramAccess | ownedSubprogramGroupAccess+=SubprogramGroupAccess
	//	| ownedAbstractFeature+=AbstractFeature)+))? ('flows' (ownedFlowSpecification+=FlowSpecification+ | noFlows?='none'
	//	';'))? (derivedModes?='requires' 'modes'
	//	ownedMode+=Mode+ | 'modes' ((ownedMode+=Mode | ownedModeTransition+=ModeTransition)+ | noModes?='none' ';'))?
	//	('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' ID ';';
	public Aadl2GrammarAccess.SubprogramTypeElements getSubprogramTypeAccess() {
		return gaAadl2.getSubprogramTypeAccess();
	}
	
	public ParserRule getSubprogramTypeRule() {
		return getSubprogramTypeAccess().getRule();
	}
	
	//SubprogramGroupType aadl2::SubprogramGroupType:
	//	'subprogram' 'group' name=ID (ownedExtension=TypeExtension ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')?)? ('prototypes' (noPrototypes?='none' ';' |
	//	ownedPrototype+=Prototype+))? ('features' (noFeatures?='none' ';' | (ownedSubprogramAccess+=SubprogramAccess
	//	| ownedFeatureGroup+=FeatureGroup
	//	| ownedSubprogramGroupAccess+=SubprogramGroupAccess
	//	| ownedAbstractFeature+=AbstractFeature)+))? ('flows' (ownedFlowSpecification+=FlowSpecification+ | noFlows?='none'
	//	';'))? ('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' ID ';';
	public Aadl2GrammarAccess.SubprogramGroupTypeElements getSubprogramGroupTypeAccess() {
		return gaAadl2.getSubprogramGroupTypeAccess();
	}
	
	public ParserRule getSubprogramGroupTypeRule() {
		return getSubprogramGroupTypeAccess().getRule();
	}
	
	//ProcessorType aadl2::ProcessorType:
	//	'processor' name=ID (ownedExtension=TypeExtension ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')?)? ('prototypes' (noPrototypes?='none' ';' |
	//	ownedPrototype+=Prototype+))? ('features' (noFeatures?='none' ';' | (ownedDataPort+=DataPort |
	//	ownedEventPort+=EventPort | ownedEventDataPort+=EventDataPort
	//	| ownedFeatureGroup+=FeatureGroup | ownedBusAccess+=BusAccess
	//	| ownedSubprogramAccess+=SubprogramAccess | ownedSubprogramGroupAccess+=SubprogramGroupAccess
	//	| ownedAbstractFeature+=AbstractFeature)+))? ('flows' (ownedFlowSpecification+=FlowSpecification+ | noFlows?='none'
	//	';'))? (derivedModes?='requires' 'modes'
	//	ownedMode+=Mode+ | 'modes' ((ownedMode+=Mode | ownedModeTransition+=ModeTransition)+ | noModes?='none' ';'))?
	//	('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' ID ';';
	public Aadl2GrammarAccess.ProcessorTypeElements getProcessorTypeAccess() {
		return gaAadl2.getProcessorTypeAccess();
	}
	
	public ParserRule getProcessorTypeRule() {
		return getProcessorTypeAccess().getRule();
	}
	
	//DeviceType aadl2::DeviceType:
	//	'device' name=ID (ownedExtension=TypeExtension ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')?)? ('prototypes' (noPrototypes?='none' ';' |
	//	ownedPrototype+=Prototype+))? ('features' (noFeatures?='none' ';' | (ownedDataPort+=DataPort |
	//	ownedEventPort+=EventPort | ownedEventDataPort+=EventDataPort
	//	| ownedFeatureGroup+=FeatureGroup | ownedBusAccess+=BusAccess
	//	| ownedSubprogramAccess+=SubprogramAccess | ownedSubprogramGroupAccess+=SubprogramGroupAccess
	//	| ownedAbstractFeature+=AbstractFeature)+))? ('flows' (ownedFlowSpecification+=FlowSpecification+ | noFlows?='none'
	//	';'))? (derivedModes?='requires' 'modes'
	//	ownedMode+=Mode+ | 'modes' ((ownedMode+=Mode | ownedModeTransition+=ModeTransition)+ | noModes?='none' ';'))?
	//	('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' ID ';';
	public Aadl2GrammarAccess.DeviceTypeElements getDeviceTypeAccess() {
		return gaAadl2.getDeviceTypeAccess();
	}
	
	public ParserRule getDeviceTypeRule() {
		return getDeviceTypeAccess().getRule();
	}
	
	//MemoryType aadl2::MemoryType:
	//	'memory' name=ID (ownedExtension=TypeExtension ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')?)? ('prototypes' (noPrototypes?='none' ';' |
	//	ownedPrototype+=Prototype+))? ('features' (noFeatures?='none' ';' | (ownedFeatureGroup+=FeatureGroup |
	//	ownedBusAccess+=BusAccess
	//	| ownedDataPort+=DataPort | ownedEventPort+=EventPort | ownedEventDataPort+=EventDataPort
	//	| ownedAbstractFeature+=AbstractFeature)+))? (derivedModes?='requires' 'modes'
	//	ownedMode+=Mode+ | 'modes' ((ownedMode+=Mode | ownedModeTransition+=ModeTransition)+ | noModes?='none' ';'))?
	//	('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' ID ';';
	public Aadl2GrammarAccess.MemoryTypeElements getMemoryTypeAccess() {
		return gaAadl2.getMemoryTypeAccess();
	}
	
	public ParserRule getMemoryTypeRule() {
		return getMemoryTypeAccess().getRule();
	}
	
	//BusType aadl2::BusType:
	//	'bus' name=ID (ownedExtension=TypeExtension ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')?)? ('prototypes' (noPrototypes?='none' ';' |
	//	ownedPrototype+=Prototype+))? ('features' (noFeatures?='none' ';' | (ownedFeatureGroup+=FeatureGroup |
	//	ownedBusAccess+=BusAccess
	//	| ownedDataPort+=DataPort | ownedEventPort+=EventPort | ownedEventDataPort+=EventDataPort
	//	| ownedAbstractFeature+=AbstractFeature)+))? (derivedModes?='requires' 'modes'
	//	ownedMode+=Mode+ | 'modes' ((ownedMode+=Mode | ownedModeTransition+=ModeTransition)+ | noModes?='none' ';'))?
	//	('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' ID ';';
	public Aadl2GrammarAccess.BusTypeElements getBusTypeAccess() {
		return gaAadl2.getBusTypeAccess();
	}
	
	public ParserRule getBusTypeRule() {
		return getBusTypeAccess().getRule();
	}
	
	//VirtualBusType aadl2::VirtualBusType:
	//	'virtual' 'bus' name=ID (ownedExtension=TypeExtension ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')?)? ('prototypes' (noPrototypes?='none' ';' |
	//	ownedPrototype+=Prototype+))? ('features' (noFeatures?='none' ';' | (ownedFeatureGroup+=FeatureGroup
	//	| ownedDataPort+=DataPort | ownedEventPort+=EventPort | ownedEventDataPort+=EventDataPort
	//	| ownedAbstractFeature+=AbstractFeature | ownedBusAccess+=BusAccess)+))? (derivedModes?='requires' 'modes'
	//	ownedMode+=Mode+ | 'modes' ((ownedMode+=Mode | ownedModeTransition+=ModeTransition)+ | noModes?='none' ';'))?
	//	('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' ID ';';
	public Aadl2GrammarAccess.VirtualBusTypeElements getVirtualBusTypeAccess() {
		return gaAadl2.getVirtualBusTypeAccess();
	}
	
	public ParserRule getVirtualBusTypeRule() {
		return getVirtualBusTypeAccess().getRule();
	}
	
	//VirtualProcessorType aadl2::VirtualProcessorType:
	//	'virtual' 'processor' name=ID (ownedExtension=TypeExtension ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')?)? ('prototypes' (noPrototypes?='none' ';' |
	//	ownedPrototype+=Prototype+))? ('features' (noFeatures?='none' ';' | (ownedDataPort+=DataPort |
	//	ownedEventPort+=EventPort | ownedEventDataPort+=EventDataPort
	//	| ownedFeatureGroup+=FeatureGroup
	//	| ownedSubprogramAccess+=SubprogramAccess | ownedSubprogramGroupAccess+=SubprogramGroupAccess
	//	| ownedAbstractFeature+=AbstractFeature | ownedBusAccess+=BusAccess)+))? ('flows'
	//	(ownedFlowSpecification+=FlowSpecification+ | noFlows?='none' ';'))? (derivedModes?='requires' 'modes'
	//	ownedMode+=Mode+ | 'modes' ((ownedMode+=Mode | ownedModeTransition+=ModeTransition)+ | noModes?='none' ';'))?
	//	('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' ID ';';
	public Aadl2GrammarAccess.VirtualProcessorTypeElements getVirtualProcessorTypeAccess() {
		return gaAadl2.getVirtualProcessorTypeAccess();
	}
	
	public ParserRule getVirtualProcessorTypeRule() {
		return getVirtualProcessorTypeAccess().getRule();
	}
	
	//DataType aadl2::DataType:
	//	'data' name=ID (ownedExtension=TypeExtension ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')?)? ('prototypes' (noPrototypes?='none' ';' |
	//	ownedPrototype+=Prototype+))? ('features' (noFeatures?='none' ';' | (ownedFeatureGroup+=FeatureGroup |
	//	ownedDataAccess+=DataAccess | ownedSubprogramAccess+=SubprogramAccess |
	//	ownedSubprogramGroupAccess+=SubprogramGroupAccess
	//	| ownedAbstractFeature+=AbstractFeature)+))? ('flows' (ownedFlowSpecification+=FlowSpecification+ | noFlows?='none'
	//	';'))? (derivedModes?='requires' 'modes'
	//	ownedMode+=Mode+ | 'modes' ((ownedMode+=Mode | ownedModeTransition+=ModeTransition)+ | noModes?='none' ';'))?
	//	('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' ID ';';
	public Aadl2GrammarAccess.DataTypeElements getDataTypeAccess() {
		return gaAadl2.getDataTypeAccess();
	}
	
	public ParserRule getDataTypeRule() {
		return getDataTypeAccess().getRule();
	}
	
	//// Component Implementations: 
	//ComponentImplementation aadl2::ComponentImplementation:
	//	AbstractImplementation | SystemImplementation | ProcessorImplementation | ProcessImplementation |
	//	ThreadGroupImplementation | ThreadImplementation | DeviceImplementation | BusImplementation |
	//	VirtualProcessorImplementation | VirtualBusImplementation | MemoryImplementation | SubprogramImplementation |
	//	SubprogramGroupImplementation | DataImplementation;
	public Aadl2GrammarAccess.ComponentImplementationElements getComponentImplementationAccess() {
		return gaAadl2.getComponentImplementationAccess();
	}
	
	public ParserRule getComponentImplementationRule() {
		return getComponentImplementationAccess().getRule();
	}
	
	//Realization aadl2::Realization:
	//	implemented=[aadl2::ComponentType];
	public Aadl2GrammarAccess.RealizationElements getRealizationAccess() {
		return gaAadl2.getRealizationAccess();
	}
	
	public ParserRule getRealizationRule() {
		return getRealizationAccess().getRule();
	}
	
	//AbstractImplementation aadl2::AbstractImplementation:
	//	'abstract' 'implementation'
	//	ownedRealization=Realization '.' name=INAME
	//	ownedExtension=ImplementationExtension? ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')? ('prototypes' (ownedPrototype+=Prototype+
	//	| noPrototypes?='none' ';'))? ('subcomponents' ((ownedSystemSubcomponent+=SystemSubcomponent
	//	| ownedSubprogramSubcomponent+=SubprogramSubcomponent | ownedSubprogramGroupSubcomponent+=SubprogramGroupSubcomponent
	//	| ownedThreadSubcomponent+=ThreadSubcomponent | ownedThreadGroupSubcomponent+=ThreadGroupSubcomponent
	//	| ownedProcessSubcomponent+=ProcessSubcomponent
	//	| ownedProcessorSubcomponent+=ProcessorSubcomponent
	//	| ownedVirtualProcessorSubcomponent+=VirtualProcessorSubcomponent
	//	| ownedMemorySubcomponent+=MemorySubcomponent | ownedDeviceSubcomponent+=DeviceSubcomponent
	//	| ownedBusSubcomponent+=BusSubcomponent | ownedVirtualBusSubcomponent+=VirtualBusSubcomponent
	//	| ownedDataSubcomponent+=DataSubcomponent | ownedAbstractSubcomponent+=AbstractSubcomponent)+
	//	| noSubcomponents?='none' ';'))? ('internal' 'features' (ownedEventSource+=EventSource
	//	| ownedEventDataSource+=EventDataSource)+)? ('processor' 'features' (ownedPortProxy+=PortProxy
	//	| ownedSubprogramProxy+=SubprogramProxy)+)? ('calls' (ownedSubprogramCallSequence+=SubprogramCallSequence+
	//	| noCalls?='none' ';'))? ('connections' ((ownedPortConnection+=PortConnection |
	//	ownedAccessConnection+=AccessConnection
	//	| ownedFeatureGroupConnection+=FeatureGroupConnection | ownedFeatureConnection+=FeatureConnection
	//	| ownedParameterConnection+=ParameterConnection)+
	//	| noConnections?='none' ';'))? ('flows' ((ownedFlowImplementation+=FlowImplementation |
	//	ownedEndToEndFlow+=EndToEndFlow)+ | noFlows?='none' ';'))? ('modes' ((ownedMode+=Mode |
	//	ownedModeTransition+=ModeTransition)+
	//	| noModes?='none' ';'))? ('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' FULLINAME ';';
	public Aadl2GrammarAccess.AbstractImplementationElements getAbstractImplementationAccess() {
		return gaAadl2.getAbstractImplementationAccess();
	}
	
	public ParserRule getAbstractImplementationRule() {
		return getAbstractImplementationAccess().getRule();
	}
	
	//SystemImplementation aadl2::SystemImplementation:
	//	'system' 'implementation'
	//	ownedRealization=Realization '.' name=INAME
	//	ownedExtension=ImplementationExtension? ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')? ('prototypes' (ownedPrototype+=Prototype+
	//	| noPrototypes?='none' ';'))? ('subcomponents' ((ownedSystemSubcomponent+=SystemSubcomponent
	//	| ownedSubprogramSubcomponent+=SubprogramSubcomponent | ownedSubprogramGroupSubcomponent+=SubprogramGroupSubcomponent
	//	| ownedProcessSubcomponent+=ProcessSubcomponent
	//	| ownedProcessorSubcomponent+=ProcessorSubcomponent
	//	| ownedVirtualProcessorSubcomponent+=VirtualProcessorSubcomponent
	//	| ownedMemorySubcomponent+=MemorySubcomponent | ownedDeviceSubcomponent+=DeviceSubcomponent
	//	| ownedBusSubcomponent+=BusSubcomponent | ownedVirtualBusSubcomponent+=VirtualBusSubcomponent
	//	| ownedDataSubcomponent+=DataSubcomponent | ownedAbstractSubcomponent+=AbstractSubcomponent)+
	//	| noSubcomponents?='none' ';'))? ('internal' 'features' (ownedEventSource+=EventSource
	//	| ownedEventDataSource+=EventDataSource)+)? ('processor' 'features' (ownedPortProxy+=PortProxy
	//	| ownedSubprogramProxy+=SubprogramProxy)+)? ('connections' ((ownedPortConnection+=PortConnection |
	//	ownedAccessConnection+=AccessConnection
	//	| ownedFeatureGroupConnection+=FeatureGroupConnection | ownedFeatureConnection+=FeatureConnection)+
	//	| noConnections?='none' ';'))? ('flows' ((ownedFlowImplementation+=FlowImplementation |
	//	ownedEndToEndFlow+=EndToEndFlow)+ | noFlows?='none' ';'))? ('modes' ((ownedMode+=Mode |
	//	ownedModeTransition+=ModeTransition)+
	//	| noModes?='none' ';'))? ('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' FULLINAME ';';
	public Aadl2GrammarAccess.SystemImplementationElements getSystemImplementationAccess() {
		return gaAadl2.getSystemImplementationAccess();
	}
	
	public ParserRule getSystemImplementationRule() {
		return getSystemImplementationAccess().getRule();
	}
	
	//ProcessImplementation aadl2::ProcessImplementation:
	//	'process' 'implementation'
	//	ownedRealization=Realization '.' name=INAME
	//	ownedExtension=ImplementationExtension? ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')? ('prototypes' (ownedPrototype+=Prototype+
	//	| noPrototypes?='none' ';'))? ('subcomponents' ((ownedThreadGroupSubcomponent+=ThreadGroupSubcomponent |
	//	ownedThreadSubcomponent+=ThreadSubcomponent
	//	| ownedSubprogramSubcomponent+=SubprogramSubcomponent | ownedSubprogramGroupSubcomponent+=SubprogramGroupSubcomponent
	//	| ownedDataSubcomponent+=DataSubcomponent | ownedAbstractSubcomponent+=AbstractSubcomponent)+
	//	| noSubcomponents?='none' ';'))? ('internal' 'features' (ownedEventSource+=EventSource
	//	| ownedEventDataSource+=EventDataSource)+)? ('processor' 'features' (ownedPortProxy+=PortProxy
	//	| ownedSubprogramProxy+=SubprogramProxy)+)? ('connections' ((ownedPortConnection+=PortConnection |
	//	ownedAccessConnection+=AccessConnection
	//	| ownedFeatureGroupConnection+=FeatureGroupConnection | ownedFeatureConnection+=FeatureConnection)+
	//	| noConnections?='none' ';'))? ('flows' ((ownedFlowImplementation+=FlowImplementation |
	//	ownedEndToEndFlow+=EndToEndFlow)+ | noFlows?='none' ';'))? ('modes' ((ownedMode+=Mode |
	//	ownedModeTransition+=ModeTransition)+
	//	| noModes?='none' ';'))? ('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' FULLINAME ';';
	public Aadl2GrammarAccess.ProcessImplementationElements getProcessImplementationAccess() {
		return gaAadl2.getProcessImplementationAccess();
	}
	
	public ParserRule getProcessImplementationRule() {
		return getProcessImplementationAccess().getRule();
	}
	
	//ThreadGroupImplementation aadl2::ThreadGroupImplementation:
	//	'thread' 'group' 'implementation'
	//	ownedRealization=Realization '.' name=INAME
	//	ownedExtension=ImplementationExtension? ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')? ('prototypes' (ownedPrototype+=Prototype+
	//	| noPrototypes?='none' ';'))? ('subcomponents' ((ownedThreadGroupSubcomponent+=ThreadGroupSubcomponent |
	//	ownedThreadSubcomponent+=ThreadSubcomponent
	//	| ownedSubprogramSubcomponent+=SubprogramSubcomponent | ownedSubprogramGroupSubcomponent+=SubprogramGroupSubcomponent
	//	| ownedDataSubcomponent+=DataSubcomponent | ownedAbstractSubcomponent+=AbstractSubcomponent)+
	//	| noSubcomponents?='none' ';'))? ('internal' 'features' (ownedEventSource+=EventSource
	//	| ownedEventDataSource+=EventDataSource)+)? ('processor' 'features' (ownedPortProxy+=PortProxy
	//	| ownedSubprogramProxy+=SubprogramProxy)+)? ('connections' ((ownedPortConnection+=PortConnection |
	//	ownedAccessConnection+=AccessConnection
	//	| ownedFeatureGroupConnection+=FeatureGroupConnection | ownedFeatureConnection+=FeatureConnection)+
	//	| noConnections?='none' ';'))? ('flows' ((ownedFlowImplementation+=FlowImplementation |
	//	ownedEndToEndFlow+=EndToEndFlow)+ | noFlows?='none' ';'))? ('modes' ((ownedMode+=Mode |
	//	ownedModeTransition+=ModeTransition)+
	//	| noModes?='none' ';'))? ('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' FULLINAME ';';
	public Aadl2GrammarAccess.ThreadGroupImplementationElements getThreadGroupImplementationAccess() {
		return gaAadl2.getThreadGroupImplementationAccess();
	}
	
	public ParserRule getThreadGroupImplementationRule() {
		return getThreadGroupImplementationAccess().getRule();
	}
	
	//ThreadImplementation aadl2::ThreadImplementation:
	//	'thread' 'implementation'
	//	ownedRealization=Realization '.' name=INAME
	//	ownedExtension=ImplementationExtension? ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')? ('prototypes' (ownedPrototype+=Prototype+
	//	| noPrototypes?='none' ';'))? ('subcomponents' ((ownedSubprogramSubcomponent+=SubprogramSubcomponent
	//	| ownedSubprogramGroupSubcomponent+=SubprogramGroupSubcomponent
	//	| ownedDataSubcomponent+=DataSubcomponent | ownedAbstractSubcomponent+=AbstractSubcomponent)+
	//	| noSubcomponents?='none' ';'))? ('internal' 'features' (ownedEventSource+=EventSource
	//	| ownedEventDataSource+=EventDataSource)+)? ('processor' 'features' (ownedPortProxy+=PortProxy
	//	| ownedSubprogramProxy+=SubprogramProxy)+)? ('calls' (ownedSubprogramCallSequence+=SubprogramCallSequence+
	//	| noCalls?='none' ';'))? ('connections' ((ownedPortConnection+=PortConnection |
	//	ownedAccessConnection+=AccessConnection
	//	| ownedFeatureGroupConnection+=FeatureGroupConnection | ownedFeatureConnection+=FeatureConnection
	//	| ownedParameterConnection+=ParameterConnection)+
	//	| noConnections?='none' ';'))? ('flows' ((ownedFlowImplementation+=FlowImplementation |
	//	ownedEndToEndFlow+=EndToEndFlow)+ | noFlows?='none' ';'))? ('modes' ((ownedMode+=Mode |
	//	ownedModeTransition+=ModeTransition)+
	//	| noModes?='none' ';'))? ('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' FULLINAME ';';
	public Aadl2GrammarAccess.ThreadImplementationElements getThreadImplementationAccess() {
		return gaAadl2.getThreadImplementationAccess();
	}
	
	public ParserRule getThreadImplementationRule() {
		return getThreadImplementationAccess().getRule();
	}
	
	//SubprogramImplementation aadl2::SubprogramImplementation:
	//	'subprogram' 'implementation'
	//	ownedRealization=Realization '.' name=INAME
	//	ownedExtension=ImplementationExtension? ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')? ('prototypes' (ownedPrototype+=Prototype+
	//	| noPrototypes?='none' ';'))? ('subcomponents' ((ownedAbstractSubcomponent+=AbstractSubcomponent
	//	| ownedSubprogramSubcomponent+=SubprogramSubcomponent
	//	| ownedDataSubcomponent+=DataSubcomponent)+
	//	| noSubcomponents?='none' ';'))? ('internal' 'features' (ownedEventSource+=EventSource
	//	| ownedEventDataSource+=EventDataSource)+)? ('processor' 'features' (ownedPortProxy+=PortProxy
	//	| ownedSubprogramProxy+=SubprogramProxy)+)? ('calls' (ownedSubprogramCallSequence+=SubprogramCallSequence+
	//	| noCalls?='none' ';'))? ('connections' ((ownedPortConnection+=PortConnection |
	//	ownedAccessConnection+=AccessConnection
	//	| ownedFeatureGroupConnection+=FeatureGroupConnection | ownedFeatureConnection+=FeatureConnection
	//	| ownedParameterConnection+=ParameterConnection)+
	//	| noConnections?='none' ';'))? ('flows' ((ownedFlowImplementation+=FlowImplementation |
	//	ownedEndToEndFlow+=EndToEndFlow)+ | noFlows?='none' ';'))? ('modes' ((ownedMode+=Mode |
	//	ownedModeTransition+=ModeTransition)+
	//	| noModes?='none' ';'))? ('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' FULLINAME ';';
	public Aadl2GrammarAccess.SubprogramImplementationElements getSubprogramImplementationAccess() {
		return gaAadl2.getSubprogramImplementationAccess();
	}
	
	public ParserRule getSubprogramImplementationRule() {
		return getSubprogramImplementationAccess().getRule();
	}
	
	//SubprogramGroupImplementation aadl2::SubprogramGroupImplementation:
	//	'subprogram' 'group' 'implementation'
	//	ownedRealization=Realization '.' name=INAME
	//	ownedExtension=ImplementationExtension? ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')? ('prototypes' (ownedPrototype+=Prototype+
	//	| noPrototypes?='none' ';'))? ('subcomponents' ((ownedSubprogramSubcomponent+=SubprogramSubcomponent |
	//	ownedAbstractSubcomponent+=AbstractSubcomponent
	//	| ownedSubprogramGroupSubcomponent+=SubprogramGroupSubcomponent
	//	| ownedDataSubcomponent+=DataSubcomponent)+
	//	| noSubcomponents?='none' ';'))? ('internal' 'features' (ownedEventSource+=EventSource
	//	| ownedEventDataSource+=EventDataSource)+)? ('processor' 'features' (ownedPortProxy+=PortProxy
	//	| ownedSubprogramProxy+=SubprogramProxy)+)? ('connections' ((ownedAccessConnection+=AccessConnection
	//	| ownedFeatureGroupConnection+=FeatureGroupConnection | ownedFeatureConnection+=FeatureConnection)+
	//	| noConnections?='none' ';'))? ('modes' ((ownedMode+=Mode | ownedModeTransition+=ModeTransition)+
	//	| noModes?='none' ';'))? ('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' FULLINAME ';';
	public Aadl2GrammarAccess.SubprogramGroupImplementationElements getSubprogramGroupImplementationAccess() {
		return gaAadl2.getSubprogramGroupImplementationAccess();
	}
	
	public ParserRule getSubprogramGroupImplementationRule() {
		return getSubprogramGroupImplementationAccess().getRule();
	}
	
	//ProcessorImplementation aadl2::ProcessorImplementation:
	//	'processor' 'implementation'
	//	ownedRealization=Realization '.' name=INAME
	//	ownedExtension=ImplementationExtension? ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')? ('prototypes' (ownedPrototype+=Prototype+
	//	| noPrototypes?='none' ';'))? ('subcomponents' ((ownedMemorySubcomponent+=MemorySubcomponent |
	//	ownedAbstractSubcomponent+=AbstractSubcomponent
	//	| ownedBusSubcomponent+=BusSubcomponent | ownedVirtualBusSubcomponent+=VirtualBusSubcomponent
	//	| ownedVirtualProcessorSubcomponent+=VirtualProcessorSubcomponent)+
	//	| noSubcomponents?='none' ';'))? ('internal' 'features' (ownedEventSource+=EventSource
	//	| ownedEventDataSource+=EventDataSource)+)? ('connections' ((ownedPortConnection+=PortConnection |
	//	ownedAccessConnection+=AccessConnection
	//	| ownedFeatureGroupConnection+=FeatureGroupConnection | ownedFeatureConnection+=FeatureConnection)+
	//	| noConnections?='none' ';'))? ('flows' ((ownedFlowImplementation+=FlowImplementation |
	//	ownedEndToEndFlow+=EndToEndFlow)+ | noFlows?='none' ';'))? ('modes' ((ownedMode+=Mode |
	//	ownedModeTransition+=ModeTransition)+
	//	| noModes?='none' ';'))? ('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' FULLINAME ';';
	public Aadl2GrammarAccess.ProcessorImplementationElements getProcessorImplementationAccess() {
		return gaAadl2.getProcessorImplementationAccess();
	}
	
	public ParserRule getProcessorImplementationRule() {
		return getProcessorImplementationAccess().getRule();
	}
	
	//VirtualProcessorImplementation aadl2::VirtualProcessorImplementation:
	//	'virtual' 'processor' 'implementation'
	//	ownedRealization=Realization '.' name=INAME
	//	ownedExtension=ImplementationExtension? ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')? ('prototypes' (ownedPrototype+=Prototype+
	//	| noPrototypes?='none' ';'))? ('subcomponents' ((ownedAbstractSubcomponent+=AbstractSubcomponent
	//	| ownedVirtualBusSubcomponent+=VirtualBusSubcomponent
	//	| ownedVirtualProcessorSubcomponent+=VirtualProcessorSubcomponent)+
	//	| noSubcomponents?='none' ';'))? ('internal' 'features' (ownedEventSource+=EventSource
	//	| ownedEventDataSource+=EventDataSource)+)? ('processor' 'features' (ownedPortProxy+=PortProxy
	//	| ownedSubprogramProxy+=SubprogramProxy)+)? ('connections' ((ownedPortConnection+=PortConnection |
	//	ownedAccessConnection+=AccessConnection
	//	| ownedFeatureGroupConnection+=FeatureGroupConnection | ownedFeatureConnection+=FeatureConnection)+
	//	| noConnections?='none' ';'))? ('flows' ((ownedFlowImplementation+=FlowImplementation |
	//	ownedEndToEndFlow+=EndToEndFlow)+ | noFlows?='none' ';'))? ('modes' ((ownedMode+=Mode |
	//	ownedModeTransition+=ModeTransition)+
	//	| noModes?='none' ';'))? ('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' FULLINAME ';';
	public Aadl2GrammarAccess.VirtualProcessorImplementationElements getVirtualProcessorImplementationAccess() {
		return gaAadl2.getVirtualProcessorImplementationAccess();
	}
	
	public ParserRule getVirtualProcessorImplementationRule() {
		return getVirtualProcessorImplementationAccess().getRule();
	}
	
	//DeviceImplementation aadl2::DeviceImplementation:
	//	'device' 'implementation'
	//	ownedRealization=Realization '.' name=INAME
	//	ownedExtension=ImplementationExtension? ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')? ('prototypes' (ownedPrototype+=Prototype+
	//	| noPrototypes?='none' ';'))? ('subcomponents' ((ownedAbstractSubcomponent+=AbstractSubcomponent |
	//	ownedDataSubcomponent+=DataSubcomponent
	//	| ownedVirtualBusSubcomponent+=VirtualBusSubcomponent
	//	| ownedBusSubcomponent+=BusSubcomponent)+
	//	| noSubcomponents?='none' ';'))? ('internal' 'features' (ownedEventSource+=EventSource
	//	| ownedEventDataSource+=EventDataSource)+)? ('processor' 'features' (ownedPortProxy+=PortProxy
	//	| ownedSubprogramProxy+=SubprogramProxy)+)? ('connections' ((ownedPortConnection+=PortConnection |
	//	ownedAccessConnection+=AccessConnection
	//	| ownedFeatureGroupConnection+=FeatureGroupConnection | ownedFeatureConnection+=FeatureConnection)+
	//	| noConnections?='none' ';'))? ('flows' ((ownedFlowImplementation+=FlowImplementation |
	//	ownedEndToEndFlow+=EndToEndFlow)+ | noFlows?='none' ';'))? ('modes' ((ownedMode+=Mode |
	//	ownedModeTransition+=ModeTransition)+
	//	| noModes?='none' ';'))? ('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' FULLINAME ';';
	public Aadl2GrammarAccess.DeviceImplementationElements getDeviceImplementationAccess() {
		return gaAadl2.getDeviceImplementationAccess();
	}
	
	public ParserRule getDeviceImplementationRule() {
		return getDeviceImplementationAccess().getRule();
	}
	
	//MemoryImplementation aadl2::MemoryImplementation:
	//	'memory' 'implementation'
	//	ownedRealization=Realization '.' name=INAME
	//	ownedExtension=ImplementationExtension? ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')? ('prototypes' (ownedPrototype+=Prototype+
	//	| noPrototypes?='none' ';'))? ('subcomponents' ((ownedAbstractSubcomponent+=AbstractSubcomponent
	//	| ownedMemorySubcomponent+=MemorySubcomponent
	//	| ownedBusSubcomponent+=BusSubcomponent)+
	//	| noSubcomponents?='none' ';'))? ('internal' 'features' (ownedEventSource+=EventSource
	//	| ownedEventDataSource+=EventDataSource)+)? ('connections' ((ownedAccessConnection+=AccessConnection
	//	| ownedFeatureGroupConnection+=FeatureGroupConnection | ownedFeatureConnection+=FeatureConnection)+
	//	| noConnections?='none' ';'))? ('modes' ((ownedMode+=Mode | ownedModeTransition+=ModeTransition)+
	//	| noModes?='none' ';'))? ('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' FULLINAME ';';
	public Aadl2GrammarAccess.MemoryImplementationElements getMemoryImplementationAccess() {
		return gaAadl2.getMemoryImplementationAccess();
	}
	
	public ParserRule getMemoryImplementationRule() {
		return getMemoryImplementationAccess().getRule();
	}
	
	//BusImplementation aadl2::BusImplementation:
	//	'bus' 'implementation'
	//	ownedRealization=Realization '.' name=INAME
	//	ownedExtension=ImplementationExtension? ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')? ('prototypes' (ownedPrototype+=Prototype+
	//	| noPrototypes?='none' ';'))? ('subcomponents' ((ownedAbstractSubcomponent+=AbstractSubcomponent
	//	| ownedVirtualBusSubcomponent+=VirtualBusSubcomponent)+
	//	| noSubcomponents?='none' ';'))? ('internal' 'features' (ownedEventSource+=EventSource
	//	| ownedEventDataSource+=EventDataSource)+)? ('modes' ((ownedMode+=Mode | ownedModeTransition+=ModeTransition)+
	//	| noModes?='none' ';'))? ('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' FULLINAME ';';
	public Aadl2GrammarAccess.BusImplementationElements getBusImplementationAccess() {
		return gaAadl2.getBusImplementationAccess();
	}
	
	public ParserRule getBusImplementationRule() {
		return getBusImplementationAccess().getRule();
	}
	
	//VirtualBusImplementation aadl2::VirtualBusImplementation:
	//	'virtual' 'bus' 'implementation'
	//	ownedRealization=Realization '.' name=INAME
	//	ownedExtension=ImplementationExtension? ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')? ('prototypes' (ownedPrototype+=Prototype+
	//	| noPrototypes?='none' ';'))? ('subcomponents' ((ownedAbstractSubcomponent+=AbstractSubcomponent
	//	| ownedVirtualBusSubcomponent+=VirtualBusSubcomponent)+
	//	| noSubcomponents?='none' ';'))? ('internal' 'features' (ownedEventSource+=EventSource
	//	| ownedEventDataSource+=EventDataSource)+)? ('modes' ((ownedMode+=Mode | ownedModeTransition+=ModeTransition)+
	//	| noModes?='none' ';'))? ('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' FULLINAME ';';
	public Aadl2GrammarAccess.VirtualBusImplementationElements getVirtualBusImplementationAccess() {
		return gaAadl2.getVirtualBusImplementationAccess();
	}
	
	public ParserRule getVirtualBusImplementationRule() {
		return getVirtualBusImplementationAccess().getRule();
	}
	
	//DataImplementation aadl2::DataImplementation:
	//	{aadl2::DataImplementation}
	//	'data' 'implementation'
	//	ownedRealization=Realization '.' name=INAME
	//	ownedExtension=ImplementationExtension? ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')? ('prototypes' (ownedPrototype+=Prototype+
	//	| noPrototypes?='none' ';'))? ('subcomponents' ((ownedAbstractSubcomponent+=AbstractSubcomponent
	//	| ownedDataSubcomponent+=DataSubcomponent | ownedSubprogramSubcomponent+=SubprogramSubcomponent)+
	//	| noSubcomponents?='none' ';'))? ('internal' 'features' (ownedEventSource+=EventSource
	//	| ownedEventDataSource+=EventDataSource)+)? ('connections' ((ownedAccessConnection+=AccessConnection
	//	| ownedFeatureGroupConnection+=FeatureGroupConnection | ownedFeatureConnection+=FeatureConnection)+
	//	| noConnections?='none' ';'))? ('flows' ((ownedFlowImplementation+=FlowImplementation |
	//	ownedEndToEndFlow+=EndToEndFlow)+ | noFlows?='none' ';'))? ('modes' ((ownedMode+=Mode |
	//	ownedModeTransition+=ModeTransition)+
	//	| noModes?='none' ';'))? ('properties' (ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' FULLINAME ';';
	public Aadl2GrammarAccess.DataImplementationElements getDataImplementationAccess() {
		return gaAadl2.getDataImplementationAccess();
	}
	
	public ParserRule getDataImplementationRule() {
		return getDataImplementationAccess().getRule();
	}
	
	///* subprogram calls */ SubprogramCallSequence aadl2::SubprogramCallSequence:
	//	name=ID ':' '{'
	//	ownedSubprogramCall+=SubprogramCall+
	//	'}' ('{' ownedPropertyAssociation+=PropertyAssociation+ '}')? ('in' 'modes' '(' (inMode+=[aadl2::Mode] (','
	//	inMode+=[aadl2::Mode])*) ')')?
	//	';';
	public Aadl2GrammarAccess.SubprogramCallSequenceElements getSubprogramCallSequenceAccess() {
		return gaAadl2.getSubprogramCallSequenceAccess();
	}
	
	public ParserRule getSubprogramCallSequenceRule() {
		return getSubprogramCallSequenceAccess().getRule();
	}
	
	//SubprogramCall aadl2::SubprogramCall:
	//	{aadl2::SubprogramCall} name=ID ':' 'subprogram' (context=[aadl2::CallContext|PNAME] '.'
	//	calledSubprogram=[aadl2::CalledSubprogram] | calledSubprogram=[aadl2::CalledSubprogram|PNAME] | 'processor' '.'
	//	calledSubprogram=[aadl2::SubprogramProxy]) ('{' ownedPropertyAssociation+=PropertyAssociation+ '}')?
	//	';';
	public Aadl2GrammarAccess.SubprogramCallElements getSubprogramCallAccess() {
		return gaAadl2.getSubprogramCallAccess();
	}
	
	public ParserRule getSubprogramCallRule() {
		return getSubprogramCallAccess().getRule();
	}
	
	////******* Prototypes
	//Prototype aadl2::Prototype:
	//	ComponentPrototype | FeatureGroupPrototype | FeaturePrototype;
	public Aadl2GrammarAccess.PrototypeElements getPrototypeAccess() {
		return gaAadl2.getPrototypeAccess();
	}
	
	public ParserRule getPrototypeRule() {
		return getPrototypeAccess().getRule();
	}
	
	//ComponentPrototype aadl2::ComponentPrototype:
	//	AbstractPrototype | BusPrototype | DevicePrototype | MemoryPrototype | ProcessPrototype | ProcessorPrototype |
	//	SubprogramPrototype | SubprogramGroupPrototype | ThreadPrototype | ThreadGroupPrototype | VirtualBusPrototype |
	//	VirtualProcessorPrototype | SystemPrototype | DataPrototype;
	public Aadl2GrammarAccess.ComponentPrototypeElements getComponentPrototypeAccess() {
		return gaAadl2.getComponentPrototypeAccess();
	}
	
	public ParserRule getComponentPrototypeRule() {
		return getComponentPrototypeAccess().getRule();
	}
	
	//AbstractPrototype aadl2::AbstractPrototype:
	//	(name=ID ':' | refined=[aadl2::ComponentPrototype|REFINEDNAME] ':' 'refined' 'to')
	//	'abstract'
	//	constrainingClassifier=[aadl2::ComponentClassifier|QCREF]? (array?='[' ']')? ('{'
	//	ownedPropertyAssociation+=PropertyAssociation+ '}')?
	//	';';
	public Aadl2GrammarAccess.AbstractPrototypeElements getAbstractPrototypeAccess() {
		return gaAadl2.getAbstractPrototypeAccess();
	}
	
	public ParserRule getAbstractPrototypeRule() {
		return getAbstractPrototypeAccess().getRule();
	}
	
	//BusPrototype aadl2::BusPrototype:
	//	(name=ID ':' | refined=[aadl2::ComponentPrototype|REFINEDNAME] ':' 'refined' 'to')
	//	'bus'
	//	constrainingClassifier=[aadl2::ComponentClassifier|QCREF]? (array?='[' ']')? ('{'
	//	ownedPropertyAssociation+=PropertyAssociation+ '}')?
	//	';';
	public Aadl2GrammarAccess.BusPrototypeElements getBusPrototypeAccess() {
		return gaAadl2.getBusPrototypeAccess();
	}
	
	public ParserRule getBusPrototypeRule() {
		return getBusPrototypeAccess().getRule();
	}
	
	//DataPrototype aadl2::DataPrototype:
	//	(name=ID ':' | refined=[aadl2::ComponentPrototype|REFINEDNAME] ':' 'refined' 'to')
	//	'data'
	//	constrainingClassifier=[aadl2::ComponentClassifier|QCREF]? (array?='[' ']')? ('{'
	//	ownedPropertyAssociation+=PropertyAssociation+ '}')?
	//	';';
	public Aadl2GrammarAccess.DataPrototypeElements getDataPrototypeAccess() {
		return gaAadl2.getDataPrototypeAccess();
	}
	
	public ParserRule getDataPrototypeRule() {
		return getDataPrototypeAccess().getRule();
	}
	
	//DevicePrototype aadl2::DevicePrototype:
	//	(name=ID ':' | refined=[aadl2::ComponentPrototype|REFINEDNAME] ':' 'refined' 'to')
	//	'device'
	//	constrainingClassifier=[aadl2::ComponentClassifier|QCREF]? (array?='[' ']')? ('{'
	//	ownedPropertyAssociation+=PropertyAssociation+ '}')?
	//	';';
	public Aadl2GrammarAccess.DevicePrototypeElements getDevicePrototypeAccess() {
		return gaAadl2.getDevicePrototypeAccess();
	}
	
	public ParserRule getDevicePrototypeRule() {
		return getDevicePrototypeAccess().getRule();
	}
	
	//MemoryPrototype aadl2::MemoryPrototype:
	//	(name=ID ':' | refined=[aadl2::ComponentPrototype|REFINEDNAME] ':' 'refined' 'to')
	//	'memory'
	//	constrainingClassifier=[aadl2::ComponentClassifier|QCREF]? (array?='[' ']')? ('{'
	//	ownedPropertyAssociation+=PropertyAssociation+ '}')?
	//	';';
	public Aadl2GrammarAccess.MemoryPrototypeElements getMemoryPrototypeAccess() {
		return gaAadl2.getMemoryPrototypeAccess();
	}
	
	public ParserRule getMemoryPrototypeRule() {
		return getMemoryPrototypeAccess().getRule();
	}
	
	//ProcessPrototype aadl2::ProcessPrototype:
	//	(name=ID ':' | refined=[aadl2::ComponentPrototype|REFINEDNAME] ':' 'refined' 'to')
	//	'process'
	//	constrainingClassifier=[aadl2::ComponentClassifier|QCREF]? (array?='[' ']')? ('{'
	//	ownedPropertyAssociation+=PropertyAssociation+ '}')?
	//	';';
	public Aadl2GrammarAccess.ProcessPrototypeElements getProcessPrototypeAccess() {
		return gaAadl2.getProcessPrototypeAccess();
	}
	
	public ParserRule getProcessPrototypeRule() {
		return getProcessPrototypeAccess().getRule();
	}
	
	//ProcessorPrototype aadl2::ProcessorPrototype:
	//	(name=ID ':' | refined=[aadl2::ComponentPrototype|REFINEDNAME] ':' 'refined' 'to')
	//	'processor'
	//	constrainingClassifier=[aadl2::ComponentClassifier|QCREF]? (array?='[' ']')? ('{'
	//	ownedPropertyAssociation+=PropertyAssociation+ '}')?
	//	';';
	public Aadl2GrammarAccess.ProcessorPrototypeElements getProcessorPrototypeAccess() {
		return gaAadl2.getProcessorPrototypeAccess();
	}
	
	public ParserRule getProcessorPrototypeRule() {
		return getProcessorPrototypeAccess().getRule();
	}
	
	//SubprogramPrototype aadl2::SubprogramPrototype:
	//	(name=ID ':' | refined=[aadl2::ComponentPrototype|REFINEDNAME] ':' 'refined' 'to')
	//	'subprogram'
	//	constrainingClassifier=[aadl2::ComponentClassifier|QCREF]? (array?='[' ']')? ('{'
	//	ownedPropertyAssociation+=PropertyAssociation+ '}')?
	//	';';
	public Aadl2GrammarAccess.SubprogramPrototypeElements getSubprogramPrototypeAccess() {
		return gaAadl2.getSubprogramPrototypeAccess();
	}
	
	public ParserRule getSubprogramPrototypeRule() {
		return getSubprogramPrototypeAccess().getRule();
	}
	
	//SubprogramGroupPrototype aadl2::SubprogramGroupPrototype:
	//	(name=ID ':' | refined=[aadl2::ComponentPrototype|REFINEDNAME] ':' 'refined' 'to')
	//	'subprogram' 'group'
	//	constrainingClassifier=[aadl2::ComponentClassifier|QCREF]? (array?='[' ']')? ('{'
	//	ownedPropertyAssociation+=PropertyAssociation+ '}')?
	//	';';
	public Aadl2GrammarAccess.SubprogramGroupPrototypeElements getSubprogramGroupPrototypeAccess() {
		return gaAadl2.getSubprogramGroupPrototypeAccess();
	}
	
	public ParserRule getSubprogramGroupPrototypeRule() {
		return getSubprogramGroupPrototypeAccess().getRule();
	}
	
	//SystemPrototype aadl2::SystemPrototype:
	//	(name=ID ':' | refined=[aadl2::ComponentPrototype|REFINEDNAME] ':' 'refined' 'to')
	//	'system'
	//	constrainingClassifier=[aadl2::ComponentClassifier|QCREF]? (array?='[' ']')? ('{'
	//	ownedPropertyAssociation+=PropertyAssociation+ '}')?
	//	';';
	public Aadl2GrammarAccess.SystemPrototypeElements getSystemPrototypeAccess() {
		return gaAadl2.getSystemPrototypeAccess();
	}
	
	public ParserRule getSystemPrototypeRule() {
		return getSystemPrototypeAccess().getRule();
	}
	
	//ThreadPrototype aadl2::ThreadPrototype:
	//	(name=ID ':' | refined=[aadl2::ComponentPrototype|REFINEDNAME] ':' 'refined' 'to')
	//	'thread'
	//	constrainingClassifier=[aadl2::ComponentClassifier|QCREF]? (array?='[' ']')? ('{'
	//	ownedPropertyAssociation+=PropertyAssociation+ '}')?
	//	';';
	public Aadl2GrammarAccess.ThreadPrototypeElements getThreadPrototypeAccess() {
		return gaAadl2.getThreadPrototypeAccess();
	}
	
	public ParserRule getThreadPrototypeRule() {
		return getThreadPrototypeAccess().getRule();
	}
	
	//ThreadGroupPrototype aadl2::ThreadGroupPrototype:
	//	(name=ID ':' | refined=[aadl2::ComponentPrototype|REFINEDNAME] ':' 'refined' 'to')
	//	'thread' 'group'
	//	constrainingClassifier=[aadl2::ComponentClassifier|QCREF]? (array?='[' ']')? ('{'
	//	ownedPropertyAssociation+=PropertyAssociation+ '}')?
	//	';';
	public Aadl2GrammarAccess.ThreadGroupPrototypeElements getThreadGroupPrototypeAccess() {
		return gaAadl2.getThreadGroupPrototypeAccess();
	}
	
	public ParserRule getThreadGroupPrototypeRule() {
		return getThreadGroupPrototypeAccess().getRule();
	}
	
	//VirtualBusPrototype aadl2::VirtualBusPrototype:
	//	(name=ID ':' | refined=[aadl2::ComponentPrototype|REFINEDNAME] ':' 'refined' 'to')
	//	'virtual' 'bus'
	//	constrainingClassifier=[aadl2::ComponentClassifier|QCREF]? (array?='[' ']')? ('{'
	//	ownedPropertyAssociation+=PropertyAssociation+ '}')?
	//	';';
	public Aadl2GrammarAccess.VirtualBusPrototypeElements getVirtualBusPrototypeAccess() {
		return gaAadl2.getVirtualBusPrototypeAccess();
	}
	
	public ParserRule getVirtualBusPrototypeRule() {
		return getVirtualBusPrototypeAccess().getRule();
	}
	
	//VirtualProcessorPrototype aadl2::VirtualProcessorPrototype:
	//	(name=ID ':' | refined=[aadl2::ComponentPrototype|REFINEDNAME] ':' 'refined' 'to')
	//	'virtual' 'processor'
	//	constrainingClassifier=[aadl2::ComponentClassifier|QCREF]? (array?='[' ']')? ('{'
	//	ownedPropertyAssociation+=PropertyAssociation+ '}')?
	//	';';
	public Aadl2GrammarAccess.VirtualProcessorPrototypeElements getVirtualProcessorPrototypeAccess() {
		return gaAadl2.getVirtualProcessorPrototypeAccess();
	}
	
	public ParserRule getVirtualProcessorPrototypeRule() {
		return getVirtualProcessorPrototypeAccess().getRule();
	}
	
	//FeatureGroupPrototype aadl2::FeatureGroupPrototype:
	//	(name=ID ':' | refined=[aadl2::FeatureGroupPrototype|REFINEDNAME] ':' 'refined' 'to')
	//	'feature' 'group'
	//	constrainingFeatureGroupType=[aadl2::FeatureGroupType|QCREF]? ('{' ownedPropertyAssociation+=PropertyAssociation+
	//	'}')?
	//	';';
	public Aadl2GrammarAccess.FeatureGroupPrototypeElements getFeatureGroupPrototypeAccess() {
		return gaAadl2.getFeatureGroupPrototypeAccess();
	}
	
	public ParserRule getFeatureGroupPrototypeRule() {
		return getFeatureGroupPrototypeAccess().getRule();
	}
	
	//FeaturePrototype aadl2::FeaturePrototype:
	//	(name=ID ':' | refined=[aadl2::FeaturePrototype|REFINEDNAME] ':' 'refined' 'to') (in?='in' | out?='out')? // only in or out not inout
	//	'feature'
	//	constrainingClassifier=[aadl2::ComponentClassifier|QCREF]? ('{' ownedPropertyAssociation+=PropertyAssociation+ '}')?
	//	';';
	public Aadl2GrammarAccess.FeaturePrototypeElements getFeaturePrototypeAccess() {
		return gaAadl2.getFeaturePrototypeAccess();
	}
	
	public ParserRule getFeaturePrototypeRule() {
		return getFeaturePrototypeAccess().getRule();
	}
	
	///*
	// * Prototype Bindings
	// */ PrototypeBinding aadl2::PrototypeBinding:
	//	FeatureGroupPrototypeBinding | FeaturePrototypeBinding | ComponentPrototypeBinding;
	public Aadl2GrammarAccess.PrototypeBindingElements getPrototypeBindingAccess() {
		return gaAadl2.getPrototypeBindingAccess();
	}
	
	public ParserRule getPrototypeBindingRule() {
		return getPrototypeBindingAccess().getRule();
	}
	
	//FeatureGroupPrototypeBinding aadl2::FeatureGroupPrototypeBinding:
	//	formal=[aadl2::Prototype] '=>' 'feature' 'group'
	//	actual=FeatureGroupPrototypeActual;
	public Aadl2GrammarAccess.FeatureGroupPrototypeBindingElements getFeatureGroupPrototypeBindingAccess() {
		return gaAadl2.getFeatureGroupPrototypeBindingAccess();
	}
	
	public ParserRule getFeatureGroupPrototypeBindingRule() {
		return getFeatureGroupPrototypeBindingAccess().getRule();
	}
	
	//FeatureGroupPrototypeActual aadl2::FeatureGroupPrototypeActual:
	//	featureType=[aadl2::FeatureType|QCREF] ('(' binding+=PrototypeBinding (',' binding+=PrototypeBinding)* ')')?;
	public Aadl2GrammarAccess.FeatureGroupPrototypeActualElements getFeatureGroupPrototypeActualAccess() {
		return gaAadl2.getFeatureGroupPrototypeActualAccess();
	}
	
	public ParserRule getFeatureGroupPrototypeActualRule() {
		return getFeatureGroupPrototypeActualAccess().getRule();
	}
	
	//FeaturePrototypeBinding aadl2::FeaturePrototypeBinding:
	//	formal=[aadl2::Prototype] '=>'
	//	actual=(PortSpecification | AccessSpecification | FeaturePrototypeReference);
	public Aadl2GrammarAccess.FeaturePrototypeBindingElements getFeaturePrototypeBindingAccess() {
		return gaAadl2.getFeaturePrototypeBindingAccess();
	}
	
	public ParserRule getFeaturePrototypeBindingRule() {
		return getFeaturePrototypeBindingAccess().getRule();
	}
	
	//PortSpecification aadl2::PortSpecification:
	//	(in?='in' out?='out'? | out?='out') category=PortCategory 'port' classifier=[aadl2::ComponentClassifier|QCREF]?;
	public Aadl2GrammarAccess.PortSpecificationElements getPortSpecificationAccess() {
		return gaAadl2.getPortSpecificationAccess();
	}
	
	public ParserRule getPortSpecificationRule() {
		return getPortSpecificationAccess().getRule();
	}
	
	//AccessSpecification aadl2::AccessSpecification:
	//	kind=AccessDirection category=AccessCategory 'access' classifier=[aadl2::ComponentClassifier|QCREF]?;
	public Aadl2GrammarAccess.AccessSpecificationElements getAccessSpecificationAccess() {
		return gaAadl2.getAccessSpecificationAccess();
	}
	
	public ParserRule getAccessSpecificationRule() {
		return getAccessSpecificationAccess().getRule();
	}
	
	//FeaturePrototypeReference aadl2::FeaturePrototypeReference:
	//	(in?='in' | out?='out')? 'feature' prototype=[aadl2::FeaturePrototype];
	public Aadl2GrammarAccess.FeaturePrototypeReferenceElements getFeaturePrototypeReferenceAccess() {
		return gaAadl2.getFeaturePrototypeReferenceAccess();
	}
	
	public ParserRule getFeaturePrototypeReferenceRule() {
		return getFeaturePrototypeReferenceAccess().getRule();
	}
	
	//ComponentPrototypeBinding aadl2::ComponentPrototypeBinding:
	//	formal=[aadl2::Prototype] '=>' (actual+=ComponentReference |
	//	'(' actual+=ComponentReference (',' actual+=ComponentReference)* ')');
	public Aadl2GrammarAccess.ComponentPrototypeBindingElements getComponentPrototypeBindingAccess() {
		return gaAadl2.getComponentPrototypeBindingAccess();
	}
	
	public ParserRule getComponentPrototypeBindingRule() {
		return getComponentPrototypeBindingAccess().getRule();
	}
	
	//ComponentReference aadl2::ComponentPrototypeActual:
	//	category=ComponentCategory subcomponentType=[aadl2::SubcomponentType|QCREF] ('(' binding+=PrototypeBinding (','
	//	binding+=PrototypeBinding)* ')')?;
	public Aadl2GrammarAccess.ComponentReferenceElements getComponentReferenceAccess() {
		return gaAadl2.getComponentReferenceAccess();
	}
	
	public ParserRule getComponentReferenceRule() {
		return getComponentReferenceAccess().getRule();
	}
	
	///*
	// * Subcomponents
	// */ AbstractSubcomponent aadl2::AbstractSubcomponent:
	//	(name=ID ':' | refined=[aadl2::AbstractSubcomponent|REFINEDNAME] ':' 'refined' 'to')
	//	'abstract' (abstractSubcomponentType=[aadl2::AbstractSubcomponentType|QCREF] ('('
	//	ownedPrototypeBinding+=PrototypeBinding (',' ownedPrototypeBinding+=PrototypeBinding)* ')')?)?
	//	(arrayDimension+=ArrayDimension+ ('(' implementationReference+=ComponentImplementationReference (','
	//	implementationReference+=ComponentImplementationReference)*
	//	')')?)? ('{' ownedPropertyAssociation+=ContainedPropertyAssociation+ '}')? ('in' 'modes' '('
	//	(ownedModeBinding+=ModeRef (',' ownedModeBinding+=ModeRef)*) ')')?
	//	';';
	public Aadl2GrammarAccess.AbstractSubcomponentElements getAbstractSubcomponentAccess() {
		return gaAadl2.getAbstractSubcomponentAccess();
	}
	
	public ParserRule getAbstractSubcomponentRule() {
		return getAbstractSubcomponentAccess().getRule();
	}
	
	//SystemSubcomponent aadl2::SystemSubcomponent:
	//	(name=ID ':' | refined=[aadl2::Subcomponent|REFINEDNAME] ':' 'refined' 'to')
	//	'system' (systemSubcomponentType=[aadl2::SystemSubcomponentType|QCREF] ('(' ownedPrototypeBinding+=PrototypeBinding
	//	(',' ownedPrototypeBinding+=PrototypeBinding)* ')')?)? (arrayDimension+=ArrayDimension+ ('('
	//	implementationReference+=ComponentImplementationReference (','
	//	implementationReference+=ComponentImplementationReference)*
	//	')')?)? ('{' ownedPropertyAssociation+=ContainedPropertyAssociation+ '}')? ('in' 'modes' '('
	//	(ownedModeBinding+=ModeRef (',' ownedModeBinding+=ModeRef)*) ')')?
	//	';';
	public Aadl2GrammarAccess.SystemSubcomponentElements getSystemSubcomponentAccess() {
		return gaAadl2.getSystemSubcomponentAccess();
	}
	
	public ParserRule getSystemSubcomponentRule() {
		return getSystemSubcomponentAccess().getRule();
	}
	
	//ProcessSubcomponent aadl2::ProcessSubcomponent:
	//	(name=ID ':' | refined=[aadl2::Subcomponent|REFINEDNAME] ':' 'refined' 'to')
	//	'process' (processSubcomponentType=[aadl2::ProcessSubcomponentType|QCREF] ('(' ownedPrototypeBinding+=PrototypeBinding
	//	(',' ownedPrototypeBinding+=PrototypeBinding)* ')')?)? (arrayDimension+=ArrayDimension+ ('('
	//	implementationReference+=ComponentImplementationReference (','
	//	implementationReference+=ComponentImplementationReference)*
	//	')')?)? ('{' ownedPropertyAssociation+=ContainedPropertyAssociation+ '}')? ('in' 'modes' '('
	//	(ownedModeBinding+=ModeRef (',' ownedModeBinding+=ModeRef)*) ')')?
	//	';';
	public Aadl2GrammarAccess.ProcessSubcomponentElements getProcessSubcomponentAccess() {
		return gaAadl2.getProcessSubcomponentAccess();
	}
	
	public ParserRule getProcessSubcomponentRule() {
		return getProcessSubcomponentAccess().getRule();
	}
	
	//ThreadGroupSubcomponent aadl2::ThreadGroupSubcomponent:
	//	(name=ID ':' | refined=[aadl2::Subcomponent|REFINEDNAME] ':' 'refined' 'to')
	//	'thread' 'group' (threadGroupSubcomponentType=[aadl2::ThreadGroupSubcomponentType|QCREF] ('('
	//	ownedPrototypeBinding+=PrototypeBinding (',' ownedPrototypeBinding+=PrototypeBinding)* ')')?)?
	//	(arrayDimension+=ArrayDimension+ ('(' implementationReference+=ComponentImplementationReference (','
	//	implementationReference+=ComponentImplementationReference)*
	//	')')?)? ('{' ownedPropertyAssociation+=ContainedPropertyAssociation+ '}')? ('in' 'modes' '('
	//	(ownedModeBinding+=ModeRef (',' ownedModeBinding+=ModeRef)*) ')')?
	//	';';
	public Aadl2GrammarAccess.ThreadGroupSubcomponentElements getThreadGroupSubcomponentAccess() {
		return gaAadl2.getThreadGroupSubcomponentAccess();
	}
	
	public ParserRule getThreadGroupSubcomponentRule() {
		return getThreadGroupSubcomponentAccess().getRule();
	}
	
	//ThreadSubcomponent aadl2::ThreadSubcomponent:
	//	(name=ID ':' | refined=[aadl2::Subcomponent|REFINEDNAME] ':' 'refined' 'to')
	//	'thread' (threadSubcomponentType=[aadl2::ThreadSubcomponentType|QCREF] ('(' ownedPrototypeBinding+=PrototypeBinding
	//	(',' ownedPrototypeBinding+=PrototypeBinding)* ')')?)? (arrayDimension+=ArrayDimension+ ('('
	//	implementationReference+=ComponentImplementationReference (','
	//	implementationReference+=ComponentImplementationReference)*
	//	')')?)? ('{' ownedPropertyAssociation+=ContainedPropertyAssociation+ '}')? ('in' 'modes' '('
	//	(ownedModeBinding+=ModeRef (',' ownedModeBinding+=ModeRef)*) ')')?
	//	';';
	public Aadl2GrammarAccess.ThreadSubcomponentElements getThreadSubcomponentAccess() {
		return gaAadl2.getThreadSubcomponentAccess();
	}
	
	public ParserRule getThreadSubcomponentRule() {
		return getThreadSubcomponentAccess().getRule();
	}
	
	//SubprogramSubcomponent aadl2::SubprogramSubcomponent:
	//	(name=ID ':' | refined=[aadl2::Subcomponent|REFINEDNAME] ':' 'refined' 'to')
	//	'subprogram' (subprogramSubcomponentType=[aadl2::SubprogramSubcomponentType|QCREF] ('('
	//	ownedPrototypeBinding+=PrototypeBinding (',' ownedPrototypeBinding+=PrototypeBinding)* ')')?)?
	//	(arrayDimension+=ArrayDimension+ ('(' implementationReference+=ComponentImplementationReference (','
	//	implementationReference+=ComponentImplementationReference)*
	//	')')?)? ('{' ownedPropertyAssociation+=ContainedPropertyAssociation+ '}')? ('in' 'modes' '('
	//	(ownedModeBinding+=ModeRef (',' ownedModeBinding+=ModeRef)*) ')')?
	//	';';
	public Aadl2GrammarAccess.SubprogramSubcomponentElements getSubprogramSubcomponentAccess() {
		return gaAadl2.getSubprogramSubcomponentAccess();
	}
	
	public ParserRule getSubprogramSubcomponentRule() {
		return getSubprogramSubcomponentAccess().getRule();
	}
	
	//SubprogramGroupSubcomponent aadl2::SubprogramGroupSubcomponent:
	//	(name=ID ':' | refined=[aadl2::Subcomponent|REFINEDNAME] ':' 'refined' 'to')
	//	'subprogram' 'group' (subprogramGroupSubcomponentType=[aadl2::SubprogramGroupSubcomponentType|QCREF] ('('
	//	ownedPrototypeBinding+=PrototypeBinding (',' ownedPrototypeBinding+=PrototypeBinding)* ')')?)?
	//	(arrayDimension+=ArrayDimension+ ('(' implementationReference+=ComponentImplementationReference (','
	//	implementationReference+=ComponentImplementationReference)*
	//	')')?)? ('{' ownedPropertyAssociation+=ContainedPropertyAssociation+ '}')? ('in' 'modes' '('
	//	(ownedModeBinding+=ModeRef (',' ownedModeBinding+=ModeRef)*) ')')?
	//	';';
	public Aadl2GrammarAccess.SubprogramGroupSubcomponentElements getSubprogramGroupSubcomponentAccess() {
		return gaAadl2.getSubprogramGroupSubcomponentAccess();
	}
	
	public ParserRule getSubprogramGroupSubcomponentRule() {
		return getSubprogramGroupSubcomponentAccess().getRule();
	}
	
	//ProcessorSubcomponent aadl2::ProcessorSubcomponent:
	//	(name=ID ':' | refined=[aadl2::Subcomponent|REFINEDNAME] ':' 'refined' 'to')
	//	'processor' (processorSubcomponentType=[aadl2::ProcessorSubcomponentType|QCREF] ('('
	//	ownedPrototypeBinding+=PrototypeBinding (',' ownedPrototypeBinding+=PrototypeBinding)* ')')?)?
	//	(arrayDimension+=ArrayDimension+ ('(' implementationReference+=ComponentImplementationReference (','
	//	implementationReference+=ComponentImplementationReference)*
	//	')')?)? ('{' ownedPropertyAssociation+=ContainedPropertyAssociation+ '}')? ('in' 'modes' '('
	//	(ownedModeBinding+=ModeRef (',' ownedModeBinding+=ModeRef)*) ')')?
	//	';';
	public Aadl2GrammarAccess.ProcessorSubcomponentElements getProcessorSubcomponentAccess() {
		return gaAadl2.getProcessorSubcomponentAccess();
	}
	
	public ParserRule getProcessorSubcomponentRule() {
		return getProcessorSubcomponentAccess().getRule();
	}
	
	//VirtualProcessorSubcomponent aadl2::VirtualProcessorSubcomponent:
	//	(name=ID ':' | refined=[aadl2::Subcomponent|REFINEDNAME] ':' 'refined' 'to')
	//	'virtual' 'processor' (virtualProcessorSubcomponentType=[aadl2::VirtualProcessorSubcomponentType|QCREF] ('('
	//	ownedPrototypeBinding+=PrototypeBinding (',' ownedPrototypeBinding+=PrototypeBinding)* ')')?)?
	//	(arrayDimension+=ArrayDimension+ ('(' implementationReference+=ComponentImplementationReference (','
	//	implementationReference+=ComponentImplementationReference)*
	//	')')?)? ('{' ownedPropertyAssociation+=ContainedPropertyAssociation+ '}')? ('in' 'modes' '('
	//	(ownedModeBinding+=ModeRef (',' ownedModeBinding+=ModeRef)*) ')')?
	//	';';
	public Aadl2GrammarAccess.VirtualProcessorSubcomponentElements getVirtualProcessorSubcomponentAccess() {
		return gaAadl2.getVirtualProcessorSubcomponentAccess();
	}
	
	public ParserRule getVirtualProcessorSubcomponentRule() {
		return getVirtualProcessorSubcomponentAccess().getRule();
	}
	
	//DeviceSubcomponent aadl2::DeviceSubcomponent:
	//	(name=ID ':' | refined=[aadl2::Subcomponent|REFINEDNAME] ':' 'refined' 'to')
	//	'device' (deviceSubcomponentType=[aadl2::DeviceSubcomponentType|QCREF] ('(' ownedPrototypeBinding+=PrototypeBinding
	//	(',' ownedPrototypeBinding+=PrototypeBinding)* ')')?)? (arrayDimension+=ArrayDimension+ ('('
	//	implementationReference+=ComponentImplementationReference (','
	//	implementationReference+=ComponentImplementationReference)*
	//	')')?)? ('{' ownedPropertyAssociation+=ContainedPropertyAssociation+ '}')? ('in' 'modes' '('
	//	(ownedModeBinding+=ModeRef (',' ownedModeBinding+=ModeRef)*) ')')?
	//	';';
	public Aadl2GrammarAccess.DeviceSubcomponentElements getDeviceSubcomponentAccess() {
		return gaAadl2.getDeviceSubcomponentAccess();
	}
	
	public ParserRule getDeviceSubcomponentRule() {
		return getDeviceSubcomponentAccess().getRule();
	}
	
	//MemorySubcomponent aadl2::MemorySubcomponent:
	//	(name=ID ':' | refined=[aadl2::Subcomponent|REFINEDNAME] ':' 'refined' 'to')
	//	'memory' (memorySubcomponentType=[aadl2::MemorySubcomponentType|QCREF] ('(' ownedPrototypeBinding+=PrototypeBinding
	//	(',' ownedPrototypeBinding+=PrototypeBinding)* ')')?)? (arrayDimension+=ArrayDimension+ ('('
	//	implementationReference+=ComponentImplementationReference (','
	//	implementationReference+=ComponentImplementationReference)*
	//	')')?)? ('{' ownedPropertyAssociation+=ContainedPropertyAssociation+ '}')? ('in' 'modes' '('
	//	(ownedModeBinding+=ModeRef (',' ownedModeBinding+=ModeRef)*) ')')?
	//	';';
	public Aadl2GrammarAccess.MemorySubcomponentElements getMemorySubcomponentAccess() {
		return gaAadl2.getMemorySubcomponentAccess();
	}
	
	public ParserRule getMemorySubcomponentRule() {
		return getMemorySubcomponentAccess().getRule();
	}
	
	//BusSubcomponent aadl2::BusSubcomponent:
	//	(name=ID ':' | refined=[aadl2::Subcomponent|REFINEDNAME] ':' 'refined' 'to')
	//	'bus' (busSubcomponentType=[aadl2::BusSubcomponentType|QCREF] ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')?)? (arrayDimension+=ArrayDimension+ ('('
	//	implementationReference+=ComponentImplementationReference (','
	//	implementationReference+=ComponentImplementationReference)*
	//	')')?)? ('{' ownedPropertyAssociation+=ContainedPropertyAssociation+ '}')? ('in' 'modes' '('
	//	(ownedModeBinding+=ModeRef (',' ownedModeBinding+=ModeRef)*) ')')?
	//	';';
	public Aadl2GrammarAccess.BusSubcomponentElements getBusSubcomponentAccess() {
		return gaAadl2.getBusSubcomponentAccess();
	}
	
	public ParserRule getBusSubcomponentRule() {
		return getBusSubcomponentAccess().getRule();
	}
	
	//VirtualBusSubcomponent aadl2::VirtualBusSubcomponent:
	//	(name=ID ':' | refined=[aadl2::Subcomponent|REFINEDNAME] ':' 'refined' 'to')
	//	'virtual' 'bus' (virtualBusSubcomponentType=[aadl2::VirtualBusClassifier|QCREF] ('('
	//	ownedPrototypeBinding+=PrototypeBinding (',' ownedPrototypeBinding+=PrototypeBinding)* ')')?)?
	//	(arrayDimension+=ArrayDimension+ ('(' implementationReference+=ComponentImplementationReference (','
	//	implementationReference+=ComponentImplementationReference)*
	//	')')?)? ('{' ownedPropertyAssociation+=ContainedPropertyAssociation+ '}')? ('in' 'modes' '('
	//	(ownedModeBinding+=ModeRef (',' ownedModeBinding+=ModeRef)*) ')')?
	//	';';
	public Aadl2GrammarAccess.VirtualBusSubcomponentElements getVirtualBusSubcomponentAccess() {
		return gaAadl2.getVirtualBusSubcomponentAccess();
	}
	
	public ParserRule getVirtualBusSubcomponentRule() {
		return getVirtualBusSubcomponentAccess().getRule();
	}
	
	//DataSubcomponent aadl2::DataSubcomponent:
	//	(name=ID ':' | refined=[aadl2::Subcomponent|REFINEDNAME] ':' 'refined' 'to')
	//	'data' (dataSubcomponentType=[aadl2::DataSubcomponentType|QCREF] ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')?)? (arrayDimension+=ArrayDimension+ ('('
	//	implementationReference+=ComponentImplementationReference (','
	//	implementationReference+=ComponentImplementationReference)*
	//	')')?)? ('{' ownedPropertyAssociation+=ContainedPropertyAssociation+ '}')? ('in' 'modes' '('
	//	(ownedModeBinding+=ModeRef (',' ownedModeBinding+=ModeRef)*) ')')?
	//	';';
	public Aadl2GrammarAccess.DataSubcomponentElements getDataSubcomponentAccess() {
		return gaAadl2.getDataSubcomponentAccess();
	}
	
	public ParserRule getDataSubcomponentRule() {
		return getDataSubcomponentAccess().getRule();
	}
	
	//ArrayDimension aadl2::ArrayDimension:
	//	{aadl2::ArrayDimension} '[' size=ArraySize? ']';
	public Aadl2GrammarAccess.ArrayDimensionElements getArrayDimensionAccess() {
		return gaAadl2.getArrayDimensionAccess();
	}
	
	public ParserRule getArrayDimensionRule() {
		return getArrayDimensionAccess().getRule();
	}
	
	//ArraySize aadl2::ArraySize:
	//	size=INTVALUE | sizeProperty=[aadl2::ArraySizeProperty|QPREF];
	public Aadl2GrammarAccess.ArraySizeElements getArraySizeAccess() {
		return gaAadl2.getArraySizeAccess();
	}
	
	public ParserRule getArraySizeRule() {
		return getArraySizeAccess().getRule();
	}
	
	//ComponentImplementationReference aadl2::ComponentImplementationReference:
	//	{aadl2::ComponentImplementationReference} implementation=[aadl2::ComponentImplementation|QCREF] ('('
	//	ownedPrototypeBinding+=PrototypeBinding (',' ownedPrototypeBinding+=PrototypeBinding)* ')')?;
	public Aadl2GrammarAccess.ComponentImplementationReferenceElements getComponentImplementationReferenceAccess() {
		return gaAadl2.getComponentImplementationReferenceAccess();
	}
	
	public ParserRule getComponentImplementationReferenceRule() {
		return getComponentImplementationReferenceAccess().getRule();
	}
	
	//// ******** Features
	//DataPort aadl2::DataPort:
	//	{aadl2::DataPort} (name=ID ':' | refined=[aadl2::Feature|REFINEDNAME] ':' 'refined' 'to') (in?='in' out?='out'? |
	//	out?='out')
	//	'data' 'port' dataFeatureClassifier=[aadl2::DataSubcomponentType|QCREF]?
	//	arrayDimension+=ArrayDimension? ('{' ownedPropertyAssociation+=PropertyAssociation+ '}')?
	//	';';
	public Aadl2GrammarAccess.DataPortElements getDataPortAccess() {
		return gaAadl2.getDataPortAccess();
	}
	
	public ParserRule getDataPortRule() {
		return getDataPortAccess().getRule();
	}
	
	//EventDataPort aadl2::EventDataPort:
	//	{aadl2::EventDataPort} (name=ID ':' | refined=[aadl2::Feature|REFINEDNAME] ':' 'refined' 'to') (in?='in' out?='out'? |
	//	out?='out')
	//	'event' 'data' 'port' dataFeatureClassifier=[aadl2::DataSubcomponentType|QCREF]?
	//	arrayDimension+=ArrayDimension? ('{' ownedPropertyAssociation+=PropertyAssociation+ '}')?
	//	';';
	public Aadl2GrammarAccess.EventDataPortElements getEventDataPortAccess() {
		return gaAadl2.getEventDataPortAccess();
	}
	
	public ParserRule getEventDataPortRule() {
		return getEventDataPortAccess().getRule();
	}
	
	//EventPort aadl2::EventPort:
	//	{aadl2::EventPort} (name=ID ':' | refined=[aadl2::Feature|REFINEDNAME] ':' 'refined' 'to') (in?='in' out?='out'? |
	//	out?='out')
	//	'event' 'port'
	//	arrayDimension+=ArrayDimension? ('{' ownedPropertyAssociation+=PropertyAssociation+ '}')?
	//	';';
	public Aadl2GrammarAccess.EventPortElements getEventPortAccess() {
		return gaAadl2.getEventPortAccess();
	}
	
	public ParserRule getEventPortRule() {
		return getEventPortAccess().getRule();
	}
	
	//FeatureGroup aadl2::FeatureGroup:
	//	(name=ID ':' | refined=[aadl2::Feature|REFINEDNAME] ':' 'refined' 'to') (in?='in' | out?='out')? 'feature' 'group'
	//	((inverse?='inverse' 'of')?
	//	featureType=[aadl2::FeatureType|QCREF])?
	//	arrayDimension+=ArrayDimension? ('{' ownedPropertyAssociation+=ContainedPropertyAssociation+ '}')? ';';
	public Aadl2GrammarAccess.FeatureGroupElements getFeatureGroupAccess() {
		return gaAadl2.getFeatureGroupAccess();
	}
	
	public ParserRule getFeatureGroupRule() {
		return getFeatureGroupAccess().getRule();
	}
	
	//Parameter aadl2::Parameter:
	//	(name=ID ':' | refined=[aadl2::Feature|REFINEDNAME] ':' 'refined' 'to') (in?='in' out?='out'? | out?='out')
	//	'parameter' dataFeatureClassifier=[aadl2::DataSubcomponentType|QCREF]?
	//	arrayDimension+=ArrayDimension? ('{' ownedPropertyAssociation+=PropertyAssociation+ '}')? ';';
	public Aadl2GrammarAccess.ParameterElements getParameterAccess() {
		return gaAadl2.getParameterAccess();
	}
	
	public ParserRule getParameterRule() {
		return getParameterAccess().getRule();
	}
	
	//// AccessCategory not set (is encoded in type)
	//SubprogramAccess aadl2::SubprogramAccess:
	//	(name=ID ':' | refined=[aadl2::Feature|REFINEDNAME] ':' 'refined' 'to') kind=AccessDirection
	//	'subprogram' 'access' subprogramFeatureClassifier=[aadl2::SubprogramSubcomponentType|QCREF]?
	//	arrayDimension+=ArrayDimension? ('{' ownedPropertyAssociation+=PropertyAssociation+ '}')? ';';
	public Aadl2GrammarAccess.SubprogramAccessElements getSubprogramAccessAccess() {
		return gaAadl2.getSubprogramAccessAccess();
	}
	
	public ParserRule getSubprogramAccessRule() {
		return getSubprogramAccessAccess().getRule();
	}
	
	//SubprogramGroupAccess aadl2::SubprogramGroupAccess:
	//	(name=ID ':' | refined=[aadl2::Feature|REFINEDNAME] ':' 'refined' 'to') kind=AccessDirection
	//	'subprogram' 'group' 'access' subprogramGroupFeatureClassifier=[aadl2::SubprogramGroupSubcomponentType|QCREF]?
	//	arrayDimension+=ArrayDimension? ('{' ownedPropertyAssociation+=PropertyAssociation+ '}')? ';';
	public Aadl2GrammarAccess.SubprogramGroupAccessElements getSubprogramGroupAccessAccess() {
		return gaAadl2.getSubprogramGroupAccessAccess();
	}
	
	public ParserRule getSubprogramGroupAccessRule() {
		return getSubprogramGroupAccessAccess().getRule();
	}
	
	//BusAccess aadl2::BusAccess:
	//	(name=ID ':' | refined=[aadl2::Feature|REFINEDNAME] ':' 'refined' 'to') kind=AccessDirection virtual?='virtual'?
	//	'bus' 'access' busFeatureClassifier=[aadl2::BusSubcomponentType|QCREF]?
	//	arrayDimension+=ArrayDimension? ('{' ownedPropertyAssociation+=PropertyAssociation+ '}')? ';';
	public Aadl2GrammarAccess.BusAccessElements getBusAccessAccess() {
		return gaAadl2.getBusAccessAccess();
	}
	
	public ParserRule getBusAccessRule() {
		return getBusAccessAccess().getRule();
	}
	
	//DataAccess aadl2::DataAccess:
	//	(name=ID ':' | refined=[aadl2::Feature|REFINEDNAME] ':' 'refined' 'to') kind=AccessDirection
	//	'data' 'access' dataFeatureClassifier=[aadl2::DataSubcomponentType|QCREF]?
	//	arrayDimension+=ArrayDimension? ('{' ownedPropertyAssociation+=PropertyAssociation+ '}')? ';';
	public Aadl2GrammarAccess.DataAccessElements getDataAccessAccess() {
		return gaAadl2.getDataAccessAccess();
	}
	
	public ParserRule getDataAccessRule() {
		return getDataAccessAccess().getRule();
	}
	
	//AbstractFeature aadl2::AbstractFeature:
	//	(name=ID ':' | refined=[aadl2::AbstractFeature|REFINEDNAME] ':' 'refined' 'to') (in?='in' | out?='out')? ('prototype'
	//	featurePrototype=[aadl2::FeaturePrototype|QCREF]? | 'feature'
	//	abstractFeatureClassifier=[aadl2::FeatureClassifier|QCREF]?) arrayDimension+=ArrayDimension? ('{'
	//	ownedPropertyAssociation+=PropertyAssociation+ '}')? ';';
	public Aadl2GrammarAccess.AbstractFeatureElements getAbstractFeatureAccess() {
		return gaAadl2.getAbstractFeatureAccess();
	}
	
	public ParserRule getAbstractFeatureRule() {
		return getAbstractFeatureAccess().getRule();
	}
	
	//PortDirection aadl2::DirectionType:
	//	'in' | 'out' | 'in' 'out';
	public Aadl2GrammarAccess.PortDirectionElements getPortDirectionAccess() {
		return gaAadl2.getPortDirectionAccess();
	}
	
	public ParserRule getPortDirectionRule() {
		return getPortDirectionAccess().getRule();
	}
	
	//InOutDirection aadl2::DirectionType:
	//	'in' | 'out';
	public Aadl2GrammarAccess.InOutDirectionElements getInOutDirectionAccess() {
		return gaAadl2.getInOutDirectionAccess();
	}
	
	public ParserRule getInOutDirectionRule() {
		return getInOutDirectionAccess().getRule();
	}
	
	//AccessDirection aadl2::AccessType:
	//	'requires' | 'provides';
	public Aadl2GrammarAccess.AccessDirectionElements getAccessDirectionAccess() {
		return gaAadl2.getAccessDirectionAccess();
	}
	
	public ParserRule getAccessDirectionRule() {
		return getAccessDirectionAccess().getRule();
	}
	
	//PortCategory aadl2::PortCategory:
	//	'data' | 'event' | 'event' 'data';
	public Aadl2GrammarAccess.PortCategoryElements getPortCategoryAccess() {
		return gaAadl2.getPortCategoryAccess();
	}
	
	public ParserRule getPortCategoryRule() {
		return getPortCategoryAccess().getRule();
	}
	
	//FeatureGroupType aadl2::FeatureGroupType:
	//	'feature' 'group' name=ID (ownedExtension=GroupExtension ('(' ownedPrototypeBinding+=PrototypeBinding (','
	//	ownedPrototypeBinding+=PrototypeBinding)* ')')?)? ('prototypes' (noPrototypes?='none' ';' |
	//	ownedPrototype+=Prototype+))? ('features' (ownedDataPort+=DataPort | ownedEventPort+=EventPort |
	//	ownedEventDataPort+=EventDataPort
	//	| ownedFeatureGroup+=FeatureGroup | ownedDataAccess+=DataAccess | ownedBusAccess+=BusAccess
	//	| ownedSubprogramAccess+=SubprogramAccess | ownedSubprogramGroupAccess+=SubprogramGroupAccess
	//	| ownedAbstractFeature+=AbstractFeature
	//	| ownedParameter+=Parameter)+)? ('inverse' 'of' inverse=[aadl2::FeatureGroupType|QCREF])? ('properties'
	//	(ownedPropertyAssociation+=ContainedPropertyAssociation+
	//	| noProperties?='none' ';'))?
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' ID ';';
	public Aadl2GrammarAccess.FeatureGroupTypeElements getFeatureGroupTypeAccess() {
		return gaAadl2.getFeatureGroupTypeAccess();
	}
	
	public ParserRule getFeatureGroupTypeRule() {
		return getFeatureGroupTypeAccess().getRule();
	}
	
	//// ********* internal features
	//EventSource aadl2::EventSource:
	//	name=ID ':' 'event' ('{'
	//	ownedPropertyAssociation+=PropertyAssociation+
	//	'}')?
	//	';';
	public Aadl2GrammarAccess.EventSourceElements getEventSourceAccess() {
		return gaAadl2.getEventSourceAccess();
	}
	
	public ParserRule getEventSourceRule() {
		return getEventSourceAccess().getRule();
	}
	
	//EventDataSource aadl2::EventDataSource:
	//	name=ID ':' 'event' 'data'
	//	dataClassifier=[aadl2::DataClassifier|QCREF]? ('{'
	//	ownedPropertyAssociation+=PropertyAssociation+
	//	'}')?
	//	';';
	public Aadl2GrammarAccess.EventDataSourceElements getEventDataSourceAccess() {
		return gaAadl2.getEventDataSourceAccess();
	}
	
	public ParserRule getEventDataSourceRule() {
		return getEventDataSourceAccess().getRule();
	}
	
	//PortProxy aadl2::PortProxy:
	//	name=ID ':' 'port'
	//	dataClassifier=[aadl2::DataClassifier|QCREF]? ('{'
	//	ownedPropertyAssociation+=PropertyAssociation+
	//	'}')?
	//	';';
	public Aadl2GrammarAccess.PortProxyElements getPortProxyAccess() {
		return gaAadl2.getPortProxyAccess();
	}
	
	public ParserRule getPortProxyRule() {
		return getPortProxyAccess().getRule();
	}
	
	//SubprogramProxy aadl2::SubprogramProxy:
	//	name=ID ':' 'subprogram'
	//	subprogramClassifier=[aadl2::SubprogramClassifier|QCREF]? ('{'
	//	ownedPropertyAssociation+=PropertyAssociation+
	//	'}')?
	//	';';
	public Aadl2GrammarAccess.SubprogramProxyElements getSubprogramProxyAccess() {
		return gaAadl2.getSubprogramProxyAccess();
	}
	
	public ParserRule getSubprogramProxyRule() {
		return getSubprogramProxyAccess().getRule();
	}
	
	//// ********* connections 
	//NestedConnectedElement aadl2::ConnectedElement:
	//	ConnectedElement | context=[aadl2::Context] '.' connectionEnd=[aadl2::ConnectionEnd] '.' next=ConnectedElementChain;
	public Aadl2GrammarAccess.NestedConnectedElementElements getNestedConnectedElementAccess() {
		return gaAadl2.getNestedConnectedElementAccess();
	}
	
	public ParserRule getNestedConnectedElementRule() {
		return getNestedConnectedElementAccess().getRule();
	}
	
	//ConnectedElementChain aadl2::ConnectedElement:
	//	connectionEnd=[aadl2::ConnectionEnd] ('.' next=ConnectedElementChain)?;
	public Aadl2GrammarAccess.ConnectedElementChainElements getConnectedElementChainAccess() {
		return gaAadl2.getConnectedElementChainAccess();
	}
	
	public ParserRule getConnectedElementChainRule() {
		return getConnectedElementChainAccess().getRule();
	}
	
	//ConnectedElement aadl2::ConnectedElement:
	//	(context=[aadl2::Context] '.')?
	//	connectionEnd=[aadl2::ConnectionEnd];
	public Aadl2GrammarAccess.ConnectedElementElements getConnectedElementAccess() {
		return gaAadl2.getConnectedElementAccess();
	}
	
	public ParserRule getConnectedElementRule() {
		return getConnectedElementAccess().getRule();
	}
	
	//ProcessorPort aadl2::ConnectedElement:
	//	'processor' '.' connectionEnd=[aadl2::PortProxy];
	public Aadl2GrammarAccess.ProcessorPortElements getProcessorPortAccess() {
		return gaAadl2.getProcessorPortAccess();
	}
	
	public ParserRule getProcessorPortRule() {
		return getProcessorPortAccess().getRule();
	}
	
	//ProcessorSubprogram aadl2::ConnectedElement:
	//	'processor' '.' connectionEnd=[aadl2::SubprogramProxy];
	public Aadl2GrammarAccess.ProcessorSubprogramElements getProcessorSubprogramAccess() {
		return gaAadl2.getProcessorSubprogramAccess();
	}
	
	public ParserRule getProcessorSubprogramRule() {
		return getProcessorSubprogramAccess().getRule();
	}
	
	//InternalEvent aadl2::ConnectedElement:
	//	'self' '.' connectionEnd=[aadl2::InternalFeature];
	public Aadl2GrammarAccess.InternalEventElements getInternalEventAccess() {
		return gaAadl2.getInternalEventAccess();
	}
	
	public ParserRule getInternalEventRule() {
		return getInternalEventAccess().getRule();
	}
	
	//AbstractConnectionEnd aadl2::ConnectedElement:
	//	ConnectedElement | ProcessorPort | InternalEvent;
	public Aadl2GrammarAccess.AbstractConnectionEndElements getAbstractConnectionEndAccess() {
		return gaAadl2.getAbstractConnectionEndAccess();
	}
	
	public ParserRule getAbstractConnectionEndRule() {
		return getAbstractConnectionEndAccess().getRule();
	}
	
	//ProcessorConnectionEnd aadl2::ConnectedElement:
	//	ConnectedElement | ProcessorPort;
	public Aadl2GrammarAccess.ProcessorConnectionEndElements getProcessorConnectionEndAccess() {
		return gaAadl2.getProcessorConnectionEndAccess();
	}
	
	public ParserRule getProcessorConnectionEndRule() {
		return getProcessorConnectionEndAccess().getRule();
	}
	
	//AccessConnectionEnd aadl2::ConnectedElement:
	//	ConnectedElement | ProcessorSubprogram;
	public Aadl2GrammarAccess.AccessConnectionEndElements getAccessConnectionEndAccess() {
		return gaAadl2.getAccessConnectionEndAccess();
	}
	
	public ParserRule getAccessConnectionEndRule() {
		return getAccessConnectionEndAccess().getRule();
	}
	
	//PortConnection aadl2::PortConnection:
	//	(name=ID ':' 'port'
	//	source=AbstractConnectionEnd ('->' | bidirectional?='<->') destination=AbstractConnectionEnd |
	//	refined=[aadl2::PortConnection|REFINEDNAME] ':' 'refined' 'to' 'port') ('{'
	//	ownedPropertyAssociation+=PropertyAssociation+ '}')? ('in' 'modes' '(' (inModeOrTransition+=[aadl2::ModeFeature] (','
	//	inModeOrTransition+=[aadl2::ModeFeature])*) ')')?
	//	';';
	public Aadl2GrammarAccess.PortConnectionElements getPortConnectionAccess() {
		return gaAadl2.getPortConnectionAccess();
	}
	
	public ParserRule getPortConnectionRule() {
		return getPortConnectionAccess().getRule();
	}
	
	//AccessConnection aadl2::AccessConnection:
	//	(name=ID ':' accessCategory=AccessCategory 'access'
	//	source=AccessConnectionEnd ('->' | bidirectional?='<->') destination=AccessConnectionEnd |
	//	refined=[aadl2::AccessConnection|REFINEDNAME] ':' 'refined' 'to' accessCategory=AccessCategory 'access') ('{'
	//	ownedPropertyAssociation+=PropertyAssociation+ '}')? ('in' 'modes' '(' (inModeOrTransition+=[aadl2::ModeFeature] (','
	//	inModeOrTransition+=[aadl2::ModeFeature])*) ')')?
	//	';';
	public Aadl2GrammarAccess.AccessConnectionElements getAccessConnectionAccess() {
		return gaAadl2.getAccessConnectionAccess();
	}
	
	public ParserRule getAccessConnectionRule() {
		return getAccessConnectionAccess().getRule();
	}
	
	//FeatureGroupConnection aadl2::FeatureGroupConnection:
	//	(name=ID ':' 'feature' 'group'
	//	source=NestedConnectedElement ('->' | bidirectional?='<->') destination=NestedConnectedElement |
	//	refined=[aadl2::FeatureGroupConnection|REFINEDNAME] ':' 'refined' 'to' 'feature' 'group') ('{'
	//	ownedPropertyAssociation+=PropertyAssociation+ '}')? ('in' 'modes' '(' (inModeOrTransition+=[aadl2::ModeFeature] (','
	//	inModeOrTransition+=[aadl2::ModeFeature])*) ')')?
	//	';';
	public Aadl2GrammarAccess.FeatureGroupConnectionElements getFeatureGroupConnectionAccess() {
		return gaAadl2.getFeatureGroupConnectionAccess();
	}
	
	public ParserRule getFeatureGroupConnectionRule() {
		return getFeatureGroupConnectionAccess().getRule();
	}
	
	//FeatureConnection aadl2::FeatureConnection:
	//	(name=ID ':' 'feature'
	//	source=NestedConnectedElement ('->' | bidirectional?='<->') destination=NestedConnectedElement |
	//	refined=[aadl2::FeatureConnection|REFINEDNAME] ':' 'refined' 'to' 'feature') ('{'
	//	ownedPropertyAssociation+=PropertyAssociation+ '}')? ('in' 'modes' '(' (inModeOrTransition+=[aadl2::ModeFeature] (','
	//	inModeOrTransition+=[aadl2::ModeFeature])*) ')')?
	//	';';
	public Aadl2GrammarAccess.FeatureConnectionElements getFeatureConnectionAccess() {
		return gaAadl2.getFeatureConnectionAccess();
	}
	
	public ParserRule getFeatureConnectionRule() {
		return getFeatureConnectionAccess().getRule();
	}
	
	//ParameterConnection aadl2::ParameterConnection:
	//	(name=ID ':' 'parameter'
	//	source=ConnectedElement
	//	'->'
	//	destination=ConnectedElement
	//	| refined=[aadl2::ParameterConnection|REFINEDNAME] ':' 'refined' 'to' 'parameter') ('{'
	//	ownedPropertyAssociation+=PropertyAssociation+ '}')? ('in' 'modes' '(' (inModeOrTransition+=[aadl2::ModeFeature] (','
	//	inModeOrTransition+=[aadl2::ModeFeature])*) ')')?
	//	';';
	public Aadl2GrammarAccess.ParameterConnectionElements getParameterConnectionAccess() {
		return gaAadl2.getParameterConnectionAccess();
	}
	
	public ParserRule getParameterConnectionRule() {
		return getParameterConnectionAccess().getRule();
	}
	
	//AccessCategory aadl2::AccessCategory:
	//	'bus' | 'data' | 'subprogram' | 'subprogram' 'group' | 'virtual' 'bus';
	public Aadl2GrammarAccess.AccessCategoryElements getAccessCategoryAccess() {
		return gaAadl2.getAccessCategoryAccess();
	}
	
	public ParserRule getAccessCategoryRule() {
		return getAccessCategoryAccess().getRule();
	}
	
	//enum FlowKind returns aadl2::FlowKind:
	//	source | path | sink;
	public Aadl2GrammarAccess.FlowKindElements getFlowKindAccess() {
		return gaAadl2.getFlowKindAccess();
	}
	
	public EnumRule getFlowKindRule() {
		return getFlowKindAccess().getRule();
	}
	
	//FlowSource aadl2::FlowKind:
	//	'source';
	public Aadl2GrammarAccess.FlowSourceElements getFlowSourceAccess() {
		return gaAadl2.getFlowSourceAccess();
	}
	
	public ParserRule getFlowSourceRule() {
		return getFlowSourceAccess().getRule();
	}
	
	//FlowSink aadl2::FlowKind:
	//	'sink';
	public Aadl2GrammarAccess.FlowSinkElements getFlowSinkAccess() {
		return gaAadl2.getFlowSinkAccess();
	}
	
	public ParserRule getFlowSinkRule() {
		return getFlowSinkAccess().getRule();
	}
	
	//FlowPath aadl2::FlowKind:
	//	'path';
	public Aadl2GrammarAccess.FlowPathElements getFlowPathAccess() {
		return gaAadl2.getFlowPathAccess();
	}
	
	public ParserRule getFlowPathRule() {
		return getFlowPathAccess().getRule();
	}
	
	//FlowSpecification aadl2::FlowSpecification:
	//	FlowSourceSpec | FlowSinkSpec | FlowPathSpec | FlowSpecRefinement;
	public Aadl2GrammarAccess.FlowSpecificationElements getFlowSpecificationAccess() {
		return gaAadl2.getFlowSpecificationAccess();
	}
	
	public ParserRule getFlowSpecificationRule() {
		return getFlowSpecificationAccess().getRule();
	}
	
	//FlowSourceSpec aadl2::FlowSpecification:
	//	name=ID ':'
	//	'flow' kind=FlowSource
	//	outEnd=FlowEnd ('{' ownedPropertyAssociation+=PropertyAssociation+ '}')? ('in' 'modes' '('
	//	inModeOrTransition+=[aadl2::ModeFeature] (',' inModeOrTransition+=[aadl2::ModeFeature])* ')')?
	//	';';
	public Aadl2GrammarAccess.FlowSourceSpecElements getFlowSourceSpecAccess() {
		return gaAadl2.getFlowSourceSpecAccess();
	}
	
	public ParserRule getFlowSourceSpecRule() {
		return getFlowSourceSpecAccess().getRule();
	}
	
	//FlowSinkSpec aadl2::FlowSpecification:
	//	name=ID ':'
	//	'flow' kind=FlowSink
	//	InEnd=FlowEnd ('{' ownedPropertyAssociation+=PropertyAssociation+ '}')? ('in' 'modes' '('
	//	inModeOrTransition+=[aadl2::ModeFeature] (',' inModeOrTransition+=[aadl2::ModeFeature])* ')')?
	//	';';
	public Aadl2GrammarAccess.FlowSinkSpecElements getFlowSinkSpecAccess() {
		return gaAadl2.getFlowSinkSpecAccess();
	}
	
	public ParserRule getFlowSinkSpecRule() {
		return getFlowSinkSpecAccess().getRule();
	}
	
	//FlowPathSpec aadl2::FlowSpecification:
	//	name=ID ':'
	//	'flow' kind=FlowPath
	//	InEnd=FlowEnd
	//	'->' outEnd=FlowEnd ('{' ownedPropertyAssociation+=PropertyAssociation+ '}')? ('in' 'modes' '('
	//	inModeOrTransition+=[aadl2::ModeFeature] (',' inModeOrTransition+=[aadl2::ModeFeature])* ')')?
	//	';';
	public Aadl2GrammarAccess.FlowPathSpecElements getFlowPathSpecAccess() {
		return gaAadl2.getFlowPathSpecAccess();
	}
	
	public ParserRule getFlowPathSpecRule() {
		return getFlowPathSpecAccess().getRule();
	}
	
	//FlowEnd aadl2::FlowEnd:
	//	(context=[aadl2::Context] '.')? feature=[aadl2::Feature];
	public Aadl2GrammarAccess.FlowEndElements getFlowEndAccess() {
		return gaAadl2.getFlowEndAccess();
	}
	
	public ParserRule getFlowEndRule() {
		return getFlowEndAccess().getRule();
	}
	
	//FlowSpecRefinement aadl2::FlowSpecification:
	//	refined=[aadl2::FlowSpecification|REFINEDNAME]
	//	':' 'refined' 'to' 'flow' kind=FlowKind ('{' ownedPropertyAssociation+=PropertyAssociation+ '}')? ('in' 'modes' '('
	//	inModeOrTransition+=[aadl2::ModeFeature] (',' inModeOrTransition+=[aadl2::ModeFeature])* ')')?
	//	';';
	public Aadl2GrammarAccess.FlowSpecRefinementElements getFlowSpecRefinementAccess() {
		return gaAadl2.getFlowSpecRefinementAccess();
	}
	
	public ParserRule getFlowSpecRefinementRule() {
		return getFlowSpecRefinementAccess().getRule();
	}
	
	//FlowImplementation aadl2::FlowImplementation:
	//	FlowSourceImpl | FlowSinkImpl | FlowPathImpl;
	public Aadl2GrammarAccess.FlowImplementationElements getFlowImplementationAccess() {
		return gaAadl2.getFlowImplementationAccess();
	}
	
	public ParserRule getFlowImplementationRule() {
		return getFlowImplementationAccess().getRule();
	}
	
	//FlowSourceImpl aadl2::FlowImplementation:
	//	specification=[aadl2::FlowSpecification]
	//	':' 'flow' kind=FlowSource (ownedFlowSegment+=SubcomponentFlow '->' ownedFlowSegment+=ConnectionFlow '->')*
	//	outEnd=FlowEnd ('{' ownedPropertyAssociation+=PropertyAssociation+ '}')? ('in' 'modes' '('
	//	inModeOrTransition+=[aadl2::ModeFeature] (',' inModeOrTransition+=[aadl2::ModeFeature])* ')')?
	//	';';
	public Aadl2GrammarAccess.FlowSourceImplElements getFlowSourceImplAccess() {
		return gaAadl2.getFlowSourceImplAccess();
	}
	
	public ParserRule getFlowSourceImplRule() {
		return getFlowSourceImplAccess().getRule();
	}
	
	//FlowSinkImpl aadl2::FlowImplementation:
	//	specification=[aadl2::FlowSpecification] //name=ID 
	//	':' 'flow' kind=FlowSink
	//	inEnd=FlowEnd ('->' ownedFlowSegment+=ConnectionFlow '->' ownedFlowSegment+=SubcomponentFlow)* ('{'
	//	ownedPropertyAssociation+=PropertyAssociation+ '}')? ('in' 'modes' '(' inModeOrTransition+=[aadl2::ModeFeature] (','
	//	inModeOrTransition+=[aadl2::ModeFeature])* ')')?
	//	';';
	public Aadl2GrammarAccess.FlowSinkImplElements getFlowSinkImplAccess() {
		return gaAadl2.getFlowSinkImplAccess();
	}
	
	public ParserRule getFlowSinkImplRule() {
		return getFlowSinkImplAccess().getRule();
	}
	
	//FlowPathImpl aadl2::FlowImplementation:
	//	specification=[aadl2::FlowSpecification] //name=ID 
	//	':' 'flow' kind=FlowPath
	//	inEnd=FlowEnd (('->' ownedFlowSegment+=ConnectionFlow '->' ownedFlowSegment+=SubcomponentFlow)*
	//	'->' ownedFlowSegment+=ConnectionFlow)?
	//	'->' outEnd=FlowEnd ('{' ownedPropertyAssociation+=PropertyAssociation+ '}')? ('in' 'modes' '('
	//	inModeOrTransition+=[aadl2::ModeFeature] (',' inModeOrTransition+=[aadl2::ModeFeature])* ')')?
	//	';';
	public Aadl2GrammarAccess.FlowPathImplElements getFlowPathImplAccess() {
		return gaAadl2.getFlowPathImplAccess();
	}
	
	public ParserRule getFlowPathImplRule() {
		return getFlowPathImplAccess().getRule();
	}
	
	//EndToEndFlow aadl2::EndToEndFlow:
	//	(name=ID ':' 'end' 'to' 'end' 'flow'
	//	ownedEndToEndFlowSegment+=ETESubcomponentFlow ('->' ownedEndToEndFlowSegment+=ETEConnectionFlow '->'
	//	ownedEndToEndFlowSegment+=ETESubcomponentFlow)+ | refined=[aadl2::EndToEndFlow|REFINEDNAME] ':' 'refined' 'to' 'end'
	//	'to' 'end' 'flow') ('{' ownedPropertyAssociation+=PropertyAssociation+ '}')? ('in' 'modes' '('
	//	inModeOrTransition+=[aadl2::ModeFeature] (',' inModeOrTransition+=[aadl2::ModeFeature])* ')')?
	//	';';
	public Aadl2GrammarAccess.EndToEndFlowElements getEndToEndFlowAccess() {
		return gaAadl2.getEndToEndFlowAccess();
	}
	
	public ParserRule getEndToEndFlowRule() {
		return getEndToEndFlowAccess().getRule();
	}
	
	//// refinement should have at elast one of property or in modes. We perform a validation check    
	//SubcomponentFlow aadl2::FlowSegment:
	//	(context=[aadl2::Subcomponent] '.')? flowElement=[aadl2::FlowElement];
	public Aadl2GrammarAccess.SubcomponentFlowElements getSubcomponentFlowAccess() {
		return gaAadl2.getSubcomponentFlowAccess();
	}
	
	public ParserRule getSubcomponentFlowRule() {
		return getSubcomponentFlowAccess().getRule();
	}
	
	//ConnectionFlow aadl2::FlowSegment:
	//	flowElement=[aadl2::Connection];
	public Aadl2GrammarAccess.ConnectionFlowElements getConnectionFlowAccess() {
		return gaAadl2.getConnectionFlowAccess();
	}
	
	public ParserRule getConnectionFlowRule() {
		return getConnectionFlowAccess().getRule();
	}
	
	//ETESubcomponentFlow aadl2::EndToEndFlowSegment:
	//	(context=[aadl2::Subcomponent] '.')? flowElement=[aadl2::EndToEndFlowElement];
	public Aadl2GrammarAccess.ETESubcomponentFlowElements getETESubcomponentFlowAccess() {
		return gaAadl2.getETESubcomponentFlowAccess();
	}
	
	public ParserRule getETESubcomponentFlowRule() {
		return getETESubcomponentFlowAccess().getRule();
	}
	
	//ETEConnectionFlow aadl2::EndToEndFlowSegment:
	//	flowElement=[aadl2::Connection];
	public Aadl2GrammarAccess.ETEConnectionFlowElements getETEConnectionFlowAccess() {
		return gaAadl2.getETEConnectionFlowAccess();
	}
	
	public ParserRule getETEConnectionFlowRule() {
		return getETEConnectionFlowAccess().getRule();
	}
	
	//// Modes 
	//Mode aadl2::Mode:
	//	name=ID ':' initial?='initial'? 'mode' ('{' ownedPropertyAssociation+=PropertyAssociation+ '}')?
	//	';';
	public Aadl2GrammarAccess.ModeElements getModeAccess() {
		return gaAadl2.getModeAccess();
	}
	
	public ParserRule getModeRule() {
		return getModeAccess().getRule();
	}
	
	//ModeTransition aadl2::ModeTransition:
	//	(name=ID ':')?
	//	source=[aadl2::Mode] '-['
	//	ownedTrigger+=Trigger (',' ownedTrigger+=Trigger)*
	//	']->' destination=[aadl2::Mode] ('{' ownedPropertyAssociation+=PropertyAssociation+ '}')?
	//	';';
	public Aadl2GrammarAccess.ModeTransitionElements getModeTransitionAccess() {
		return gaAadl2.getModeTransitionAccess();
	}
	
	public ParserRule getModeTransitionRule() {
		return getModeTransitionAccess().getRule();
	}
	
	//Trigger aadl2::ModeTransitionTrigger:
	//	(context=[aadl2::Context] '.')?
	//	triggerPort=[aadl2::Port] | 'self' '.' triggerPort=[aadl2::InternalFeature] | 'processor' '.'
	//	triggerPort=[aadl2::PortProxy];
	public Aadl2GrammarAccess.TriggerElements getTriggerAccess() {
		return gaAadl2.getTriggerAccess();
	}
	
	public ParserRule getTriggerRule() {
		return getTriggerAccess().getRule();
	}
	
	//// used where component_in_modes is shown in standard grammar
	//ModeRef aadl2::ModeBinding:
	//	parentMode=[aadl2::Mode] ('=>' derivedMode=[aadl2::Mode])?;
	public Aadl2GrammarAccess.ModeRefElements getModeRefAccess() {
		return gaAadl2.getModeRefAccess();
	}
	
	public ParserRule getModeRefRule() {
		return getModeRefAccess().getRule();
	}
	
	//AnnexLibrary aadl2::AnnexLibrary:
	//	DefaultAnnexLibrary;
	public Aadl2GrammarAccess.AnnexLibraryElements getAnnexLibraryAccess() {
		return gaAadl2.getAnnexLibraryAccess();
	}
	
	public ParserRule getAnnexLibraryRule() {
		return getAnnexLibraryAccess().getRule();
	}
	
	//DefaultAnnexLibrary aadl2::DefaultAnnexLibrary:
	//	'annex' name=ID
	//	sourceText=ANNEXTEXT
	//	';';
	public Aadl2GrammarAccess.DefaultAnnexLibraryElements getDefaultAnnexLibraryAccess() {
		return gaAadl2.getDefaultAnnexLibraryAccess();
	}
	
	public ParserRule getDefaultAnnexLibraryRule() {
		return getDefaultAnnexLibraryAccess().getRule();
	}
	
	//AnnexSubclause aadl2::AnnexSubclause:
	//	DefaultAnnexSubclause;
	public Aadl2GrammarAccess.AnnexSubclauseElements getAnnexSubclauseAccess() {
		return gaAadl2.getAnnexSubclauseAccess();
	}
	
	public ParserRule getAnnexSubclauseRule() {
		return getAnnexSubclauseAccess().getRule();
	}
	
	//DefaultAnnexSubclause aadl2::DefaultAnnexSubclause:
	//	'annex' name=ID
	//	sourceText=ANNEXTEXT ('in' 'modes' '(' (inMode+=[aadl2::Mode] (',' inMode+=[aadl2::Mode])*) ')')?
	//	';';
	public Aadl2GrammarAccess.DefaultAnnexSubclauseElements getDefaultAnnexSubclauseAccess() {
		return gaAadl2.getDefaultAnnexSubclauseAccess();
	}
	
	public ParserRule getDefaultAnnexSubclauseRule() {
		return getDefaultAnnexSubclauseAccess().getRule();
	}
	
	//// **************
	//// Properties
	//PropertySet aadl2::PropertySet:
	//	'property' 'set' name=ID 'is' ('with' importedUnit+=[aadl2::PropertySet] (',' importedUnit+=[aadl2::PropertySet])*
	//	';')* (ownedPropertyType+=PropertyType | ownedProperty+=PropertyDefinition
	//	| ownedPropertyConstant+=PropertyConstant)*
	//	ownedAnnexSubclause+=AnnexSubclause*
	//	'end' ID ';';
	public Aadl2GrammarAccess.PropertySetElements getPropertySetAccess() {
		return gaAadl2.getPropertySetAccess();
	}
	
	public ParserRule getPropertySetRule() {
		return getPropertySetAccess().getRule();
	}
	
	//PropertyType aadl2::PropertyType:
	//	(BooleanType | StringType | EnumerationType | UnitsType | RealType
	//	| IntegerType | RangeType | ClassifierType | ReferenceType | RecordType)
	//	';';
	public Aadl2GrammarAccess.PropertyTypeElements getPropertyTypeAccess() {
		return gaAadl2.getPropertyTypeAccess();
	}
	
	public ParserRule getPropertyTypeRule() {
		return getPropertyTypeAccess().getRule();
	}
	
	//UnnamedPropertyType aadl2::PropertyType:
	//	ListType | UnnamedBooleanType | UnnamedStringType | UnnamedEnumerationType | UnnamedUnitsType | UnnamedRealType
	//	| UnnamedIntegerType | UnnamedRangeType | UnnamedClassifierType | UnnamedReferenceType | UnnamedRecordType;
	public Aadl2GrammarAccess.UnnamedPropertyTypeElements getUnnamedPropertyTypeAccess() {
		return gaAadl2.getUnnamedPropertyTypeAccess();
	}
	
	public ParserRule getUnnamedPropertyTypeRule() {
		return getUnnamedPropertyTypeAccess().getRule();
	}
	
	//BooleanType aadl2::AadlBoolean:
	//	name=ID ':' 'type'
	//	'aadlboolean';
	public Aadl2GrammarAccess.BooleanTypeElements getBooleanTypeAccess() {
		return gaAadl2.getBooleanTypeAccess();
	}
	
	public ParserRule getBooleanTypeRule() {
		return getBooleanTypeAccess().getRule();
	}
	
	//UnnamedBooleanType aadl2::AadlBoolean:
	//	{aadl2::AadlBoolean} 'aadlboolean';
	public Aadl2GrammarAccess.UnnamedBooleanTypeElements getUnnamedBooleanTypeAccess() {
		return gaAadl2.getUnnamedBooleanTypeAccess();
	}
	
	public ParserRule getUnnamedBooleanTypeRule() {
		return getUnnamedBooleanTypeAccess().getRule();
	}
	
	//StringType aadl2::AadlString:
	//	name=ID ':' 'type'
	//	'aadlstring';
	public Aadl2GrammarAccess.StringTypeElements getStringTypeAccess() {
		return gaAadl2.getStringTypeAccess();
	}
	
	public ParserRule getStringTypeRule() {
		return getStringTypeAccess().getRule();
	}
	
	//UnnamedStringType aadl2::AadlString:
	//	{aadl2::AadlString} 'aadlstring';
	public Aadl2GrammarAccess.UnnamedStringTypeElements getUnnamedStringTypeAccess() {
		return gaAadl2.getUnnamedStringTypeAccess();
	}
	
	public ParserRule getUnnamedStringTypeRule() {
		return getUnnamedStringTypeAccess().getRule();
	}
	
	//EnumerationType aadl2::EnumerationType:
	//	name=ID ':' 'type'
	//	'enumeration'
	//	'(' ownedLiteral+=EnumerationLiteral (',' ownedLiteral+=EnumerationLiteral)* ')';
	public Aadl2GrammarAccess.EnumerationTypeElements getEnumerationTypeAccess() {
		return gaAadl2.getEnumerationTypeAccess();
	}
	
	public ParserRule getEnumerationTypeRule() {
		return getEnumerationTypeAccess().getRule();
	}
	
	//UnnamedEnumerationType aadl2::EnumerationType:
	//	'enumeration'
	//	'(' ownedLiteral+=EnumerationLiteral (',' ownedLiteral+=EnumerationLiteral)* ')';
	public Aadl2GrammarAccess.UnnamedEnumerationTypeElements getUnnamedEnumerationTypeAccess() {
		return gaAadl2.getUnnamedEnumerationTypeAccess();
	}
	
	public ParserRule getUnnamedEnumerationTypeRule() {
		return getUnnamedEnumerationTypeAccess().getRule();
	}
	
	//EnumerationLiteral aadl2::EnumerationLiteral:
	//	name=ID;
	public Aadl2GrammarAccess.EnumerationLiteralElements getEnumerationLiteralAccess() {
		return gaAadl2.getEnumerationLiteralAccess();
	}
	
	public ParserRule getEnumerationLiteralRule() {
		return getEnumerationLiteralAccess().getRule();
	}
	
	//UnitsType aadl2::UnitsType:
	//	name=ID ':' 'type'
	//	'units'
	//	'(' ownedLiteral+=UnitLiteral (',' ownedLiteral+=UnitLiteralConversion)* ')';
	public Aadl2GrammarAccess.UnitsTypeElements getUnitsTypeAccess() {
		return gaAadl2.getUnitsTypeAccess();
	}
	
	public ParserRule getUnitsTypeRule() {
		return getUnitsTypeAccess().getRule();
	}
	
	//UnnamedUnitsType aadl2::UnitsType:
	//	'units'
	//	'(' ownedLiteral+=UnitLiteral (',' ownedLiteral+=UnitLiteralConversion)* ')';
	public Aadl2GrammarAccess.UnnamedUnitsTypeElements getUnnamedUnitsTypeAccess() {
		return gaAadl2.getUnnamedUnitsTypeAccess();
	}
	
	public ParserRule getUnnamedUnitsTypeRule() {
		return getUnnamedUnitsTypeAccess().getRule();
	}
	
	//UnitLiteral aadl2::UnitLiteral:
	//	name=ID;
	public Aadl2GrammarAccess.UnitLiteralElements getUnitLiteralAccess() {
		return gaAadl2.getUnitLiteralAccess();
	}
	
	public ParserRule getUnitLiteralRule() {
		return getUnitLiteralAccess().getRule();
	}
	
	//UnitLiteralConversion aadl2::UnitLiteral:
	//	name=ID '=>' baseUnit=[aadl2::UnitLiteral] STAR factor=NumberValue;
	public Aadl2GrammarAccess.UnitLiteralConversionElements getUnitLiteralConversionAccess() {
		return gaAadl2.getUnitLiteralConversionAccess();
	}
	
	public ParserRule getUnitLiteralConversionRule() {
		return getUnitLiteralConversionAccess().getRule();
	}
	
	//RealType aadl2::AadlReal:
	//	name=ID ':' 'type'
	//	'aadlreal' range=RealRange? (ownedUnitsType=UnnamedUnitsType | 'units' referencedUnitsType=[aadl2::UnitsType|QPREF])?;
	public Aadl2GrammarAccess.RealTypeElements getRealTypeAccess() {
		return gaAadl2.getRealTypeAccess();
	}
	
	public ParserRule getRealTypeRule() {
		return getRealTypeAccess().getRule();
	}
	
	//UnnamedRealType aadl2::AadlReal:
	//	{aadl2::AadlReal}
	//	'aadlreal' range=RealRange? (ownedUnitsType=UnnamedUnitsType | 'units' referencedUnitsType=[aadl2::UnitsType|QPREF])?;
	public Aadl2GrammarAccess.UnnamedRealTypeElements getUnnamedRealTypeAccess() {
		return gaAadl2.getUnnamedRealTypeAccess();
	}
	
	public ParserRule getUnnamedRealTypeRule() {
		return getUnnamedRealTypeAccess().getRule();
	}
	
	//IntegerType aadl2::AadlInteger:
	//	name=ID ':' 'type'
	//	'aadlinteger' range=IntegerRange? (ownedUnitsType=UnnamedUnitsType | 'units'
	//	referencedUnitsType=[aadl2::UnitsType|QPREF])?;
	public Aadl2GrammarAccess.IntegerTypeElements getIntegerTypeAccess() {
		return gaAadl2.getIntegerTypeAccess();
	}
	
	public ParserRule getIntegerTypeRule() {
		return getIntegerTypeAccess().getRule();
	}
	
	//UnnamedIntegerType aadl2::AadlInteger:
	//	{aadl2::AadlInteger}
	//	'aadlinteger' range=IntegerRange? (ownedUnitsType=UnnamedUnitsType | 'units'
	//	referencedUnitsType=[aadl2::UnitsType|QPREF])?;
	public Aadl2GrammarAccess.UnnamedIntegerTypeElements getUnnamedIntegerTypeAccess() {
		return gaAadl2.getUnnamedIntegerTypeAccess();
	}
	
	public ParserRule getUnnamedIntegerTypeRule() {
		return getUnnamedIntegerTypeAccess().getRule();
	}
	
	//RangeType aadl2::RangeType:
	//	name=ID ':' 'type'
	//	'range' 'of' (ownedNumberType=(UnnamedIntegerType | UnnamedRealType) |
	//	referencedNumberType=[aadl2::NumberType|QPREF]);
	public Aadl2GrammarAccess.RangeTypeElements getRangeTypeAccess() {
		return gaAadl2.getRangeTypeAccess();
	}
	
	public ParserRule getRangeTypeRule() {
		return getRangeTypeAccess().getRule();
	}
	
	//UnnamedRangeType aadl2::RangeType:
	//	{aadl2::RangeType}
	//	'range' 'of' (ownedNumberType=(UnnamedIntegerType | UnnamedRealType) |
	//	referencedNumberType=[aadl2::NumberType|QPREF]);
	public Aadl2GrammarAccess.UnnamedRangeTypeElements getUnnamedRangeTypeAccess() {
		return gaAadl2.getUnnamedRangeTypeAccess();
	}
	
	public ParserRule getUnnamedRangeTypeRule() {
		return getUnnamedRangeTypeAccess().getRule();
	}
	
	//ClassifierType aadl2::ClassifierType:
	//	name=ID ':' 'type'
	//	'classifier' ('(' classifierReference+=QMReference (',' classifierReference+=QMReference)* ')')?;
	public Aadl2GrammarAccess.ClassifierTypeElements getClassifierTypeAccess() {
		return gaAadl2.getClassifierTypeAccess();
	}
	
	public ParserRule getClassifierTypeRule() {
		return getClassifierTypeAccess().getRule();
	}
	
	//UnnamedClassifierType aadl2::ClassifierType:
	//	{aadl2::ClassifierType}
	//	'classifier' ('(' classifierReference+=QMReference (',' classifierReference+=QMReference)* ')')?;
	public Aadl2GrammarAccess.UnnamedClassifierTypeElements getUnnamedClassifierTypeAccess() {
		return gaAadl2.getUnnamedClassifierTypeAccess();
	}
	
	public ParserRule getUnnamedClassifierTypeRule() {
		return getUnnamedClassifierTypeAccess().getRule();
	}
	
	//QMReference aadl2::MetaclassReference:
	//	('{' annexName=ID '}' STAR STAR)?
	//	metaclassName+=(CoreKeyWord | ID)+;
	public Aadl2GrammarAccess.QMReferenceElements getQMReferenceAccess() {
		return gaAadl2.getQMReferenceAccess();
	}
	
	public ParserRule getQMReferenceRule() {
		return getQMReferenceAccess().getRule();
	}
	
	//QCReference aadl2::ClassifierValue:
	//	classifier=[aadl2::ComponentClassifier|FQCREF];
	public Aadl2GrammarAccess.QCReferenceElements getQCReferenceAccess() {
		return gaAadl2.getQCReferenceAccess();
	}
	
	public ParserRule getQCReferenceRule() {
		return getQCReferenceAccess().getRule();
	}
	
	//ReferenceType aadl2::ReferenceType:
	//	name=ID ':' 'type'
	//	'reference' ('(' namedElementReference+=QMReference (',' namedElementReference+=QMReference)* ')')?;
	public Aadl2GrammarAccess.ReferenceTypeElements getReferenceTypeAccess() {
		return gaAadl2.getReferenceTypeAccess();
	}
	
	public ParserRule getReferenceTypeRule() {
		return getReferenceTypeAccess().getRule();
	}
	
	//UnnamedReferenceType aadl2::ReferenceType:
	//	'reference' {aadl2::ReferenceType} ('(' namedElementReference+=QMReference (',' namedElementReference+=QMReference)*
	//	')')?;
	public Aadl2GrammarAccess.UnnamedReferenceTypeElements getUnnamedReferenceTypeAccess() {
		return gaAadl2.getUnnamedReferenceTypeAccess();
	}
	
	public ParserRule getUnnamedReferenceTypeRule() {
		return getUnnamedReferenceTypeAccess().getRule();
	}
	
	//RecordType aadl2::RecordType:
	//	name=ID ':' 'type'
	//	'record' '('
	//	ownedField+=RecordField+
	//	')';
	public Aadl2GrammarAccess.RecordTypeElements getRecordTypeAccess() {
		return gaAadl2.getRecordTypeAccess();
	}
	
	public ParserRule getRecordTypeRule() {
		return getRecordTypeAccess().getRule();
	}
	
	//UnnamedRecordType aadl2::RecordType:
	//	'record' '('
	//	ownedField+=RecordField+
	//	')';
	public Aadl2GrammarAccess.UnnamedRecordTypeElements getUnnamedRecordTypeAccess() {
		return gaAadl2.getUnnamedRecordTypeAccess();
	}
	
	public ParserRule getUnnamedRecordTypeRule() {
		return getUnnamedRecordTypeAccess().getRule();
	}
	
	//RecordField aadl2::BasicProperty:
	//	name=ID ':' (referencedPropertyType=[aadl2::PropertyType|QPREF] | ownedPropertyType=UnnamedPropertyType)
	//	';';
	public Aadl2GrammarAccess.RecordFieldElements getRecordFieldAccess() {
		return gaAadl2.getRecordFieldAccess();
	}
	
	public ParserRule getRecordFieldRule() {
		return getRecordFieldAccess().getRule();
	}
	
	////&&&&& Property Definition
	//PropertyDefinition aadl2::Property:
	//	name=ID ':'
	//	inherit?='inherit'? (referencedPropertyType=[aadl2::PropertyType|QPREF] | ownedPropertyType=UnnamedPropertyType) ('=>'
	//	defaultValue=PropertyExpression)?
	//	'applies' 'to' '(' (appliesTo+=PropertyOwner (',' appliesTo+=PropertyOwner)* | appliesTo+=AllReference)
	//	')'
	//	';';
	public Aadl2GrammarAccess.PropertyDefinitionElements getPropertyDefinitionAccess() {
		return gaAadl2.getPropertyDefinitionAccess();
	}
	
	public ParserRule getPropertyDefinitionRule() {
		return getPropertyDefinitionAccess().getRule();
	}
	
	//AllReference aadl2::MetaclassReference:
	//	metaclassName+='all' // &&&&& actually set it to NamedElement using ALL rule returning "named element" as string
	//;
	public Aadl2GrammarAccess.AllReferenceElements getAllReferenceAccess() {
		return gaAadl2.getAllReferenceAccess();
	}
	
	public ParserRule getAllReferenceRule() {
		return getAllReferenceAccess().getRule();
	}
	
	//ListType aadl2::ListType:
	//	'list' 'of' (referencedElementType=[aadl2::PropertyType|QPREF] | ownedElementType=UnnamedPropertyType);
	public Aadl2GrammarAccess.ListTypeElements getListTypeAccess() {
		return gaAadl2.getListTypeAccess();
	}
	
	public ParserRule getListTypeRule() {
		return getListTypeAccess().getRule();
	}
	
	//PropertyOwner aadl2::PropertyOwner:
	//	QMReference | QCReference;
	public Aadl2GrammarAccess.PropertyOwnerElements getPropertyOwnerAccess() {
		return gaAadl2.getPropertyOwnerAccess();
	}
	
	public ParserRule getPropertyOwnerRule() {
		return getPropertyOwnerAccess().getRule();
	}
	
	//PropertyConstant aadl2::PropertyConstant:
	//	name=ID ':' 'constant' (referencedPropertyType=[aadl2::PropertyType|QPREF] | ownedPropertyType=UnnamedPropertyType)
	//	'=>' constantValue=ConstantPropertyExpression
	//	';';
	public Aadl2GrammarAccess.PropertyConstantElements getPropertyConstantAccess() {
		return gaAadl2.getPropertyConstantAccess();
	}
	
	public ParserRule getPropertyConstantRule() {
		return getPropertyConstantAccess().getRule();
	}
	
	//NumberValue aadl2::NumberValue:
	//	RealLit | IntegerLit;
	public Aadl2GrammarAccess.NumberValueElements getNumberValueAccess() {
		return gaAadl2.getNumberValueAccess();
	}
	
	public ParserRule getNumberValueRule() {
		return getNumberValueAccess().getRule();
	}
	
	//RealLit aadl2::RealLiteral:
	//	value=SignedReal;
	public Aadl2GrammarAccess.RealLitElements getRealLitAccess() {
		return gaAadl2.getRealLitAccess();
	}
	
	public ParserRule getRealLitRule() {
		return getRealLitAccess().getRule();
	}
	
	//IntegerLit aadl2::IntegerLiteral:
	//	value=SignedInt;
	public Aadl2GrammarAccess.IntegerLitElements getIntegerLitAccess() {
		return gaAadl2.getIntegerLitAccess();
	}
	
	public ParserRule getIntegerLitRule() {
		return getIntegerLitAccess().getRule();
	}
	
	//ConstantPropertyExpression aadl2::PropertyExpression:
	//	RecordTerm | ComponentClassifierTerm
	//	| ComputedTerm | StringTerm | NumericRangeTerm
	//	| RealTerm | IntegerTerm
	//	| ListTerm
	//	| BooleanLiteral | LiteralorReferenceTerm;
	public Aadl2GrammarAccess.ConstantPropertyExpressionElements getConstantPropertyExpressionAccess() {
		return gaAadl2.getConstantPropertyExpressionAccess();
	}
	
	public ParserRule getConstantPropertyExpressionRule() {
		return getConstantPropertyExpressionAccess().getRule();
	}
	
	//IntegerRange aadl2::NumericRange:
	//	lowerBound=(IntegerTerm | SignedConstant | ConstantValue)
	//	'..' upperBound=(IntegerTerm | SignedConstant | ConstantValue);
	public Aadl2GrammarAccess.IntegerRangeElements getIntegerRangeAccess() {
		return gaAadl2.getIntegerRangeAccess();
	}
	
	public ParserRule getIntegerRangeRule() {
		return getIntegerRangeAccess().getRule();
	}
	
	//RealRange aadl2::NumericRange:
	//	lowerBound=(RealTerm | SignedConstant | ConstantValue)
	//	'..' upperBound=(RealTerm | SignedConstant | ConstantValue);
	public Aadl2GrammarAccess.RealRangeElements getRealRangeAccess() {
		return gaAadl2.getRealRangeAccess();
	}
	
	public ParserRule getRealRangeRule() {
		return getRealRangeAccess().getRule();
	}
	
	//CoreKeyWord:
	//	'abstract' | 'access' | 'annex' | 'applies' | 'binding'
	//	| 'bus' | 'calls' | 'classifier' | 'connections' | 'constant' | 'data' | 'device'
	//	| 'end' | 'enumeration' | 'event' | 'extends'
	//	| 'feature' | 'features' | 'flow' | 'flows'
	//	| 'group' | 'implementation' | 'in' | 'inherit' | 'initial' | 'internal' | 'inverse' | 'is' | 'list'
	//	| 'memory' | 'mode' | 'modes' | 'none' | 'of' | 'out' | 'package' | 'parameter' | 'path' | 'port' | 'private'
	//	| 'process' | 'processor' | 'prototype'
	//	| 'prototypes' | 'provides' | 'public'
	//	| 'refined' | 'renames' | 'requires' | 'self' | 'set' | 'sink' | 'source'
	//	| 'subcomponents' | 'subprogram' | 'system' | 'thread' | 'type' | 'to' | 'virtual' | 'with'
	//	//// properties grammar	|'properties'|'property'|'delta'|'false'|'not'| 'and'|'or'|'range'|'record'|'reference'|'true'|'units'
	//;
	public Aadl2GrammarAccess.CoreKeyWordElements getCoreKeyWordAccess() {
		return gaAadl2.getCoreKeyWordAccess();
	}
	
	public ParserRule getCoreKeyWordRule() {
		return getCoreKeyWordAccess().getRule();
	}
	
	//// package anme
	//PNAME:
	//	ID ('::' ID)*;
	public Aadl2GrammarAccess.PNAMEElements getPNAMEAccess() {
		return gaAadl2.getPNAMEAccess();
	}
	
	public ParserRule getPNAMERule() {
		return getPNAMEAccess().getRule();
	}
	
	//// fully qualified classifier name (always includes package name
	//FQCREF:
	//	(ID '::')+ ID ('.' ID)?;
	public Aadl2GrammarAccess.FQCREFElements getFQCREFAccess() {
		return gaAadl2.getFQCREFAccess();
	}
	
	public ParserRule getFQCREFRule() {
		return getFQCREFAccess().getRule();
	}
	
	//// implementation name (used by value converter)
	//INAME:
	//	ID;
	public Aadl2GrammarAccess.INAMEElements getINAMEAccess() {
		return gaAadl2.getINAMEAccess();
	}
	
	public ParserRule getINAMERule() {
		return getINAMEAccess().getRule();
	}
	
	//// implementation name (used by value converter)
	//FULLINAME:
	//	ID '.' ID;
	public Aadl2GrammarAccess.FULLINAMEElements getFULLINAMEAccess() {
		return gaAadl2.getFULLINAMEAccess();
	}
	
	public ParserRule getFULLINAMERule() {
		return getFULLINAMEAccess().getRule();
	}
	
	//// name of refined entity. Used to set name field by value converter	
	//REFINEDNAME:
	//	ID;
	public Aadl2GrammarAccess.REFINEDNAMEElements getREFINEDNAMEAccess() {
		return gaAadl2.getREFINEDNAMEAccess();
	}
	
	public ParserRule getREFINEDNAMERule() {
		return getREFINEDNAMEAccess().getRule();
	}
	
	//terminal ANNEXTEXT:
	//	'{**'->'**}';
	public TerminalRule getANNEXTEXTRule() {
		return gaAadl2.getANNEXTEXTRule();
	}
	
	//PModel aadl2::Element:
	//	ContainedPropertyAssociation //| BasicPropertyAssociation | PropertyAssociation
	//;
	public PropertiesGrammarAccess.PModelElements getPModelAccess() {
		return gaProperties.getPModelAccess();
	}
	
	public ParserRule getPModelRule() {
		return getPModelAccess().getRule();
	}
	
	//// Properties
	//ContainedPropertyAssociation aadl2::PropertyAssociation:
	//	property=[aadl2::Property|QPREF] ('=>' | append?='+=>') constant?='constant'? (ownedValue+=OptionalModalPropertyValue
	//	(',' ownedValue+=OptionalModalPropertyValue)*) ('applies' 'to' appliesTo+=ContainmentPath (','
	//	appliesTo+=ContainmentPath)*)? ('in' 'binding' '(' inBinding+=[aadl2::Classifier|QCREF] ')')?
	//	';';
	public PropertiesGrammarAccess.ContainedPropertyAssociationElements getContainedPropertyAssociationAccess() {
		return gaProperties.getContainedPropertyAssociationAccess();
	}
	
	public ParserRule getContainedPropertyAssociationRule() {
		return getContainedPropertyAssociationAccess().getRule();
	}
	
	//PropertyAssociation aadl2::PropertyAssociation:
	//	property=[aadl2::Property|QPREF] ('=>' | append?='+=>') constant?='constant'? (ownedValue+=OptionalModalPropertyValue
	//	(',' ownedValue+=OptionalModalPropertyValue)*) ('in' 'binding' '(' inBinding+=[aadl2::Classifier|QCREF] ')')?
	//	';';
	public PropertiesGrammarAccess.PropertyAssociationElements getPropertyAssociationAccess() {
		return gaProperties.getPropertyAssociationAccess();
	}
	
	public ParserRule getPropertyAssociationRule() {
		return getPropertyAssociationAccess().getRule();
	}
	
	//BasicPropertyAssociation aadl2::PropertyAssociation:
	//	property=[aadl2::Property|QPREF]
	//	'=>' ownedValue+=PropertyValue ';';
	public PropertiesGrammarAccess.BasicPropertyAssociationElements getBasicPropertyAssociationAccess() {
		return gaProperties.getBasicPropertyAssociationAccess();
	}
	
	public ParserRule getBasicPropertyAssociationRule() {
		return getBasicPropertyAssociationAccess().getRule();
	}
	
	//ContainmentPath aadl2::ContainedNamedElement:
	//	path=ContainmentPathElement
	//	//	( 'annex' containmentPathElement+=AnnexPath )?
	//;
	public PropertiesGrammarAccess.ContainmentPathElements getContainmentPathAccess() {
		return gaProperties.getContainmentPathAccess();
	}
	
	public ParserRule getContainmentPathRule() {
		return getContainmentPathAccess().getRule();
	}
	
	////AnnexPath returns aadl2::ContainmentPathElement:
	////	 namedElement=[aadl2::NamedElement|IDANNEXTEXT];
	//ModalPropertyValue aadl2::ModalPropertyValue:
	//	ownedValue=PropertyExpression
	//	'in' 'modes' '('
	//	inMode+=[aadl2::Mode] (',' inMode+=[aadl2::Mode])*
	//	')';
	public PropertiesGrammarAccess.ModalPropertyValueElements getModalPropertyValueAccess() {
		return gaProperties.getModalPropertyValueAccess();
	}
	
	public ParserRule getModalPropertyValueRule() {
		return getModalPropertyValueAccess().getRule();
	}
	
	//OptionalModalPropertyValue aadl2::ModalPropertyValue:
	//	ownedValue=PropertyExpression ('in' 'modes' '('
	//	inMode+=[aadl2::Mode] (',' inMode+=[aadl2::Mode])*
	//	')')?;
	public PropertiesGrammarAccess.OptionalModalPropertyValueElements getOptionalModalPropertyValueAccess() {
		return gaProperties.getOptionalModalPropertyValueAccess();
	}
	
	public ParserRule getOptionalModalPropertyValueRule() {
		return getOptionalModalPropertyValueAccess().getRule();
	}
	
	//// &&&&&&&&&& handling of in binding
	//PropertyValue aadl2::ModalPropertyValue:
	//	ownedValue=PropertyExpression;
	public PropertiesGrammarAccess.PropertyValueElements getPropertyValueAccess() {
		return gaProperties.getPropertyValueAccess();
	}
	
	public ParserRule getPropertyValueRule() {
		return getPropertyValueAccess().getRule();
	}
	
	//PropertyExpression aadl2::PropertyExpression:
	//	RecordTerm | ReferenceTerm | ComponentClassifierTerm
	//	| ComputedTerm | StringTerm | NumericRangeTerm
	//	| RealTerm | IntegerTerm
	//	| ListTerm
	//	| BooleanLiteral | LiteralorReferenceTerm;
	public PropertiesGrammarAccess.PropertyExpressionElements getPropertyExpressionAccess() {
		return gaProperties.getPropertyExpressionAccess();
	}
	
	public ParserRule getPropertyExpressionRule() {
		return getPropertyExpressionAccess().getRule();
	}
	
	//LiteralorReferenceTerm aadl2::NamedValue:
	//	namedValue=[aadl2::AbstractNamedValue|QPREF];
	public PropertiesGrammarAccess.LiteralorReferenceTermElements getLiteralorReferenceTermAccess() {
		return gaProperties.getLiteralorReferenceTermAccess();
	}
	
	public ParserRule getLiteralorReferenceTermRule() {
		return getLiteralorReferenceTermAccess().getRule();
	}
	
	//BooleanLiteral aadl2::BooleanLiteral:
	//	{aadl2::BooleanLiteral} (value?='true' | 'false');
	public PropertiesGrammarAccess.BooleanLiteralElements getBooleanLiteralAccess() {
		return gaProperties.getBooleanLiteralAccess();
	}
	
	public ParserRule getBooleanLiteralRule() {
		return getBooleanLiteralAccess().getRule();
	}
	
	//ConstantValue aadl2::NamedValue:
	//	namedValue=[aadl2::PropertyConstant|QPREF];
	public PropertiesGrammarAccess.ConstantValueElements getConstantValueAccess() {
		return gaProperties.getConstantValueAccess();
	}
	
	public ParserRule getConstantValueRule() {
		return getConstantValueAccess().getRule();
	}
	
	//ReferenceTerm aadl2::ReferenceValue:
	//	'reference' '('
	//	path=ContainmentPathElement
	//	//	( 'annex' ID '{**' 
	//	//	containmentPathElement+=ContainmentPathElement
	//	//	( '.' containmentPathElement+=ContainmentPathElement)*
	//	//	'**}')?
	//	')';
	public PropertiesGrammarAccess.ReferenceTermElements getReferenceTermAccess() {
		return gaProperties.getReferenceTermAccess();
	}
	
	public ParserRule getReferenceTermRule() {
		return getReferenceTermAccess().getRule();
	}
	
	//RecordTerm aadl2::RecordValue:
	//	'['
	//	ownedFieldValue+=FieldPropertyAssociation+
	//	']';
	public PropertiesGrammarAccess.RecordTermElements getRecordTermAccess() {
		return gaProperties.getRecordTermAccess();
	}
	
	public ParserRule getRecordTermRule() {
		return getRecordTermAccess().getRule();
	}
	
	//OldRecordTerm aadl2::RecordValue:
	//	'('
	//	ownedFieldValue+=FieldPropertyAssociation+
	//	')';
	public PropertiesGrammarAccess.OldRecordTermElements getOldRecordTermAccess() {
		return gaProperties.getOldRecordTermAccess();
	}
	
	public ParserRule getOldRecordTermRule() {
		return getOldRecordTermAccess().getRule();
	}
	
	//ComputedTerm aadl2::ComputedValue:
	//	'compute' '('
	//	function=ID
	//	')';
	public PropertiesGrammarAccess.ComputedTermElements getComputedTermAccess() {
		return gaProperties.getComputedTermAccess();
	}
	
	public ParserRule getComputedTermRule() {
		return getComputedTermAccess().getRule();
	}
	
	//ComponentClassifierTerm aadl2::ClassifierValue:
	//	'classifier' '('
	//	classifier=[aadl2::ComponentClassifier|QCREF]
	//	')';
	public PropertiesGrammarAccess.ComponentClassifierTermElements getComponentClassifierTermAccess() {
		return gaProperties.getComponentClassifierTermAccess();
	}
	
	public ParserRule getComponentClassifierTermRule() {
		return getComponentClassifierTermAccess().getRule();
	}
	
	//ListTerm aadl2::ListValue:
	//	{aadl2::ListValue}
	//	'(' (ownedListElement+=PropertyExpression (',' ownedListElement+=PropertyExpression)*)?
	//	')';
	public PropertiesGrammarAccess.ListTermElements getListTermAccess() {
		return gaProperties.getListTermAccess();
	}
	
	public ParserRule getListTermRule() {
		return getListTermAccess().getRule();
	}
	
	//FieldPropertyAssociation aadl2::BasicPropertyAssociation:
	//	property=[aadl2::BasicProperty]
	//	'=>'
	//	ownedValue=PropertyExpression
	//	';';
	public PropertiesGrammarAccess.FieldPropertyAssociationElements getFieldPropertyAssociationAccess() {
		return gaProperties.getFieldPropertyAssociationAccess();
	}
	
	public ParserRule getFieldPropertyAssociationRule() {
		return getFieldPropertyAssociationAccess().getRule();
	}
	
	//// from AADL2
	//// need to add annex path element
	//ContainmentPathElement aadl2::ContainmentPathElement:
	//	(namedElement=[aadl2::NamedElement] arrayRange+=ArrayRange*) ('.' path=ContainmentPathElement)?
	//	//	 | 	 'annex' namedElement=[aadl2::NamedElement|ID]
	//;
	public PropertiesGrammarAccess.ContainmentPathElementElements getContainmentPathElementAccess() {
		return gaProperties.getContainmentPathElementAccess();
	}
	
	public ParserRule getContainmentPathElementRule() {
		return getContainmentPathElementAccess().getRule();
	}
	
	//ANNEXREF: // check what values are ok inside ** **
	//	'{' STAR STAR ID STAR STAR '}';
	public PropertiesGrammarAccess.ANNEXREFElements getANNEXREFAccess() {
		return gaProperties.getANNEXREFAccess();
	}
	
	public ParserRule getANNEXREFRule() {
		return getANNEXREFAccess().getRule();
	}
	
	//PlusMinus aadl2::OperationKind:
	//	'+' | '-';
	public PropertiesGrammarAccess.PlusMinusElements getPlusMinusAccess() {
		return gaProperties.getPlusMinusAccess();
	}
	
	public ParserRule getPlusMinusRule() {
		return getPlusMinusAccess().getRule();
	}
	
	//StringTerm aadl2::StringLiteral:
	//	value=NoQuoteString;
	public PropertiesGrammarAccess.StringTermElements getStringTermAccess() {
		return gaProperties.getStringTermAccess();
	}
	
	public ParserRule getStringTermRule() {
		return getStringTermAccess().getRule();
	}
	
	//NoQuoteString:
	//	STRING;
	public PropertiesGrammarAccess.NoQuoteStringElements getNoQuoteStringAccess() {
		return gaProperties.getNoQuoteStringAccess();
	}
	
	public ParserRule getNoQuoteStringRule() {
		return getNoQuoteStringAccess().getRule();
	}
	
	//ArrayRange aadl2::ArrayRange:
	//	{aadl2::ArrayRange}
	//	'[' lowerBound=INTVALUE ('..' upperBound=INTVALUE)?
	//	']';
	public PropertiesGrammarAccess.ArrayRangeElements getArrayRangeAccess() {
		return gaProperties.getArrayRangeAccess();
	}
	
	public ParserRule getArrayRangeRule() {
		return getArrayRangeAccess().getRule();
	}
	
	//SignedConstant aadl2::Operation:
	//	op=PlusMinus ownedPropertyExpression+=ConstantValue;
	public PropertiesGrammarAccess.SignedConstantElements getSignedConstantAccess() {
		return gaProperties.getSignedConstantAccess();
	}
	
	public ParserRule getSignedConstantRule() {
		return getSignedConstantAccess().getRule();
	}
	
	//IntegerTerm aadl2::IntegerLiteral:
	//	value=SignedInt unit=[aadl2::UnitLiteral]?;
	public PropertiesGrammarAccess.IntegerTermElements getIntegerTermAccess() {
		return gaProperties.getIntegerTermAccess();
	}
	
	public ParserRule getIntegerTermRule() {
		return getIntegerTermAccess().getRule();
	}
	
	//SignedInt aadl2::Integer:
	//	('+' | '-')? INTEGER_LIT;
	public PropertiesGrammarAccess.SignedIntElements getSignedIntAccess() {
		return gaProperties.getSignedIntAccess();
	}
	
	public ParserRule getSignedIntRule() {
		return getSignedIntAccess().getRule();
	}
	
	//RealTerm aadl2::RealLiteral:
	//	value=SignedReal unit=[aadl2::UnitLiteral]?;
	public PropertiesGrammarAccess.RealTermElements getRealTermAccess() {
		return gaProperties.getRealTermAccess();
	}
	
	public ParserRule getRealTermRule() {
		return getRealTermAccess().getRule();
	}
	
	//SignedReal aadl2::Real:
	//	('+' | '-')? REAL_LIT;
	public PropertiesGrammarAccess.SignedRealElements getSignedRealAccess() {
		return gaProperties.getSignedRealAccess();
	}
	
	public ParserRule getSignedRealRule() {
		return getSignedRealAccess().getRule();
	}
	
	//NumericRangeTerm aadl2::RangeValue:
	//	minimum=NumAlt //(RealTerm|IntegerTerm| SignedConstant | ConstantValue)  
	//	'..' maximum=NumAlt ('delta' delta=NumAlt //(RealTerm|IntegerTerm| SignedConstant | ConstantValue)
	//)?;
	public PropertiesGrammarAccess.NumericRangeTermElements getNumericRangeTermAccess() {
		return gaProperties.getNumericRangeTermAccess();
	}
	
	public ParserRule getNumericRangeTermRule() {
		return getNumericRangeTermAccess().getRule();
	}
	
	//NumAlt aadl2::PropertyExpression:
	//	RealTerm | IntegerTerm | SignedConstant | ConstantValue;
	public PropertiesGrammarAccess.NumAltElements getNumAltAccess() {
		return gaProperties.getNumAltAccess();
	}
	
	public ParserRule getNumAltRule() {
		return getNumAltAccess().getRule();
	}
	
	//terminal SL_COMMENT:
	//	'--' !('\n' | '\r')* ('\r'? '\n')?;
	public TerminalRule getSL_COMMENTRule() {
		return gaProperties.getSL_COMMENTRule();
	}
	
	//INTVALUE aadl2::Integer:
	//	INTEGER_LIT //NUMERAL 	
	//;
	public PropertiesGrammarAccess.INTVALUEElements getINTVALUEAccess() {
		return gaProperties.getINTVALUEAccess();
	}
	
	public ParserRule getINTVALUERule() {
		return getINTVALUEAccess().getRule();
	}
	
	//terminal fragment EXPONENT:
	//	('e' | 'E') ('+' | '-')? DIGIT+;
	public TerminalRule getEXPONENTRule() {
		return gaProperties.getEXPONENTRule();
	}
	
	//terminal fragment INT_EXPONENT:
	//	('e' | 'E') '+'? DIGIT+;
	public TerminalRule getINT_EXPONENTRule() {
		return gaProperties.getINT_EXPONENTRule();
	}
	
	//terminal REAL_LIT:
	//	DIGIT+ ('_' DIGIT+)* ('.' DIGIT+ ('_' DIGIT+)* EXPONENT?);
	public TerminalRule getREAL_LITRule() {
		return gaProperties.getREAL_LITRule();
	}
	
	//terminal INTEGER_LIT:
	//	DIGIT+ ('_' DIGIT+)* ('#' BASED_INTEGER '#' INT_EXPONENT? | INT_EXPONENT?);
	public TerminalRule getINTEGER_LITRule() {
		return gaProperties.getINTEGER_LITRule();
	}
	
	//terminal fragment DIGIT:
	//	'0'..'9';
	public TerminalRule getDIGITRule() {
		return gaProperties.getDIGITRule();
	}
	
	//terminal fragment EXTENDED_DIGIT:
	//	'0'..'9' | 'a'..'f' | 'A'..'F';
	public TerminalRule getEXTENDED_DIGITRule() {
		return gaProperties.getEXTENDED_DIGITRule();
	}
	
	//terminal fragment BASED_INTEGER:
	//	EXTENDED_DIGIT ('_'? EXTENDED_DIGIT)*;
	public TerminalRule getBASED_INTEGERRule() {
		return gaProperties.getBASED_INTEGERRule();
	}
	
	//QCLREF:
	//	ID '::' ID;
	public PropertiesGrammarAccess.QCLREFElements getQCLREFAccess() {
		return gaProperties.getQCLREFAccess();
	}
	
	public ParserRule getQCLREFRule() {
		return getQCLREFAccess().getRule();
	}
	
	//QPREF:
	//	ID ('::' ID)?;
	public PropertiesGrammarAccess.QPREFElements getQPREFAccess() {
		return gaProperties.getQPREFAccess();
	}
	
	public ParserRule getQPREFRule() {
		return getQPREFAccess().getRule();
	}
	
	//QCREF:
	//	(ID '::')* ID ('.' ID)?;
	public PropertiesGrammarAccess.QCREFElements getQCREFAccess() {
		return gaProperties.getQCREFAccess();
	}
	
	public ParserRule getQCREFRule() {
		return getQCREFAccess().getRule();
	}
	
	//STAR:
	//	'*';
	public PropertiesGrammarAccess.STARElements getSTARAccess() {
		return gaProperties.getSTARAccess();
	}
	
	public ParserRule getSTARRule() {
		return getSTARAccess().getRule();
	}
	
	//terminal STRING:
	//	'"' ('\\' ('b' | 't' | 'n' | 'f' | 'r' | 'u' | '"' | "'" | '\\') | !('\\' | '"'))* '"' |
	//	"'" ('\\' ('b' | 't' | 'n' | 'f' | 'r' | 'u' | '"' | "'" | '\\') | !('\\' | "'"))* "'";
	public TerminalRule getSTRINGRule() {
		return gaProperties.getSTRINGRule();
	}
	
	//terminal ID:
	//	('a'..'z'
	//	| 'A'..'Z') ('_'? ('a'..'z'
	//	| 'A'..'Z'
	//	| '0'..'9'))*;
	public TerminalRule getIDRule() {
		return gaProperties.getIDRule();
	}
	
	//terminal WS:
	//	' ' | '\t' | '\r' | '\n'+;
	public TerminalRule getWSRule() {
		return gaProperties.getWSRule();
	}
}