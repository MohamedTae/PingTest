
package test;

import static org.junit.Assert.*;
import org.junit.internal.AssumptionViolatedException;

class Base {

    protected void run() {
        double r = 5;
        if (r <= 5) {
            fail("oops");
        } 
        //else if (r < 0.2) {
        //    throw new AssumptionViolatedException("skipping");
        //}
    }

}
