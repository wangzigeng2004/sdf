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

@SuppressWarnings("all") public class contracts_1_0 extends Strategy 
{ 
  public static contracts_1_0 instance = new contracts_1_0();

  @Override public IStrategoTerm invoke(Context context, IStrategoTerm term, Strategy m_9750)
  { 
    context.push("contracts_1_0");
    Fail389:
    { 
      IStrategoTerm l_9750 = null;
      l_9750 = term;
      term = m_9750.invoke(context, term);
      if(term == null)
        break Fail389;
      term = l_9750;
      context.popOnSuccess();
      if(true)
        return term;
    }
    context.popOnFailure();
    return null;
  }
}