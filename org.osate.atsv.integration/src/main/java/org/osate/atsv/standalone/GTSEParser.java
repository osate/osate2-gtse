package org.osate.atsv.standalone;

import java.io.File;
import java.util.Vector;

import javax.xml.bind.JAXBException;

import org.osate.atsv.integration.ChoicePointModel.ATSVVariableType;

import engine.parsers.AbstractParser;
import engine.variables.AbstractEngineVariable;
import engine.variables.CategoricalVariable;
import engine.variables.ContinuousVariable;
import engine.variables.DiscreteIntegerVariable;

/**
 * This class handles the parsing of input and output text files into 
 * a format understandable by ATSV
 * 
 * @author sam
 *
 */
public class GTSEParser extends AbstractParser {

	public GTSEParser() {
		super();
	}

	@Override
	public Vector<AbstractEngineVariable> loadInputVariables(File[] inputFileArr) {
		return parseVarsFromFile(inputFileArr[0], true);
	}

	@Override
	public Vector<AbstractEngineVariable> loadOutputVariables(File[] outputFileArr) {
		return parseVarsFromFile(outputFileArr[0], false);
	}

	@Override
	public Vector<AbstractEngineVariable> parseOut(File[] outputFileArr, Vector<AbstractEngineVariable> vars) {
		// Unclear how this method differs from loadOutputVariables
		return parseVarsFromFile(outputFileArr[0], false);
	}

	@Override
	public int writeInput(File[] inputFileArr, Vector<AbstractEngineVariable> vars) {
		try {
			loadFromATSVVars(vars).writeToFile(inputFileArr[0]);
			return AbstractParser.SUCCESSFUL;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return AbstractParser.UNSUCCESSFUL;
	}

	private Vector<AbstractEngineVariable> parseVarsFromFile(File file, boolean isInput) {
		try {
			ATSVVarCollection avc = new ATSVVarCollection();
			avc.loadFromFile(file);
			return convertToATSVVars(avc, isInput);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private ATSVVarCollection loadFromATSVVars(Vector<AbstractEngineVariable> atsvVars) {
		ATSVVarCollection ret = new ATSVVarCollection();
		for (AbstractEngineVariable atsvVar : atsvVars) {
			ret.addVar(atsvVar.getTitle(), ATSVVariableType.values()[atsvVar.getType()], atsvVar.getStringValue());
		}
		return ret;
	}

	private Vector<AbstractEngineVariable> convertToATSVVars(ATSVVarCollection avc, boolean isInput) throws Exception {
		Vector<AbstractEngineVariable> atsvVars = new Vector<>();
		for (ATSVVar var : avc.getVars()) {
			atsvVars.addElement(getAbstractEngineVar(var, isInput));
		}
		return atsvVars;
	}

	private AbstractEngineVariable getAbstractEngineVar(ATSVVar av, boolean isInput) throws Exception {
		int mode;

		String name = av.getName();
		String val = av.getVal();
		ATSVVariableType type = av.getType();

		if (isInput) {
			mode = AbstractEngineVariable.INPUT;
		} else {
			mode = AbstractEngineVariable.OUTPUT;
		}

		if (type == ATSVVariableType.INTEGER) {
			DiscreteIntegerVariable var = new DiscreteIntegerVariable(name, mode, Integer.valueOf(val));
			return var;
		} else if (type == ATSVVariableType.FLOAT) {
			ContinuousVariable var = new ContinuousVariable(name, mode, Float.valueOf(val));
			return var;
		} else if (type == ATSVVariableType.STRING) {
			CategoricalVariable var = new CategoricalVariable(name, mode);
			var.setValue(val);
			return var;
		}
		throw new Exception("Couldn't convert the var named '" + name + "' to an ATSV Engine Variable");
	}
}
