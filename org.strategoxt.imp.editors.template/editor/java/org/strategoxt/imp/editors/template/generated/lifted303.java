package org.strategoxt.imp.editors.template.generated;

import org.strategoxt.stratego_lib.*;
import org.strategoxt.stratego_lib.*;
import org.strategoxt.stratego_sglr.*;
import org.strategoxt.stratego_gpp.*;
import org.strategoxt.stratego_xtc.*;
import org.strategoxt.stratego_aterm.*;
import org.strategoxt.stratego_sdf.*;
import org.strategoxt.strc.*;
import org.strategoxt.imp.editors.template.generated.*;
import org.strategoxt.java_front.*;
import org.strategoxt.lang.*;
import org.spoofax.interpreter.terms.*;
import static org.strategoxt.lang.Term.*;
import org.spoofax.interpreter.library.AbstractPrimitive;
import java.util.ArrayList;
import java.lang.ref.WeakReference;

@SuppressWarnings("all") final class lifted303 extends Strategy 
{ 
  public static final lifted303 instance = new lifted303();

  @Override public IStrategoTerm invoke(Context context, IStrategoTerm term)
  { 
    Fail1714:
    { 
      IStrategoTerm term385 = term;
      Success627:
      { 
        Fail1715:
        { 
          term = string_is_layout_0_0.instance.invoke(context, term);
          if(term == null)
            break Fail1715;
          { 
            if(true)
              break Fail1714;
            if(true)
              break Success627;
          }
        }
        term = term385;
      }
      if(true)
        return term;
    }
    return null;
  }
}