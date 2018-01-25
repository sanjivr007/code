package statePattern;

import java.lang.reflect.Method;

/*NOState ->correctstate
        ->errorstate
ErrorState -> CorrectState

CorrectState -> Nowhere*/

public class Machinemain {

	public static void main(String[] args){
		Machine m = new Machine();
		System.out.println(m.nodestate.getClass().getName());
	    m.insertCode("1234");
	    System.out.println(m.getenteredPinStatus());
	    System.out.println(m.nodestate.getClass().getName());
	    
	  
	}

}
