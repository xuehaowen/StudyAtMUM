package lesson5.labs.prob1.rulesets;

import java.awt.Component;
import java.util.HashMap;

import lesson5.labs.prob1.gui.BookWindow;
import lesson5.labs.prob1.gui.CDWindow;

final public class RuleSetFactory {
	private RuleSetFactory(){
		map.put(BookWindow.class, new BookRuleSet());
		map.put(CDWindow.class, new CDRuleSet());
	}
	private static RuleSetFactory instance;
	static HashMap<Class<? extends Component>, RuleSet> map = new HashMap<>();
	
	public static RuleSetFactory getInstance() {
		if(instance == null) {
			synchronized (RuleSetFactory.class) {
				instance = new RuleSetFactory();
			}
		}
		return instance;
	}
	
	public RuleSet getRuleSet(Component c) {
		Class<? extends Component> c1 = c.getClass();
		if(map.containsKey(c1)) {
			return map.get(c1);
		}
		throw new IllegalArgumentException("No RuleSet found for this Component");
	}
}
