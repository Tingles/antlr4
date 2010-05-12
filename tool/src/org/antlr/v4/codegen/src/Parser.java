package org.antlr.v4.codegen.src;

import org.antlr.v4.codegen.OutputModelFactory;
import org.antlr.v4.tool.Rule;

import java.util.ArrayList;
import java.util.List;

/** */
public class Parser extends OutputModelObject {
	public String name;
	public List<RuleFunction> funcs = new ArrayList<RuleFunction>();
	ParserFile file;

	public Parser(OutputModelFactory factory, ParserFile file) {
		this.factory = factory;
		this.file = file; // who contains us?
		name = factory.g.getRecognizerName();
		for (Rule r : factory.g.rules.values()) funcs.add( new RuleFunction(factory, r) );

		// We create dfa and bitsets during rule function construction.
		// They get stored in code gen for convenience as we walk rule block tree
//		for (DFA dfa : gen.g.decisionDFAs.values()) {
//			file.dfaDefs.add( new DFADef("DFA"+dfa.decision, dfa) );
//		}
	}

	@Override
	public List<String> getChildren() {
		final List<String> sup = super.getChildren();
		return new ArrayList<String>() {{ if ( sup!=null ) addAll(sup); add("funcs"); }};
	}
}
