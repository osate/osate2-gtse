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
package org.osate.gtse.config.serializer;

import com.google.inject.Inject;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AlternativeAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.GroupAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;
import org.osate.gtse.config.services.ConfigGrammarAccess;

@SuppressWarnings("all")
public class ConfigSyntacticSequencer extends AbstractSyntacticSequencer {

	protected ConfigGrammarAccess grammarAccess;
	protected AbstractElementAlias match_AadlPackage___PropertiesKeyword_3_0_NoneKeyword_3_1_1_0_SemicolonKeyword_3_1_1_1__q;
	protected AbstractElementAlias match_AbstractFeature_FeatureKeyword_2_1_0_or_PrototypeKeyword_2_0_0;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (ConfigGrammarAccess) access;
		match_AadlPackage___PropertiesKeyword_3_0_NoneKeyword_3_1_1_0_SemicolonKeyword_3_1_1_1__q = new GroupAlias(false, true, new TokenAlias(false, false, grammarAccess.getAadlPackageAccess().getPropertiesKeyword_3_0()), new TokenAlias(false, false, grammarAccess.getAadlPackageAccess().getNoneKeyword_3_1_1_0()), new TokenAlias(false, false, grammarAccess.getAadlPackageAccess().getSemicolonKeyword_3_1_1_1()));
		match_AbstractFeature_FeatureKeyword_2_1_0_or_PrototypeKeyword_2_0_0 = new AlternativeAlias(false, false, new TokenAlias(false, false, grammarAccess.getAbstractFeatureAccess().getFeatureKeyword_2_1_0()), new TokenAlias(false, false, grammarAccess.getAbstractFeatureAccess().getPrototypeKeyword_2_0_0()));
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (ruleCall.getRule() == grammarAccess.getFULLINAMERule())
			return getFULLINAMEToken(semanticObject, ruleCall, node);
		else if (ruleCall.getRule() == grammarAccess.getIDRule())
			return getIDToken(semanticObject, ruleCall, node);
		else if (ruleCall.getRule() == grammarAccess.getPNAMERule())
			return getPNAMEToken(semanticObject, ruleCall, node);
		else if (ruleCall.getRule() == grammarAccess.getSTARRule())
			return getSTARToken(semanticObject, ruleCall, node);
		return "";
	}
	
	/**
	 * FULLINAME:
	 * 	ID '.' ID;
	 */
	protected String getFULLINAMEToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return ".";
	}
	
	/**
	 * terminal ID:	('a'..'z'
	 *         |'A'..'Z'
	 *         ) ( ('_')? ('a'..'z'
	 *         |'A'..'Z'
	 *         |'0'..'9'))*;
	 */
	protected String getIDToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "";
	}
	
	/**
	 * PNAME:
	 * 	ID ('::' ID)*;
	 */
	protected String getPNAMEToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "";
	}
	
	/**
	 * STAR : '*';
	 */
	protected String getSTARToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "*";
	}
	
	@Override
	protected void emitUnassignedTokens(EObject semanticObject, ISynTransition transition, INode fromNode, INode toNode) {
		if (transition.getAmbiguousSyntaxes().isEmpty()) return;
		List<INode> transitionNodes = collectNodes(fromNode, toNode);
		for (AbstractElementAlias syntax : transition.getAmbiguousSyntaxes()) {
			List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
			if (match_AadlPackage___PropertiesKeyword_3_0_NoneKeyword_3_1_1_0_SemicolonKeyword_3_1_1_1__q.equals(syntax))
				emit_AadlPackage___PropertiesKeyword_3_0_NoneKeyword_3_1_1_0_SemicolonKeyword_3_1_1_1__q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_AbstractFeature_FeatureKeyword_2_1_0_or_PrototypeKeyword_2_0_0.equals(syntax))
				emit_AbstractFeature_FeatureKeyword_2_1_0_or_PrototypeKeyword_2_0_0(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * Ambiguous syntax:
	 *     ('properties' 'none' ';')?
	 *
	 * This ambiguous syntax occurs at:
	 *     ownedPrivateSection=PrivatePackageSection (ambiguity) 'end' PNAME ';' (rule end)
	 *     ownedPublicSection=PublicPackageSection (ambiguity) 'end' PNAME ';' (rule end)
	 */
	protected void emit_AadlPackage___PropertiesKeyword_3_0_NoneKeyword_3_1_1_0_SemicolonKeyword_3_1_1_1__q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * Ambiguous syntax:
	 *     'prototype' | 'feature'
	 *
	 * This ambiguous syntax occurs at:
	 *     in?='in' (ambiguity) ';' (rule end)
	 *     in?='in' (ambiguity) '{' ownedPropertyAssociation+=PropertyAssociation
	 *     in?='in' (ambiguity) arrayDimension+=ArrayDimension
	 *     name=ID ':' (ambiguity) ';' (rule end)
	 *     name=ID ':' (ambiguity) '{' ownedPropertyAssociation+=PropertyAssociation
	 *     name=ID ':' (ambiguity) arrayDimension+=ArrayDimension
	 *     out?='out' (ambiguity) ';' (rule end)
	 *     out?='out' (ambiguity) '{' ownedPropertyAssociation+=PropertyAssociation
	 *     out?='out' (ambiguity) arrayDimension+=ArrayDimension
	 *     refined=[AbstractFeature|REFINEDNAME] ':' 'refined' 'to' (ambiguity) ';' (rule end)
	 *     refined=[AbstractFeature|REFINEDNAME] ':' 'refined' 'to' (ambiguity) '{' ownedPropertyAssociation+=PropertyAssociation
	 *     refined=[AbstractFeature|REFINEDNAME] ':' 'refined' 'to' (ambiguity) arrayDimension+=ArrayDimension
	 */
	protected void emit_AbstractFeature_FeatureKeyword_2_1_0_or_PrototypeKeyword_2_0_0(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}
