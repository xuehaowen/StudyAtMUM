package lesson10.labs.prob3;

import java.util.List;
import java.util.stream.Collectors;

public class FixThisSoln {

    	List<String> processList(List<String> list)  {

	    return list.stream()
	    		   .map(s -> FunctionWithException
						   .unchecked(function::apply).apply(s)) // This will fail to compile
	               .collect(Collectors.toList());

	}

	private static final FunctionWithException<String, String> function
            = (s) -> {
    		if(s.length() > 3)
    			throw  new Exception();
    		return s;

	};
}
